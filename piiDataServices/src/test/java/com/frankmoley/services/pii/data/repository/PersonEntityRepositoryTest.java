package com.frankmoley.services.pii.data.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.frankmoley.services.pii.data.entity.PersonEntity;


/**
 * @author Frank Moley
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/repository-test-context.xml")
@Transactional
public class PersonEntityRepositoryTest {

    private String person1Id = "8824b797-2b05-4166-8b79-3cd479d7ba29";
    private String person2Id = "28221c10-5643-4d9f-907a-2531b9fac74e";
    private String person3Id = "a6743c55-56fa-4e5c-b734-f885db9f1c74";

    @Autowired
    private PersonEntityRepository repository;

    @Test
    public void testAddPerson() throws Exception {
        PersonEntity entity = new PersonEntity();
        entity.setPrefix("MS");
        entity.setFirstName("Johnathon");
        entity.setMiddleName("Appleseed");
        entity.setLastName("Junit");
        entity.setSuffix("IV");
        entity = this.repository.addPerson(entity);
        assertNotNull(entity.getId());
    }

    @Test
    public void testGetPerson() throws Exception {
        PersonEntity entity = this.repository.getPerson(person1Id);
        assertEquals("MR", entity.getPrefix());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        PersonEntity entity = this.repository.getPerson(person2Id);
        assertEquals("MRS", entity.getPrefix());
        entity.setPrefix("MR");
        entity = this.repository.updatePerson(person2Id, entity);
        assertEquals("MR", entity.getPrefix());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeletePerson() throws Exception {
        PersonEntity entity = this.repository.getPerson(person3Id);
        assertNotNull(entity);
        this.repository.deletePerson(person3Id);
        entity = this.repository.getPerson(person3Id);
    }

    @Test
    public void testGetAll() throws Exception {
        List<PersonEntity> entities = this.repository.getAll();
        assertEquals(3, entities.size());
    }
}

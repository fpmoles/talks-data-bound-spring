package com.frankmoley.services.pii.data.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.frankmoley.services.pii.data.entity.PersonEntity;


/**
 * @author Frank Moley
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/repository-test-context.xml")
public class PersonEntityRepositoryTest {

    private String person1Id = "8824b797-2b05-4166-8b79-3cd479d7ba29";
    private String person2Id = "28221c10-5643-4d9f-907a-2531b9fac74e";
    private String person3Id = "a6743c55-56fa-4e5c-b734-f885db9f1c74";

    private final static List<String> IDS = new ArrayList<>();

    @Autowired
    private PersonEntityRepository repository;

    @Before
    public void setUp() {
        PersonEntity entity = new PersonEntity();
        entity.setId(person1Id);
        entity.setPrefix("MR");
        entity.setFirstName("John");
        entity.setMiddleName("Wayne");
        entity.setLastName("Doe");
        entity.setSuffix("JR");
        this.repository.save(entity);
        entity = new PersonEntity();
        entity.setId(person2Id);
        entity.setPrefix("MRS");
        entity.setFirstName("Jane");
        entity.setMiddleName("Samantha");
        entity.setLastName("Doe");
        entity.setSuffix(null);
        this.repository.save(entity);
        entity.setId(person3Id);
        entity.setPrefix("MISS");
        entity.setFirstName("Alexandra");
        entity.setMiddleName("Arista");
        entity.setLastName("Doe");
        entity.setSuffix("null");
        this.repository.save(entity);

        this.IDS.add(person1Id);
        this.IDS.add(person2Id);
        this.IDS.add(person3Id);

    }

    @After
    public void tearDown() {
        for (String id : IDS) {
            this.repository.delete(id);
        }

    }

    @Test
    public void testAddPerson() throws Exception {
        PersonEntity entity = new PersonEntity();
        entity.setPrefix("MS");
        entity.setFirstName("Johnathon");
        entity.setMiddleName("Appleseed");
        entity.setLastName("Junit");
        entity.setSuffix("IV");
        entity = this.repository.save(entity);
        assertNotNull(entity.getId());
        IDS.add(entity.getId());
    }

    @Test
    public void testGetPerson() throws Exception {
        PersonEntity entity = this.repository.findOne(person1Id);
        assertEquals("MR", entity.getPrefix());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        PersonEntity entity = this.repository.findOne(person2Id);
        assertEquals("MRS", entity.getPrefix());
        entity.setPrefix("MR");
        entity = this.repository.save(entity);
        assertEquals("MR", entity.getPrefix());
    }

    @Test
    public void testDeletePerson() throws Exception {
        PersonEntity entity = this.repository.findOne(person3Id);
        assertNotNull(entity);
        this.repository.delete(person3Id);
        entity = this.repository.findOne(person3Id);
        assertNull(entity);
    }

    @Test
    public void testGetAll() throws Exception {
        Iterable<PersonEntity> entities = this.repository.findAll();
        int counter = 0;
        for(PersonEntity entity:entities){
            counter++;
        }
        assertEquals(3, counter);
    }
}

package com.frankmoley.services.pii.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.frankmoley.services.pii.data.entity.PersonEntity;
import com.frankmoley.services.pii.data.repository.PersonEntityRepository;
import com.frankmoley.services.pii.domain.Person;

/**
 * @author Frank Moley
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonManagerTest {

    @Mock
    private PersonEntityRepository personEntityRepository;

    @InjectMocks
    private PersonManager personManager;

    @Test
    public void testGetPerson() throws Exception {

        PersonEntity mockEntity = this.getMockPersonEntity(null);
        String personId = mockEntity.getId();
        when(this.personEntityRepository.getPerson(personId)).thenReturn(mockEntity);

        Person person = this.personManager.getPerson(personId);
        assertEquals("MR", person.getPrefix());
    }

    @Test
    public void testGetAllPersons() throws Exception {

        List<PersonEntity> persons = new ArrayList<>();
        persons.add(this.getMockPersonEntity(null));
        persons.add(this.getMockPersonEntity(null));
        when(this.personEntityRepository.getAll()).thenReturn(persons);
        List<Person> people = this.personManager.getAllPersons();
        assertEquals(2, people.size());
    }

    @Test
    public void testAddPerson() throws Exception {
        Person personModel = this.getMockPerson(null);
        personModel.setPersonId(null);
        PersonEntity personEntity = this.getMockPersonEntity(null);
        when(this.personEntityRepository.addPerson(any(PersonEntity.class))).thenReturn(personEntity);

        Person person = this.personManager.addPerson(personModel);
        assertEquals(personEntity.getId(), person.getPersonId());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Person personModel = this.getMockPerson(null);
        String personId = personModel.getPersonId();
        PersonEntity personEntity = this.getMockPersonEntity(personId);
        when(this.personEntityRepository.updatePerson(eq(personId), any(PersonEntity.class))).thenReturn(personEntity);

        Person person = this.personManager.updatePerson(personId, personModel);
        assertEquals(personEntity.getPrefix(), person.getPrefix());
    }

    @Test
    public void testDeletePerson() throws Exception {
        String personId = UUID.randomUUID().toString();
        this.personManager.deletePerson(personId);
        verify(this.personEntityRepository, times(1)).deletePerson(personId);
    }

    private PersonEntity getMockPersonEntity(String personId){
        PersonEntity person = new PersonEntity();
        person.setId(null==personId? UUID.randomUUID().toString():personId);
        person.setFirstName("Mockito");
        person.setMiddleName("Testing");
        person.setLastName("Mock");
        person.setPrefix("MR");
        person.setSuffix("JR");
        return person;
    }

    private Person getMockPerson(String personId){
        Person person = new Person();
        person.setPersonId(null==personId? UUID.randomUUID().toString():personId);
        person.setFirstName("Mockito");
        person.setMiddleName("Testing");
        person.setLastName("Mock");
        person.setPrefix("MR");
        person.setSuffix("JR");
        return person;
    }
}

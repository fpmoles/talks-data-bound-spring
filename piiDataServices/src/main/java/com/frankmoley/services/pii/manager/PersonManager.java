package com.frankmoley.services.pii.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frankmoley.services.pii.data.entity.PersonEntity;
import com.frankmoley.services.pii.data.repository.PersonEntityRepository;
import com.frankmoley.services.pii.domain.Person;

/**
 * @author Frank Moley
 */
@Service
public class PersonManager {

    @Autowired
    private PersonEntityRepository personEntityRepository;

    public Person getPerson(String personId){
        PersonEntity entity = this.personEntityRepository.getPerson(personId);
        if(null != entity){
            return this.translatePersonEntityToPerson(entity);
        }
        return null;
    }

    public List<Person> getAllPersons(){
        List<PersonEntity> entities = this.personEntityRepository.getAll();
        if(null!=entities){
            List<Person> persons = new ArrayList<>(entities.size());
            for(PersonEntity entity:entities){
                persons.add(this.translatePersonEntityToPerson(entity));
            }
            return persons;
        }
        return null;
    }

    public Person addPerson(Person model){
        PersonEntity entity = this.translatePersonToPersonEntity(model);
        entity = this.personEntityRepository.addPerson(entity);
        if(null != entity) {
            return this.translatePersonEntityToPerson(entity);
        }
        return null;
    }

    public Person updatePerson(String personId, Person model){
        PersonEntity entity = this.translatePersonToPersonEntity(model);
        entity = this.personEntityRepository.updatePerson(personId, entity);
        if(null!=entity){
            return this.translatePersonEntityToPerson(entity);
        }
        return null;
    }

    public void deletePerson(String personId){
        this.personEntityRepository.deletePerson(personId);
    }


    private Person translatePersonEntityToPerson(PersonEntity entity){
        Person person = new Person();
        BeanUtils.copyProperties(entity, person);
        person.setPersonId(entity.getId());
        return person;
    }

    private PersonEntity translatePersonToPersonEntity(Person person){
        PersonEntity entity = new PersonEntity();
        BeanUtils.copyProperties(person, entity);
        entity.setId(person.getPersonId());
        return entity;
    }
}

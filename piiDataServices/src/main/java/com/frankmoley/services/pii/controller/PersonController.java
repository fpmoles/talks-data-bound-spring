package com.frankmoley.services.pii.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.frankmoley.services.pii.domain.Person;
import com.frankmoley.services.pii.manager.PersonManager;

/**
 * @author Frank Moley
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonManager personManager;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<Person>> getPersons() {
        List<Person> persons = this.personManager.getAllPersons();
        for (Person person : persons) {
            person.add(linkTo(methodOn(PersonController.class).getPerson(person.getPersonId())).withSelfRel());
        }
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<Person> addPerson(@RequestBody Person model) {
        Person person = this.personManager.addPerson(model);
        person.add(linkTo(methodOn(PersonController.class).getPerson(person.getPersonId())).withSelfRel());
        ResponseEntity entity = new ResponseEntity<Person>(person, HttpStatus.CREATED);
        entity.getHeaders().add("Location", linkTo(methodOn(PersonController.class).getPerson(person.getPersonId())).toString());
        return entity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<Person> getPerson(@PathVariable(value = "id") String personId) {
        Person person = this.personManager.getPerson(personId);
        person.add(linkTo(methodOn(PersonController.class).getPerson(person.getPersonId())).withSelfRel());
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public HttpEntity<Person> updatePerson(@PathVariable(value = "id") String personId, @RequestBody Person model) {
        Person person = this.personManager.updatePerson(personId, model);
        person.add(linkTo(methodOn(PersonController.class).getPerson(person.getPersonId())).withSelfRel());
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable(value = "id") String personId) {
        this.personManager.deletePerson(personId);
    }
}

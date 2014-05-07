package com.frankmoley.services.pii.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.frankmoley.services.pii.data.entity.PersonEntity;

/**
 * @author Frank Moley
 */
@Repository
public interface PersonEntityRepository extends MongoRepository<PersonEntity, String> {
    //marker interface for now

}

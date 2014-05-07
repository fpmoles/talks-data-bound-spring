package com.frankmoley.services.pii.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.frankmoley.services.pii.data.entity.PersonEntity;

/**
 * @author Frank Moley
 */
@Repository
@Transactional
public interface PersonEntityRepository extends CrudRepository<PersonEntity, String> {
    //marker interface for now

}

package com.frankmoley.services.pii.data.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.frankmoley.services.pii.data.entity.PersonEntity;
import com.frankmoley.services.pii.data.mapper.PersonEntityRowMapper;

/**
 * @author Frank Moley
 */
public class PersonEntityRepository extends PiiRepository {

    private final static String PARAM_PERSON_ID = "personId";
    private final static String PARAM_PREFIX = "prefix";
    private final static String PARAM_FIRST_NAME = "firstName";
    private final static String PARAM_MIDDLE_NAME = "middleName";
    private final static String PARAM_LAST_NAME = "lastName";
    private final static String PARAM_SUFFIX = "suffix";

    public PersonEntityRepository(DataSource dataSource, Properties sqlProperties) {
        super(dataSource, sqlProperties);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PersonEntity addPerson(PersonEntity model){
        String personId = UUID.randomUUID().toString();
        String sql = this.sqlProperties.getProperty(INSERT);
        Map<String, Object> namedParameters = new HashMap<>(6);
        namedParameters.put(PARAM_PERSON_ID, personId);
        namedParameters.put(PARAM_PREFIX, StringUtils.trimToNull(model.getPrefix()));
        namedParameters.put(PARAM_FIRST_NAME, StringUtils.trimToNull(model.getFirstName()));
        namedParameters.put(PARAM_MIDDLE_NAME, StringUtils.trimToNull(model.getMiddleName()));
        namedParameters.put(PARAM_LAST_NAME, StringUtils.trimToNull(model.getLastName()));
        namedParameters.put(PARAM_SUFFIX, StringUtils.trimToNull(model.getSuffix()));
        jdbcTemplate.update(sql, namedParameters);
        return this.getPerson(personId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PersonEntity getPerson(String personId){
        String sql = this.sqlProperties.getProperty(GET);
        return  jdbcTemplate.queryForObject(sql, Collections.singletonMap(PARAM_PERSON_ID, personId), new PersonEntityRowMapper());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PersonEntity updatePerson(String personId, PersonEntity model){
        String sql = this.sqlProperties.getProperty(UPDATE);
        Map<String, Object> namedParameters = new HashMap<>(6);
        namedParameters.put(PARAM_PERSON_ID, personId);
        namedParameters.put(PARAM_PREFIX, StringUtils.trimToNull(model.getPrefix()));
        namedParameters.put(PARAM_FIRST_NAME, StringUtils.trimToNull(model.getFirstName()));
        namedParameters.put(PARAM_MIDDLE_NAME, StringUtils.trimToNull(model.getMiddleName()));
        namedParameters.put(PARAM_LAST_NAME, StringUtils.trimToNull(model.getLastName()));
        namedParameters.put(PARAM_SUFFIX, StringUtils.trimToNull(model.getSuffix()));
        jdbcTemplate.update(sql, namedParameters);
        return this.getPerson(personId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletePerson(String personId){
        String sql = this.sqlProperties.getProperty(DELETE);
        jdbcTemplate.update(sql, Collections.singletonMap(PARAM_PERSON_ID, personId));
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PersonEntity> getAll(){
        String sql = this.sqlProperties.getProperty(FIND_ALL);
        return jdbcTemplate.query(sql, new PersonEntityRowMapper());
    }
}

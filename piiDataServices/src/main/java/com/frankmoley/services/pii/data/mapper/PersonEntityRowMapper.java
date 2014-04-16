package com.frankmoley.services.pii.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frankmoley.services.pii.data.entity.PersonEntity;

/**
 * @author Frank Moley
 */
public class PersonEntityRowMapper implements RowMapper<PersonEntity> {

    private static final String COLUMN_PERSON_ID = "PERSON_ID";
    private static final String COLUMN_PREFIX = "PREFIX";
    private static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    private static final String COLUMN_MIDDLE_NAME = "MIDDLE_NAME";
    private static final String COLUMN_LAST_NAME = "LAST_NAME";
    private static final String COLUMN_SUFFIX = "SUFFIX";

    @Override
    public PersonEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        PersonEntity entity = new PersonEntity();
        entity.setId(resultSet.getString(COLUMN_PERSON_ID));
        entity.setFirstName(resultSet.getString(COLUMN_FIRST_NAME));
        entity.setMiddleName(resultSet.getString(COLUMN_MIDDLE_NAME));
        entity.setLastName(resultSet.getString(COLUMN_LAST_NAME));
        entity.setPrefix(resultSet.getString(COLUMN_PREFIX));
        entity.setSuffix(resultSet.getString(COLUMN_SUFFIX));
        return entity;
    }
}

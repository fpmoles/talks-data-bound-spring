package com.frankmoley.services.pii.data.repository;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author Frank Moley
 */
public abstract class PiiRepository {

    protected NamedParameterJdbcTemplate jdbcTemplate;
    protected Properties sqlProperties;
    protected final static String INSERT = "insert";
    protected final static String GET = "get";
    protected final static String UPDATE = "update";
    protected final static String DELETE = "delete";
    protected final static String FIND_ALL = "get_all";

    PiiRepository(DataSource dataSource, Properties sqlProperties){
        super();
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.sqlProperties = sqlProperties;
    }


}

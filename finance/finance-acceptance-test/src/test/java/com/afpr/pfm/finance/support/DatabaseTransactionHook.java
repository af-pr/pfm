package com.afpr.pfm.finance.support;

import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseTransactionHook {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void cleanupDatabase() {
        jdbcTemplate.execute("TRUNCATE TABLE category CASCADE");
    }
}

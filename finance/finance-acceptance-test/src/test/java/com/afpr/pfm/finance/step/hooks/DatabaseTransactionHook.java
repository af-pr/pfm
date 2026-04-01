package com.afpr.pfm.finance.step.hooks;

import io.cucumber.java.Before;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseTransactionHook {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseTransactionHook(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Before
    public void cleanupDatabase() {
        jdbcTemplate.execute("TRUNCATE TABLE category CASCADE");
    }
}

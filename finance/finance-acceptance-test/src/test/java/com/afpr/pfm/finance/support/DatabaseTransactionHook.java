package com.afpr.pfm.finance.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import io.cucumber.java.Before;

public class DatabaseTransactionHook {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void cleanupDatabase() {
        List<String> tables = jdbcTemplate.queryForList(
            "SELECT tablename FROM pg_tables WHERE schemaname = 'finance' ORDER BY tablename",
            String.class
        );

        for (String table : tables) {
            jdbcTemplate.execute("TRUNCATE TABLE finance." + table + " CASCADE");
        }
    }
}

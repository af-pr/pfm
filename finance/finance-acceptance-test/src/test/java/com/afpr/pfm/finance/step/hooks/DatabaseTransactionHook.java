package com.afpr.pfm.finance.step.hooks;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import io.cucumber.java.Before;

public class DatabaseTransactionHook {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseTransactionHook(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Before
    public void cleanupDatabase() {
        // Get all tables from finance schema and truncate them
        List<String> tables = jdbcTemplate.queryForList(
            "SELECT tablename FROM pg_tables WHERE schemaname = 'finance' ORDER BY tablename",
            String.class
        );

        for (String table : tables) {
            jdbcTemplate.execute("TRUNCATE TABLE finance." + table + " CASCADE");
        }
    }


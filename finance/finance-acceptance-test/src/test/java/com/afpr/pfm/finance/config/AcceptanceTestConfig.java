package com.afpr.pfm.finance.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = AcceptanceTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("acceptance-test")
@EnableFeignClients(basePackages = {"com.afpr.pfm.finance.client.api", "com.afpr.pfm.finance.support"})
@ImportAutoConfiguration({
        FeignAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
        JacksonAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class
})
@ComponentScan({"com.afpr.pfm.finance.step", "com.afpr.pfm.finance.support"})
public class AcceptanceTestConfig {

}

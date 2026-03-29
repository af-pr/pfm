package com.afpr.pfm.finance;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features")
@ConfigurationParameters({
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/cucumber_report.json"),
        @ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "not @skip_scenario"),
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.afpr.pfm.finance")
})
public class AcceptanceCriteria {
}

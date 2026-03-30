package com.afpr.pfm.finance.step.given;

import io.cucumber.java.en.Given;

public class GivenAppHasStarted {

    @Given("the app has started")
    public void theAppHasStarted() {
        // No implementation needed
        // The app is started and stopped by spring-boot-maven-plugin in pre/post-integration-test
    }

}

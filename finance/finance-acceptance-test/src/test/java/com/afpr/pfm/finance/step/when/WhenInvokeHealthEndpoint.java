package com.afpr.pfm.finance.step.when;

import com.afpr.pfm.finance.step.worlds.HealthWorld;
import com.afpr.pfm.finance.support.actuator.application.ActuatorRequester;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WhenInvokeHealthEndpoint {

    private final ActuatorRequester actuatorRequester;
    private final HealthWorld world;

    @When("the health endpoint is invoked")
    public void theHealthEndpointIsInvoked() {
        world.setLastResponse(actuatorRequester.health());
    }

}

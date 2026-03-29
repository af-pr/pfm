package com.afpr.pfm.finance.step.actuator.when;

import com.afpr.pfm.finance.step.actuator.World;
import com.afpr.pfm.finance.support.actuator.application.ActuatorRequester;
import io.cucumber.java.en.When;

public class WhenInvokeHealthEndpoint {

    private final ActuatorRequester actuatorRequester;
    private final World world;

    public WhenInvokeHealthEndpoint(ActuatorRequester actuatorRequester, World world) {
        this.actuatorRequester = actuatorRequester;
        this.world = world;
    }

    @When("the health endpoint is invoked")
    public void theHealthEndpointIsInvoked() {
        world.setResponse(actuatorRequester.health());
    }

}

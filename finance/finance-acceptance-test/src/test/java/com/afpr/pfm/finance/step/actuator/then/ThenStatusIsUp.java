package com.afpr.pfm.finance.step.actuator.then;

import com.afpr.pfm.finance.step.actuator.World;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenStatusIsUp {

    private final World world;

    public ThenStatusIsUp(World world) {
        this.world = world;
    }

    @Then("the status of the app is UP")
    public void theStatusOfTheAppIsUp() {
        assertThat(world.getResponse().getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(world.getResponse().getBody().status()).isEqualTo("UP");
    }

}

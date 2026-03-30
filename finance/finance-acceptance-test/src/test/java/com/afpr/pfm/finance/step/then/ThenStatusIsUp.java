package com.afpr.pfm.finance.step.then;

import com.afpr.pfm.finance.step.worlds.HealthWorld;
import io.cucumber.java.en.Then;
import lombok.AllArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class ThenStatusIsUp {

    private final HealthWorld world;

    @Then("the status of the app is UP")
    public void theStatusOfTheAppIsUp() {
        assertThat(world.getHealthResponse().getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(world.getHealthResponse().getBody().status()).isEqualTo("UP");
    }

}

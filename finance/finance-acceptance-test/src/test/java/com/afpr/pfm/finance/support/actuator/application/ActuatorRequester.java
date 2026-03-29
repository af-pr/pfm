package com.afpr.pfm.finance.support.actuator.application;

import com.afpr.pfm.finance.support.actuator.infrastructure.http.ActuatorClient;
import com.afpr.pfm.finance.support.actuator.infrastructure.http.dto.HealthResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ActuatorRequester {

    private final ActuatorClient actuatorClient;

    public ActuatorRequester(ActuatorClient actuatorClient) {
        this.actuatorClient = actuatorClient;
    }

    public ResponseEntity<HealthResponse> health() {
        try {
            return actuatorClient.health();
        } catch (FeignException e) {
            return new ResponseEntity<>(HttpStatus.valueOf(e.status()));
        }
    }

}

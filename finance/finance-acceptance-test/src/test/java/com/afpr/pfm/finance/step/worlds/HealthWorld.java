package com.afpr.pfm.finance.step.worlds;

import com.afpr.pfm.finance.support.actuator.infrastructure.http.dto.HealthResponse;
import io.cucumber.spring.ScenarioScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class HealthWorld extends World {

    @SuppressWarnings("unchecked")
    public ResponseEntity<HealthResponse> getHealthResponse() {
        return (ResponseEntity<HealthResponse>) getLastResponse();
    }

}

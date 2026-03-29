package com.afpr.pfm.finance.step.actuator;

import com.afpr.pfm.finance.support.actuator.infrastructure.http.dto.HealthResponse;
import io.cucumber.spring.ScenarioScope;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ScenarioScope
public class World {

    private ResponseEntity<HealthResponse> response;

}

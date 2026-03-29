package com.afpr.pfm.finance.support.actuator.infrastructure.http;

import com.afpr.pfm.finance.support.actuator.infrastructure.http.dto.HealthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "actuator-client", url = "${app.base-url}")
public interface ActuatorClient {

    @GetMapping("/actuator/health")
    ResponseEntity<HealthResponse> health();

}

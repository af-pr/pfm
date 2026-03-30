package com.afpr.pfm.finance.step.worlds;

import org.springframework.http.ResponseEntity;

public abstract class World {

    private ResponseEntity<?> lastResponse;

    protected ResponseEntity<?> getLastResponse() {
        return lastResponse;
    }

    public void setLastResponse(ResponseEntity<?> lastResponse) {
        this.lastResponse = lastResponse;
    }

}

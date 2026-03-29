Feature: Smoke tests - full end to end test with app

  Scenario: app has health
    Given the app has started
    When the health endpoint is invoked
    Then the status of the app is UP

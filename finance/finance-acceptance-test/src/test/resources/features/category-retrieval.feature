Feature: Category retrieval

  Scenario: category retrieval by admin
    Given a category is created
    When admin looks for the category
    Then the category is retrieved successfully

#    TODO: this test should be enabled and implemented when security is available
  # Scenario: category retrieval by regular user
  #   Given a category is created
  #   When a regular user looks for the category
  #   Then the system returns an error for insufficient permissions

  Scenario: category list retrieval by admin
    Given several categories are created
    When admin looks for the category list
    Then the category list is retrieved successfully

#    TODO: this test should be enabled and implemented when security is available
  # Scenario: category list retrieval by regular user
  #   Given a category is created
  #   When a regular user looks for the category list
  #   Then the system returns an error for insufficient permissions

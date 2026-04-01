Feature: Category edition

  Scenario: category edition by admin
    Given a valid category is declared
    When admin requests a category edition
    Then the category is edited

#    TODO: this test should be enabled and implemented when security is available
#  Scenario: category edition by regular user
#    Given a valid category is declared
#    When a regular user requests a category edition
 #   Then the category is not edited
 #   And the system returns an error for insufficient permissions

Feature: Category creation

  Scenario: category creation by admin
    Given a valid category is declared
    When admin requests the category creation
    Then the category is created

#    TODO: this test should be enabled and implemented when security is available
#  Scenario: category creation by regular user
#    Given a valid category is declared
#    When a regular user requests the category creation
 #   Then the category is not created
 #   And the system returns an error for insufficient permissions

  Scenario: category duplicated
    Given a category is created
    And another category with the same name
    When admin requests the category creation
    Then the category is not created for duplicated name

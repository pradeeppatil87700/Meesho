Feature: Home Page Functionality Check

  Scenario: Verify the home page title
    Given I navigate to the Meesho home page
    When I fetch the page title
    Then the title should contain "Meesho"

  Scenario: Verify navigation to Bags under the Men category
    Given I navigate to the Meesho home page
    When I hover over the "Men" category
    And I click the "Bags" link
    Then the page title should contain "Bags"

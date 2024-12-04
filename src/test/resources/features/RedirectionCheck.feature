Feature: Page Redirection Check Meesho Website

  Scenario: Verify the app download & Play store link is opening or not
    Given I open the Meesho home page
    When I click the Download Meesho App button
    Then I verify the app download link is triggered

  Scenario: Click Profile button & Hello User is visible or not
    Given I open the Meesho home page
    When I click the Profile button
    Then I verify the Profile dropdown is displayed

  Scenario: Click Cart button & it is working or not
    Given I open the Meesho home page
    When I click the Cart button
    Then I verify the Cart page is displayed

  Scenario: Click Sign-Up button & check supplier login & create account page with redirection
    Given I open the Meesho home page
    When I click the Sign Up button
    Then I verify the Sign-Up page is displayed

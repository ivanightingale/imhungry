Feature: Results Page

Background:

    Given I visit the website

  #1
  Scenario: page requires a login

    When I visit the results page
    Then I should see the "Login Page" page




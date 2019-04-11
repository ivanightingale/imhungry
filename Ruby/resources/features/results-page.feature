Feature: Results Page

Background:

    Given I visit the website

  #1
  Scenario: page requires a login

    When I visit the results page
    Then I should see the "LogIn Page" page

  #2
  Scenario: there is a next page button for pagination

    When I press "login" button
    And enter "testuser" into "username"
    And enter "password" into "password"
    And press "submit" button
    And I search for "chicken" and expect 5 results
    And press "submit" button
    Then I should see the "next" button


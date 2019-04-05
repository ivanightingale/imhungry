Feature: Detailed Results Pages

  Given: I visit the website

  #1
  Scenario: recipe page requires login#1

    When I visit the recipe page
    Then I should see the "LogIn Page" page

  #2
  Scenario: restaurant page requires a login

    When I visit the restaurant page
    Then I should see the "LogIn Page" page
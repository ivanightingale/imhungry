Feature: Detailed Results Pages

  Given: I visit the website

  #1
  Scenario: recipe page requires login#1

    When I visit the recipe page
    Then I should see the "Login Page" page

  #2
  Scenario: restaurant page requires a login

    When I visit the restaurant page page
    Then I should see the "Login Page" page


  # COMMENTED OUT SO THAT WE DON'T OVERLOAD API KEY
  #3
  #Scenario: Once logged in

   # When I visit the website
   # And I press "login" button
   # And enter "testuser" into "username"
  #  And enter "password" into "password"
   # And press "submit" button
   # And I should see the "Search" page
   # And I search for "chicken"
   # And expect 5 results
   # And press "submit" button
   # And press a restaurant
   # Then I should not see "Grocery"



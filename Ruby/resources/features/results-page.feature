Feature: Results Page

Background:

	Given I visit the website

#2
Scenario: there is a next page button for pagination

  When I search for "chicken" and expect 5 results
  And press "submit" button
  Then I should see the "next" button
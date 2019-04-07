Feature: Search Page

Background:

	Given I visit the website

#1
Scenario: the default page should be Search Page

	When I visit the website
	Then I should see the "Search" page
	And There should be a "login" button
	And There should be a "signup" button

#2

Scenario: initiating the search redirects to Results Page if I am logged in a

	When I press "login" button
    And enter "testuser" into "username"
    And enter "password" into "password"
    And press "submit" button
    And I should see the "Search" page
    And I search for "chicken" and expect 5 results
	And press "submit" button
	Then I should see the "Result" page

#3
Scenario: there should be a dropdown menu to let users filter restaurant results by radius
	Then I should see the dropdown menu for selecting specific radius

#4 Add recipe to grocery list
Scenario: if logged in you can add recipe item to grocery list

	When I press "login" button
	And enter "testuser" into "username"
	And enter "password" into "password"
	And press "submit" button
	And I should see the "Search" page
	And I search for "chicken" and expect 5 results
	And press "submit" button
	And press a recipe
	And select the list "Grocery List"
	And press "addtolist" button
	And press "backtoresults" button
	And select the list "Grocery List"
	And press "manage_list" button
	Then I should see an info item

#4 There is a button for previousSearches on the Search page
Scenario: There is a button for previousSearches on the Search page

	When I press "login" button
	And enter "testuser" into "username"
	And enter "password" into "password"
	And press "submit" button
	And I should see the "Search" page
	And I should see a "prevSearch" button
	And I click "prevSearch" button
	Then I should see "Your Previous Searches" field

#5 previousSearches stores previous Searches
Scenario: There is a button for previousSearches on the Search page

	When I press "login" button
	And enter "testuser" into "username"
	And enter "password" into "password"
	And press "submit" button
	And I search for "chicken" and expect 5 results
	And enter radius of 1
	And press a recipe
	And press "backtoresults" button
	And press "backtosearch" button
	And press "prevSearch" button
	Then I should see "chicken, 5, 1"

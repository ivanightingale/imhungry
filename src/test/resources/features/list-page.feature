Feature: List Management Page

Background:

	Given I visit the website
#1
#2
Scenario: clicking on an info item redirects to the restaurant or recipe page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press a recipe
	And select the list "Favorites"
	And press "addtolist" button
	And press "backtoresults" button
	And select the list "Favorites"
	And press "manage_list" button
	And press an info item
	Then I should see the "Restaurant" page
#3
Scenario: an item can be removed from a list
	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press a recipe
	And select the list "Favorites"
	And press "addtolist" button
	And press "backtoresults" button
	And select the list "Favorites"
	And press "manage_list" button
	And select the list "To Explore"
	And press "Change List" button
	And press "manage_list" button
	Then I should see an info item
#4
Scenario: clicking on "Return to Results Page" redirects to Results Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And select the list "Favorites"
	And press "manage_list" button
	And press "Back to Result" button
	Then I should see the "Result" page
#5
Scenario: clicking on "Return to Search Page" redirects to Search Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And select the list "Favorites"
	And press "manage_list" button
	And press "Back to Search" button
	Then I should see the "Search" page
#6
#7
Scenario: clicking on "Manage List" redirects to the list selected

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And select the list "Favorites"
	And press "manage_list" button
	And select the list "To Explore"
	And press "Manage List" button
	Then I should see the page of To Explore List

Feature: Results Page

Background:

	Given I visit the website

#1
#2
#3
#4
Scenario: clicking "Manage List" button redirects to List Management Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And select the list "Favorites"
	And press "manage_list" button
	Then I should see the "List Management" page

#5, 5a
#5b
#5c
Scenario: clicking on a restaurant redirects to Restaurant Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press a restaurant
	Then I should see the "Restaurant" page

#6, 6a
#6b
#6c
Scenario: clicking on a recipe redirects to Recipe Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press a recipe
	Then I should see the "Recipe" page

#7
#8
Scenario: clicking on "Return to Search Page" redirects to Search Page

	When press "return to search" button
	Then I should see the "Search" page

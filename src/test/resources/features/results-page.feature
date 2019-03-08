Feature: Results Page

Background:

	Given I visit the website

#1
#2
Scenario: Results page displays reasonable title

	When I search for "chicken" and expect 5 results
	And press "search" button
	Then the title of Results Page should be "Results for chicken"

#3
#4
Scenario: clicking "Manage List" button redirects to List Management Page

	When I search for "chicken" and expect 5 results
	And press "search" button
	And select the list "Favorites List"
	And press "manage list" button
	Then I should see the "List Management" page

#5, 5a
#5b
Scenario: Results page displays appropriate restaurant information

	When I search for "chicken" and expect 5 results
	And press "search" button
	Then I should see an element "restaurant name"
	And I should see an element "restaurant address"
	And I should see an element "restaurant rating"
	And I should see an element "restaurant drive time"
	And I should see an element "price range"
#5c
Scenario: clicking on a restaurant redirects to Restaurant Page

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a restaurant
	Then I should see the "Restaurant" page

#6, 6a
#6b
Scenario: Results page displays appropriate recipe information

	When I search for "chicken" and expect 5 results
	And press "search" button
	Then I should see an element "recipe name"
	And I should see an element "recipe rating"
	And I should see an element "recipe prep time"
	And I should see an element "recipe cook time"
#6c
Scenario: clicking on a recipe redirects to Recipe Page

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a recipe
	Then I should see the "Recipe" page

#7
#8
Scenario: clicking on "Return to Search Page" redirects to Search Page

	When press "return to search" button
	Then I should see the "Search" page

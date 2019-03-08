Feature: Recipe Page

Background:

	Given I visit the website
#1, 2, 3, 4, 5
Scenario: Recipe Page displays appropriate information

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a recipe
	Then I should see an element "recipe name"
	And I should see an element "recipe image"
	And I should see an element "recipe prep time"
	And I should see an element "recipe cook time"
	And I should see an element "recipe ingredients"
	And I should see an element "recipe instructions"
#6
Scenario: Recipe Page should be able to generate a printable version

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a recipe
	And press "printable version" button
	Then I should see the "Recipe Printable Version" page
#7
Scenario: clicking on "Return to Results Page" redirects to Results Page

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a recipe
	And press "return to results page" button
	Then I should see the "Results" page
#8, 9
Scenario: recipe can be added to a predefined list

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a recipe
	And select the list "Favorites List"
	And press "add to list" button
	And press "return to results page" button
	And select the list "Favorites List"
	And press "manage list" button
	Then I should see an info item

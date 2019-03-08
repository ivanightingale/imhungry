Feature: Recipe Page

Background:

	Given I visit the website
#1, 2, 3, 4, 5
Scenario: Recipe Page displays appropriate information

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press a recipe
	Then I should see an element "title"
	And I should see an element "img"
	And I should see an element "prept1"
	And I should see an element "cookt1"
	And I should see an element "ingre1"
	And I should see an element "instr1"
#6
Scenario: Recipe Page should be able to generate a printable version

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press a recipe
	And press "printableversion" button
	Then I should see the printable version page
#7
Scenario: clicking on "Return to Results Page" redirects to Results Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press a recipe
	And press "backtoresults" button
	Then I should see the "Result" page
#8, 9
Scenario: recipe can be added to a predefined list

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press a recipe
	And select the list "Favorites"
	And press "addtolist" button
	And press "backtoresults" button
	And select the list "Favorites"
	And press "manage_list" button
	Then I should see an info item

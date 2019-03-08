Feature: Restaurant Page

Background:

	Given I visit the website
#1
Scenario: Restaurant Page displays appropriate information

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a restaurant
	Then I should see an element "restaurant name"
	And I should see an element "restaurant address"
	And I should see an element "restaurant phone"
	And I should see an element "restaurant link"
#1a
Scenario: clicking on the address redirects to Google Maps directions page

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a restaurant
	And press the "address"
	Then I should see the "Google Maps" page
#1b
Scenario: clicking on the link redirects to restaurant website

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a restaurant
	And press the "link"
	Then I should see the "" page
#2
Scenario: Restaurant Page should be able to generate a printable version

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a restaurant
	And press "printable version" button
	Then I should see the "Restaurant Printable Version" page
#3
Scenario: clicking on "Return to Results Page" redirects to Results Page

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a restaurant
	And press "return to results page" button
	Then I should see the "Results" page
#4, 5
Scenario: restaurant can be added to a predefined list

	When I search for "chicken" and expect 5 results
	And press "search" button
	And press a restaurant
	And select the list "Favorites List"
	And press "add to list" button
	And press "return to results page" button
	And select the list "Favorites List"
	And press "manage list" button
	Then I should see an info item

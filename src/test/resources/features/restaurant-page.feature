Feature: Restaurant Page

Background:

	Given I visit the website
#1
Scenario: Restaurant Page displays appropriate information

	When I search for "burger" and expect 5 results
	And press "submit" button
	And press a restaurant
	Then I should see an element "title"
	And I should see an element "address1"
	And I should see an element "tel1"
	And I should see an element "website1"
#1a
Scenario: clicking on the address redirects to Google Maps directions page

	When I search for "burger" and expect 5 results
	And press "submit" button
	And press a restaurant
	And press the "address2"
	Then I should see the "Google Maps" page
#1b
Scenario: clicking on the link redirects to restaurant website

	When I search for "burger" and expect 5 results
	And press "submit" button
	And press a restaurant
	And press the "website2"
	Then I should see the "" page
#2
Scenario: Restaurant Page should be able to generate a printable version

	When I search for "burger" and expect 5 results
	And press "submit" button
	And press a restaurant
	And press "printableversion" button
	Then I should see the printable version page
#3
Scenario: clicking on "Return to Results Page" redirects to Results Page

	When I search for "burger" and expect 5 results
	And press "submit" button
	And press a restaurant
	And press "backtoresults" button
	Then I should see the "Result" page
#4, 5
Scenario: restaurant can be added to a predefined list

	When I search for "burger" and expect 5 results
	And press "submit" button
	And press a restaurant
	And select the list "Favorites"
	And press "addtolist" button
	And press "backtoresults" button
	And select the list "Favorites"
	And press "manage_list" button
	Then I should see an info item

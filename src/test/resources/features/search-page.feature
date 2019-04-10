Feature: Search Page

Background:

	Given I visit the website

#1
Scenario: the default page should be Search Page

	Then I should see the "Search" page

#5
Scenario: initiating the search redirects to Results Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	Then I should see the "Result" page

#6
Scenario: there should be an input box to let users filter restaurant results by radius
	Then I should see the input box for selecting specific radius


 #7
 Scenario: default page should have a background image
	 Then I should see a background image
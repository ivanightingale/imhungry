Feature: Search Page

Background:

	Given I visit the website

Scenario: the default page should be Search Page

	Then I should see the "Search" page

Scenario: initiating the search redirects to Results Page

	When I search for "chicken" and expect 5 results
	And press "search" button
	Then I should see the "Results" page

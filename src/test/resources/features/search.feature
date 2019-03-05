Feature: Searching for a homepage via Google

Background:

	Given I am on the Google page

Scenario: search on Google

	When I enter "William Halfond" in the search box
	And press search
	Then I should see Google search results for "William Halfond"

Scenario: search again

	When I enter "GJ Halfond" in the search box
	And press search
	Then I should see Google search results for "GJ Halfond"


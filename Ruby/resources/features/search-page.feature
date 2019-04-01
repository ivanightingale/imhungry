Feature: Search Page

Background:

	Given I visit the website

#1
Scenario: the default page should be Search Page

	When I visit the website
	Then I should see the "Search" page
	And There should be a "Login" button
	And There should be a "Sign up" button

#2
Scenario: searching without logging in
	When I search for "burger" and expect 5 results
	But I have not logged in
	Then there should be a "Please log in" message

 #3
Scenario: initiating the search redirects to Results Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	Then I should see the "Result" page

#6
Scenario: there should be a dropdown menu to let users filter restaurant results by radius
	Then I should see the dropdown menu for selecting specific radius
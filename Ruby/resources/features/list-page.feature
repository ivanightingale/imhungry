Feature: List Management Page

Background:

	Given I visit the website
#1
Scenario: login to testuser and see that favorites

	When I press "login" button
	And enter "testuser" into "username"
	And enter "password" into "password"
	And press "submit" button
	And I search for "chicken" and expect 5 results
	And press "submit" button
	And select the list "Favorites"
	And press "manage_list" button
	Then I should see an info item



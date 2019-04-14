Feature: Search Page

Background:

	Given I visit the website

#1
Scenario: the default page should be Search Page

	When I visit the website
	Then I should see the "Search" page
	And There should be a "login" button
	And There should be a "signup" button

#2
Scenario: if you login with wrong password it displays invalid password

	When I press "login" button
	And enter "testuser" into "username"
	And enter "wrongpassword" into "password"
	And press "submit" button
	Then I should see "Invalid Password!" text

#2
Scenario: if you login with not a real user name it displays invalid username

	When I press "login" button
	And enter "fakeuser" into "username"
	And enter "password" into "password"
	And press "submit" button
	Then I should see "Invalid Username!" text
#3

Scenario: initiating the search redirects to Results Page if I am logged in a

	When I press "login" button
    And enter "testuser" into "username"
    And enter "password" into "password"
    And press "submit" button
    And I should see the "Search" page

	# COMMENTED OUT SO THAT WE DON'T OVERLOAD API KEY
   # And I search for "chicken"
	#And expect 5 results
	#And press "submit" button
	#Then I should see the "Result" page


#4 Add recipe to grocery list
Scenario: if logged in you can add recipe item to grocery list

	When I press "login" button
	And enter "testuser" into "username"
	And enter "password" into "password"
	And press "submit" button
	And I should see the "Search" page
	And I search for "chicken"
	And expect 5 results
	And press "submit" button
	And press a recipe
	And select the list "Grocery"
	And press "addtolist" button
	And press "backtoresults" button
	And select the list "Grocery"
	And press "manage_list" button
	Then I should see an info item

#5 There is a button for previousSearches on the Search page
Scenario: There is a button for previousSearches on the Search page

	When I press "login" button
	And enter "testuser" into "username"
	And enter "password" into "password"
	And press "submit" button
	And I should see the "Search" page
	And I should see a "prevSearch" button
	And I click "prevSearch" button
	Then I should see "Your Previous Searches" field

#6 previousSearches stores previous Searches
Scenario: There is a button for previousSearches on the Search page

	When I press "login" button
	And enter "testuser" into "username"
	And enter "password" into "password"
	And press "submit" button
	And I search for "chicken"
	And expect 5 results
	And enter radius of 1
	And press a recipe
	And press "backtoresults" button
	And press "backtosearch" button
	And press "prevSearch" button
	Then I should see "chicken, 5, 1" text

#7
Scenario: search results page should have pagination buttons
   When I press "login" button
   And enter "testuser" into "username"
   And enter "password" into "password"
   And press "submit" button
   And I search for "pizza"
   And expect 10 results
   And enter radius of 5
   Then I should see a "page 1" button

	#8
Scenario: search page should show only five results
	When I press "login" button
	And enter "testuser" into "username"
	And enter "password" into "password"
	And press "submit" button
	And I search for "pizza"
	And expect 16 results
	And enter radius of 5
	Then I should see a "page 1" button
	And I should see a "page 2" button
	And I should see a "page 3" button
	And there should be 5 recipe results
	And there should be 5 restaurant results
	And there should be 6 recipe results
	And there should be 6 restaurant results
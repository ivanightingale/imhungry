Feature: Results Page

Background:

	Given I visit the website

#2
Scenario: Results Page should display an appropriate title

	When I search for "chicken" and expect 5 results
	And press "submit" button
	Then I should see a title "Results for chicken"

#4
Scenario: clicking "Manage List" button redirects to List Management Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And select the list "Favorites"
	And press "manage_list" button
	Then I should see the "List Management" page

#5
Scenario: there is a column of results with the title "Restaurants"

	When I search for "chicken" and expect 5 results
	And press "submit" button
	Then I should see a title "Restaurants"
	And I should see an element "Res_item0"

#5a, 6a
Scenario: number of restaurant and recipe results are as specified

	When I search for "chicken" and expect 5 results
	And press "submit" button
	Then I should see  "5" results

#5c
Scenario: clicking on a restaurant redirects to Restaurant Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press a restaurant
	Then I should see the "Restaurant" page

#6
Scenario: there is a column of results with the title "Recipes"

	When I search for "chicken" and expect 5 results
	And press "submit" button
	Then I should see a title "Recipes"
	And I should see an element "Rec_item0"

#6c
Scenario: clicking on a recipe redirects to Recipe Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press a recipe
	Then I should see the "Recipe" page

#7b
Scenario: items in the Favorites list should be ranked at the top

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press the third recipe
	And select the list "Favorites"
	And press "Add to List" button
	And press "Back to Results" button
	And press "Back to Search" button
	And I search for "chicken" and expect 5 results
	Then I should see "Jerk Chicken (Grilled Spicy Marinated Chicken)" on the top of recipes

#7c
Scenario: items in the Do Not Show list should not appear in the results list

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press a recipe
	And select the list "Do Not Show"
	And press "Add to List" button
	And press "Back to Results" button
	And press "Back to Search" button
	And I search for "chicken" and expect 5 results
	Then I should not see recipe "Chicken Karaage (Japanese Deep Fried Chicken)"

#8
Scenario: clicking on "Return to Search Page" redirects to Search Page

	When I search for "chicken" and expect 5 results
	And press "submit" button
	And press "Back to Search" button
	Then I should see the "Search" page

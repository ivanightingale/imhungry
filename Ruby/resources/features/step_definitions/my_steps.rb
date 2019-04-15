
When(/^I visit the website$/) do
  visit('localhost:8080')
end

When(/^I visit the results page/) do
  visit('localhost:8080/resultPage.jsp')
end

When(/^I visit the list page/) do
  visit('localhost:8080/listPage.jsp')
end

When(/^I visit the recipe page/) do
  visit('localhost:8080/recipePage.jsp')
end

When(/^I visit the restaurant page/) do
  visit('localhost:8080/restaurantPage.jsp')
end

When(/^press search$/) do
  find_field("q").native.send_keys(:enter)
end

When(/^I search for "([^"]*)"$/) do |query|
  fill_in('search', :with => query)
end

When(/^press "([^"]*)" button$/) do |buttonName|
  click_button(buttonName)
end

When(/^press a recipe$/) do
  find('#Rec_item0').click
end

When(/^press the third recipe$/) do
  find('#Rec_item2').click
end

When(/^press a restaurant$/) do
  find('#Res_item0').click
end

When(/^press an info item$/) do
  find('#item0').click
end

When(/^press the "([^"]*)"$/) do |elementName|
  find('#' + elementName).click
end

Then(/^I should see the "([^"]*)" page$/) do |pageTitle|
  expect(page).to have_title pageTitle
end

Then(/^I should see a title "([^"]*)"$/) do |query|
  expect(page).to have_field(query)
end

Then(/^I should see an element "([^"]*)"$/) do |elementName|
  expect(page).to have_css('#' + elementName)
end

Then(/^I should see  "([^"]*)" results$/) do |numResults|
  expect(page).to_not have_css('#Res_item' + numResults)
  expect(page).to_not have_css('#Rec_item' + numResults)
end

Then(/^I should see "([^"]*)" on the top of recipes$/) do |recipeName|
  expect(page).to have_css('div.Rec_section1')
end

Then(/^I should not see recipe "([^"]*)"$/) do |recipeName|
  expect(page).to_not have_field(recipeName)
end


When(/^select the list "([^"]*)"$/) do |listName|
  find('.select-selected').click
  all('div', :text => listName)[2].click
end

Then(/^I should see an info item$/) do
  expect(page).to have_css('#item0')
end

Then(/^I should see the page of "([^"]*)"$/) do |arg|
  expect(page).to have_field(arg)
end


And(/^There should be a "([^"]*)" button$/) do |arg|
  expect(page).to have_button(arg)
end


Then(/^I should see the dropdown menu for selecting specific radius$/) do
  expect(page).to have_select('radius_dropdown')
end



When(/^I press "([^"]*)" button$/) do |arg|
  click_button(arg);
end

And(/^enter "([^"]*)" into "([^"]*)"$/) do |arg1, arg2|
  fill_in(arg2, :with => arg1)
end

Then(/^I should see the "([^"]*)" button$/) do |arg|
  expect(page).to have_button(arg)
end


Then(/^I should not see "([^"]*)"$/) do |arg|
  expect(page).to_not have_selector('#' + arg)
end

And(/^I should see a "([^"]*)" button$/) do |arg|
  expect(page).to have_button(arg)
end

And(/^I click "([^"]*)" button$/) do |arg|
  click_button(arg)
end

Then(/^I should see "([^"]*)" field$/) do |arg|
  expect(page).to have_field(arg)
end

And(/^enter radius of (\d+)$/) do |arg|
  fill_in('radius', :with => arg)
end

Then(/^I should see "([^"]*)" text$/) do |arg|
  expect(page).to have_text(arg)
end


And(/^expect (\d+) results$/) do |arg|
  fill_in('number', :with => arg)
end


And(/^there should be (\d+) restaurant results$/) do |arg1|
  expect(page).to have_css('#Res_item5')
end

And(/^there should be (\d+) recipe results$/) do |arg1|
  expect(page).to have_css('#Rec_item5')
end

And(/^there should not be (\d+) recipe results$/) do |arg1|
  expect(page).to_not have_css('#Rec_item6')
end

And(/^there should not be (\d+) restaurant results$/) do |arg1|
  expect(page).to_not have_css('#Res_item6')
end

And(/^I should see a "([^"]*)" dropdown$/) do |arg|
  expect(page).to have_selector(arg)
end

And(/^I should see prevSearch dropdown$/) do
  expect(page).to have_select('prev_search')
end

And(/^click the "([^"]*)" selector$/) do |arg|
  find_by_id(arg).click
end

Then(/^I should see "([^"]*)" in "([^"]*)"$/) do |arg1, arg2|
  expect(find_field(arg2).value).to eq arg1
end

And(/^I should see (\d+) in "([^"]*)"$/) do |arg1, arg2|
  expect(find_field(arg2).value).to eq arg1
end

And(/^select "([^"]*)" from dropdown$/) do |arg1|
  find_by_id(arg1).select
end

And(/^click prevSearch dropdown$/) do
  find_by_id('prev_search').click
end

And(/^click on dropdown$/) do
  find_by_id('dropdown').click
end
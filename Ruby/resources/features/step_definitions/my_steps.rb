
When(/^I visit the website$/) do
  visit('localhost:8080')
end
When(/^press search$/) do
  find_field("q").native.send_keys(:enter)
end

When(/^I search for "([^"]*)" and expect 5 results$/) do |query|
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
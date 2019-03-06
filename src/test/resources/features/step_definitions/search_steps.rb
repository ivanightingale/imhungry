Given(/^I am on the Google page$/) do
  visit "https://www.google.com"
end

When(/^I enter "([^"]*)" in the search box$/) do |searchArg|
  fill_in('q', :with => searchArg)
end

When(/^press search$/) do
  find_field("q").native.send_key(:enter)
end

Then(/^I should see Google search results for "([^"]*)"$/) do |arg1|
  expect(page).to have_title arg1
end


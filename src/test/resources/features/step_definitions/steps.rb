When(/^press search$/) do
  find_field("q").native.send_key(:enter)
end



Given(/^I visit the website$/) do
  visit "localhost:9090"
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

Then(/^I should see the "([^"]*)" page$/) do |pageTitle|
    expect(page).to have_title pageTitle
end

Then(/^I should see an element "([^"]*)"$/) do |elementName|
    expect_page.to have_css('#' + elementName)
end

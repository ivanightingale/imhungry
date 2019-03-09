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
    expect(page).to have_content(query)
end

Then(/^I should see an element "([^"]*)"$/) do |elementName|
    expect(page).to have_css('#' + elementName)
end

Then(/^I should see  "([^"]*)" results$/) do |numResults|
    expect(page).to have_no_css('#Res_item' + numResults)
    expect(page).to have_no_css('#Rec_item' + numResults)
end

Then(/^I should see "([^"]*)" on the top of recipes$/) do |recipeName|
    expect(page).to have_css('div.Rec_section1')
end

Then(/^I should not see recipe "([^"]*)"$/) do |recipeName|
    expect(page).to have_no_content(recipeName)
end

Then(/^I should see the printable version page$/) do
    expect(page).to have_no_css('image')
end

When(/^select the list "([^"]*)"$/) do |listName|
    find('.select-selected').click
    all('div', :text => listName)[2].click
end

Then(/^I should see an info item$/) do
    expect(page).to have_css('#item0')
end

Then(/^I should see the page of To Explore List$/) do
    expect(page).to have_content('To Explore List')
end

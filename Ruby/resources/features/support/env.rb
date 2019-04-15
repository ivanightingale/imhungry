
require 'capybara'
require 'capybara/cucumber'
require 'rspec'
require 'webdrivers'


#Capybara.register_driver :chrome do |app|
#  Capybara::Selenium::Driver.new(app, :browser => :chrome, :driver_path=>"/Users/mackenziemcclung/Downloads/chromedriver")
#end

Capybara.register_driver :selenium do |app|
  Capybara::Selenium::Driver.new(app, browser: :chrome)
end

Capybara.default_driver = :selenium

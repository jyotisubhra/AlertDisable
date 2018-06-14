Feature: Disable Nagios Alert for All Hosts scenario


Scenario: Disable Nagios Alert
Given Open IE and start applications
When I Select Hosts link

@run
Scenario: schedule downtime for all services
Given Open IE and start the application
When Iterate the Hostlists
Then Select "hostlink" for each host
Then select schedule downtime for all services for the "hsotname"
Then Enter Comment as "comment"
Then Enter Start Date as "startDate"
Then Enter End Date as "End Date"
Then Click on Commit as "Enable"
Then Click on Hosts link
Then click on Downtime link




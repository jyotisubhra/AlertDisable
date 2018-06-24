Feature: Disable Nagios Alert for All Hosts scenario


Scenario: Disable Nagios Alert
Given Open IE and start applications
When I Select Hosts link

@run
Scenario: schedule downtime for all services
Given Open IE and start the application
When Iterate the Hostlists

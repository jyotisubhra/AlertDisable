$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("myApplication.feature");
formatter.feature({
  "line": 1,
  "name": "Disable Nagios Alert for All Hosts scenario",
  "description": "",
  "id": "disable-nagios-alert-for-all-hosts-scenario",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 9,
  "name": "schedule downtime for all services",
  "description": "",
  "id": "disable-nagios-alert-for-all-hosts-scenario;schedule-downtime-for-all-services",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 8,
      "name": "@run"
    }
  ]
});
formatter.step({
  "line": 10,
  "name": "Open IE and start the application",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "Iterate the Hostlists",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "Select \"hostlink\" for each host",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "select schedule downtime for all services for the \"hsotname\"",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "Enter Comment as \"comment\"",
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "Enter Start Date as \"startDate\"",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "Enter End Date as \"End Date\"",
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "Click on Commit as \"Enable\"",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "Click on Hosts link",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "click on Downtime link",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDef.open_IE_and_start_the_application()"
});
formatter.result({
  "duration": 166232511,
  "status": "passed"
});
formatter.match({
  "location": "StepDef.iterate_the_Hostlists()"
});
formatter.result({
  "duration": 598447450287,
  "status": "passed"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});
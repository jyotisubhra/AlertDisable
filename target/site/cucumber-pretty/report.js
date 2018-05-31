$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("myApplication.feature");
formatter.feature({
  "line": 1,
  "name": "Disable Nagios Alert for All Hosts scenario",
  "description": "",
  "id": "disable-nagios-alert-for-all-hosts-scenario",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Disable Nagios Alert",
  "description": "",
  "id": "disable-nagios-alert-for-all-hosts-scenario;disable-nagios-alert",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@run"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "Open IE and start application",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I Select Hosts link",
  "keyword": "When "
});
formatter.match({
  "location": "StepDef.Open_IE_and_start_application()"
});
formatter.result({
  "duration": 19373629145,
  "status": "passed"
});
formatter.match({
  "location": "StepDef.I_Select_Hosts_link()"
});
formatter.result({
  "duration": 42873122243,
  "status": "passed"
});
});
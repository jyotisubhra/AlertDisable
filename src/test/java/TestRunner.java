package test.java;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
//@Cucumber.Options(features="features",glue={"test/stepDefinition"},monochrome = true,
//format = {"pretty", "html:target/cucumber-htmlreport","json:target/cucumber-report.json"})
@CucumberOptions(features = {"features"}, 
glue = {"test/stepDefinition"},
monochrome = true,
tags = {"@run"},
plugin = {"pretty",
		"html:target/site/cucumber-pretty",
		"rerun:target/return.txt",
		"json:target/cucumber.json"})
public class TestRunner {

}

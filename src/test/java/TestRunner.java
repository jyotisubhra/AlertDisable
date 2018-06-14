package test.java;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import test.resource.utils.ConfigUtils;


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
	
	@BeforeClass
	public static void before() throws SQLException {
		ConfigUtils.fnBeforeTestBlock();
	}
	
	@AfterClass
	public static void after() throws SQLException {
		ConfigUtils.fnLoggerEnd();
	}

}

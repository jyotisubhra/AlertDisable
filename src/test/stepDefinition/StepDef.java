package test.stepDefinition;

import static test.resource.utils.ConfigUtils.comment;
import static test.resource.utils.ConfigUtils.enable;
import static test.resource.utils.ConfigUtils.endTime;
import static test.resource.utils.ConfigUtils.startTime;

import java.util.ArrayList;
import java.util.Iterator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import test.resource.utils.BusinessUtils;

public class StepDef {
	

	@Given("^Open IE and start application$")
	public void Open_IE_and_start_application() throws Throwable {
		BusinessUtils.startApplication();
	}

	@When("^I Select Hosts link$")
	public void I_Select_Hosts_link() throws Throwable {
		BusinessUtils.selectHostLinks();		
	}
	
	@Given("^Open IE and start the application$")
	public void open_IE_and_start_the_application() throws Throwable {
		BusinessUtils.startApplication();
	}

	@When("^Iterate the Hostlists$")
	public void iterate_the_Hostlists() throws Throwable {
		ArrayList<String> hostList = BusinessUtils.getAllHosts();

		if (null != hostList) {
			Iterator<String> itr = hostList.iterator();
			while (itr.hasNext()) {			
				String hostname = (String) itr.next();
				System.out.println("Hostname, going to disable now is: " + hostname);
				BusinessUtils.clickOnHost(hostname);
				BusinessUtils.clickSchdDownTimeAllServices(hostname);
				Thread.sleep(10000);
				BusinessUtils.entComment(comment);
				Thread.sleep(5000);
				BusinessUtils.entStartTime(startTime);
				Thread.sleep(5000);
				BusinessUtils.entEndTime(endTime);
				Thread.sleep(5000);
				BusinessUtils.clickCommit(enable);
				BusinessUtils.rest();
			}
		}
		BusinessUtils.getDisabledPageScreenshots();
	}


}

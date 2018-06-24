package test.resource.utils;


import static test.resource.utils.ConfigUtils.URL;
import static test.resource.utils.ConfigUtils.browser;
import static test.resource.utils.ConfigUtils.comment;
import static test.resource.utils.ConfigUtils.enable;
import static test.resource.utils.ConfigUtils.endTime;
import static test.resource.utils.ConfigUtils.environment;
import static test.resource.utils.ConfigUtils.map;
import static test.resource.utils.ConfigUtils.startTime;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import test.resource.pageObjects.NagiosPage;


public class BusinessUtils {
	
public static WebDriver driver;
	
	public static void setUp() {
		
		if (browser.equalsIgnoreCase("FF")) {
			//todo
		} else {
			System.out.println("***************");
			System.out.println("launching IE Browser");
			
			System.setProperty("webdriver.ie.driver", "src/test/resource/driver_32/IEDriverServer.exe");			
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);		
			driver=new InternetExplorerDriver(capabilities);
			driver.manage().window().maximize();
		    driver.get(URL);		    
		}
	}

	public static void startApplication() {		
		ConfigUtils.ReportLog(true, "Application Started", false);
		
		
	}

	public static void selectHostLinks() {
		try {
			selectHostLink();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConfigUtils.ReportLog(true, "HostLink Selected", false);
	}
	
	public static void selectHostLink() throws Throwable {
		NagiosPage.clickLinkByHref("nagios/cgi-bin/status.cgi?hostgroup=all&style=hostdetail", "side");
		driver.switchTo().defaultContent();
		ArrayList<String> hostList = (ArrayList<String>) map.get(environment);
		System.out.println("List Size " + hostList.size());
		
		if (null != hostList) {
			Iterator<String> itr = hostList.iterator();
			while (itr.hasNext()) {			
				String hostname = (String) itr.next();
				System.out.println("Hostname, going to disable now is: " + hostname);
				clickOnHost(hostname);
				clickSchdDownTime(hostname);
				entComment(comment);
				entStartTime(startTime);
				entEndTime(endTime);
				clickCommit(enable);
				NagiosPage.clickLinkByHref("nagios/cgi-bin/status.cgi?hostgroup=all&style=hostdetail", "side");
				driver.switchTo().defaultContent();
			}
		}
		getDisabledPageScreenshots();
		
	}
	
	public static void clickOnHost(String hostname) throws Throwable {
		Thread.sleep(10000);
		NagiosPage.clickLinkByHref("extinfo.cgi?type=1&host=" + hostname, "main");
		driver.switchTo().defaultContent();
		ConfigUtils.ReportLog(true, "Click on Host " + hostname, false);
	}
	
	public static void clickSchdDownTime(String hostname) throws Throwable {
		Thread.sleep(5000);
		NagiosPage.clickLinkByHref("cmd.cgi?cmd_typ=55&host=" + hostname, "main");
		driver.switchTo().defaultContent();
		ConfigUtils.ReportLog(true, "Click on Schedule Downtime ", false);
	}
	
	public static void entComment(String disabledComment) throws Throwable {
		Thread.sleep(5000);
		driver.switchTo().frame("main");
		WebElement element = NagiosPage.getComData();		
		element.clear();
		element.sendKeys(disabledComment);
		driver.switchTo().defaultContent();
		ConfigUtils.ReportLog(true, "Enter Comment ", false);
	}
	
	public static void entStartTime(String stTime) throws Throwable {
		Thread.sleep(2000);
		driver.switchTo().frame("main");
		WebElement element = NagiosPage.getStartDate();	
		element.clear();
		element.sendKeys(stTime);
		driver.switchTo().defaultContent();
		ConfigUtils.ReportLog(true, "Enter Start Date ", false);
	}
	
	public static void entEndTime(String ndTime) throws Throwable {
		Thread.sleep(2000);
		driver.switchTo().frame("main");
		WebElement element = NagiosPage.getEndDate();	
		element.clear();
		element.sendKeys(ndTime);
		driver.switchTo().defaultContent();
		ConfigUtils.ReportLog(true, "Enter End Date ", false);
		
	}
	
	public static void clickCommit(String nbltrue) throws Throwable {

		Thread.sleep(2000);
		driver.switchTo().frame("main");
		WebElement element = NagiosPage.getCommitBtn();	
		if (null != nbltrue && nbltrue.equalsIgnoreCase("Y")) {
			System.out.println("enable");
			driver.findElement(By.name("btnSubmit")).sendKeys(Keys.ENTER);
			element.click();
		}
		driver.switchTo().defaultContent();
		ConfigUtils.ReportLog(true, "Commit ", false);

	}
	
	public static void getDisabledPageScreenshots() throws Throwable {
		Thread.sleep(10000);
		NagiosPage.clickLinkByHref("/nagios/cgi-bin/extinfo.cgi?type=6", "side");
		Thread.sleep(20000);
		driver.switchTo().defaultContent();
		ConfigUtils.ReportLog(true, "Downtime Window clicked ", true);
		
	}

	public static ArrayList<String> getAllHosts() {
		
		NagiosPage.clickLinkByHref("nagios/cgi-bin/status.cgi?hostgroup=all&style=hostdetail", "side");
		driver.switchTo().defaultContent();
		ArrayList<String> hostList = (ArrayList<String>) map.get(environment);
		System.out.println("List Size " + hostList.size());
		
		return hostList;
	}

	public static void rest() {
		
		NagiosPage.clickLinkByHref("nagios/cgi-bin/status.cgi?hostgroup=all&style=hostdetail", "side");
		driver.switchTo().defaultContent();		
	}

	public static void clickSchdDownTimeAllServices(String hostname) throws Throwable {
		Thread.sleep(10000);
		NagiosPage.clickLinkByHref("cmd.cgi?cmd_typ=86&host=" + hostname, "main");
		driver.switchTo().defaultContent();
		ConfigUtils.ReportLog(true, "Click on scheduled Downtime link " + hostname, false);
		
	}

}

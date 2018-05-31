package test.stepDefinition;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef {
	
	WebDriver driver;
	Map map = null;
	String comment = null;
	String startTime = null;
	String endTime = null;
	String environment = null;
	String browser = null;
	String URL = null;
	String enable = null;
	
	@Given("^Open Firefox and start application$")
	public void Open_Firefox_and_start_application() throws Throwable {
		//below is for IE
		//File file =new File("C:\\Workspace\\AlertAutomation\\SeleniumCucumberProject\\src\\test\\resource\\driver_64\\IEDriverServer.exe");
		//System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		System.setProperty("webdriver.ie.driver", "src/test/resource/driver_32/IEDriverServer.exe");
		driver=new InternetExplorerDriver();
		
		//Below is for FF
		/*File file =new File("C:/EC WORKSPACE/Webservice/SeleniumCucumberProject/src/test/resource/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
		driver = new FirefoxDriver();*/
	   
		
		
		driver.manage().window().maximize();
	    driver.get("http://localhost:18180/nagios/");
	    
	    String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
	    String subWindowHandler = null;

	    Set<String> handles = driver.getWindowHandles(); // get all window handles
	    Iterator<String> iterator = handles.iterator();
	    while (iterator.hasNext()){
	    	subWindowHandler = iterator.next();
	    	if (subWindowHandler!=parentWindowHandler){
	    		System.out.println("Inside If block");
	            WebDriver popup=driver.switchTo().window(subWindowHandler); 
	            System.out.println(popup.getTitle());
	         }
	    }
	    driver.switchTo().window(parentWindowHandler);
	    System.out.println(driver.getTitle());
	}

	@Given("^Open IE and start application$")
	public void Open_IE_and_start_application() throws Throwable {
		
		setupData();
		setupEnvironment();
	}

	private void setupEnvironment() {
		
		if (browser.equalsIgnoreCase("FF")) {
			//todo
		} else {
			//File file =new File("C:\\Workspace\\AlertAutomation\\SeleniumCucumberProject\\src\\test\\resource\\driver_64\\IEDriverServer.exe");
			//System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			System.setProperty("webdriver.ie.driver", "src/test/resource/driver_32/IEDriverServer.exe");
			
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);		
			driver=new InternetExplorerDriver(capabilities);
			driver.manage().window().maximize();
		    driver.get(URL);
		}
		
	}

	private void setupData() {
		
		InputStream input = null;
		Properties configProp = new Properties();
		String[] intAllHosts = null;
		
		try {
			String filename = "conf/config.properties";
			input = StepDef.class.getClassLoader().getResourceAsStream(filename);			
			if(input == null){
		        System.out.println("Sorry, unable to find " + filename);
			    return;
			}
			// load a properties file
			configProp.load(input);	
			environment = configProp.getProperty("ENV");
			URL = configProp.getProperty("NAGIOS_URL");
			browser = configProp.getProperty("BROWSER");
			comment = configProp.getProperty("comment");
			startTime = configProp.getProperty("startTime");
			endTime = configProp.getProperty("endTime");
			enable = configProp.getProperty("ENABLE");
			System.out.println("Environment Selected is :" + environment);
			if (null != environment) {
				intAllHosts = configProp.getProperty(environment).split(",");
				System.out.println("Host Lists size, going to disabled: " + intAllHosts.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != intAllHosts) {
			List<String> intHosts= new ArrayList<String>(Arrays.asList(intAllHosts));
			map = new HashMap<>();
			map.put(environment, intHosts);
		}
	}

	@When("^I Select Hosts link$")
	public void I_Select_Hosts_link() throws Throwable {
		
		clickLinkByHref("nagios/cgi-bin/status.cgi?hostgroup=all&style=hostdetail", "side");
		driver.switchTo().defaultContent();
		ArrayList<String> hostList = (ArrayList<String>) map.get(environment);
		System.out.println("List Size " + hostList.size());
		
		if (null != hostList) {
			Iterator<String> itr = hostList.iterator();
			while (itr.hasNext()) {			
				String hostname = (String) itr.next();
				System.out.println("Hostname, going to disable now is: " + hostname);
				I_Click_on_hostname(hostname);
				I_Click_on_Schedule_downtime_for_this_host(hostname);
				I_enter_Comment_Data(comment);
				I_select_Start_Time(startTime);
				I_select_End_Time(endTime);
				I_click_on_Commit_Button(enable);
				clickLinkByHref("nagios/cgi-bin/status.cgi?hostgroup=all&style=hostdetail", "side");
				driver.switchTo().defaultContent();
			}
		}
	}

	@Then("^I Click on hostname$")
	public void I_Click_on_hostname(String hostname) throws Throwable {
		Thread.sleep(10000);
		/*driver.switchTo().frame("main");
		WebElement element = driver.findElement(By.xpath("//a[@href='extinfo.cgi?type=1&host=vmh-lh-adcc-dlxdud01a']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);*/		
		clickLinkByHref("extinfo.cgi?type=1&host=" + hostname, "main");
		driver.switchTo().defaultContent();
		
	}

	@Then("^I Click on Schedule downtime for this host$")
	public void I_Click_on_Schedule_downtime_for_this_host(String hostname) throws Throwable {
		Thread.sleep(5000);
		/*driver.switchTo().frame("main");
		WebElement element = driver.findElement(By.xpath("//a[@href='cmd.cgi?cmd_typ=55&host=vmh-lh-adcc-dlxdud01a']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);*/
		clickLinkByHref("cmd.cgi?cmd_typ=55&host=" + hostname, "main");
		driver.switchTo().defaultContent();
	}

	@Then("^I enter Comment Data$")
	public void I_enter_Comment_Data(String disabledComment) throws Throwable {
		Thread.sleep(5000);
		driver.switchTo().frame("main");
		WebElement element = driver.findElement(By.name("com_data"));
		element.clear();
		element.sendKeys(disabledComment);
		driver.switchTo().defaultContent();
	}

	@Then("^I select Start Time$")
	public void I_select_Start_Time(String stTime) throws Throwable {
		Thread.sleep(2000);
		driver.switchTo().frame("main");
		WebElement element = driver.findElement(By.name("start_time"));
		element.clear();
		element.sendKeys(stTime);
		driver.switchTo().defaultContent();
	}

	@Then("^I select End Time$")
	public void I_select_End_Time(String ndTime) throws Throwable {
		Thread.sleep(2000);
		driver.switchTo().frame("main");
		WebElement element = driver.findElement(By.name("end_time"));
		element.clear();
		element.sendKeys(ndTime);
		driver.switchTo().defaultContent();
		
	}

	@Then("^I click on Commit Button$")
	public void I_click_on_Commit_Button(String nbltrue) throws Throwable {
		
		Thread.sleep(2000);
		driver.switchTo().frame("main");
		WebElement element = driver.findElement(By.name("btnSubmit"));
		if (null != nbltrue && nbltrue.equalsIgnoreCase("Y")) {
			element.click();
		}
		driver.switchTo().defaultContent();
		
	}
	
	public void clickLinkByHref(String href, String framename) {
		driver.switchTo().frame(framename);
        List<WebElement> anchors = driver.findElements(By.tagName("a"));
        System.out.println("Size " + anchors.size());
        Iterator<WebElement> i = anchors.iterator();

        while(i.hasNext()) {
            WebElement anchor = i.next();
            if (null != anchor) {
            	System.out.println("Anchor " + anchor.getAttribute("href"));
                if(anchor.getAttribute("href").contains(href)) {
                	System.out.println("match found " + anchor.getText());
                	JavascriptExecutor executor = (JavascriptExecutor) driver;
                	executor.executeScript("arguments[0].click();", anchor);
                    //anchor.click();
                    break;
                }
            }
        }
        anchors = null;
    }

}

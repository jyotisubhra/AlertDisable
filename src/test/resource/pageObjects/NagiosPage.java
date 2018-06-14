package test.resource.pageObjects;

import static test.resource.utils.BusinessUtils.driver;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class NagiosPage {
	

	public static void clickLinkByHref(String href, String framename) {
		driver.switchTo().frame(framename);
        List<WebElement> anchors = driver.findElements(By.tagName("a"));
        System.out.println("Size " + anchors.size());
        Iterator<WebElement> i = anchors.iterator();

        while(i.hasNext()) {
            WebElement anchor = i.next();
            if (null != anchor) {
            	//System.out.println("Anchor " + anchor.getAttribute("href"));
                if(anchor.getAttribute("href").contains(href)) {
                	System.out.println("match found: " + anchor.getText());
                	JavascriptExecutor executor = (JavascriptExecutor) driver;
                	executor.executeScript("arguments[0].click();", anchor);
                    //anchor.click();
                    break;
                }
            }
        }
        anchors = null;
    }

	public static WebElement getComData() {
		WebElement element = driver.findElement(By.name("com_data"));
		return element;
	}

	public static WebElement getStartDate() {
		WebElement element = driver.findElement(By.name("start_time"));
		return element;
	}

	public static WebElement getEndDate() {
		WebElement element = driver.findElement(By.name("end_time"));
		return element;
	}

	public static WebElement getCommitBtn() {
		WebElement element = driver.findElement(By.name("btnSubmit"));
		return element;
	}

}

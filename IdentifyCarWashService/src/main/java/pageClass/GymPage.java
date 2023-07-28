package pageClass;

import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;


import baseClass.BaseUI;

public class GymPage extends BaseUI {

     
	    public GymPage(WebDriver driver, ExtentReports report) {
	    	
	        BaseUI.driver = driver;
	  
	    }
	// XPath for Fitness Page
	By fitnessXpath = By.xpath("//a[@id='ContextualHotkey_27']");
	
	By gymXpath = By.xpath("//*[@id=\"__next\"]/div/section/section/div[2]/div/div/div/ul/li[3]");

	By gymMenu =By.cssSelector("span.jsx-841ef9f82318bbcf.catefilter_item_text.pl-15.font15.fw400.color111");

	WebDriverWait wait = new WebDriverWait(driver, 30);

	
	// Automate Fitness
	@Test
	public void fitness() {
		try {

			logger = report.createTest("Fitness -Click Fitness","");
			
			
			WebElement fitness = driver.findElement(fitnessXpath);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", fitness);
			
				reportInfo("Fitness functionality available and clicked");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Fitness Functionality
	public void fitnessMenu() {
		try {
			logger = report.createTest("Fitness -Verify Fitness menu page");
			String title = driver.getTitle();
			if (title.contains("Fitness")) {
				reportInfo("Fitness menu available and page verified");
				takeScreenshot();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Automate Gym
	public void gym() throws Exception {
		try {
			logger = report.createTest("Fitness -Click Gym");
			WebElement gym = wait.until(ExpectedConditions.visibilityOfElementLocated(gymXpath));
			if (gym.isEnabled()) {
				reportInfo("Gym button is visible and selected");
				takeScreenshot();
				gym.click();
				Thread.sleep(3000);
			} else {
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Verify Gym Functionality
	public void gymMenu() {
		try {
			logger = report.createTest("Fitness -Print all the sub-menu available under Gym menu");
			
			List<WebElement> lst = driver.findElements(gymMenu);
			System.out.println(lst.size());
			for (int j = 1; j < lst.size(); j++) {
				reportInfo(lst.get(j).getText());
				System.out.println(lst.get(j).getText());
			}
			
			reportPass("Gym sub-menu displayed successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

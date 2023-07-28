package pageClass;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import baseClass.BaseUI;

public class CarWashPage extends BaseUI {

	WebDriverWait wait = new WebDriverWait(driver, 3);
	static int count = 0;
	public boolean verify;
	static String Phno;


	// XPath to Select Car Wash Functionality
	//By autoCare = By.xpath("//span[text() = 'Auto care']");
	By autoCare = By.id("main-auto");
	By carWash = By.xpath("//span[text() = 'Car Wash']");
	 By close =  By.cssSelector("#jdLgnbox > .modal_head_right > .jsx-2f5bb4caeae542ba");
	// XPath to filter by Location, Ratings and Votes
	By location = By.cssSelector(".input_location");
	By enterLocation = By.cssSelector(".location_text:nth-child(2)");
	 By searchButton = By.cssSelector("#react-autowhatever-main-auto-suggest--item-0 .search_text");
	By clickGo = By.xpath("//button[@id = 'sortbydistbtn'] [text() = 'Go']");
	By rating = By.xpath("//li[@id = 'rating']");

	// XPath for Location Verification
	By verifyLocation = By.xpath("(//span[@class='jcn'])[2]");
	By verifyLocationPage = By.xpath("(//span[@class='lng_add'])[1]");

	// XPath to print the details
	By shopNameXpath = By.xpath("//*[contains(@class, 'resultbox_title font22 fw500 color111 complist_title')]");
	By starXpath = By.xpath("//*[contains(@class, 'resultbox_totalrate mr-6 font14 fw700 colorFFF')]");
	By voteXpath = By.xpath("//div[@class='resultbox_countrate ml-12 mr-12 font14 fw400 color777']");
	

	// Enter Location
		public void location() {
			try {
				logger = report.createTest("Car Wash -Click Location");
				wait.until(ExpectedConditions.elementToBeClickable(close));
				driver.findElement(close).click();
				wait.until(ExpectedConditions.elementToBeClickable(location));
				driver.findElement(location).click();
				wait.until(ExpectedConditions.elementToBeClickable(enterLocation));
				
				if(Location == 1)
				{
					
					driver.findElement(location).sendKeys("Mumbai");
					driver.findElement(By.linkText("Mumbai")).click();
				}
				else
				{
					
				driver.findElement(enterLocation).click();
				}
				
				reportPass("Location functionality clicked to enter nearest location");

			} catch (Exception e) {
				// Print if the Test Case Failed
				e.printStackTrace();
				reportFail("Location functionality not selected");
			}
		}

	// Automate Auto Care
	public void autoCarMenu() {
		try {
			// Creating Test report for "autoCarMenu" function
			//logger = extent.createTest("Google Test", "Test to launch google site");
			logger = report.createTest("Car Wash -Click Auto Car");
			driver.findElement(autoCare).click();
			driver.findElement(autoCare).sendKeys("Car Washing Services");
			driver.findElement(searchButton).click();
			reportPass("Search Box is Clicked");
			takeScreenshot();

		} catch (Exception e) {
			
			// Print if the Test Case Failed
			e.printStackTrace();
			reportFail("Search Box is not clicked" + e.getMessage());
		}
	}

	
	// Check Title of Car Wash
	public void carWashTitleCheck() {
		try {
			logger = report.createTest("Car Wash -Verify title");
			String title = driver.getTitle();
			
			if (title.contains("Top Car Washing Services near Mumbai - Best Car Washing Centre - Justdial")) {
				// Print if the Test Case Passed
				reportInfo("Title matched for the Car Wash Services Page");
			} else {
				reportInfo("Title not matched for the Car Wash Services Page");
			}
		} catch (Exception e) {
			// Print if the Test Case Failed
			e.printStackTrace();
		}
	}

	
	 public void HighestRating()
	    {
	    	try {
	    		
			//   driver.findElement(By.cssSelector("#__next > div > section > div > div > div.jsx-d2567e42169d6c8d.bestdeal_right > div")).click();
			   
			    
				}catch(NoSuchElementException e)
				{
					
				}
				try {
					logger = report.createTest("Rating Drop down menu is clicked");
					 driver.findElement(By.xpath("//*[@id=\"filter_ul\"]/li[2]")).click();
				reportPass("Rating drop down menu is working");
				}catch(NoSuchElementException e)
				{
					reportFail("Rating drop down menu is not working");
				}
				try {
				logger = report.createTest("Rating set to Highest to Lowest");
				driver.findElement(By.cssSelector(".fltrsub_item:nth-child(1) .animtext")).click();
				reportPass("Highest to Lowest rating is working");
				}catch(Exception e)
				{
					reportFail("Highest to Lowest rating is not working");
				}
	    }
	// Print all the required details
	public void displayDetails() {
		try {
			logger = report.createTest("Car Wash -Print Shop names filtered by more then 4 ratings and 20 votes");
			  Thread.sleep(10000);
			// driver.findElement(By.cssSelector("#__next > div > section > div > div > div.jsx-d2567e42169d6c8d.bestdeal_right > div")).click();
			List<WebElement> shopName = driver.findElements(shopNameXpath);
			List<WebElement> star = driver.findElements(starXpath);
			List<WebElement> vote = driver.findElements(voteXpath);
			List<WebElement> PhNobutton = driver.findElements(By.className("button_flare"));
			
			
			for (int i = 0; i < shopName.size(); i++) {
			
			
				
				float ratings = Float.parseFloat(star.get(i).getText());
				String number = vote.get(i).getText().split(" ")[0];
				int votes = Integer.parseInt(number);
				if (!PhNobutton.isEmpty()) {
				    // Access the first matching element
				    WebElement element = PhNobutton.get(i);
				    element.click();
				}
				java.util.List<WebElement> verifiedElement= driver.findElements(By.className("callcontent callNowAnchor"));
				if (!verifiedElement.isEmpty()) {
					WebElement element=driver.findElement(By.className("callcontent callNowAnchor"));
					 Phno = element.getText();
					
					
				} else {
				  
					WebElement element=  driver.findElement(By.className("callcontent"));
					 Phno = element.getText();
				}
				if (count < 5) {
					
					
					if (ratings > 4.0 && votes > 20) {
						
						
						reportInfo(i + 1 + ". " + shopName.get(i).getText() + "||" + ratings + "||"
								+ vote.get(i).getText()+"||"+Phno);
						System.out.println(shopName.get(i).getText() + Phno);
						
						count++;
					
				}
			}
				if (i == 5) {
			        break; // break out of the loop when i is equal to 5
			    }
		
			}
			// Print if the Test Case Passed
			reportPass("Car Wash Service shop name has been displayed successfully");
			
		} catch (Exception e) {
			// Print if the Test Case Failed
			e.printStackTrace();
			reportFail("Car Wash Service shop names not displayed");
		}
	}
}
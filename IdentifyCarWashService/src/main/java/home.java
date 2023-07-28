import baseClass.BaseUI;
import pageClass.CarWashPage;
import pageClass.FreeListingPage;
import pageClass.GymPage;

public class home extends BaseUI {
 public static void main(String[] args) throws Exception {
	  
	BaseUI base = new BaseUI();
	//base.ChooseDestination();
	//base.reportinitialize("HackathonReport");
	base.invokeBrowser();
	//base.openURL();
	System.out.print(BaseUI.report);
	

	CarWashPage carwash = new CarWashPage();
	carwash.location();
	carwash.autoCarMenu();
	carwash.carWashTitleCheck();
	carwash.HighestRating();
	carwash.displayDetails();
	
	FreeListingPage freelist = new FreeListingPage();
	freelist.freeListing();
	freelist.verifyTitle();
	freelist.reader();
	//freelist.registration(null, null, null, null, null);
	GymPage gymPage= new GymPage(driver,BaseUI.report);
	gymPage.fitness();
	gymPage.fitnessMenu();
	gymPage.gym();
	gymPage.gymMenu();
	
    base.tearDown();
	base.closeBrowser();
	
	     
}
}

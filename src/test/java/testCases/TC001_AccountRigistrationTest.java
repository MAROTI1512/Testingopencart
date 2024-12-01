package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.AccountRigisterPage;
import pageObjects.HomePage;

public class TC001_AccountRigistrationTest extends BaseClass
{
	
	 @Test(groups={"Regression","Master"})
      public void verify_account_rigistration()
      {
		 logger.info("*****starting TC001_AccountRigistrationTest******");
		 try
		 {
		 HomePage hp = new HomePage(driver);
		 hp.clickMyAccount();
		 logger.info("clicked on myAccount link");
		 hp.clickRigister();
		 logger.info("clicked on mregister link");

		 
		 AccountRigisterPage  regPage =new AccountRigisterPage (driver);
		 logger.info("providing customer details");

		 regPage.setname(randamString().toUpperCase());
		 regPage.setname(randamString().toUpperCase());
		  regPage.teli(randamNumric());
		 regPage.setEmail(randamString()+"@gmail.com");
		 
		 String password = randamAlphaNumric();
		 regPage.SetPassword(password);
		 regPage.confrm(password);
		 regPage.setprivacy();
		 regPage.clickbutton();
		 
		 logger.info("validating expected massege");

		String conf= regPage.setconfirmsg();
		 Assert.assertEquals(conf, "Register Account");
		 }
		 catch(Exception e)
		 {
			 
			 Assert.fail();
		  }
		 logger.info("*****finished TC001_AccountRigistrationTest******");

      }
	

}

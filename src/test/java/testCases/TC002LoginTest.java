package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002LoginTest extends  BaseClass
{
	@Test(groups={"Sanity","Master"})
 public void Verify_login()
 {
		logger.info("***Starting Tc002_Loging Test");
		try
		{
		// home page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.loginTest2();
		
		
		// loging page
		LoginPage lp = new LoginPage(driver);
		lp.SetEmail(p.getProperty("email"));
		lp.setPwd(p.getProperty("password"));
		lp.clickLoging();
		
		//My account 
		MyAccountPage macc = new MyAccountPage(driver);
	boolean targetpage = macc.isMyAccountPageExists();
		
//	Assert.assertEquals(targetpage,true , "login failed");	
		
	Assert.assertTrue(targetpage);	
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	logger.info("***Finished Tc002_Loging Test");
		
 }
}

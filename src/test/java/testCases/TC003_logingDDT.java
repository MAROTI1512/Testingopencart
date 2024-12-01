package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProvider;

/* Data is valid ---loging success --test pass -->logout
   data is valid -->loging failed --->fail
   
   Data is invalid ---loging success --test fail -->logout
  data is invalid -->loging failed --->pass   */
public class TC003_logingDDT extends BaseClass
{
	
	@Test(dataProvider="LogingData",dataProviderClass=DataProvider.class,groups="Datadriven")//data proovider from differnt class	
   public void verify_LogingDDT(String email ,String pwd,String exp) throws InterruptedException
   {
		logger.info("***Starting TC_003logingDDT Test");
	
		try
		{
		// home page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.loginTest2();
			
			
			// loging page
			LoginPage lp = new LoginPage(driver);
			lp.SetEmail(email);
			lp.setPwd(pwd);
			lp.clickLoging();
			
			//My account 
			MyAccountPage macc = new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountPageExists();
			
       if (exp.equalsIgnoreCase("Valid"))
       {
    	   if(targetpage==true)
    	   {
    		   macc.clicklogout();
    		   Assert.assertTrue(true);    		 
    	   }
       }
       else
       {
    	   Assert.assertTrue(false);
       } 
       
       
       if(exp.equalsIgnoreCase("Invalid"))
       {
    	   if(targetpage==true)
    	   {
    		   macc.clicklogout();
    		   Assert.assertTrue(false);   		   
    	   }  	   
       }
       else
       {
    	   Assert.assertTrue(true); 
       }
       
	}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***Starting TC_003logingDDT Test");
     Thread.sleep(500);
   }
}

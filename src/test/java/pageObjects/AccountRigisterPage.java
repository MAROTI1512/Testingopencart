package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRigisterPage  extends BasePage

  {
	public AccountRigisterPage(WebDriver driver) {
		super(driver);		
    }   

    @FindBy(xpath="//input[@id='input-firstname']") 
	 WebElement txtfirstName;
    
   @FindBy(xpath="//input[@id='input-lastname']") 
     WebElement txtlastName;

   @FindBy(xpath="//input[@id='input-email']") 
     WebElement txtEmail;
   
   @FindBy(xpath="//input[@value='Continue']")
   WebElement telphone;
   
   @FindBy(xpath="//input[@id='input-password']")
      WebElement txtpassword;
   
   @FindBy(xpath="//input[@value='Continue']") 
      WebElement btncontinues;
   
   @FindBy(xpath="//input[@id='input-confirm']")
   WebElement conform;
   
   @FindBy(xpath="//input[@name='agree']")
   WebElement Chhpolicy;
   
   @FindBy(xpath="//h1[normalize-space()='Register Account']") 
      WebElement msgconfirmation;


        public void setname(String fname)
        {
	    txtfirstName.sendKeys(fname);
        }
        public void setlastN(String LName)
        {
	    txtlastName.sendKeys(LName);
        }
        public void setEmail( String email)
        {
    	 txtEmail.sendKeys(email);
       }
        public void SetPassword(String pwd)
        {
        	txtpassword.sendKeys(pwd);
        }
        public void confrm(String pwd)
        {
        	conform.sendKeys(pwd);
        }
        public void setprivacy()
        {
        	Chhpolicy.click();
        }
        public void teli(String tel)
        {
        	telphone.sendKeys(tel);
        }
        public void clickbutton()
        {
        	btncontinues.click();
        }
        public String setconfirmsg()
        {
        	try 
        	{
        		return (msgconfirmation.getText());
			} catch (Exception e) 
        	{
				return(e.getMessage());
			}
			
			
        }
        
        
}

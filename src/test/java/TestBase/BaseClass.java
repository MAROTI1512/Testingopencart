package TestBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass
{
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	 @BeforeClass(groups={"Sanity","Regression","Master"})
	 @Parameters({"os","browser"})
    
     public void setUp(String os ,String br) throws IOException
     {
		 // loading config file 
		 FileReader file =new FileReader("./src//test//resources//config.propertis");
		 p=new Properties();
		 p.load(file);
		 
		 
		logger=(Logger) LogManager.getLogger(getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities Capabilities= new DesiredCapabilities();
		
			// os
			if(os.equalsIgnoreCase("window"))
			{
				 Capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				Capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("no matching os");
				return;
			}
			
			// browser
			
			switch (br.toLowerCase())
			{
			case "chrome" :Capabilities.setBrowserName("chrome");break;
			case "edge" :Capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox" :Capabilities.setBrowserName("firefox");break;

			default :System.out.println("Invalid browser name");return;
			
			}
			driver= new  RemoteWebDriver(new URL ("http://localhost:4444/wd/hub"),Capabilities);
		}
		
		// local execution 
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch (br.toLowerCase())
			{
			case "chrome" :driver=new ChromeDriver();break;
			case "edge" :driver=new EdgeDriver();break;
			default :System.out.println("Invalid browser name");return;
			
			}
		}		
		 
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		 
		 driver.get(p.getProperty("appURl")); // reading url from properties file
		 driver.manage().window().maximize(); 	   
     }
	 @AfterClass(groups={"Sanity","Regression","Master"})
     public void tearDown()
     {
		 driver.quit();  	   
     }
	 
	 public String randamString()
	 {
		String genertedString = RandomStringUtils.randomAlphabetic(5);
		 return genertedString;
	 }
	 public String randamNumric()
	 {
		String genertednumber = RandomStringUtils.randomNumeric(10);
		 return genertednumber;
	 }
	 public String randamAlphaNumric()
	 {
		String genertedSnumber = RandomStringUtils.randomAlphanumeric(10);
		String genertednumber = RandomStringUtils.randomNumeric(10);
		 return (genertednumber+"@"+genertedSnumber);
	 }
	 public String captureScreen (String tname)
	 {
		  String timeStamp =new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new java.util.Date());
          
		  TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
		  File sourceFile= takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		 String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname +"_" + timeStamp +".png" ;    
		 File targetFile= new File (targetFilePath);
		 
		 sourceFile.renameTo(targetFile);
		  return targetFilePath;
		 
	 }
}

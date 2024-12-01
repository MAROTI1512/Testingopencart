package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class  ExtentReportManager  implements ITestListener
{
	public ExtentSparkReporter sparkreporter;
	  public ExtentReports extent;
	  public ExtentTest test;
	  String repName;
	  public void onStart(ITestContext testContext)
	  {
		 /* SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		  Date dt = new Date();
		  String currendatetimestamp=df.format(dt);
		 */
		  
		  String timeStamp =new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss ").format(new java.util.Date());
		  repName= "Test-Report"+timeStamp+ ".html";	
		  sparkreporter = new ExtentSparkReporter(".\\reports\\"+ repName);
		  
		  sparkreporter.config().setDocumentTitle("Opencart Automation report");
		  sparkreporter.config().setReportName("opencart Functional Testing");
		  sparkreporter.config().setTheme(Theme.DARK);
		  
		  extent = new ExtentReports();
		  extent.attachReporter(sparkreporter);
	  
		  extent.setSystemInfo("Application", " opencart");
		  extent.setSystemInfo("Module", "Admin");
		  extent.setSystemInfo("sub Module", "Customers");
		  extent.setSystemInfo("Tester Name", "Maroti Bhosle");
		  
		  extent.setSystemInfo("user Name",System.getProperty("user.name"));
		  extent.setSystemInfo("Environement", "QA");
		  
		  String os= testContext.getCurrentXmlTest() .getParameter("os");   
	      extent.setSystemInfo("operating System", os);
	    
	      String browser= testContext.getCurrentXmlTest() .getParameter("browser");   
	      extent.setSystemInfo("operating System", browser);
	    
	      List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups(); 
	      if (includedGroups.isEmpty()) 
	      {
		  extent.setSystemInfo("Groups", includedGroups.toString());
	      } 
	  }
	  public void onTestSuccess(ITestResult result)
	  {
		  test=extent.createTest(result.getTestClass().getName());
		  test.assignCategory(result.getMethod().getGroups());
		  test.log(Status.PASS,result.getName()+ "got successfully executed ");
		  
	  }
	  public void onTestFailure(ITestResult result) 
	  {
		  test=extent.createTest(result.getTestClass().getName());
		  test.assignCategory(result.getMethod().getGroups());		  
		 
		  test.log(Status.FAIL, result.getName()+"got faild");
		  test.log(Status.INFO,result.getThrowable().getMessage());
		  try 
		  {
			  String  imgPath= new BaseClass().captureScreen(result.getName());
			  test.addScreenCaptureFromPath(imgPath);
		  }
	      catch(Exception e)
		  {
	    	  e.printStackTrace();
		  }
	  }
	  public void onTestSkipped(ITestResult result)
	  {
		  test=extent.createTest(result.getTestClass().getName());
		  test.assignCategory(result.getMethod().getGroups());		  
		 
		  test.log(Status.SKIP, result.getName()+"got skipped");
		  test.log(Status.INFO,result.getThrowable().getMessage());
		  }
	  public void onFinish(ITestContext context)
	  {
		  extent.flush();
	  String pathOfExtentReport =System.getProperty("user.dir")+"\\reports\\"+repName;
	  File extentReport = new File(pathOfExtentReport);
	    
	  try
	  {
		  Desktop.getDesktop().browse(extentReport.toURI());
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  }
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
}
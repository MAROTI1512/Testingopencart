package utilities;

import java.io.IOException;

public class DataProvider 
{
//DataProvider 1
	@org.testng.annotations.DataProvider(name="LogingData")

	public String [][] getData() throws IOException
	{
		
		String path=".\\testData\\openCartData.xlsx";
		
		ExcelUtilility xlutil=new ExcelUtilility(path);
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		
		String logingdata[][]=new String[totalrows][totalcols];      
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logingdata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}		
		return logingdata;	
	}	
}

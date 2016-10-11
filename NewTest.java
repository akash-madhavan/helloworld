package com.com.fb;

import org.testng.annotations.Test;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import jxl.Sheet;
import jxl.Workbook;
public class NewTest
{
	WebDriver driver;
	Workbook wb;
	Sheet sh1;
	int numrow;
	String username;
	String password;
	@BeforeTest
	public void Setup()
	{
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}
	@Test(dataProvider="testdata")
	public void TestFireFox(String uname,String password1)
	{
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(uname);
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(password1);
	}
	@DataProvider(name="testdata")
	public Object[][] TestDataFeed()
	{
		try
		{
			wb=Workbook.getWorkbook(new File("C:"+File.separator+"Users"+File.separator+"AKASH"+File.separator+"Desktop"+File.separator+"fb.xlsx"));
			sh1= wb.getSheet(0);
			numrow=  sh1.getRows();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Object [][] facebookdata=new Object[numrow][sh1.getColumns()];
		for(int i=0;i<numrow;i++)
		{
			facebookdata[i][0]=sh1.getCell(0,i).getContents();
			facebookdata[i][1]=sh1.getCell(1,i).getContents();
		}
		return facebookdata;
	}
	@AfterTest
	public void QuitTC()
	{
		driver.quit();
	}
}
package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	//public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	
	/**
	 * This method is used for initialize the web driver for launching the browser.
	 * @param browser
	 * @return driver
	 */
	
	
	public WebDriver init_driver(Properties prop)
	{
		//optionsManager=new OptionsManager(prop);
		highlight=prop.getProperty("highlight");
		String browser=prop.getProperty("browser").toLowerCase().trim();
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver();
			tlDriver.set(new ChromeDriver());
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		}
		else if(browser.equalsIgnoreCase("safari"))
		{
			driver=new SafariDriver();
		}
		else
		{
			System.out.println("Please provide the correct Browser name");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();		
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	
	/**
	 * get the local copy of the driver
	 */
	public synchronized static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	/**
	 * This method is reading the properties from properties class
	 * @return
	 */
	
	public Properties initProp()
	{
		//mvn clean install -Denv="stage"
		
		prop=new Properties();
		FileInputStream ip=null;
		String envName=System.getProperty("env");
		System.out.println("Running test cases on Env--"+ envName);
		
		
		
		try
		{
			if(envName==null)
			{
				System.out.println("no env is passed..Running test  on QA env");
				 ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
			}
			else
			{
				switch(envName.toLowerCase().trim())
				{
					case "qa":	
					ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
					
					case "stage":	
						ip=new FileInputStream("./src/test/resources/config/staging.config.properties");
						break;
					
					case "prod":	
						ip=new FileInputStream("./src/test/resources/config/config.properties");
						break;
						
					default:
						System.out.println("No or wrong env is selected...Please select the correct environment");
						break;
				}
			}
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return prop;
		
	  }
	
	public static String getScreenshot()
	{
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}

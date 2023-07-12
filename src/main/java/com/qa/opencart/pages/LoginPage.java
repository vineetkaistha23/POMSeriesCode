package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;





public class LoginPage{

	
	
	//1. Declare Webdriver
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	//2. page constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgetPwdLink=By.linkText("Forgotten Password");
	private By registerLink=By.linkText("Register");
	private By logoImg=By.cssSelector("a .img-responsive");
	private By LoginPageLinks=By.cssSelector("div .list-group-item");
	
	
	@Step("getting login page title....")
	public String getLoginPageTitle()
	{
		String title=eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("The title of the page is--"+ title);
		return title;
		
	}
	
	
	@Step("getting login page url....")
	public String getCurrentURL()
	{
		String url=eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTIONVALUE);
		System.out.println("The url of the page is--"+ url);
		return url;
				
	}
	
	@Step("confiring forget password....")
	public boolean isForgetPwdLinkExists()
	{
		return eleUtil.waitForElementVisible(password, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	@Step("confiring register password....")
	public boolean isRegisterLinkExists()
	{
		return eleUtil.waitForElementVisible(registerLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	@Step("login with username:{0} and password:{1}")
	public AccountPage doLogin(String un, String pwd)
	{
		System.out.println("App creds "+ un+"--"+ pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountPage(driver);
	}
	
	public boolean isImgLogoExists()
	{
		System.out.println("Logo image");
		return eleUtil.waitForElementVisible(logoImg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
}

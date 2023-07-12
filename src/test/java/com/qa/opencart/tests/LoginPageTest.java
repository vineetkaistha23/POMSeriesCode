package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("EPIC-100: design login for open cart")
@Story("US-Login:101: design login features for open cart")
public class LoginPageTest  extends BaseTest{
	
	

	@Severity(SeverityLevel.TRIVIAL)	
	@Description("......Getting the title of the page......")
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String actualTitle=loginPage.getLoginPageTitle();
		System.out.println("The title of the page :- "+ actualTitle);
		Assert.assertEquals(actualTitle,Constants.LOGIN_PAGE_TITLE);
	}
	
	@Severity(SeverityLevel.TRIVIAL)	
	@Description("......Getting the URL of the page......")
	@Test(priority=2)
	public void loginPageURL()
	{
		String url=loginPage.getCurrentURL();
		System.out.println("The URL is --"+ url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	
	@Severity(SeverityLevel.TRIVIAL)	
	@Description("......Is forget password is available on the Login page......")
	@Test(priority=3)
	public void verifyForgetPasswordLink()
	{
		Assert.assertTrue(loginPage.isForgetPwdLinkExists());
	}
	
	@Test(priority=4)
	public void verifyRegisterLink()
	{
		Assert.assertTrue(loginPage.isRegisterLinkExists());
	}
	
	@Severity(SeverityLevel.CRITICAL)	
	@Description("......User able to do login successfully with the provided username and password......")
	
	@Test(priority=6)
	public void loginTest()
	{
		accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogOutLinkExists());
	}
	
	@Test(priority=5)
	public void verifyLogoImage()
	{
		Assert.assertTrue(loginPage.isImgLogoExists());
	}
	
	
	
	

}

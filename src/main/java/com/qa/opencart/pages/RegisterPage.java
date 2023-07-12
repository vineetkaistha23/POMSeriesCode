package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	private By input_first_name=By.id("input-firstname");
	private By input_last_name=By.id("input-lastname");
	private By input_email=By.id("input-email");
	private By input_telephone=By.id("input-telephone");
	private By input_password=By.id("input-password");
	private By input_confirm_password=By.id("input-confirm");
	private By radio_subscribeYes=By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By radio_subscribeNo=By.xpath("//label[normalize-space()='No']/input[@type='radio']");
	private By chkbox_agree=By.xpath("//input[@type='checkbox' and @name='agree']");
	private By btn_continue=By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By registerSuccessMsg=By.tagName("h1");
	//
	
	private By regLogout=By.linkText("Logout");
	private By regLink=By.linkText("Register");
	
	
	public boolean registerUser(String firnstName, String lastName, String email, String phoneNum,
			String password,String subscribe) {
		
		eleUtil.waitForElementVisible(this.input_first_name, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firnstName);
		eleUtil.doSendKeys(this.input_last_name, lastName);
		eleUtil.doSendKeys(this.input_email, email);
		eleUtil.doSendKeys(this.input_telephone, phoneNum);
		eleUtil.doSendKeys(this.input_password, password);
		eleUtil.doSendKeys(this.input_confirm_password, password);
		
		if(subscribe.equalsIgnoreCase("yes"))
		{
			eleUtil.doClick(radio_subscribeYes);
		}
		else
		{
			eleUtil.doClick(radio_subscribeNo);
		}
		
		eleUtil.doClick(chkbox_agree);
		eleUtil.doClick(btn_continue);
		
		String regSuccesMsg=eleUtil.waitForElementVisible(registerSuccessMsg, AppConstants.DEFAULT_LONG_TIME_OUT).getText();
		
		if(regSuccesMsg.contains(AppConstants.USER_REG_SUCCESS_MSG))
		{
			
			eleUtil.doClick(regLogout);
			eleUtil.doClick(regLink);
			return true;
		}
		return false;
		
		
	}
	
	
	
	
	
	

}

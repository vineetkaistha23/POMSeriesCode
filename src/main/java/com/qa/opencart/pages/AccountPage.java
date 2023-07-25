package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By passwordLink=By.linkText("Logout");
	private By accsHeader=By.cssSelector("div #content h2");
	private By search=By.name("search");
	private By searchIcon=By.cssSelector("#search button");
	
	
	public AccountPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		String title=eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.ACCOUNTPAGE_PAGE_TITLE_VALUE);
		System.out.println("The title of the page is that--"+ title);
		return title;
		
	}
	
	public String getAccPageURL() {
		String url=eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.ACCOUNTPAGE_PAGE_URL_FRACTIONVALUE);
		System.out.println("The url of the page is--"+ url);
		return url;
			}
	
	
	 public boolean isLogOutLinkExists()
	 {
		 return eleUtil.waitForElementVisible(passwordLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	 }

	 public boolean isSearchExists()
	 {
		 return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	 }
	 
	 public List<String> getAccountPageHeaderList()
	 {
		 List<WebElement> accHeadersList=eleUtil.waitForElementsVisible(accsHeader, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		 List<String> accHeaderValList=new ArrayList<String>();
		 
		 for(WebElement e: accHeadersList)
		 {
			String text=e.getText();
			System.out.println(text);
			accHeaderValList.add(text);		
			
		 }
		 return accHeaderValList;
	 }
	 
	 public SearchPage performSearchKey(String searchproductInfo)
	 {
		 if(isSearchExists())
		 {
			 eleUtil.doSendKeys(search, searchproductInfo);
			 eleUtil.doClick(searchIcon);
			 return new SearchPage(driver);
			 
		 }
		 else
		 {
			 System.out.println("Search field is not present on the page");
		 }
		return null;
		 
	 }

}

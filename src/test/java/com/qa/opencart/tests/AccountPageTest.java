package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.base.BaseTest;

public class AccountPageTest extends BaseTest
{
	
	@BeforeClass
	public void accPageSetup()
	{
		accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	
	@Test(priority=1)
	public void accPageTitleTest()
	{
		String title=accPage.getAccPageTitle();
		String accPageTitle=AppConstants.ACCOUNTPAGE_PAGE_TITLE_VALUE.trim();
		Assert.assertEquals(title, accPageTitle);
	}
	
	@Test(priority=2)
	public void accPageURLTest()
	{
		String actualURL=accPage.getAccPageURL();
		System.out.println("The Account page url==="+actualURL);
		Assert.assertTrue(actualURL.contains(AppConstants.ACCOUNTPAGE_PAGE_URL_FRACTIONVALUE));
	}
	
	@Test(priority=3)
	public void isLogoutLinkExistsTest()
	{
		Assert.assertTrue(accPage.isLogOutLinkExists());
	}
	
	@Test(priority=4)
	public void accPageHeadersCountTest()
	{
		List<String> accAccountPageHeaderList=accPage.getAccountPageHeaderList();
		System.out.println("Acc page header list"+ accAccountPageHeaderList);
		Assert.assertEquals(accAccountPageHeaderList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"},
			{"vineet"}
			
		};
		
	}
	
	
	@Test(priority=5)
	public void accPageHeaderValueTest()
	{
		List<String> accAccountPageHeaderList=accPage.getAccountPageHeaderList();
		System.out.println("Actual Acc page header list"+ accAccountPageHeaderList);
		System.out.println("Expected   page header list found"+ AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(accAccountPageHeaderList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST); 
	}
	
	
	@Test(dataProvider = "getProductData", priority=6)
	public void searchProductCountList(String searchKey)
	{
		searchPage=accPage.performSearchKey(searchKey);
		Assert.assertTrue(searchPage.searchProductsCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductTestData(){
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"}
			
		};
		
	}
	
	
	
	@Test(dataProvider="getProductTestData",priority=7)
	public void searchProductTest(String searchKey, String productName)
	{
		searchPage=accPage.performSearchKey(searchKey);
		if(searchPage.searchProductsCount()>0)
		{
			productInfoPage=searchPage.selectProduct(productName);
			String actProductHeader=productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProductHeader, productName);
			System.out.println("Product found on the search Page");
		}
		else
		{
			System.out.println("Product not found on search page");
		}
	}
}

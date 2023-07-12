package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;

public class ProductPageInfoTest extends BaseTest
{

	@BeforeClass
	public void productInfoPageSetup()
	{
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider 
	public Object[][] getProductImagesCount()
	{
		return new Object[][] {
			{"Macbook", "MacBook Pro",4},
			{"iMac", "iMac",3},
			{"Apple", "Apple Cinema 30\"",6},
			{"Samsung", "Samsung SyncMaster 941BW",1}
			
		};
				
	}
	
	
	@Test(dataProvider= "getProductImagesCount")
	public void verifyProductImageCount(String searchkey, String productName, int imageCount)
	{
		searchPage=accPage.performSearchKey(searchkey);
		productInfoPage=searchPage.selectProduct(productName);
		int actImageCount=productInfoPage.getProductImagesCount();
		String actualTitle=productInfoPage.getProductPageTittle(productName);
		Assert.assertEquals(actImageCount, imageCount);
		
		
	}
	
	@Test
	public void productInformationTest()
	{
		searchPage=accPage.performSearchKey("MacBook");
		productInfoPage=searchPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap=productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(actProductInfoMap.get("productname"),"MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("productprice"),"$2,000.00");
		softAssert.assertAll();
	}
	
	@DataProvider
	public Object[][] getSuccessMsg(){
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"iMac", "iMac"}
			
		};
	}
	
	@Test(dataProvider="getSuccessMsg")
	public void addToCart(String productKey, String productName)
	{
		searchPage=accPage.performSearchKey(productKey);
		productInfoPage=searchPage.selectProduct(productName);
		productInfoPage.enterQuantity(2);
		String cartSuccessMsg=productInfoPage.addProductToCart();
		softAssert.assertTrue(cartSuccessMsg.indexOf("Success")>=0);
		softAssert.assertTrue(cartSuccessMsg.indexOf(productName)>=0);
		//Success: You have added MacBook Air to your shopping cart!
		//softAssert.assertNotEquals(cartSuccessMsg, "You have added "+productKey+" to your shopping cart!");
		softAssert.assertAll();
		
		
	}
	
	
	
	
	
		
		
	
	
	

	
	
	
	
	
	
}

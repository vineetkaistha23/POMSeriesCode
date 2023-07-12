package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By searchProductResults=By.cssSelector("div#content div.product-layout");

	public SearchPage(WebDriver driver) 
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public int searchProductsCount()
	{
		int productCount=eleUtil.waitForElementsVisible(searchProductResults, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Product Count--"+productCount);
		return productCount;
	}
	
	
	public ProductInfoPage selectProduct(String productName)
	{
		By productLocator=By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
		
		
	}

}

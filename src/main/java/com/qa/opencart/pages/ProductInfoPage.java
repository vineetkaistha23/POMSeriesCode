package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v109.systeminfo.SystemInfo;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage 
{
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader=By.tagName("h1");
	private By productPrice=By.cssSelector("ul.list-unstyled h2");
	private By totalProductImageCount=By.cssSelector("ul.thumbnails a");
	private By addToCartLink=By.id("button-cart");
	private By productDescription=By.cssSelector("div #tab-description p");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By inputQuantity=By.id("input-quantity");
	private By btnAddToCart=By.id("button-cart");
	private By cartSuccessMsg=By.cssSelector("div .alert.alert-success");
	private By btnTotalCost=By.cssSelector("span#cart-total");
	private By totalProductCost=By.xpath("//table[@class='table table-bordered']/tbody/tr[4]/td[2]");
	
	
	private Map<String,String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getProductHeaderValue()
	{
		String productHeaderVal=eleUtil.doElementGetText(productHeader);
		System.out.println("product header"+ productHeader);
		return productHeaderVal;
		
	} 
	
	public void enterQuantity(int qty)
	{
		System.out.println("The quantity--"+qty);
		eleUtil.doSendKeys(inputQuantity, String.valueOf(qty));
	}
	
	public String addProductToCart()
	{
		eleUtil.doClick(btnAddToCart);
		String successMsg=eleUtil.waitForElementVisible(cartSuccessMsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		StringBuilder sb=new StringBuilder(successMsg);
		String msg=sb.substring(0, sb.length()-1).replace("\n", "");
		System.out.println("The successMsg is--"+ msg);
		return msg;

	}
	
	public Map<String, String> getProductInfo()
	{
		productInfoMap=new HashMap<String, String>();
		//productInfoMap=new LinkedHashMap<String, String>();
		productInfoMap=new TreeMap<String, String>();
		//header 
		productInfoMap.put("productname", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productInfoMap);
		return productInfoMap;
		
	}
	
	
	private void getProductMetaData()
	{
		//metalist
		List<WebElement> metaList=eleUtil.getElements(productMetaData);
		for(WebElement e: metaList)
		{
			String meta =e.getText();
			String metaInfo[]=meta.split(":");
			String key=metaInfo[0].trim();
			String value=metaInfo[1].trim();
			productInfoMap.put(key, value);
			
		}
	}
	
	private void getProductPriceData()
	{
		//price
		List<WebElement> priceList=eleUtil.getElements(productPriceData);
		String price=priceList.get(0).getText();
		String exTax=priceList.get(1).getText();
		String extraTaxVal=exTax.split(":")[1].trim();		
		productInfoMap.put("productprice", price);
		productInfoMap.put(exTax, extraTaxVal);
	}
	
	public String getPrice()
	{
		String productPriceVal=eleUtil.getElement(productPrice).getText();
		System.out.println("product price"+ productPrice);
		return productPriceVal;
		
	}
	
	public String getTotalPriceOfProduct()
	{
		eleUtil.doClick(btnAddToCart);
		eleUtil.doClick(btnTotalCost);
		String totalProCost=eleUtil.waitForElementVisible(totalProductCost, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		System.out.println("Total price is--"+ totalProCost);
		return totalProCost;
		
	}
	
	
	public int getProductImagesCount()
	{
		int totalImage=eleUtil.waitForElementsVisible(totalProductImageCount, AppConstants.DEFAULT_LONG_TIME_OUT).size();
		
		if(totalImage>0)
		{
			System.out.println("Total image count is----"+totalImage);
		}
		else
		{
			System.out.println("No image is found on product page");
		}
		return totalImage;
	}
	
	public boolean isAddToCartLinkExists()
	{
		return eleUtil.waitForElementVisible(addToCartLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public String getProductPageTittle(String actTitle)
	{
		String title=eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT,actTitle);
		return title;
	}
	
	public boolean isProductDescriptionAvailable()
	{
		return eleUtil.waitForElementVisible(productDescription, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}

}

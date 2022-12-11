package com.ibm.fst1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05_SauceLabDemo extends CommonMethods{
	
		
	@Test
	public void verifySauceLabDemo() {
		navigateToAppURL("https://www.saucedemo.com/inventory.html");
		Assert.assertEquals("Swag Labs", driver.getTitle(),"Verify Page Title");
		login("standard_user","secret_sauce");
		verifyCartFunctionality();
		driver.findElement(By.id("continue-shopping")).click();
		if(verifyPricesAreSorted())
			System.out.println("prices are sorted from low to high");
		else
			System.out.println("prices are not sorted from low to high");
	}

	private boolean verifyPricesAreSorted() {
		boolean sorted =true;
		new Select(driver.findElement(By.className("product_sort_container"))).selectByVisibleText("Price (low to high)");
		double[] prices = new double[6];
		List<WebElement> wblPrices = driver.findElements(By.className("inventory_item_price"));
		System.out.print("PRICES : ");
		for(int i=0;i<6;i++)
		{
			prices[i] = Double.valueOf(wblPrices.get(i).getText().replace("$", ""));
			if(i!=0 && prices[i]<prices[i-1])
				sorted = false;
			System.out.println( prices[i] + " , ");
		}
		
		return sorted;
		
	}

	private void verifyCartFunctionality() {
		List<WebElement> wblAddToCart = driver.findElements(By.xpath("//button[text()='Add to cart']"));
		wblAddToCart.get(0).click();
		WebElement cartCnt = driver.findElement(By.xpath("//div[@id='shopping_cart_container']//span"));
		if(cartCnt.getText().contentEquals("1"))
			System.out.println("After adding an item to cart,Cart Count is 1 as Expected");
		else
			System.out.println("After adding an item to cart,Cart Count is Not 1 as Expected");
		
		wblAddToCart.get(wblAddToCart.size()-1).click();
		if(cartCnt.getText().contentEquals("2"))
			System.out.println("After adding an item to cart,Cart Count is increased as Expected");
		else
			System.out.println("After adding an item to cart,Cart Count is Not increased as Expected");
		
		driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack'][1]")).click();
		if(cartCnt.getText().contentEquals("1"))
			System.out.println("After Removing product from cart,Cart Count is 1 as Expected");
		else
			System.out.println("After Removing product from cart,Cart Count is Not 1 as Expected");
		
		String expecItemInCart = driver.findElement(By.id("item_3_title_link")).getText();
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();	
		String actualItemInCart = driver.findElement(By.className("inventory_item_name")).getText();
		textComparison(expecItemInCart,actualItemInCart ,"Verify Item Name In Cart");
	}

	private void login(String uname, String pwd) {
		WebElement btnLogin = driver.findElement(By.id("login-button"));		
		driver.findElement(By.id("user-name")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pwd);
		btnLogin.click();
	}

}

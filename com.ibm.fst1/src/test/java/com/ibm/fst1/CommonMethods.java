package com.ibm.fst1;

import org.openqa.selenium.WebElement;

public class CommonMethods extends MainClass{
	
	public void elementPresent(WebElement wbl,String wblName) {
		if(wbl.isDisplayed())
			System.out.println(wblName + " Element Is Displayed");
		else
			System.out.println(wblName + " Element Is Not Displayed");
	}

	public void navigateToAppURL(String url) {
		driver.get(url);
		System.out.println("Navigated to URL :" + url);
	}
	
	public void textComparison(String txt1,String txt2,String msg) {
		System.out.print(msg + ":");
		if(txt1.contentEquals(txt2))
			System.out.println("True - " + txt1 + " & " + txt2);
		else
			System.out.println("False - " + txt1 + " & " + txt2);
	}
}

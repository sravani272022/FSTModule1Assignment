package com.ibm.fst1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TC04_ComputerDatabase extends CommonMethods{

	WebElement wblSrchBox ;
	WebElement wblAddCmpBtn;
	WebElement wblFltrNameBtn;


	@Test
	public void cmpDbsDemo() throws InterruptedException {
		String compName = "Nokia";
		navigateToAppURL("https://computer-database.gatling.io/computers");	
		verifyPageElements();
		addNewComputer(compName,"Nokia");
		if(searchComputer(compName))
			System.out.println(compName + " Found");
		else
			System.out.println(compName + " Not Found");
	}

	private boolean searchComputer(String compName) throws InterruptedException {
		driver.findElement(By.id("searchbox")).sendKeys(compName);
		driver.findElement(By.id("searchsubmit")).click();
		Thread.sleep(4000);
		List<WebElement> wblSearchTblVal = driver.findElements(By.xpath("//table//tbody/tr[1]//a"));
		for(int i=0;i<wblSearchTblVal.size();i++)
			if(wblSearchTblVal.get(i).getText().equals(compName)) 
				return true;
				
		return false;
				
	}

	private void addNewComputer(String compName, String companyName) {
		wblAddCmpBtn.click();
		driver.findElement(By.id("name")).sendKeys(compName);
		Select lstCompany = new Select(driver.findElement(By.id("Company")));
		lstCompany.selectByVisibleText(companyName);
		driver.findElement(By.xpath("//input[@value='Create this computer']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,5000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='alert-message warning']"))));
		System.out.println("New Computer " + compName + " Added Successfully");
	}

	private void verifyPageElements() {
		//Verify the page header is the same as the page title
		WebElement wblPgHeader = driver.findElement(By.xpath("//h1/a"));
		textComparison(driver.getTitle(), wblPgHeader.getText(), "Verify Page Title and Page Header are Same");

		//User must see the filter by computer name text box
		wblSrchBox = driver.findElement(By.id("searchbox"));
		elementPresent(wblSrchBox,"Filter By Computer Name TextBox");

		//User must see the Add a new computer button
		wblAddCmpBtn = driver.findElement(By.id("add"));
		elementPresent(wblAddCmpBtn,"Add a New Computer Button");

		//User must see the Filter By Name button
		wblFltrNameBtn = driver.findElement(By.id("searchsubmit"));
		elementPresent(wblFltrNameBtn,"Filter By Name Button");

		//verify table headers
		verifyTableHeaders(new String[]{"Computer name","Introduced","Discontinued","Company"});		

		//verify pagination is present
		elementPresent(driver.findElement(By.id("pagination")),"Pagination");
	}

	private void verifyTableHeaders(String[] expecTableHeaders) {
		List<WebElement> wblSearchTblHdr = driver.findElements(By.xpath("//table//th/a"));
		String[] actualTableHeaders = new String[4];
		for(int i=0;i<wblSearchTblHdr.size();i++)
			actualTableHeaders[i]=wblSearchTblHdr.get(i).getText();
		System.out.println("Expected Header Names : " + expecTableHeaders.toString());
		System.out.println("Actual Header Names : " + actualTableHeaders.toString());
		if(expecTableHeaders.equals(actualTableHeaders))
			System.out.println("Expected Table Headers Are Showing");
		else
			System.out.println("Expected Table Headers Are Not Showing");
	}
}

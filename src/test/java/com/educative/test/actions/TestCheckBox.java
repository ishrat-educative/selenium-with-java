package com.educative.test.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.educative.BaseTest;
import com.educative.webdriver.DriverManager;

public class TestCheckBox extends BaseTest {

	@Test
	public void testCheckBox() {

		// Create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		// open the web url
		driver.get("http://codetoautomate.com/educative-selenium-demo/");

		// Create a Javascript executor object.
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(("Wait exception"));
			e.printStackTrace();
		}
		
		// Scroll down to the element
		jsDriver.executeScript("document.getElementById('checked').scrollIntoViewIfNeeded()");

		

		// find the check-box element
		WebElement checked = driver.findElement(By.id("checked"));

		// Assert that if it's already selected
		Assert.assertFalse(checked.isSelected());

		// find the element to avoid any Stale element exception before performing
		// any new operation
		checked = driver.findElement(By.id("checked"));
		checked.click();

		// find the element to avoid any Stale element exception before performing
		// any new operation
		checked = driver.findElement(By.id("checked"));

		// Assert the selection
		Assert.assertTrue(checked.isSelected());
	}

}

package com.educative.test.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.educative.BaseTest;
import com.educative.webdriver.DriverManager;

public class TestDropDown extends BaseTest {

	@Test
	public void testDropDownSelect() {

		// Create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		driver.get("http://codetoautomate.com/educative-selenium-demo/");

		// Create a Javascript executor object.
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

		// Scroll down to the element
		jsDriver.executeScript("document.getElementById('my-select').scrollIntoViewIfNeeded()");

		// Identify the drop down - select element
		Select select = new Select(driver.findElement(By.id("my-select")));

		// select by value.
		String value = "Orange";
		select.selectByValue(value);

		// Click on the print selected option button
		WebElement button = driver.findElement(By.id("select-button"));
		button.click();

		// Verify the printed option == selected option
		WebElement result = driver.findElement(By.id("demo1"));
		Assert.assertEquals(result.getText().trim(), value);

		// Select by Index
		select.selectByIndex(2);

		// Select by Visible text
		select.selectByVisibleText("Banana");

	}

	@Test
	public void testDropDownDeSelect() {

		// Create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		driver.get("http://codetoautomate.com/educative-selenium-demo/");

		// Create a Javascript executor object.
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

		// Scroll down to the element
		jsDriver.executeScript("document.getElementById('my-select').scrollIntoViewIfNeeded()");

		// Identify the drop down - select element
		Select select = new Select(driver.findElement(By.id("my-select")));

		// select by value.
		String value = "Orange";
		select.selectByValue(value);
		select.deselectByValue(value);

		// Click on the print selected option button
		WebElement button = driver.findElement(By.id("select-button"));
		button.click();

		// Verify the printed option == empty
		WebElement result = driver.findElement(By.id("demo1"));
		Assert.assertTrue(result.getText().trim().isEmpty());

		// Select by Index
		select.selectByIndex(2);
		select.deselectByIndex(2);

		// Select by Visible text
		select.selectByVisibleText("Banana");
		select.deselectByVisibleText("Banana");

	}

}

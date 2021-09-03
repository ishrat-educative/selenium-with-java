package com.educative.test.actions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.educative.BaseTest;
import com.educative.webdriver.DriverManager;

public class TestAlert extends BaseTest {

	@Test
	public void testAlert() {

		// Create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		// open the web url
		driver.get("http://codetoautomate.com/educative-selenium-demo/");

		// Click the element to activate the alert
		driver.findElement(By.id("alert-button")).click();

		// Wait for the alert to be displayed and switch to the alert popup
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		// close the alert
		alert.dismiss();

	}

}

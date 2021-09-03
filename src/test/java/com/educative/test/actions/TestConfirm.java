package com.educative.test.actions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.educative.BaseTest;
import com.educative.webdriver.DriverManager;

public class TestConfirm extends BaseTest {

	@Test
	public void testConfirm() {

		// Create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		// open the web URL
		driver.get("http://codetoautomate.com/educative-selenium-demo/");

		// Click the element to activate the alert
		driver.findElement(By.id("confirm")).click();

		// Wait for the alert to be displayed and switch to the alert popup
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		// accept the alert - click OK
		alert.accept();

		// Click the element to activate the alert
		driver.findElement(By.id("confirm")).click();

		// Wait for the alert to be displayed and switch to the alert popup
		alert = wait.until(ExpectedConditions.alertIsPresent());

		// Cancel the alert - click cancel
		alert.dismiss();
	}

}

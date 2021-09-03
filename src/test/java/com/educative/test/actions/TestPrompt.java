package com.educative.test.actions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.educative.BaseTest;
import com.educative.webdriver.DriverManager;

public class TestPrompt extends BaseTest {

	@Test
	public void testPrompt() {

		// create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		// open the web URL
		driver.get("http://codetoautomate.com/educative-selenium-demo/");

		// Click the element to activate the prompt popup
		driver.findElement(By.id("prompt")).click();

		// Wait for the alert to be displayed and switch to the alert popup
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		// Enter your message/text
		alert.sendKeys("Educative");

		// Press the OK button
		alert.accept();

	}

}

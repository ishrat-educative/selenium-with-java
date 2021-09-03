package com.educative.test.actions;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.educative.BaseTest;
import com.educative.webdriver.DriverManager;

public class TestCaptureScreenshot extends BaseTest {

	@Test
	public void testTakeScreenshotOfWholeWindow() {

		// Create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		// open the web url
		driver.get("http://codetoautomate.com/educative-selenium-demo/");

		// take screenshot of the whole window
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File("screenshot.png");
		source.renameTo(destination);

		System.out.println("screenshot saved at " + destination.getAbsolutePath());

		Assert.assertTrue(destination.exists(), "screenshot file does not exist");
		Assert.assertTrue(destination.length() > 0, "screenshot file size is zero");
	}

	@Test
	public void testTakeScreenshotOfWebElement() {

		// Create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		// open the web url
		driver.get("http://codetoautomate.com/educative-selenium-demo/");

		// take screenshot of the WebElement
		WebElement element = driver.findElement(By.id("drag1"));
		File source = element.getScreenshotAs(OutputType.FILE);
		File destination = new File("screenshot.png");
		source.renameTo(destination);

		System.out.println("screenshot saved at " + destination.getAbsolutePath());

		Assert.assertTrue(destination.exists(), "screenshot file does not exist");
		Assert.assertTrue(destination.length() > 0, "screenshot file size is zero");
	}

}

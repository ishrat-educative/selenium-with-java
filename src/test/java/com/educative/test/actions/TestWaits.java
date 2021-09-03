package com.educative.test.actions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.educative.BaseTest;
import com.educative.webdriver.DriverManager;

public class TestWaits extends BaseTest {

	@Test
	public void testImplicitWait() throws Exception {

		// Create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		// open the web url
		driver.get("https://www.google.com");

		// Find element will wait for 20 seconds for the WebElement
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("educative", Keys.RETURN);

		By locator = By.xpath("//a[contains(@href,'https://www.educative.io')]");
		List<WebElement> list = driver.findElements(locator);

		Assert.assertTrue(!list.isEmpty(), "number of elements is zero");
	}

	@Test
	public void testExplicitFluentWait() throws Exception {

		// Create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		// open the web url
		driver.get("https://www.google.com");

		// create a WebDriver wait object, max wait time of 30 seconds before throwing
		// an exception
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);

		// wait for the visibility of the element
		// once the element is visible, perform actions
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		element.sendKeys("educative", Keys.RETURN);

		// create a WebDriver wait object, max wait time of 15 seconds before throwing
		// an exception
		Wait<WebDriver> waitB = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);

		By locator = By.xpath("//a[contains(@href, 'https://www.educative.io')]");
		List<WebElement> list = waitB.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		Assert.assertTrue(!list.isEmpty(), "number of elements is zero");
	}

	@Test
	public void testExplicitWebDriverWait() throws Exception {

		// Create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		// open the web url
		driver.get("https://www.google.com");

		// create a WebDriver wait object, max wait time of 30 seconds before throwing
		// an exception
		WebDriverWait waitA = new WebDriverWait(driver, 30);

		// wait for the visibility of the element
		// once the element is visible, perform actions
		WebElement element = waitA.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		element.sendKeys("educative", Keys.RETURN);

		// create a WebDriver wait object, max wait time of 15 seconds before throwing
		// an exception
		WebDriverWait waitB = new WebDriverWait(driver, 15);
		By locator = By.xpath("//a[contains(@href, 'https://www.educative.io')]");
		List<WebElement> list = waitB.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		Assert.assertTrue(!list.isEmpty(), "number of elements is zero");
	}

}

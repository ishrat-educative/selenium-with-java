package com.educative.test.actions;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.educative.BaseTest;
import com.educative.webdriver.DriverManager;

public class TestCookie extends BaseTest {

	@Test
	public void testACookie() {

		// Create Webdriver instance
		WebDriver driver = DriverManager.getWebDriver();

		// Open the web URL.
		driver.get("http://codetoautomate.com/educative-selenium-demo/");

		// Create and add a new cookie
		Cookie addCookie = new Cookie("newCookie", "educativeCookie");
		driver.manage().addCookie(addCookie);

		// Get the already set cookie name
		String getCookieValue = driver.manage().getCookieNamed("newCookie").getValue();

		LOG.info("Cookie Value: " + getCookieValue);

		Assert.assertEquals(getCookieValue, "educativeCookie");

		// Delete the cookie
		driver.manage().deleteCookieNamed("newCookie");

	}

}

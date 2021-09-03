package com.educative.webdriver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {

	protected static final Logger LOG = LoggerFactory.getLogger(DriverManager.class);

	private DriverManager() {
	}

	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

	public static WebDriver getWebDriver() {
		return DRIVER.get();
	}

	public static void setWebDriver(WebDriver driver) {
		DRIVER.set(driver);
	}

	public static void closeDriver() {
		if (DRIVER.get() != null) {
			LOG.info("removing webdriver {}", DRIVER.get());
			DRIVER.get().quit();
			DRIVER.remove();
		}
	}
}

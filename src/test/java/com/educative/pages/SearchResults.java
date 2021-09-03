package com.educative.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class SearchResults extends AbstractBasePage<SearchResults> {

	public SearchResults(WebDriver driver) {
		super(driver, "search-results.properties");
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(wait(urlContains("search?"), 5000), "url mismatch");
	}

	@Step
	public SearchResults nextPage() {
		LOG.info("navigating to next page");
		driver.findElement(getLocator("next_page")).click();
		return this;
	}

}

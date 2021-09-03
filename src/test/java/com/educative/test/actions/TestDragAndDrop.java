package com.educative.test.actions;

import java.awt.AWTException;
import java.awt.Robot;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.educative.BaseTest;
import com.educative.webdriver.DriverManager;

public class TestDragAndDrop extends BaseTest {

	@Test
	public void testDragAndDrop() throws AWTException {

		// Create a WebDriver object
		WebDriver driver = DriverManager.getWebDriver();

		// open the web url
		driver.get("http://codetoautomate.com/educative-selenium-demo/");

		// scroll to the end of the page
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		jsDriver.executeScript("document.getElementById('div1').scrollIntoViewIfNeeded()");

		WebElement source = driver.findElement(By.id("drag1"));
		WebElement target = driver.findElement(By.id("div1"));

		// using Robot, move the mouse pointer to the target element
		// This is required, as the drop happens onto the location where the move
		// pointer is currently placed
		Robot robot = new Robot();

		// get co-ordinates to the center of the target element

		Long height = (Long) jsDriver.executeScript("return window.innerHeight");
		System.out.println();

		System.out.println(ReflectionToStringBuilder.toString(driver.manage().window().getSize()));
		System.out.println(ReflectionToStringBuilder.toString(target.getLocation()));
		System.out.println(ReflectionToStringBuilder.toString(target.getRect()));

		int x = target.getLocation().getX() + target.getRect().getWidth() / 2;
		int y = target.getLocation().getY() + target.getRect().getHeight() - height.intValue();

		// move the mouse pointer to the co-ordinates
		robot.mouseMove(x, y);

		// perform drag-drop
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).build().perform();

		// an overloaded method to move by offset from source location
		// actions.dragAndDropBy(source, 100, 100).build().perform();

	}

}

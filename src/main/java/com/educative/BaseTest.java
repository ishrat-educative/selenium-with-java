package com.educative;

import static com.educative.webdriver.DriverFactory.createInstance;
import static com.educative.webdriver.DriverManager.getWebDriver;
import static com.educative.webdriver.DriverManager.setWebDriver;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.educative.utils.ConfigurationManager;
import com.educative.webdriver.DriverManager;

import io.qameta.allure.Attachment;

/**
 * This is the super class of all TestClasses
 */
public abstract class BaseTest {

	private static final String DEFAULT_BROWSER = ConfigurationManager.getInstance().getProperty("browser");

	protected static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

	@BeforeMethod
	public void setUp(Method method) {
		if (getWebDriver() == null) {
			boolean isMobile = method.getName().toLowerCase().contains("mobile")
					|| method.getDeclaringClass().getName().toLowerCase().contains("mobile");
			setWebDriver(createInstance(DEFAULT_BROWSER, isMobile));
		}
	}

	@AfterMethod
	public void closeDriver(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
			takeScreenshot(getWebDriver(), getScreenshotFileName(result));
		}
		DriverManager.closeDriver();
	}

	private String getScreenshotFileName(ITestResult result) {
		return String.format("%s_%s.png", result.getTestClass().getRealClass().getSimpleName(),
				result.getMethod().getMethodName())
				.toLowerCase();
	}

	private void takeScreenshot(WebDriver driver, String file) {
		if (driver != null) {
			byte[] bytes = getScreenshot(driver);
			Path path = Paths.get(ConfigurationManager.getInstance().getProperty("screenshot_directory"), file);
			if (!path.toFile().getParentFile().exists()) {
				path.toFile().getParentFile().mkdirs();
			}
			try {
				Files.write(path, bytes);
				LOG.info("saving screenshot to {}", path.toAbsolutePath().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Attachment(value = "screenshot", type = "image/png", fileExtension = ".png")
	private byte[] getScreenshot(WebDriver driver) {
		TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
		return screenshotDriver.getScreenshotAs(OutputType.BYTES);
	}

	public static void main(String[] args)
	{
		System.out.println("Alhamdolillah");
	}

}
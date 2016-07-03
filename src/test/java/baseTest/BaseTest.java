package baseTest;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import factory.WebDriverFactory;
import static pages.BasePage.driver;

public abstract class BaseTest {

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browserName" })
	public void browserOpened(@Optional("firefox") String browserName) {
		driver = WebDriverFactory.getInstanse();
		// webDriver.manage().window().maximize();
		Dimension targetSize = new Dimension(1440, 900);
		driver.manage().window().setSize(targetSize);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			WebDriverFactory.killInstance();
		}

	}
}

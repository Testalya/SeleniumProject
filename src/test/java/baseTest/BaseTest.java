package baseTest;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import factory.WebDriverFactory;

public abstract class BaseTest {
	public static WebDriver webDriver;

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browserName" })
	public void browserOpened(@Optional("firefox") String browserName) {
		webDriver = WebDriverFactory.getInstanse();
		// webDriver.manage().window().maximize();
		Dimension targetSize = new Dimension(1440, 900);
		webDriver.manage().window().setSize(targetSize);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (webDriver != null) {
			WebDriverFactory.killInstance();
		}

	}
}

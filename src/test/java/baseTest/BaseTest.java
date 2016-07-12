package baseTest;

import factory.WebDriverFactory;
import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.PropertyReader;

import static pages.BasePage.driver;

public abstract class BaseTest {

	public final String URL = PropertyReader.loadProperty().getProperty("BASE_URL");

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browserName" })
	public void browserOpened(@Optional("firefox") String browserName) {
		driver = WebDriverFactory.getInstanse();
		Dimension targetSize = new Dimension(1440, 900);
		driver.get(URL);
		driver.manage().window().setSize(targetSize);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			WebDriverFactory.killInstance();
		}
	}
}

package baseTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import factory.WebDriverFactory;


public abstract class BaseTest {
	public static WebDriver webDriver;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "browserName" })
	public void browserOpened(@Optional("firefox") String browserName) {
		webDriver = WebDriverFactory.getInstanse(browserName);
		webDriver.get("https://my-hit.org");
		webDriver.manage().window().maximize();
	}
	
	@AfterSuite(alwaysRun = true)
	public void tearDown(){
		if (webDriver != null) {
			WebDriverFactory.killInstance();
		}

	}
}


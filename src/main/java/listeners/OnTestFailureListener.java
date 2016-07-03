package listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import factory.WebDriverFactory;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import utils.PropertyReader;

public class OnTestFailureListener extends TestListenerAdapter {
	public void onTestFailure(ITestResult arg0) {
		testScreenshotOnFinish(WebDriverFactory.getInstanse());
	}

	@Step("Screenshot")
	public void testScreenshotOnFinish(WebDriver webDriver) {
		takeScreenshot(webDriver);
	}

	@Attachment(value = "Screenshot of the failed test", type = "image/jpeg")
	public byte[] takeScreenshot(WebDriver webDriver) {
		File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		byte[] screen = null;
		try {
			screen = IOUtils.toByteArray(new FileInputStream(screenshot));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screen;
	}

}
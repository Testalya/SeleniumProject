package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";

	private static WebDriver webDriver;

	private WebDriverFactory() {

	}

	public static WebDriver getInstanse(String browser) {
		if (webDriver == null) {
			if (CHROME.equals(browser)) {
				setChromeDriver();
				webDriver = new ChromeDriver();
			} else if (FIREFOX.equals(browser)) {
				webDriver = new FirefoxDriver();
			} else {
				throw new RuntimeException("Invalid browser property set in configuration file");
			}
		}
		return webDriver;
	}

	public static void killInstance() {
		if (webDriver != null) {
			webDriver.quit();
			webDriver = null;
		}
	}

	private static void setChromeDriver() {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuffer chromeBinaryPath = new StringBuffer("src/main/resources/Drivers/");

		if (osName.startsWith("win")) {
			chromeBinaryPath.append("chrome-win/chromedriver.exe");
		} else if (osName.startsWith("lin")) {
			chromeBinaryPath.append("chrome-lin/chromedriver");
		} else if (osName.startsWith("mac")) {
			chromeBinaryPath.append("chrome-mac/chromedriver");
		} else
			throw new RuntimeException("Your OS is invalid for webdriver tests");

		System.setProperty("webdriver.chrome.driver", chromeBinaryPath.toString());
	}

}

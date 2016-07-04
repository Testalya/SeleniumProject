package factory;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import utils.PropertyReader;

public class WebDriverFactory {

    private static final String BROWSER = "BROWSER";

    private static WebDriver webDriver;

    private WebDriverFactory() {

    }

    public static WebDriver getInstanse() {

        PropertyReader.loadProperty().getProperty(BROWSER);

        if (webDriver == null) {
            if (PropertyReader.loadProperty().getProperty(BROWSER).toLowerCase().equals("firefox")) {
                webDriver = new FirefoxDriver();
            } else if (PropertyReader.loadProperty().getProperty(BROWSER).toLowerCase().equals("chrome")) {
                setChromeDriver();
                webDriver = new ChromeDriver();
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

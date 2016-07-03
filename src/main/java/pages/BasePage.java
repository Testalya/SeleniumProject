package pages;

import factory.WebDriverFactory;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by yuraku on 7/3/16.
 */
public class BasePage {

    public static <T extends BasePage> T initPage(Class<T> pageClass) {
        return PageFactory.initElements(WebDriverFactory.getInstanse(), pageClass);
    }
}

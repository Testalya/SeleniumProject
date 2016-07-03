package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

public class HomePage extends BasePage {

    @FindBy(how = How.XPATH, using = ".//*[@class='btn btn-success']")
    @CacheLookup
    public WebElement loginButton;

    @FindBy(how = How.ID, using = "myModalLabel")
    @CacheLookup
    public WebElement loginModalHeading;

    @FindBy(how = How.XPATH, using = ".//*[@id='login']")
    @CacheLookup
    public WebElement loginField;

    @FindBy(how = How.XPATH, using = ".//*[@id='pass']")
    @CacheLookup
    public WebElement passwordField;

    @FindBy(how = How.XPATH, using = ".//*[@id='user_login_form']/div[2]/button")
    @CacheLookup
    public WebElement submitButton;

    public String getLoginModalHeadingText() {

        return loginModalHeading.getText();
    }

    @Step("Perform login on site")
    public GeneralSearchPage performLogin(String name, String password) {

        loginButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginField.clear();
        loginField.sendKeys(name);
        passwordField.clear();
        passwordField.sendKeys(password);
        submitButton.click();

        return initPage(GeneralSearchPage.class);
    }

}

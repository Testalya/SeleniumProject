package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

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

	public void clickOnLoginButton() {
		loginButton.click();
	}

	public String getLoginModalHeadingText() {
		return loginModalHeading.getText();
	}

	public void fillLoginField(String name) {
		loginField.sendKeys(name);
	}

	public void fillPasswordField(String pass) {
		passwordField.sendKeys(pass);
	}

	public void clickOnSubmitButton() {
		submitButton.click();
		return;
	}

}

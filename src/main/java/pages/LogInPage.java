package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LogInPage {

	WebDriver webDriver;

	public LogInPage(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[2]/ul[2]/li/button")
	@CacheLookup
	public WebElement loginButton;

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

	public void fillLoginField(String name) {
		loginField.sendKeys(name);
	}

	public void fillPasswordField(String pass) {
		passwordField.sendKeys(pass);
	}

	public void clickOnSubmitButton() {
		submitButton.click();
	}

}

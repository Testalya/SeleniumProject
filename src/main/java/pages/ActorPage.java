package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ActorPage {

	WebDriver webDriver;

	public ActorPage(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}

	@FindBy(how = How.XPATH, using = "html/body/div[3]/div/div[2]/h1")
	@CacheLookup
	public WebElement actorHeading;

	public String getActorHeadingText() {
		return actorHeading.getText();
	}

}

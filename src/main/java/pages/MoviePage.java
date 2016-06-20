package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MoviePage {

	WebDriver webDriver;

	public MoviePage(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}

	@FindBy(how = How.XPATH, using = "html/body/div[3]/div/div[2]/ul[3]/li/a[1]")
	@CacheLookup
	public WebElement firstMainActor;

	public void scrollToActorsList() {
		Coordinates coords = ((Locatable) firstMainActor).getCoordinates();
		coords.inViewPort();
	}

	public void clickingOnFirstActor() {
		firstMainActor.click();
	}

}

package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GeneralSearchPage {

	WebDriver webDriver;

	public GeneralSearchPage(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}

	@FindBy(how = How.ID, using = "search-navbar-q")
	@CacheLookup
	public WebElement searchBar;

	public void clickOnSearch() {
		searchBar.click();
	}

	public void typeIntoSearchField() {
		searchBar.sendKeys("Black Mirror" + Keys.ENTER);
	}

}

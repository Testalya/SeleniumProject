package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import ru.yandex.qatools.allure.annotations.Step;

public class GeneralSearchPage extends BasePage {

	WebDriver webDriver;

	public GeneralSearchPage(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}

	@FindBy(how = How.ID, using = "search-navbar-q")
	@CacheLookup
	public WebElement searchBar;

	@FindBy(how = How.XPATH, using = ".//*[@class='input-group-btn']/button")
	@CacheLookup
	public WebElement searchButton;

	@FindBy(how = How.XPATH, using = ".//*[@id='film-list']/div[1]/div[2]/ul/li[5]/a[1]")
	@CacheLookup
	public WebElement producerName;

	@FindBy(how = How.XPATH, using = ".//*[@id='film-list']/div[1]/div[2]/b/a")
	@CacheLookup
	public WebElement searchResultHeading;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[2]/ul[1]/li[2]/a")
	@CacheLookup
	public WebElement tvSeries;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[2]/ul[1]/li[2]/ul")
	@CacheLookup
	public WebElement tvSeriesDropdown;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[2]/ul[1]/li[2]/ul/li[18]/a")
	@CacheLookup
	public WebElement psychologicalMovieType;

	@FindBy(how = How.XPATH, using = "html/body/div[3]/div/div[2]/div[2]/div[2]/div[2]/a")
	@CacheLookup
	public WebElement popularFilter;

	@FindBy(how = How.XPATH, using = "html/body/div[3]/div/div[2]/div[2]/div[2]/div[2]/button")
	@CacheLookup
	public WebElement selectDownButton;

	@FindBy(how = How.XPATH, using = "html/body/div[3]/div/div[2]/div[2]/div[2]/div[2]/ul/li[4]/a")
	@CacheLookup
	public WebElement forAllTimeFilter;

	@FindBy(how = How.XPATH, using = ".//*[@id='film-list']/div[1]/div[2]/ul/li[1]")
	@CacheLookup
	public WebElement actorBirthDate;

	@FindBy(how = How.XPATH, using = ".//*[@id='film-list']/div[1]/div[2]/ul/li[2]")
	@CacheLookup
	public WebElement actorBirthPlace;

	@FindBy(how = How.XPATH, using = "html/body/div[3]/div/div[2]/div[7]/div[1]/a/img")
	@CacheLookup
	public WebElement firstMainActor;

	@FindBy(how = How.XPATH, using = "html/body/div[3]/div/div[2]/h1")
	@CacheLookup
	public WebElement movieHeader;

	@FindBy(how = How.XPATH, using = "html/body/div[3]/div/div[2]/h1")
	@CacheLookup
	public WebElement actorHeading;

	public String getActorHeadingText() {
		return actorHeading.getText();
	}

	public String getTextOfMovieHeader() {
		return movieHeader.getText();
	}

	public void clickOnSearch() {
		searchBar.click();
	}

	public void typeIntoSearchField(String a) {
		searchBar.sendKeys(a + Keys.ENTER);
	}

	public String getProducerNameText() {
		return producerName.getText();
	}

	public void clickOnMovieHeading() {
		searchResultHeading.click();
	}

	public void clickOnTvSeriasButton() {
		tvSeries.click();
	}

	public void clickOnPsychologicalMovieType() {
		psychologicalMovieType.click();
	}

	public void selectPopularFilter() {
		popularFilter.click();
	}

	public void clickOnSelectDownButton() {
		selectDownButton.click();
	}

	public void selectForAllTimeFilter() {
		forAllTimeFilter.click();
	}

	public String getActorBirthDateText() {
		return actorBirthDate.getText();
	}

	public String getActorBirthPlaceText() {
		return actorBirthPlace.getText();
	}

	public void clickOnActorHeading() {
		searchResultHeading.click();
	}

	public void scrollToActorsList() {
		Coordinates coords = ((Locatable) firstMainActor).getCoordinates();
		coords.inViewPort();
	}

	public void clickingOnFirstActor() {
		firstMainActor.click();
	}

	@Step("Searching the movie using dropdown menu and filters")
	public void performSearchByDropdown() {
		clickOnTvSeriasButton();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(tvSeriesDropdown));
		Assert.assertTrue(tvSeriesDropdown.isDisplayed());
		clickOnPsychologicalMovieType();
		selectPopularFilter();
		clickOnSelectDownButton();
		selectForAllTimeFilter();
	}

	@Step("Searching an actor using Search bar")
	public void findActorBySearch(String actorName) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(searchBar));

		clickOnSearch();
		typeIntoSearchField(actorName);

		String actualActorBirthDate = getActorBirthDateText();
		String expectedActorBirthDate = "Дата рождения: 18.08.1969";
		Assert.assertEquals(actualActorBirthDate, expectedActorBirthDate, "Actor's birth date is not the same as: ");

		String actualActorBirthPlace = getActorBirthPlaceText();
		String expectedActorBirthPlace = "Место рождения: США, Бостон";
		Assert.assertEquals(actualActorBirthPlace, expectedActorBirthPlace, "Actor's birth place is not the same as: ");

		clickOnActorHeading();
	}

	@Step("Searching an actor using Search bar")
	public void findMovieBySearch(String movieName) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(searchBar));

		clickOnSearch();
		typeIntoSearchField(movieName);

		String actualProducerName = getProducerNameText();
		String expectedProducerName = "Отто Баферст";
		Assert.assertEquals(actualProducerName, expectedProducerName);

		clickOnMovieHeading();

		String actualMovieHeader = getTextOfMovieHeader();
		String expectedMovieHeader = "Черное зеркало (1-3 сезон)";
		Assert.assertEquals(actualMovieHeader, expectedMovieHeader);
	}

	@Step("Searching an actor using avatar")
	public void findMovieByImg(String movieNameToSearch) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(searchBar));

		clickOnSearch();
		typeIntoSearchField(movieNameToSearch);

		wait.until(ExpectedConditions.visibilityOf(searchResultHeading));
		clickOnMovieHeading();
		scrollToActorsList();

		Screen screen = new Screen();
		Pattern actorImg = new Pattern("src/test/resources/Charlie_Sheen.png");
		try {
			screen.doubleClick(actorImg);

		} catch (FindFailed e) {
			e.printStackTrace();
		}

		String actualActorHeading = getActorHeadingText();
		String expectedActorHeading = "Чарли Шин";
		Assert.assertEquals(actualActorHeading, expectedActorHeading);
	}

}

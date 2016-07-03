package generalTests;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import baseTest.BaseTest;
import pages.GeneralSearchPage;
import pages.LoginPage;
import pages.MoviePage;
import pages.ActorPage;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.properties.PropertyLoader;
import utils.PropertyReader;

public class MyHitTests extends BaseTest {

	@DataProvider(name = "loginCredentials")
	public Object[][] getLoginData() {
		return new Object[][] { {PropertyReader.loadProperty().getProperty("USERNAME"), PropertyReader.loadProperty().getProperty("PASSWORD") }, { PropertyReader.loadProperty().getProperty("EMAIL"), PropertyReader.loadProperty().getProperty("PASSWORD") } };
	}

	@Test(dataProvider = "loginCredentials")
	public void login(String name, String pass) {
		generateReport();

		webDriver.get("https://my-hit.org");
		LoginPage page = PageFactory.initElements(webDriver, LoginPage.class);

		page.clickOnLoginButton();

		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.visibilityOf(page.loginField));

		Assert.assertTrue(page.loginField.isDisplayed());
		String loginModalHeading = page.getLoginModalHeadingText();
		Assert.assertEquals(loginModalHeading, "Вход или регистрация");

		page.fillLoginField(name);
		page.fillPasswordField(pass);
		page.clickOnSubmitButton();

	}

	@Test
	public void findMovieBySearchField() {
		generateReport();

		webDriver.get("https://my-hit.org");
		GeneralSearchPage page = PageFactory.initElements(webDriver, GeneralSearchPage.class);
		MoviePage moviePage = PageFactory.initElements(webDriver, MoviePage.class);

		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.visibilityOf(page.searchBar));

		page.clickOnSearch();
		page.typeIntoSearchField("Black Mirror");

		String producerName = page.getProducerNameText();
		Assert.assertEquals(producerName, "Отто Баферст");

		page.clickOnMovieHeading();

		String movieHeader = moviePage.getTextOfMovieHeader();
		Assert.assertEquals(movieHeader, "Черное зеркало (1-3 сезон)");
	}

	@Test
	public void searchMovieByDropDownMenuAndFilters() {
		generateReport();

		webDriver.get("https://my-hit.org");
		GeneralSearchPage page = PageFactory.initElements(webDriver, GeneralSearchPage.class);

		page.clickOnTvSeriasButton();
		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.visibilityOf(page.tvSeriesDropdown));
		Assert.assertTrue(page.tvSeriesDropdown.isDisplayed());

		page.clickOnPsychologicalMovieType();
		String filteredPageTitle = webDriver.getTitle();
		Assert.assertEquals(filteredPageTitle, "Сериалы онлайн: жанр - психологический.");

		page.selectPopularFilter();
		page.clickOnSelectDownButton();
		page.selectForAllTimeFilter();

	}

	@Test
	public void findActorBySearchField() {
		generateReport();

		webDriver.get("https://my-hit.org");
		GeneralSearchPage page = PageFactory.initElements(webDriver, GeneralSearchPage.class);

		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.visibilityOf(page.searchBar));

		page.clickOnSearch();
		page.typeIntoSearchField("Edward Norton");

		String actorBirthDate = page.getActorBirthDateText();
		Assert.assertEquals(actorBirthDate, "Дата рождения: 18.08.1969");

		String actorBirthPlace = page.getActorBirthPlaceText();
		Assert.assertEquals(actorBirthPlace, "Место рождения: США, Бостон");

		page.clickOnActorHeading();

	}

	@Test
	public void findMovieActorByAvatar() {
		generateReport();

		webDriver.get("https://my-hit.org");
		GeneralSearchPage page = PageFactory.initElements(webDriver, GeneralSearchPage.class);
		ActorPage actorPage = PageFactory.initElements(webDriver, ActorPage.class);

		WebDriverWait wait = new WebDriverWait(webDriver, 10);
		wait.until(ExpectedConditions.visibilityOf(page.searchBar));

		page.clickOnSearch();
		page.typeIntoSearchField("Горячие головы");

		wait.until(ExpectedConditions.visibilityOf(page.searchResultHeading));
		page.clickOnMovieHeading();
		page.scrollToActorsList();

		Screen screen = new Screen();
		Pattern actorImg = new Pattern("src/test/resources/Charlie_Sheen.png");
		try {
			screen.doubleClick(actorImg);
		} catch (FindFailed e) {
			e.printStackTrace();
		}

		String actorHeading = actorPage.getActorHeadingText();
		Assert.assertEquals(actorHeading, "Чарли Шин");

	}

	@Step("Report generating")
	public void generateReport() {
		System.out.println("Report was successfully generated");
	}

}

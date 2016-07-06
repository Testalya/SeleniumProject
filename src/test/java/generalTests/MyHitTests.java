package generalTests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.GeneralSearchPage;
import pages.HomePage;
import pages.MoviePage;
import ru.yandex.qatools.allure.annotations.Title;
import utils.PropertyReader;

import static pages.BasePage.driver;

public class MyHitTests extends BaseTest {

	@DataProvider(name = "loginCredentials")
	public Object[][] getLoginData() {
		return new Object[][] {
				{ PropertyReader.loadProperty().getProperty("USERNAME"),
						PropertyReader.loadProperty().getProperty("PASSWORD") },
				{ PropertyReader.loadProperty().getProperty("EMAIL"),
						PropertyReader.loadProperty().getProperty("PASSWORD") } };
	}

	@Test(dataProvider = "loginCredentials",enabled = false)
	@Title("Perform login on site with data from dataprovider")
	public void login(String name, String password) {

		driver.get(URL);
		HomePage homePage = HomePage.initPage(HomePage.class);

		homePage.performLogin(name, password);
	}

	@Test(enabled = false)
	@Title("Searching movie through Search Field")
	public void findMovieBySearchField() {
		driver.get(URL);
		GeneralSearchPage searchPage = GeneralSearchPage.initPage(GeneralSearchPage.class);

		searchPage.findMovieBySearch("Black Mirror");
	}

	@Test(enabled = false)
	@Title("Perform search the movie via dropdown menu")
	public void searchMovieByDropDownMenu() {
		driver.get(URL);
		GeneralSearchPage searchPage = GeneralSearchPage.initPage(GeneralSearchPage.class);

		searchPage.performSearchByDropdown();
	}

	@Test(enabled = false)
	@Title("Find an actor using Search field")
	public void findActorBySearchField() {
		driver.get(URL);
		GeneralSearchPage searchPage = GeneralSearchPage.initPage(GeneralSearchPage.class);

		searchPage.findActorBySearch("Edward Norton");
	}

	@Test(enabled = false)
	@Title("Find movie actor by Avatar")
	public void findMovieActorByAvatar() {
		driver.get(URL);
		GeneralSearchPage searchPage = GeneralSearchPage.initPage(GeneralSearchPage.class);

		searchPage.findMovieByImg("Горячие головы");
	}

	@Test
	public void tempImgTest(){
		driver.get(URL);
		MoviePage moviePage = MoviePage.initPage(MoviePage.class);

		Assert.assertTrue(moviePage.compareActorImages());


	}
}

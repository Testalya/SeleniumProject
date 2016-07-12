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

public class MyHitTests extends BaseTest {

	@DataProvider(name = "loginCredentials")
	public Object[][] getLoginData() {
		return new Object[][] {
				{ PropertyReader.loadProperty().getProperty("USERNAME"),
						PropertyReader.loadProperty().getProperty("PASSWORD") },
				{ PropertyReader.loadProperty().getProperty("EMAIL"),
						PropertyReader.loadProperty().getProperty("PASSWORD") } };
	}

	@Test(dataProvider = "loginCredentials")
	@Title("Perform login on site with data from dataprovider")
	public void login(String name, String password) {

		HomePage homePage = HomePage.initPage(HomePage.class);
		homePage.performLogin(name, password);
	}

	@Test
	@Title("Searching movie through Search Field")
	public void findMovieBySearchField() {

		GeneralSearchPage searchPage = GeneralSearchPage.initPage(GeneralSearchPage.class);
		searchPage.findMovieBySearch("Black Mirror");
	}

	@Test
	@Title("Perform search the movie via dropdown menu")
	public void searchMovieByDropDownMenu() {

		GeneralSearchPage searchPage = GeneralSearchPage.initPage(GeneralSearchPage.class);
		searchPage.performSearchByDropdown();
	}

	@Test
	@Title("Find an actor using Search field")
	public void findActorBySearchField() {

		GeneralSearchPage searchPage = GeneralSearchPage.initPage(GeneralSearchPage.class);
		searchPage.findActorBySearch("Edward Norton");
	}

	@Test
	@Title("Find movie actor by Avatar")
	public void findMovieActorByAvatar() {

		GeneralSearchPage searchPage = GeneralSearchPage.initPage(GeneralSearchPage.class);
		searchPage.findMovieByImg("Горячие головы");
	}

	@Test
	public void tempImgTest() {

		MoviePage moviePage = MoviePage.initPage(MoviePage.class);
		Assert.assertTrue(moviePage.compareActorImages());
	}
}

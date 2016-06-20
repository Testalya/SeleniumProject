package generalTests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import baseTest.BaseTest;
import pages.ActorPage;
import pages.GeneralSearchPage;
import pages.LogInPage;
import pages.MoviePage;
import ru.yandex.qatools.allure.annotations.Step;

public class MyHitTests extends BaseTest {

	@DataProvider(name = "loginCredentials")
	public Object[][] getLoginData() {
		return new Object[][] { { "TestAccount1", "1q2w3e4r5t6y" },
				// { "testalya@yahoo.com", "1q2w3e4r5t6y" }
		};
	}

	@Test(dataProvider = "loginCredentials")
	public void login(String name, String pass) {
		generateReport();
		LogInPage page = PageFactory.initElements(webDriver, LogInPage.class);

		page.clickOnLoginButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertTrue(page.loginField.isDisplayed());
		page.fillLoginField(name);
		page.fillPasswordField(pass);
		page.clickOnSubmitButton();
	}

	@Test
	public void searchForMovie() {
		generateReport();
		GeneralSearchPage page = PageFactory.initElements(webDriver, GeneralSearchPage.class);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		page.clickOnSearch();
		page.typeIntoSearchField();
	}

	@Test(dependsOnMethods = { "searchForMovie" })
	public void findMovieByImg() {
		generateReport();
		Screen screen = new Screen();
		Pattern moviePoster = new Pattern("src/test/resources/BlackMirror.png");
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			screen.doubleClick(moviePoster);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "findMovieByImg" })
	public void scrollingDownToFindActorList() {
		generateReport();
		MoviePage page = PageFactory.initElements(webDriver, MoviePage.class);
		page.scrollToActorsList();
	}

	@Test(dependsOnMethods = { "scrollingDownToFindActorList" })
	public void findActor() {
		generateReport();
		MoviePage page = PageFactory.initElements(webDriver, MoviePage.class);

		page.clickingOnFirstActor();
	}

	@Test(dependsOnMethods = { "findActor" })
	public void verifyActorName() {
		generateReport();
		ActorPage page = PageFactory.initElements(webDriver, ActorPage.class);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Assert.assertEquals("Rory Kinnear", page.getActorName());
	}

	@Step("Report generating")
	public void generateReport() {
		System.out.println("Report was successfully generated");
	}

}

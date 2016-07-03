package generalTests;

import baseTest.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ActorPage;
import pages.GeneralSearchPage;
import pages.HomePage;
import pages.MoviePage;
import ru.yandex.qatools.allure.annotations.Title;
import utils.PropertyReader;

import static pages.BasePage.driver;

public class MyHitTests extends BaseTest {

    public final String URL = PropertyReader.loadProperty().getProperty("BASE_URL");

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginData() {
        return new Object[][]{
                {PropertyReader.loadProperty().getProperty("USERNAME"), PropertyReader.loadProperty().getProperty("PASSWORD")},
                {PropertyReader.loadProperty().getProperty("EMAIL"), PropertyReader.loadProperty().getProperty("PASSWORD")}};
    }

    @Test(dataProvider = "loginCredentials")
    @Title("Perform login on site with data from dataprovider")
    public void login(String name, String password) {

        driver.get(URL);
        HomePage homePage = HomePage.initPage(HomePage.class);

        //Assert.assertEquals(homePage.getLoginModalHeadingText(), "Вход или регистрация");
        homePage.performLogin(name, password);
    }
/*
    @Test(enabled = false)
    public void findMovieBySearchField() {

        webDriver.get(PropertyReader.loadProperty().getProperty("BASE_URL"));
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

    @Test(enabled = false)
    public void searchMovieByDropDownMenuAndFilters() {

        webDriver.get(PropertyReader.loadProperty().getProperty("BASE_URL"));
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

    @Test(enabled = false)
    public void findActorBySearchField() {


        webDriver.get(PropertyReader.loadProperty().getProperty("BASE_URL"));
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

    @Test(enabled = false)
    public void findMovieActorByAvatar() {

        webDriver.get(PropertyReader.loadProperty().getProperty("BASE_URL"));
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

    }*/
}

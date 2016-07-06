package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.ashot.AShot;
import utils.PropertyReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by yuraku on 7/5/16.
 */
public class MoviePage extends BasePage {

    private static String imgPath = "src/test/resources/Charlie_Sheen.png";

    @FindBy(how = How.ID, using = "search-navbar-q")
    @CacheLookup
    public WebElement searchBar;


    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Hot Shots!')]")
    @CacheLookup
    public WebElement movieTitle;

    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Charlie Sheen')]")
    @CacheLookup
    public WebElement actorName;

    @FindBy(how = How.XPATH, using = "//img[contains(@alt, 'Чарли Шин')]")
    @CacheLookup
    public WebElement actorImage;

    public String getMovieTitle() {
        return movieTitle.getText();
    }

    public String getActorName() {
        return actorName.getText();
    }

    public String getActorImage() {
        return actorImage.getText();
    }

    public void typeIntoSearchField(String a) {
        searchBar.sendKeys(a + Keys.ENTER);
    }

    public void clickOnSearch() {
        searchBar.click();
    }

    public boolean compareActorImages() {

        clickOnSearch();
        typeIntoSearchField(PropertyReader.loadProperty().getProperty("MOVIE"));

        BufferedImage expectedImage = null;

        try {
            expectedImage = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage actualImage = new AShot().takeScreenshot(driver, actorImage).getImage();

        return isBufferedImagesEqual(expectedImage, actualImage);
    }

    private boolean isBufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y))
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}

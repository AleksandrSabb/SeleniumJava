package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OurAppsPage extends BasePage{

    @FindBy (xpath = "//a[@href='https://play.google.com/store/apps/details?id=com.asos.app']")
    WebElement androidPane;

    public OurAppsPage(WebDriver driver) {
        super(driver);
    }
    public void navigateToGooglePlay(){
        androidPane.click();
    }
}

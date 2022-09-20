package pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;

public class BasePage {
    WebDriver driver;
    @FindBy(xpath = "//li[@class='_3Wo6fpk mD8oZFx']")
    WebElement myAccountDropdown;
    @FindBy(xpath = "//a[@data-testid='signin-link']")
    WebElement signinLink;
    @FindBy(xpath = "//input[@type='search']")
    WebElement searchField;
    @FindBy(xpath = "//a[@data-testid='miniBagIcon']")
    WebElement cartButton;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToMyAccount() {
        Actions actions = new Actions(driver);
        actions.moveToElement(myAccountDropdown).build().perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signinLink));
        signinLink.click();
    }

    public void clickOnSignInLink() {

    }

    public void Login() {
        driver.get("https://www.asos.com/");
        BasePage basePage = new BasePage(driver);
        basePage.navigateToMyAccount();
        SingIn singIn = new SingIn(driver);
        singIn.enterEmail();
        singIn.enterPass();
        singIn.clickSignIn();
    }
public void openCart(){
        cartButton.click();
}

}

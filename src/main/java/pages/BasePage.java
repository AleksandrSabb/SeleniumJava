package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    @FindBy(xpath = "//div [@id = 'navbar']")
    WebElement header;
    @FindBy(xpath = "//a [@class = 'nav-a nav-a-2   nav-progressive-attribute'][1]")
    WebElement helloSignIn;
    @FindBy(xpath = "//span [@class= 'nav-action-inner']")
    WebElement singInButton;

    public BasePage(WebDriver driver) {
        driver.manage().window().fullscreen();
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public void navigateMouseTo(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }
    public void navigateMouseToSignIn() {
        helloSignIn.click();
    }

    public SingIn pressLogButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(helloSignIn));
        navigateMouseTo(helloSignIn);
        singInButton.click();
        return (SingIn) driver;
    }
}

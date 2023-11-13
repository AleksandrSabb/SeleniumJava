package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SingIn extends BasePage {
    public SingIn(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@aria-label='Email Address:']")
    WebElement emailField;
    @FindBy(xpath = "//div[@class='main-content']//input[@aria-label='Password:']")
    WebElement passwordField;
    @FindBy(xpath = "//div[@class='main-content']//span[contains (., 'Sign In')]")
    WebElement signInButton;
    @FindBy(xpath = "//span[contains (., 'CONTINUE')]")
    WebElement continueButton;

    @FindBy(xpath = "//span[contains (., 'Skip')]")
    WebElement skipButton;

    public void enterEmail(String email) {
        waitForVisibilityOf(emailField);
        emailField.sendKeys(email);
    }
    
    public void enterPass(String pass) {
        waitForVisibilityOf(passwordField);
        passwordField.sendKeys(pass);
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public void clickContinue() {
        waitForVisibilityOf(continueButton);
        continueButton.click();

    }

    public void clickSkip() {
        waitForVisibilityOf(skipButton);
        skipButton.click();

    }
}

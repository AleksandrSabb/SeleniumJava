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

    @FindBy(xpath = "//input [@type= 'email']")
    WebElement emailField;
    @FindBy(xpath = "//span[@class= 'a-button-inner']//*[@id = 'continue']")
    WebElement continueButton;
    @FindBy(xpath = "//input [@type= 'password']")
    WebElement passwordField;
    @FindBy(xpath = "//span[@class= 'a-button-inner']//*[@id = 'signInSubmit']")
    WebElement signInBut;

    public void enterEmail() {
       String email = "mymailbox099@meta.ua";
        emailField.sendKeys(email);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void enterPass() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
       String pass = "asd5329367aaa";
        passwordField.sendKeys(pass);
    }

    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signInBut));
        signInBut.click();
    }
}

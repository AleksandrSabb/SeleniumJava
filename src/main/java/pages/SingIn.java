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

    @FindBy(xpath = "//input[@name= 'Username']")
    WebElement emailField;
    @FindBy(xpath = "//input[@name= 'Password']")
    WebElement passwordField;
    @FindBy(xpath = "//input[@id='signin']")
    WebElement signInButton;

    public void enterEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(emailField));
       String email = "mymailbox099@meta.ua";
        emailField.sendKeys(email);
    }
    
    public void enterPass() {
       String pass = "asd5329367";
        passwordField.sendKeys(pass);
    }

    public void clickSignIn() {
        signInButton.click();
    }
}

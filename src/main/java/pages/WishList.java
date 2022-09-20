package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WishList extends BasePage{

@FindBy(xpath = "//button[@class='toBagButton_mYSbq toBagButton_LHRGf']")
    WebElement addToCart;
    public WishList(WebDriver driver) {
        super(driver);
    }
    public void addToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        addToCart.click();
        Thread.sleep(2000); //need such wait for animation
    }
}

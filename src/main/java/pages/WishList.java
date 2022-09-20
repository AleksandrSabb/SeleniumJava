package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishList extends BasePage{

@FindBy(xpath = "//button[@class='toBagButton_mYSbq toBagButton_LHRGf']")
    WebElement addToCart;
    public WishList(WebDriver driver) {
        super(driver);
    }
    public void addToCart() throws InterruptedException {
        addToCart.click();
        Thread.sleep(2000);
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cart extends BasePage{
    @FindBy(xpath = "//p[@class='bag-item-name']/a")
    WebElement productName;
    @FindBy(xpath = "//span[@class='bag-subtotal-price']")
    WebElement productPrice;
    @FindBy(xpath = "//div[@class='bag-contents-holder bag-contents-holder-panel bag-contents-holder--total']//p[@class='bag-total-button-holder']")
    WebElement checkOutButton;

    public Cart(WebDriver driver) {
        super(driver);
    }
    public String getName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productName));
        return productName.getText();
    }
    public String getPrice(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        return productPrice.getText();

    }
    public void goCheckOut(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(checkOutButton));
        checkOutButton.click();
    }
}

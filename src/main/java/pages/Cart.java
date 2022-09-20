package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart extends BasePage{
    @FindBy(xpath = "//p[@class='bag-item-name']/a")
    WebElement productName;
    @FindBy(xpath = "//span[contains (@class,'bag-item-price bag-item-price--current')]")
    WebElement productPrice;

    public Cart(WebDriver driver) {
        super(driver);
    }
    public String getName(){
        return productName.getText();
    }
    public String getPrice(){
        return productPrice.getText();
    }
}

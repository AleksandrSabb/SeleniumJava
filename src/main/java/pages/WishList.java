package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class WishList extends BasePage{

@FindBy(xpath = "//button[@class='toBagButton_mYSbq toBagButton_LHRGf']")
    WebElement addToCart;
@FindBy(xpath = "//div[contains (@class, 'title')]")
WebElement firstProductTitle;
@FindBy(xpath = "//button[@aria-label='Delete']")
WebElement preButtonDelete;
@FindBy(xpath = "//select[@aria-label='Size']")
WebElement selectSizeDropdown;
    public WishList(WebDriver driver) {
        super(driver);
    }
    public void addToCart() throws InterruptedException {
        waitElementToBeClickAble(addToCart);
        addToCart.click();
        Thread.sleep(2000); //need such wait for animation
    }
    public String returnProductTitle(){
        waitForVisibilityOf(firstProductTitle);
        return firstProductTitle.getText();
    }
   public void removeFromList(int number) throws InterruptedException {
        waitForVisibilityOf(preButtonDelete);
        WebElement deleteButton = driver.findElement(By.xpath("//ol//li["+number+"]//button[@aria-label='Delete']"));
        deleteButton.click();
        Thread.sleep(2000); //need such wait for animation
    }
    public String returnProductTitle(int number){
        waitForVisibilityOf(firstProductTitle);
        WebElement searchItem = driver.findElement(By.xpath("//ol/li["+number+"]//div[contains (@class, 'title')]"));
        return searchItem.getText();
    }
    public void dumpProductNameFromList(int number){
        try (FileWriter writer = new FileWriter("productNameFromWishList.txt", false)) {
            String text = returnProductTitle(number);
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public String returnProductNameFromDump(){
        try (FileReader reader = new FileReader("productNameFromWishList.txt")) {
            StringBuilder result = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                result.append((char) c);
            }
            return result.toString();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public boolean itemOnPage(){
        try{
            return firstProductTitle.findElement(By.xpath("//*[contains (text(),'"+returnProductNameFromDump()+"')]")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public void addToCartAll() throws InterruptedException {
        waitForVisibilityOf(addToCart);
        List<WebElement> quantity = driver.findElements(By.xpath("//ol/li"));
        for (int i = 1; i <= quantity.size(); i++) {
            addToCart.click();
            Thread.sleep(2000);
        }
    }
}

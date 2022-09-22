package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

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
        WebElement deleteButton = driver.findElement(By.xpath("//button[@aria-label='Delete']["+number+"]"));
        deleteButton.click();
        Thread.sleep(2000); //need such wait for animation
    }
    public String returnProductTitle(int number){
        waitForVisibilityOf(firstProductTitle);
        WebElement searchItem = driver.findElement(By.xpath("//div[contains (@class, 'title')]["+number+"]"));
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
    public void selectAnySize(){
        waitForVisibilityOf(addToCart);
        if (!addToCart.isEnabled()){
            waitElementToBeClickAble(selectSizeDropdown);
            selectSizeDropdown.click();
        }
            int clickAble = 3;
            for (int i = 2; i < clickAble; i++) {
                try {
                    waitElementToBeClickAble(driver.findElement(By.xpath("///select[@aria-label='Size']/*["+i+"]")));
                    driver.findElement(By.xpath("///select[@aria-label='Size']/*["+i+"]")).click();
                }catch (TimeoutException e){clickAble++;}
        }
    }
}

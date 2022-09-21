package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    public WishList(WebDriver driver) {
        super(driver);
    }
    public void addToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        addToCart.click();
        Thread.sleep(2000); //need such wait for animation
    }
    public String returnProductTitle(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(firstProductTitle));
        return firstProductTitle.getText();
    }
   public void removeFromList(int nomber) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(preButtonDelete));
        WebElement deteleButton = driver.findElement(By.xpath("//button[@aria-label='Delete']["+nomber+"]"));
        deteleButton.click();
        Thread.sleep(2000); //need such wait for animation
    }
    public String returnProductTitle(int nomber){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(firstProductTitle));
        WebElement searchItem = driver.findElement(By.xpath("//div[contains (@class, 'title')]["+nomber+"]"));
        return searchItem.getText();
    }
    public void dumpProductNameFromList(int nomber){
        try (FileWriter writer = new FileWriter("productNameFromWishList.txt", false)) {
            String text = returnProductTitle(nomber);
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
}

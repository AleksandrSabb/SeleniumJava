package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ProductPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    @FindBy(xpath = "//select[@data-id='sizeSelect']")
    WebElement selectSizeDropdown;
    @FindBy(xpath = "//select[@data-id='sizeSelect']//*")
    WebElement sizeValues;
    @FindBy(xpath = "//button[@data-test-id='add-button']")
    WebElement addButton;
    @FindBy(xpath = "//h1[1]")
    WebElement productName;
    @FindBy(xpath = "//span[@data-test-id='current-price']")
    WebElement productPrice;
    @FindBy(xpath = "//div[@data-test-id='data-test-id' ]")
    WebElement cartAddConfirm;
    @FindBy(xpath = "//button[@aria-label='Save for later' and @class='PHcSE kFfQP']")
    WebElement addToWishListButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectSize(String size) {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        selectSizeDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(sizeValues));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@data-id='sizeSelect']//*[4]")));
        if (Objects.equals(size, "Any")) {

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@data-id='sizeSelect']//*[4]")));
            driver.findElement(By.xpath("//select[@data-id='sizeSelect']//*[4]")).click();
        } else
            driver.findElement(By.xpath("//select[@data-id='sizeSelect']//*[contains (text(),'" + size + "')]")).click();

    }

    public void addToBag() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        addButton.click();
        wait.until(ExpectedConditions.visibilityOf(cartAddConfirm));
    }

    public void dumpName() {
        try (FileWriter writer = new FileWriter("productName.txt", false)) {
            String text = productName.getText();
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void dumpPrice() {
        try (FileWriter writer = new FileWriter("productPrice.txt", false)) {
            String text = productPrice.getText();
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public String returnPrice() {
        try (FileReader reader = new FileReader("productPrice.txt")) {
            StringBuilder resolt = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                resolt.append((char) c);
            }
            return resolt.toString();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String returnName() {
        try (FileReader reader = new FileReader("productName.txt")) {
            StringBuilder resolt = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                resolt.append((char) c);
            }
            return resolt.toString();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void addToWishList() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(addToWishListButton));
        addToWishListButton.click();
        Thread.sleep(3000);
    }
}

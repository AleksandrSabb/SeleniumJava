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
import java.util.Objects;

public class ProductPage extends BasePage {
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

    public void selectSize(String size) throws InterruptedException {
        selectSizeDropdown.click();
        waitForVisibilityOf(sizeValues);

        if (Objects.equals(size, "Any")) {
            allMarks();
        } else {

            List<WebElement> matches = driver.findElements(By.xpath("//select[@data-id='sizeSelect']//*[contains (text(),'" + size + "')]"));
            for (int i = 0; i <matches.size() ; i++) {
                if (Objects.equals(size, matches.get(i).getText().substring(0, size.length()))){
                    waitElementToBeClickAble(driver.findElement(By.xpath("//select[@data-id='sizeSelect']//*[contains (text(),'" + size + "')]["+(i+1)+"]")));
                    driver.findElement(By.xpath("//select[@data-id='sizeSelect']//*[contains (text(),'" + size + "')]["+(i+1)+"]")).click();
                    selectSizeDropdown.click();
                }
            }

        }

    }

    public void addToBag() {
        //don't work on automation soft
        addButton.click();
        waitForVisibilityOf(cartAddConfirm);
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

    public String returnName() {
        try (FileReader reader = new FileReader("productName.txt")) {
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

    public void addToWishList() throws InterruptedException {
        waitForVisibilityOf(addToWishListButton);
        addToWishListButton.click();
        Thread.sleep(3000); //need for animation
    }

    public void allMarks() {
           try {
               List<WebElement> sizeFieldList = driver.findElements(By.xpath("//select[@data-id='sizeSelect']"));

            for (int i = 0; i < sizeFieldList.size(); i++) {
                WebElement nextSizeField = driver.findElement(By.xpath("//select[@data-id='sizeSelect' and contains(@id," + i + ")]"));
                nextSizeField.click();
                waitForVisibilityOf(sizeValues);
                int clickAble = 3;
                for (int j = 2; j < clickAble; j++) {
                    try {
                        waitElementToBeClickAble(driver.findElement(By.xpath("//select[@data-id='sizeSelect' and contains(@id," + i + ")]//*[" + j + "]")));
                        driver.findElement(By.xpath("//select[@data-id='sizeSelect' and contains(@id," + i + ")]//*[" + j + "]")).click();
                        nextSizeField.click();

                    } catch (TimeoutException e) {
                        clickAble++;
                    }
                }
            }
        } catch (ElementNotInteractableException e) {
               return;
           }

    }

}

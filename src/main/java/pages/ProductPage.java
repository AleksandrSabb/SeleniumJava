package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ProductPage extends BasePage {
    @FindBy(xpath = "//select[@id='variantSelector']")
    WebElement selectSizeDropdown;
    @FindBy(xpath = "//select[@id='variantSelector']//*")
    WebElement sizeValues;
    @FindBy(xpath = "//button[@data-test-id='add-button']")
    WebElement addButton;
    @FindBy(xpath = "//h1")
    WebElement productName;
    @FindBy(xpath = "//div[@class='product-intro__head-mainprice']/div/span")
    WebElement productPrice;
    @FindBy(xpath = "//div[@data-test-id='data-test-id' ]")
    WebElement cartAddConfirm;
    @FindBy(xpath = "//section[@id='core-product']//button[@data-testid='saveForLater']")
    WebElement addToWishListButton;
    @FindBy(xpath = "//i[@class='iconfont icon-close she-close']")
    WebElement addClose;
    @FindBy(xpath = "//button[@class='she-btn-xl she-btn-black']")
    WebElement addToCart;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectSize(String size) throws InterruptedException {

        if (Objects.equals(size, "Any")) {
            selectFirstSize();
        } else {
            waitForVisibilityOf(addClose);
            addClose.click();
            WebElement reqSize = driver.findElement(By.xpath("//div[@class='product-intro__size-choose fsp-element']//p[contains (., '"+size+"')]"));
            reqSize.click();

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
            String formattedPrice = text.substring(1);
            writer.write(formattedPrice);
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

    public void selectFirstSize() {
       WebElement element =  driver.findElement(By.xpath("//div[@class='product-intro__size-choose fsp-element']//div[@class!='product-intro__size-radio product-intro__size-radio_soldout']"));
       element.click();

    }

    public void addToCart() throws InterruptedException {
        waitForVisibilityOf(toCartButton);
        toCartButton.click();
        Thread.sleep(3000); //need for animation
    }

}

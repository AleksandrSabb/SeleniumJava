package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BrandPage extends BasePage {

    @FindBy(xpath = "//div[contains (text(), 'Colour')]")
    WebElement colourDropDown;
    @FindBy(xpath = "//div[@class = 'oBN6c3V EWBhZgg']")
    WebElement colorSelectPane;
    @FindBy(xpath = "//li[@data-dropdown-id='sort']")
    WebElement sortDropdown;
    @FindBy(xpath = "//li[@id='plp_web_sort_price_low_to_high']")
    WebElement sortPriceLtHButton;
    @FindBy(xpath = "//article[@data-auto-id]")
    WebElement firstProductOnPage;

    public BrandPage(WebDriver driver) {
        super(driver);
    }

    public void setColourAs(String colour) throws InterruptedException {
        waitElementToBeClickAble(colourDropDown);
        colourDropDown.click();
        waitElementToBeClickAble(colorSelectPane);
        WebElement color = colorSelectPane.findElement(By.xpath("//div[contains (text(), '" + colour + "')]"));
        waitForVisibilityOf(color);
        color.click();
        waitElementToBeClickAble(colourDropDown);
        colourDropDown.click();
        Thread.sleep(2000); //need for load
    }

    public boolean colorAsSet(String colour) {
  /*    for get all elements
        WebElement productsFromPage = driver.findElement(By.xpath("//article[@data-auto-id]")); */

        boolean result = true;
        if (result)
            for (int i = 1; i < 5; i++) {
                waitForVisibilityOf(driver.findElement(By.xpath("//article[@data-auto-id][" + i + "]")));
                driver.findElement(By.xpath("//article[@data-auto-id][" + i + "]")).click();
                result = driver.findElement(By.xpath("//span[@class = 'product-colour']"))
                        .getText().toLowerCase().contains(colour.toLowerCase());
                driver.navigate().back();
            }
        return result;

    }


    public void sortByPriceLtH() throws InterruptedException {
        waitElementToBeClickAble(sortDropdown);
        sortDropdown.click();
        waitForVisibilityOf(sortPriceLtHButton);
        sortPriceLtHButton.click();
        Thread.sleep(3000); //need for wait loading
    }

    public boolean sortByPriceLtHcheck() {
        boolean result = true;
        for (int i = 1; i < 5; i++) {
            if (result == true) {
                String valueOne;
                String valueTwo;
                int next = i + 1;
                try {
                    valueOne = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + i + "]//span//span[@data-auto-id]")).getText();
                } catch (NoSuchElementException e) {
                    valueOne = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + i + "]//span//span[1]")).getText();
                }
                try {
                    valueTwo = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + next + "]//span[@data-auto-id]")).getText();
                } catch (NoSuchElementException e) {
                    valueTwo = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + next + "]//span//span[1]")).getText();
                }
                valueOne = valueOne.substring(1).replace(".", "");
                valueTwo = valueTwo.substring(1).replace(".", "");
                result = (Integer.parseInt(valueOne) <= Integer.parseInt(valueTwo));
                System.out.println(result);
            } else result = false;

        }
        return result;
    }

    public void clickOnProduct() throws InterruptedException {
        waitElementToBeClickAble(firstProductOnPage);
        firstProductOnPage.click();
        Thread.sleep(2000);
    }

    public void openRandomProductFromBrandPage() {
        waitForVisibilityOf(firstProductOnPage);
        List<WebElement> countOnPage = driver.findElements(By.xpath("//article[@data-auto-id='productTile']"));
        int random = 1 + (int) (Math.random() * countOnPage.size());
        WebElement randomProduct = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + random + "]"));
        try {
            waitForVisibilityOf(randomProduct);
            randomProduct.click();
        } catch (NoSuchElementException e) {
            random = (int) (Math.random() * (countOnPage.size() + 1));
            WebElement randomProduct1 = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + random + "]"));
            waitForVisibilityOf(randomProduct1);
            randomProduct1.click();
        }

    }
    public void openRandomBrandPage(){
        String link = " ";
        int gender = 1 + (int) (Math.random()*2);
        if (gender==1) link = "https://www.asos.com/men/a-to-z-of-brands/cat/?cid=1361";
        if (gender == 2) link = "https://www.asos.com/women/a-to-z-of-brands/cat/?cid=1340";
        driver.get(link);
        List<WebElement> countAlphabet = driver.findElements(By.xpath("//li/ol"));
        int selectChar = 1 + (int) (Math.random()*countAlphabet.size());
        List<WebElement> countInChar = driver.findElements(By.xpath("//li["+selectChar+"]/ol/*"));
        int selectBrand = 1+ (int) (Math.random()*countInChar.size());
        WebElement randomElement = driver.findElement(By.xpath("//li["+selectChar+"]/ol/*["+selectBrand+"]"));
        randomElement.click();
    }
    public void openSalePageGenderRandom(){
        String link = " ";
        int gender = 1 + (int) (Math.random()*2);
        if (gender==1) link = "https://www.asos.com/men/sale/cat/?cid=8409";
        if (gender == 2) link = "https://www.asos.com/women/sale/cat/?cid=7046";
        driver.get(link);
    }
}

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

    String colour = "Blue";
    @FindBy(xpath = "//div[contains (text(), 'Colour')]")
    WebElement colourDropDown;
    @FindBy(xpath = "//div[@class = 'oBN6c3V EWBhZgg']")
    WebElement colorSelectPane;
    @FindBy(xpath = "//li[@data-dropdown-id='sort']")
    WebElement sortDropdown;
    @FindBy(xpath = "//li[@id='plp_web_sort_price_low_to_high']")
    WebElement sortPriceLtHButton;
    @FindBy(xpath = "//article[@data-auto-id='productTile']")
    WebElement firstProductOnPage;

    public BrandPage(WebDriver driver) {
        super(driver);
    }

    public void setColourAs() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(colourDropDown));
        colourDropDown.click();
        wait.until(ExpectedConditions.elementToBeClickable(colorSelectPane));
        WebElement color = colorSelectPane.findElement(By.xpath("//div[contains (text(), '" + colour + "')]"));
        wait.until(ExpectedConditions.visibilityOf(color));
        color.click();
    }

    public boolean colorAsSet() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  /*    for get all elements
        WebElement productsFromPage = driver.findElement(By.xpath("//article[@data-auto-id]")); */

        boolean resolt = true;
        if (resolt == true)
            for (int i = 1; i < 5; i++) {
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//article[@data-auto-id][" + i + "]"))));
                driver.findElement(By.xpath("//article[@data-auto-id][" + i + "]")).click();
                resolt = driver.findElement(By.xpath("//span[@class = 'product-colour']"))
                        .getText().toLowerCase().contains(colour.toLowerCase());
                driver.navigate().back();
            }
        return resolt;

    }

    public void searchRealPrise() {
        String prise = "null;";
        for (int i = 1; i < 5; i++) {
            if (driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + i + "]//span//span[@data-auto-id='productTileSaleAmount']")).isDisplayed()) {
                prise = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + i + "]//span//span[@data-auto-id='productTileSaleAmount']"))
                        .getText();
                prise = prise.substring(1, prise.length() - 1);
            } else prise = "0";
            System.out.println(prise);
        }

    }

    public void sortByPriceLtH()  {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        sortDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(sortPriceLtHButton));
        sortPriceLtHButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//article[@data-auto-id='productTile']"))));

    }

    public boolean sortByPriceLtHcheck() {
       boolean resoult = true;
        for (int i = 1; i < 5; i++) {

            if (resoult == true) {
                String valueOne;
                String valueTwo;
                try {
                    valueOne = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + i + "]//span//span[@data-auto-id]")).getText();
                } catch (NoSuchElementException e) {
                    valueOne = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + i + "]//span//span[1]")).getText();
                }
                try {
                    valueTwo = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + (i + 1) + "]//span[@data-auto-id]")).getText();
                } catch (NoSuchElementException e) {
                    valueTwo = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + (i + 1) + "]//span//span[1]")).getText();
                }
                valueOne = valueOne.substring(1).replace(".", "");
                valueTwo = valueTwo.substring(1).replace(".", "");
                resoult = (Integer.parseInt(valueOne) <= Integer.parseInt(valueTwo));
                System.out.println(resoult);
            } else resoult = false;

        }
        return resoult;
    }
    public void clickOnProduct() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(firstProductOnPage));
        firstProductOnPage.click();
        Thread.sleep(20000);
    }
}

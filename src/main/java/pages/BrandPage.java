package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class BrandPage extends BasePage {

    @FindBy(xpath = "//div[contains (text(), 'Colour')]")
    WebElement colourDropDown;
    @FindBy(xpath = "//div[@class = 'collapsibleMenu_oBN6c collapsibleMenu__open_EWBhZ']")
    WebElement colorSelectPane;
    @FindBy(xpath = "//div[@class='sui-input']")
    WebElement sortDropdown;
    @FindBy(xpath = "//li[@aria-label='Price Low to High']")
    WebElement sortPriceLtHButton;
    @FindBy(xpath = "//article")
    WebElement firstProductOnPage;
    @FindBy(xpath = "//a[contains (., 'Women Clothing')]")
    WebElement womenClothing;
    @FindBy(xpath = "//div[@class='header-optimize__cate-controller']/a[contains (., 'Kids')]")
    WebElement kidsClothing;
    @FindBy(xpath = "//a[contains (., 'Men Fashion')]")
    WebElement menClothing;

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

    public boolean colorAsSet(String color) throws InterruptedException {

        boolean result = true;
        if (result)
            for (int i = 1; i < 2; i++) {  //return 5 after
                WebElement element = driver.findElement(By.xpath("//section[@role='main']//section[@role='listitem']["+i+"]//div[@class='product-card__bottom-wrapper']//a"));
                waitForVisibilityOf(element);
                Thread.sleep(2000);
                element.click();
                System.out.println(color);
                result = driver.findElement(By.xpath("//div[@class='color-block']/span[contains (@class, 'color')]"))
                        .getText().contains(color);
                System.out.println(driver.findElement(By.xpath("//div[@class='color-block']/span[contains (@class, 'color')]"))
                        .getText());
                driver.navigate().back();
            }
        return result;

    }


    public void sortByPriceLtH() throws InterruptedException {
        waitElementToBeClickAble(sortDropdown);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        actions.moveToElement(sortDropdown).build().perform();
        sortDropdown.click();
        waitForVisibilityOf(sortPriceLtHButton);
        sortPriceLtHButton.click();

        Thread.sleep(3000); //need for wait loading

        waitForVisibilityOf(driver.findElement(By.xpath("//section[@role='main']//section[@role='listitem']")));
    }

    public boolean sortByPriceLtHcheck() {

        boolean result = true;
        for (int i = 1; i < 5; i++) {
            if (result == true) {
                String valueOne;
                String valueTwo;
                int next = i + 1;

                    valueOne = driver.findElement(By.xpath("//section[@role='listitem']["+i+"]//span[@role='contentinfo']/p/span")).getText();

                    valueTwo = driver.findElement(By.xpath("//section[@role='listitem']["+next+"]//span[@role='contentinfo']/p/span")).getText();

                valueOne = valueOne.substring(1).replace(".", "");
                valueTwo = valueTwo.substring(1).replace(".", "");
                result = (Integer.parseInt(valueOne) <= Integer.parseInt(valueTwo));
                System.out.println(valueOne + "     "+valueTwo);
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
        List<WebElement> countOnPage = driver.findElements(By.xpath("//article"));
        int random = 1 + (int) (Math.random() * countOnPage.size());
        WebElement randomProduct = driver.findElement(By.xpath("//article[" + random + "]"));
        try {
            waitForVisibilityOf(randomProduct);
            randomProduct.click();
        } catch (NoSuchElementException e) {
            random = (int) (Math.random() * (countOnPage.size() + 1));
            WebElement randomProduct1 = driver.findElement(By.xpath("//article[" + random + "]"));
            waitForVisibilityOf(randomProduct1);
            randomProduct1.click();
        }

    }

    public void openCategory(String category) {
        if (Objects.equals(category, "Any")) {
            int randomCategory = 1 + (int) (Math.random() * 3);
            switch (randomCategory) {
                case 1:
                    womenClothing.click();
                    break;
                case 2:
                    kidsClothing.click();
                    break;
                case 3:
                    menClothing.click();
                    break;
            }
        } else {
            driver.findElement(By.xpath("//div[@class='header-optimize__cate-controller']/a[contains (., '" + category + "')]")).click();
        }
    }

    public void openSalePageGenderRandom() {
        String link = " ";
        int gender = 1 + (int) (Math.random() * 2);
        if (gender == 1) link = "https://www.asos.com/men/sale/cat/?cid=8409";
        if (gender == 2) link = "https://www.asos.com/women/sale/cat/?cid=7046";
        driver.get(link);
    }

    public void selectColor(String color) throws InterruptedException {
        switch (color){
            case "Any":
                List <WebElement> allColors = driver.findElements(By.xpath("//div[@aria-label='Color']//img"));
                int randomColorNumber = 1 + (int) (Math.random() * allColors.size());
                WebElement randomColor = driver.findElement(By.xpath("//div[@class='side-filter__item-content-each side-filter__item-content-each_img']["+randomColorNumber+"]//img"));
                dumpColourSelect(randomColor);
                randomColor.click();
                break;
            default:
                WebElement element = driver.findElement(By.xpath("//div[@aria-label='Color']//img[@title='"+color+"']"));
                dumpColourSelect(element);
                element.click();
                break;
        }
        Thread.sleep(4000);
    }
    public void dumpColourSelect(WebElement element){
        try (FileWriter writer = new FileWriter("randomColour.txt", false)) {
            String text = element.getAttribute("title");
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public String getColorSelect(){
        try (FileReader reader = new FileReader("randomColour.txt")) {
            StringBuilder result = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                result.append((char) c);
            }
            String getColor = result.toString();
            int endChar = getColor.indexOf('(');
            //getColor = getColor.substring(0,endChar);
            return getColor;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}

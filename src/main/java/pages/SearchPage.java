package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{

    @FindBy(xpath = "//input[@name='header-search']")
    WebElement searchField;
    @FindBy(xpath = "//h1[@class='top-info__title-name']")
    WebElement resultTitle;

    public SearchPage(WebDriver driver) {
        super(driver);
    }
    public void search(String text) {
        searchField.click();
        searchField.sendKeys(text);
    }
    public void searchPressEnter(){
        searchField.sendKeys( Keys.ENTER);
    }
    public String getResultTitle(){
        return resultTitle.getText();

    }

    public String getSearchItem(String text) {
        return text.toLowerCase();
    }
}

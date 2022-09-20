package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{

    @FindBy(xpath = "//input[@type='search']")
    WebElement searchField;
    @FindBy(xpath = "//div[@id='search-term-banner']//*[2]")
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
        String result = resultTitle.getText();
        int size = result.length();
        result = result.substring(1,size-1).toLowerCase();
        return result;
    }

    public String getSearchItem(String text) {
        return text.toLowerCase();
    }
}

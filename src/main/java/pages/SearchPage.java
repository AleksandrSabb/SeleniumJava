package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{
    private WebDriver driver;
    public String searchItem = "Nike";
    @FindBy(xpath = "//a[@data-testid='signin-link']")
    WebElement signinLink;
    @FindBy(xpath = "//input[@type='search']")
    WebElement searchField;
    @FindBy(xpath = "//div[@id='search-term-banner']//*[2]")
    WebElement resoultTitle;

    public SearchPage(WebDriver driver) {
        super(driver);
    }
    public void search() {
        searchField.click();
        searchField.sendKeys(searchItem);
    }
    public void searchPressEnter(){
        searchField.sendKeys( Keys.ENTER);
    }
    public String getResoultTitle(){
        String resoult = resoultTitle.getText();
        int size = resoult.length();
        resoult = resoult.substring(1,size-1).toLowerCase();
        return resoult;
    }

    public String getSearchItem() {
        return searchItem.toLowerCase();
    }
}

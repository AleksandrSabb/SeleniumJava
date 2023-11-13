package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    WebDriver driver;
    @FindBy(xpath = "//button[@data-testid='myAccountIcon']")
    WebElement myAccountDropdown;
    @FindBy(xpath = "//a[@aria-label='Account']")
    WebElement signinButton;
    @FindBy(xpath = "//input[@type='search']")
    WebElement searchField;
    @FindBy(xpath = "//div[@id='miniBagDropdown']")
    WebElement cartButton;
    @FindBy(xpath = "//a[@data-testid='savedItemsIcon']")
    WebElement wishListButton;
    @FindBy(xpath = "//a[@data-test-id='bag-link']")
    WebElement toCartButton;
    @FindBy(xpath = "//div[@class='bag-item-descriptions']")
    WebElement productFrame;
    @FindBy(xpath = "//div[contains (@id, 'footer')]")
    WebElement footer;
    @FindBy(xpath = "//a[@href='https://www.asos.com/discover/our-apps/']")
    WebElement mobileAndAsosAppLink;
    @FindBy(xpath = "//i[@class='iconfont icon-close she-close']")
    WebElement addClose;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToMyAccount() {
        Actions actions = new Actions(driver);
        actions.moveToElement(myAccountDropdown).build().perform();
    }

    public void closeAdd() {
        waitForVisibilityOf(addClose);
        addClose.click();
    }

    public void clickOnSignInLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signinButton));
        signinButton.click();
    }

    public void Login() {
        driver.get("https://www.asos.com/");
        BasePage basePage = new BasePage(driver);
        basePage.navigateToMyAccount();
        SingIn singIn = new SingIn(driver);
        singIn.enterEmail("True value");
        singIn.enterPass("True value");
        singIn.clickSignIn();
    }

    public void openCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, -500).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        actions.moveToElement(cartButton).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(toCartButton));
        toCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(productFrame));
        //driver.get("https://www.asos.com/bag?ctaref=mini+bag"); if cart is empty
    }

    public void openWishList() {
        wishListButton.click();
    }
    public void scrollToFooter(){
        Actions actions = new Actions(driver);
        actions.scrollToElement(footer).build().perform();
    }
    public void navigateToUorApp(){
        mobileAndAsosAppLink.click();
    }
    // have too mach waits in code, so I create methods;
    public void waitForVisibilityOf(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitElementToBeClickAble(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}

package StepsDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.BasePage;
import pages.SingIn;

public class openMainPage {
   private WebDriver driver;
    private BasePage basePage;
    private SingIn singIn;
    private String pageTitle;


    @Given("Page is opened")
    public void pageIsOpened() {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().fullscreen();
        driver.get("https://www.amazon.com/");
        pageTitle = driver.getTitle();
    }

    @When("I navigate to Hello, sig in")
    public void iNavigateToHelloSigIn() {
      basePage = new BasePage(driver);
        basePage.navigateMouseToSignIn();
    }

    @And("I click on sign in button")
    public void iClickOnSignInButton()  {
        basePage = new BasePage(driver);
        basePage.pressLogButton();
    }

    @And("I enter valid email")
    public void iEnterValidEmail() throws InterruptedException {
        singIn = new SingIn(driver);
        singIn.enterEmail();
        singIn.clickContinue();
    }

    @And("I enter valid password")
    public void iEnterValidPassword() throws InterruptedException {
        singIn = new SingIn(driver);
        singIn.enterPass();
        singIn.clickSignIn();
        Thread.sleep(3000);
    }

    @Then("I see main page")
    public void iSeeMainPage() {
        Assert.assertEquals(pageTitle,driver.getTitle());
    }
}

package StepsDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.util.NoSuchElementException;

public class steps {
    private WebDriver driver;
    private BasePage basePage;
    private SingIn singIn;
    private SearchPage searchPage;
    private BrandPage brandPage;
    private ProductPage productPage;
    private Cart cart;
    private WishList wishList;
    @Given("Page is opened")
    public void pageIsOpened() {
        driver = WebDriverManager.chromedriver().create();
        driver.get("https://www.asos.com/");
    }

    @When("I navigate to profile icon & click on signIn link")
    public void iNavigateToProfileIconClickOnSignInLink() {
        basePage = new BasePage(driver);
        basePage.navigateToMyAccount();
    }

    @And("I click on signIn link")
    public void iClickOnSignInLink() {
        basePage = new BasePage(driver);
        basePage.clickOnSignInLink();
    }

    @And("I enter valid email")
    public void iEnterValidEmail() {
        singIn = new SingIn(driver);
        singIn.enterEmail();
    }

    @And("I enter valid password")
    public void iEnterValidPassword() {
        singIn = new SingIn(driver);
        singIn.enterPass();
    }

    @And("I click on signIn button")
    public void iClickOnSignInButton() throws InterruptedException {
        singIn = new SingIn(driver);
        singIn.clickSignIn();
        Thread.sleep(3000);
    }

    @Then("I see main page")
    public void iSeeMainPage() {
        Assert.assertEquals("ASOS | Online Shopping for the Latest Clothes & Fashion", driver.getTitle());
    }
    @When("I type text in search field")
    public void iTypeTextInSearchField() {
        searchPage = new SearchPage(driver);
        searchPage.search();
    }

    @And("Press Enter")
    public void pressEnter() {
        searchPage = new SearchPage(driver);
        searchPage.searchPressEnter();
    }

    @Then("I see resoult of my request")
    public void iSeeResoultOfMyRequest() {
        searchPage = new SearchPage(driver);
        Assert.assertEquals(searchPage.getSearchItem(),searchPage.getResoultTitle());
    }


    @Given("Brand page is opened")
    public void brandPageIsOpened() {
        driver = WebDriverManager.chromedriver().create();
        driver.get("https://www.asos.com/men/a-to-z-of-brands/nike/cat/?cid=4766&ctaref=hp|mw|prime|logo|10|nike");
    }

    @When("I set colour dropdown")
    public void iSetColourDropdown() throws InterruptedException {
        brandPage = new BrandPage(driver);
        brandPage.setColourAs();
        Thread.sleep(1000);
    }

    @Then("I see only products with color i set")
    public void iSeeOnlyProductsWithColorISet() {
        brandPage = new BrandPage(driver);
        Assert.assertTrue(brandPage.colorAsSet());
    }

    @When("I select sort by price from low to high")
    public void iSelectSortByPriceFromLowToHigh() {
        brandPage = new BrandPage(driver);
        brandPage.sortByPriceLtH();

    }

    @Then("Prise of product is lower or equal to the next product")
    public void priseOfProductIsLowerOrEqualToTheNextProduct() {
        brandPage = new BrandPage(driver);
        Assert.assertTrue(brandPage.sortByPriceLtHcheck());
    }

    @When("I click on product")
    public void iClickOnProduct() throws InterruptedException {
        brandPage = new BrandPage(driver);
        brandPage.clickOnProduct();
    }

    @And("I select size")
    public void iSelectSize()  {

        productPage = new ProductPage(driver);
        productPage.selectSize("Any");
        productPage.dumpName();
    }

    @And("I Click add to Add to bag")
    public void iClickAddToAddToBag() throws InterruptedException {
        productPage = new ProductPage(driver);
        //productPage.addToBag(); go around blocker
        productPage.addToWishList();
        basePage = new BasePage(driver);
        basePage.openWishList();
        wishList = new WishList(driver);
        wishList.addToCart();
        basePage = new BasePage(driver);
        basePage.openCart();
    }

    @Then("Product is placed to my cart")
    public void productIsPlacedToMyCart() throws InterruptedException {

        cart = new Cart(driver);
        Thread.sleep(10000);
        Assert.assertEquals(cart.getName(),productPage.returnName());
    }

    @Given("Product page is opened")
    public void productPageIsOpened()  {
        driver = WebDriverManager.chromedriver().create();
        driver.get("https://www.asos.com/nike-golf/nike-golf-air-max-90-shoes-in-blue-and-grey/prd/202358639?colourWayId=202358659&cid=4766");
    }
}

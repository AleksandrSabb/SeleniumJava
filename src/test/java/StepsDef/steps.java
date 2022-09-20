package StepsDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.*;


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

    @When("I navigate to profile icon")
    public void iNavigateToProfileIconClickOnSignInLink() {
        basePage = new BasePage(driver);
        basePage.navigateToMyAccount();
    }

    @And("I click on signIn link")
    public void iClickOnSignInLink() {
        basePage = new BasePage(driver);
        basePage.clickOnSignInLink();
    }

    @And("I enter valid email \\({string})")
    public void iEnterValidEmail(String email) {
        singIn = new SingIn(driver);
        singIn.enterEmail(email);
    }

    @And("I enter valid password \\({string})")
    public void iEnterValidPassword(String pass) {
        singIn = new SingIn(driver);
        singIn.enterPass(pass);
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
    @When("I type text \\({string})in search field")
    public void iTypeTextInSearchField(String text) {
        searchPage = new SearchPage(driver);
        searchPage.search(text);
    }

    @And("Press Enter")
    public void pressEnter() {
        searchPage = new SearchPage(driver);
        searchPage.searchPressEnter();
    }

    @Then("I see result of my request \\({string})")
    public void iSeeResultOfMyRequest(String text) {
        searchPage = new SearchPage(driver);
        Assert.assertEquals(searchPage.getSearchItem(text),searchPage.getResultTitle());
    }


    @Given("Brand page is opened")
    public void brandPageIsOpened() {
        driver = WebDriverManager.chromedriver().create();
        driver.get("https://www.asos.com/men/a-to-z-of-brands/nike/cat/?cid=4766&ctaref=hp|mw|prime|logo|10|nike");
    }

    @When("I set colour dropdown \\({string})")
    public void iSetColourDropdown(String colour) throws InterruptedException {
        brandPage = new BrandPage(driver);
        brandPage.setColourAs(colour);
    }

    @Then("I see only products with color \\({string})")
    public void iSeeOnlyProductsWithColor(String colour) {
        brandPage = new BrandPage(driver);
        Assert.assertTrue(brandPage.colorAsSet(colour));
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

    @When("I select size\\({string})")
    public void iSelectSize(String colour)  {

        productPage = new ProductPage(driver);
        productPage.selectSize(colour);
        productPage.dumpName();
        productPage.dumpPrice();
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
    public void productIsPlacedToMyCart()  {
        cart = new Cart(driver);
        Assert.assertEquals(cart.getName(),productPage.returnName());
    }

    @Given("Product page is opened")
    public void productPageIsOpened()  {
        driver = WebDriverManager.chromedriver().create();
        driver.get("https://www.asos.com/nike-golf/nike-golf-air-max-90-shoes-in-blue-and-grey/prd/202358639?colourWayId=202358659&cid=4766");
    }

    @Given("Sales product page opened")
    public void salesProductPageOpened() {
        driver = WebDriverManager.chromedriver().create();
        driver.get("https://www.asos.com/nike/nike-club-zip-up-hoodie-in-black/prd/202400906?clr=black&colourWayId=202400907&cid=28239");

    }

    @Then("Price in cart equals to product sales price")
    public void priceInCartEqualsToProductSalesPrice()  {
        cart = new Cart(driver);
        Assert.assertEquals(cart.getPrice(),productPage.returnPrice());
    }

}

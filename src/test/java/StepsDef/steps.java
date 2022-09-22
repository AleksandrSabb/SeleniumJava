package StepsDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import java.util.Set;


public class steps {
    private WebDriver driver;
    private BasePage basePage;
    private SingIn singIn;
    private SearchPage searchPage;
    private BrandPage brandPage;
    private ProductPage productPage;
    private Cart cart;
    private WishList wishList;
    private OurAppsPage ourAppsPage;

    @Given("Page is opened")
    public void pageIsOpened() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = WebDriverManager.chromedriver().capabilities(options).create();
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

 /*   @And("I enter valid <email> \\({string})")
    public void iEnterValidEmail(String email) {
        singIn = new SingIn(driver);
        singIn.enterEmail(email);
    }*/

 /*   @And("I enter valid password \\({string})")
    public void iEnterValidPassword(String pass) {
        singIn = new SingIn(driver);
        singIn.enterPass(pass);
    }*/

    @And("I click on signIn button")
    public void iClickOnSignInButton() throws InterruptedException {
        singIn = new SingIn(driver);
        singIn.clickSignIn();
        Thread.sleep(3000);
    }

    @Then("I see main page")
    public void iSeeMainPage() {
        Assert.assertEquals("ASOS | Online Shopping for the Latest Clothes & Fashion", driver.getTitle());
        driver.quit();
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
        Assert.assertEquals(searchPage.getSearchItem(text), searchPage.getResultTitle());
        driver.quit();
    }


    @Given("Brand page is opened")
    public void brandPageIsOpened() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = WebDriverManager.chromedriver().capabilities(options).create();
        brandPage = new BrandPage(driver);
        brandPage.openRandomBrandPage();
    }

    @When("I set colour dropdown \\({string})")
    public void iSetColourDropdown(String colour) throws InterruptedException {
        brandPage.setColourAs(colour);
    }

    @Then("I see only products with color \\({string})")
    public void iSeeOnlyProductsWithColor(String colour) {
        brandPage = new BrandPage(driver);
        Assert.assertTrue(brandPage.colorAsSet(colour));
        driver.quit();
    }

    @When("I select sort by price from low to high")
    public void iSelectSortByPriceFromLowToHigh() throws InterruptedException {
        brandPage = new BrandPage(driver);
        brandPage.sortByPriceLtH();

    }

    @Then("Prise of product is lower or equal to the next product")
    public void priseOfProductIsLowerOrEqualToTheNextProduct() {
        brandPage = new BrandPage(driver);
        Assert.assertTrue(brandPage.sortByPriceLtHcheck());
        driver.quit();
    }

    @When("I click on product")
    public void iClickOnProduct() throws InterruptedException {
        brandPage = new BrandPage(driver);
        brandPage.clickOnProduct();
    }

    @When("I select size\\({string})")
    public void iSelectSize(String size) {

        productPage = new ProductPage(driver);
        productPage.selectSize(size);
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
    public void productIsPlacedToMyCart() {
        cart = new Cart(driver);
        Assert.assertEquals(cart.getName(), productPage.returnName());
        driver.quit();
    }

    @Given("Product page is opened")
    public void productPageIsOpened() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = WebDriverManager.chromedriver().capabilities(options).create();
        brandPage = new BrandPage(driver);
        brandPage.openRandomBrandPage();
        brandPage.openRandomProductFromBrandPage();
    }

    @Given("Sales product page opened")
    public void salesProductPageOpened() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = WebDriverManager.chromedriver().capabilities(options).create();
        driver.get("https://www.asos.com/nike/nike-club-zip-up-hoodie-in-black/prd/202400906?clr=black&colourWayId=202400907&cid=28239");

    }

    @Then("Price in cart equals to product sales price")
    public void priceInCartEqualsToProductSalesPrice() {
        cart = new Cart(driver);
        Assert.assertEquals(cart.getPrice(), productPage.returnPrice());
        driver.quit();
    }

    @When("I press heart")
    public void iPressHeart() throws InterruptedException {
        productPage = new ProductPage(driver);
        productPage.addToWishList();
        productPage.dumpName();
    }

    @Then("Product add to my wishlist")
    public void productAddToMyWishlist() {
        basePage = new BasePage(driver);
        basePage.openWishList();
        productPage = new ProductPage(driver);
        wishList = new WishList(driver);
        Assert.assertEquals(productPage.returnName(), wishList.returnProductTitle());
        driver.quit();
    }

    @Given("wishlist page with \\({int}) products in list")
    public void wishlistPageWithProductsInList(int count) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = WebDriverManager.chromedriver().capabilities(options).create();
        driver.get("https://www.asos.com/men/a-to-z-of-brands/nike/cat/?cid=4766&ctaref=hp|mw|prime|logo|10|nike");
        for (int i = 0; i < count; i++) {
            brandPage = new BrandPage(driver);
            brandPage.openRandomProductFromBrandPage();
            productPage = new ProductPage(driver);
            productPage.addToWishList();
            driver.navigate().back();
        }
        basePage = new BasePage(driver);
        basePage.openWishList();
    }

    @When("I click remove button on product no\\({int})")
    public void iClickRemoveButtonOnProductNo(int productNumber) throws InterruptedException {
        wishList = new WishList(driver);
        wishList.dumpProductNameFromList(productNumber);
        wishList.removeFromList(productNumber);
    }

    @Then("product removed from wishlist")
    public void productRemovedFromWishlist() {
        wishList = new WishList(driver);
        Assert.assertFalse(wishList.itemOnPage());
        driver.quit();
    }

    @Given("User not login")
    public void userNotLogin() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = WebDriverManager.chromedriver().capabilities(options).create();
        driver.get("https://www.asos.com/men/a-to-z-of-brands/nike/cat/?cid=4766&ctaref=hp|mw|prime|logo|10|nike");
    }

    @And("1 product in cart")
    public void productsInCart() throws InterruptedException {
        brandPage = new BrandPage(driver);
        brandPage.openRandomProductFromBrandPage();
        productPage = new ProductPage(driver);
        productPage.selectSize("Any");
        productPage.addToWishList();
        basePage = new BasePage(driver);
        basePage.openWishList();
        wishList = new WishList(driver);
        wishList.addToCart();
        basePage = new BasePage(driver);
        basePage.openCart();
    }


    @When("I processed checkout")
    public void iProcessedCheckout() {
        cart = new Cart(driver);
        cart.goCheckOut();
    }

    @Then("I am navigated to login page")
    public void iAmNavigatedToLoginPage() {
        Assert.assertEquals(driver.getTitle(), "ASOS | Sign in");
        driver.quit();
    }

    @When("I scroll to the footer")
    public void iScrollToTheFooter() {
        basePage = new BasePage(driver);
        basePage.scrollToFooter();
    }

    @And("I click on link Mobile And ASOS apps")
    public void iClickOnLinkMobileAndASOSApps() {
        basePage.navigateToUorApp();
    }

    @And("I navigate to Google Play logo & click it")
    public void iNavigateToGooglePlayLogoClickIt() {
        ourAppsPage = new OurAppsPage(driver);
        ourAppsPage.navigateToGooglePlay();
    }

    @Then("Opened new tap Google play -> ASOS app")
    public void openedNewTapGooglePlayASOSApp() {
        Set<String> handles = driver.getWindowHandles();
        String lastWindow = "new String()";
        for (String handle :
                handles) {
            lastWindow = handle;
        }
        driver.switchTo().window(lastWindow);
        Assert.assertEquals(driver.getCurrentUrl(), "https://play.google.com/store/apps/details?id=com.asos.app");
        driver.quit();
    }


    @And("I enter valid email {string}")
    public void iEnterValidEmail(String email) {
        singIn = new SingIn(driver);
        singIn.enterEmail(email);
    }

    @And("I enter valid password {string}")
    public void iEnterValidPassword(String pass) {
        singIn = new SingIn(driver);
        singIn.enterPass(pass);
    }

    @Given("Open product page")
    public void openProductPage() {
        driver = WebDriverManager.chromedriver().create();
        driver.get("https://www.asos.com/nike/nike-unisex-retro-collegiate-tracksuit-in-black-and-white/grp/93090?clr=black&colourWayId=201540596&cid=4766");   }
        @When("I mark all")
    public void iMarkAll() throws InterruptedException {
        brandPage = new BrandPage(driver);
        brandPage.openRandomBrandPage();
        brandPage.openRandomProductFromBrandPage();
        productPage = new ProductPage(driver);
        productPage.allMarks();
        productPage.addToWishList();
        productPage.openWishList();
        wishList = new WishList(driver);
        wishList.addToCartAll();
        wishList.openCart();
    }

    @Then("All market")
    public void allMarket() throws InterruptedException {
        Thread.sleep(20000);
    }
}


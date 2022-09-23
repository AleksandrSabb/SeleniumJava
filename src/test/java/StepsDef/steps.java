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

import java.util.Objects;
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


    @Given("Brand {string} is opened")
    public void brandIsOpened(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = WebDriverManager.chromedriver().capabilities(options).create();
        brandPage = new BrandPage(driver);
        if (Objects.equals(url, "Any")) brandPage.openRandomBrandPage();
        else driver.get(url);
    }

    @When("I set {string} dropdown")
    public void iSetDropdown(String colour) throws InterruptedException {
        if (Objects.equals(colour, "Any")) brandPage.selectAnyColorFromDropdown();
        else brandPage.setColourAs(colour);
    }

    @Then("I see only products with {string}")
    public void iSeeOnlyProductsWith(String colour) {
        brandPage = new BrandPage(driver);
        if (Objects.equals(colour, "Any")) Assert.assertTrue(brandPage.colorAsSet(brandPage.getColorSelect()));
        else Assert.assertTrue(brandPage.colorAsSet(colour));

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

    @When("I select {string}")
    public void iSelect(String size) throws InterruptedException {
        if (size == "Any") {
            productPage = new ProductPage(driver);
            productPage.allMarks();
            productPage.dumpName();
            productPage.dumpPrice();
        } else {
            productPage = new ProductPage(driver);
            productPage.selectSize(size);
            productPage.dumpName();
            productPage.dumpPrice();
        }

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

    @Given("Product {string} is opened")
    public void productIsOpened(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = WebDriverManager.chromedriver().capabilities(options).create();
        if (Objects.equals(url, "Any")) {
            brandPage = new BrandPage(driver);
            brandPage.openRandomBrandPage();
            brandPage.openRandomProductFromBrandPage();
        } else {
            productPage = new ProductPage(driver);
            driver.get(url);
        }
    }

    @Given("Sales product {string} opened")
    public void salesProductOpened(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = WebDriverManager.chromedriver().capabilities(options).create();
        if (Objects.equals(url, "Any")){
            brandPage = new BrandPage(driver);
            brandPage.openSalePageGenderRandom();
            brandPage.openRandomProductFromBrandPage();
        }
        else {
            driver.get(url);
        }

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
        for (int i = 0; i < count; i++) {
            brandPage = new BrandPage(driver);
            brandPage.openRandomBrandPage();
            brandPage.openRandomProductFromBrandPage();
            productPage = new ProductPage(driver);
            productPage.allMarks();
            productPage.addToWishList();
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
        brandPage = new BrandPage(driver);
        brandPage.openRandomBrandPage();
    }

    @And("1 product in cart")
    public void productsInCart() throws InterruptedException {
        brandPage.openRandomProductFromBrandPage();
        productPage = new ProductPage(driver);
        productPage.allMarks();
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


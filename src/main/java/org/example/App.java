package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.SingIn;

import java.util.List;

import java.util.Set;

/**
 * Hello world!
 */
public class App {
    static WebDriver driver = WebDriverManager.chromedriver().create();


    public static void main(String[] args) {

        extracted();
        boolean resoult=true;
        for (int i = 1; i < 20; i++) {

            if (resoult==true) {
                String valueOne;
                String valueTwo;
                try {
                    valueOne = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + i + "]//span//span[@data-auto-id]")).getText();
                } catch (NoSuchElementException e) {
                    valueOne = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + i + "]//span//span[1]")).getText();
                }
                try {
                    valueTwo = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + (i+1) + "]//span[@data-auto-id]")).getText();
                } catch (NoSuchElementException e) {
                    valueTwo = driver.findElement(By.xpath("//article[@data-auto-id='productTile'][" + (i + 1) + "]//span//span[1]")).getText();
                }
                valueOne = valueOne.substring(1).replace(".", "");
                valueTwo = valueTwo.substring(1).replace(".", "");
                resoult = (Integer.parseInt(valueOne) <= Integer.parseInt(valueTwo));
                System.out.println(resoult);
            }
            else resoult = false;

        }
        System.out.println(resoult+"final");
    }

    private static void extracted() {
        driver.get("https://www.asos.com/men/a-to-z-of-brands/nike/cat/?cid=4766&ctaref=hp%7Cmw%7Cprime%7Clogo%7C10%7Cnike&currentpricerange=0-205&ew6325sf1=fail&ew6325sf2=fail&refine=base_colour:3&scrollTo=product-202294949&sort=priceasc");
    }

}



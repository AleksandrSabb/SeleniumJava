package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.SingIn;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import java.util.Set;

/**
 * Hello world!
 */
public class App {
    // static WebDriver driver = WebDriverManager.chromedriver().create();


    public static void main(String[] args) {
        // extracted();
        try (FileWriter writer = new FileWriter("notes3.txt", false)) {
            // запись всей строки
            String text = "Hello Gold!";
            writer.write(text);

            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        try (FileReader reader = new FileReader("notes3.txt")) {
            // читаем посимвольно
            StringBuilder resolt = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {

                resolt.append((char) c);
            }
            System.out.println(resolt);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }


/*
    private static void extracted() {
        driver.get("https://www.asos.com/men/a-to-z-of-brands/nike/cat/?cid=4766&ctaref=hp%7Cmw%7Cprime%7Clogo%7C10%7Cnike&currentpricerange=0-205&ew6325sf1=fail&ew6325sf2=fail&refine=base_colour:3&scrollTo=product-202294949&sort=priceasc");
    }
*/

    }
}



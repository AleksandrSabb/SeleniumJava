package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Cookie;
/**
 * Hello world!
 *
 */
public class App 
{
    static WebDriver driver;
    public static void main( String[] args )
    {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");

    }
}

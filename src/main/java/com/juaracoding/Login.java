package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Login {
    public static void main(String[] args) {
        String path = "C:\\MyTools\\chromedriver.exe";
        System.setProperty("webdrive.chrome.driver", path);

        WebDriver driver = new ChromeDriver();

        String web = "https://www.saucedemo.com";
        driver.manage().window().maximize();
        driver.get(web);

        //Web Scraping
        driver.getTitle();
        System.out.println("Login Page Accessed.");

        //input keys
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        System.out.println("Login Button Clicked");

        delay(3);

        String appLogo = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div")).getText();
        Assert.assertEquals(appLogo, "Swag Labs");
        System.out.println("Home page accessed.");

        driver.quit();
    }

    static void delay(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

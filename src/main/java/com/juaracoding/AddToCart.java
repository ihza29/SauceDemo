package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AddToCart {
    public static void main(String[] args) {
        String path = "C:\\MyTools\\chromedriver.exe";
        System.setProperty("webdrive.chrome.driver", path);

        WebDriver driver = new ChromeDriver();

        String web = "https://www.saucedemo.com";
        driver.manage().window().maximize();
        driver.get(web);

        String item = "Sauce Labs Bike Light";

        //login steps
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


        //navigate to Item
        WebElement itemNameHome = driver.findElement(By.xpath("//div[contains(@class, 'inventory_item_name ') and text()='" + item + "']"));
        itemNameHome.click();
        System.out.println("Item Clicked");

        //add to cart item
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bike-light\"]")).click();
        System.out.println("Added To Cart");

        //verify item in cart
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        delay(3);
        WebElement itemNameCart = driver.findElement(By.xpath("//div[contains(@class, 'inventory_item_name') and text()='" + item + "']"));
        Assert.assertEquals(itemNameCart.getText(), "Sauce Labs Bike Light");
        System.out.println("Item Verified");
        delay(3);
        driver.findElement(By.id("checkout")).click();


        //checkout
        driver.findElement(By.id("first-name")).sendKeys("Ihza");
        driver.findElement(By.id("last-name")).sendKeys("Jkt");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        WebElement successCheckoutMessage = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2"));

        Assert.assertEquals(successCheckoutMessage.getText(), "Thank you for your order!");
        System.out.println("Item successfully checkout");

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

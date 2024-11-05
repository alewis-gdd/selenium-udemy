package org.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstTestCase {

    @Test
    public void GuestCheckoutUsingDirectBankTransfer(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://askomdch.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //Select 'Store' from nav bar
        driver.findElement(By.cssSelector("#menu-item-1227 > a")).click();

        //Search Blue in search bar
        driver.findElement(By.cssSelector("#woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.cssSelector("#woocommerce_product_search-1 > form > button")).click();
        Assert.assertEquals(
                driver.findElement(By.cssSelector("#main > div > header > h1")).getText(),
                "Search results: “Blue”"
        );

        //Add shoes to cart
        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
        driver.findElement(By.cssSelector("a[title='View cart']")).click();
        Assert.assertEquals(
                driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(),
                "Blue Shoes"
        );

        //Proceed to checkout
        driver.findElement(By.cssSelector(".checkout-button.button.alt.wc-forward")).click();

        //Entering billing address
        driver.findElement(By.id("billing_first_name")).sendKeys("demo");
        driver.findElement(By.id("billing_last_name")).sendKeys("user");
        driver.findElement(By.id("billing_address_1")).sendKeys("39 Mutton Street");
        driver.findElement(By.id("billing_city")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_postcode")).sendKeys("31134");
        driver.findElement(By.id("billing_email")).sendKeys("demouser@test.com");

        driver.findElement(By.id("payment_method_bacs")).click();
        driver.findElement(By.id("place_order")).click();

    }

}

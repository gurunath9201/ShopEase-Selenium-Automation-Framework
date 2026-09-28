package com.shopEase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    private By cartItems = By.className("cart_item");

    private By checkoutButton = By.id("checkout");

    private By continueShoppingButton = By.id("continue-shopping");


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }


    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }


    public boolean containsProduct(String productName) {

        String xpath =
                "//div[contains(@class,'cart_item')]" +
                "//div[contains(@class,'inventory_item_name') " +
                "and normalize-space()=\"" + productName + "\"]";

        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }


    public String getProductPrice(String productName) {

        String xpath =
                "//div[contains(@class,'cart_item')]" +
                "[.//div[contains(@class,'inventory_item_name') " +
                "and normalize-space()=\"" + productName + "\"]]" +
                "//div[contains(@class,'inventory_item_price')]";

        return driver.findElement(By.xpath(xpath)).getText();
    }


    public void removeProduct(String productName) {

        String xpath =
                "//div[contains(@class,'cart_item')]" +
                "[.//div[contains(@class,'inventory_item_name') " +
                "and normalize-space()=\"" + productName + "\"]]" +
                "//button[contains(@id,'remove')]";

        driver.findElement(By.xpath(xpath)).click();
    }


    private String getProductId(String productName) {

        if (productName.equals("Sauce Labs Backpack")) {
            return "sauce-labs-backpack";
        }

        if (productName.equals("Sauce Labs Bike Light")) {
            return "sauce-labs-bike-light";
        }

        if (productName.equals("Sauce Labs Bolt T-Shirt")) {
            return "sauce-labs-bolt-t-shirt";
        }

        if (productName.equals("Sauce Labs Fleece Jacket")) {
            return "sauce-labs-fleece-jacket";
        }

        if (productName.equals("Sauce Labs Onesie")) {
            return "sauce-labs-onesie";
        }

        if (productName.equals("Test.allTheThings() T-Shirt (Red)")) {
            return "test.allthethings()-t-shirt-(red)";
        }

        throw new IllegalArgumentException(
                "Unknown product: " + productName
        );
    }


    public void checkout() {
        driver.findElement(checkoutButton).click();
    }


    public void continueShopping() {
        driver.findElement(continueShoppingButton).click();
    }
}
package com.shopEase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;

    private By pageTitle = By.className("title");

    private By products = By.className("inventory_item");

    private By productNames = By.className("inventory_item_name");

    private By productPrices = By.className("inventory_item_price");

    private By productImages = By.cssSelector(".inventory_item_img img");

    private By cartIcon = By.className("shopping_cart_link");

    private By cartBadge = By.className("shopping_cart_badge");

    private By sortDropdown = By.className("product_sort_container");

    private By menuButton = By.id("react-burger-menu-btn");

    private By logoutButton = By.id("logout_sidebar_link");


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getPageTitle() {

        return driver.findElement(pageTitle).getText();
    }


    public int getProductCount() {

        return driver.findElements(products).size();
    }


    public List<String> getProductNames() {

        List<String> names = new ArrayList<>();

        for (WebElement element : driver.findElements(productNames)) {

            names.add(element.getText());
        }

        return names;
    }


    public List<Double> getProductPrices() {

        List<Double> prices = new ArrayList<>();

        for (WebElement element : driver.findElements(productPrices)) {

            String price = element.getText()
                    .replace("$", "")
                    .trim();

            prices.add(Double.parseDouble(price));
        }

        return prices;
    }


    public boolean areAllImagesLoaded() {

        List<WebElement> images =
                driver.findElements(
                        By.cssSelector(".inventory_item_img img")
                );

        if (images.isEmpty()) {
            return false;
        }

        for (WebElement image : images) {

            if (!image.isDisplayed()) {
                return false;
            }

            String src = image.getAttribute("src");

            if (src == null || src.trim().isEmpty()) {
                return false;
            }
        }

        return true;
    }


    public String getProductDescription(String productName) {

        String xpath =
                "//div[contains(@class,'inventory_item')]" +
                "[.//div[contains(@class,'inventory_item_name')" +
                " and normalize-space()=\"" + productName + "\"]]" +
                "//div[contains(@class,'inventory_item_desc')]";

        return driver.findElement(
                By.xpath(xpath)
        ).getText();
    }


    public double getProductPrice(String productName) {

        String xpath =
                "//div[contains(@class,'inventory_item')]" +
                "[.//div[contains(@class,'inventory_item_name')" +
                " and normalize-space()=\"" + productName + "\"]]" +
                "//div[contains(@class,'inventory_item_price')]";

        String price =
                driver.findElement(By.xpath(xpath))
                        .getText()
                        .replace("$", "")
                        .trim();

        return Double.parseDouble(price);
    }


    public void addProduct(String productName) {

        String id = getProductId(productName);

        By addButton =
                By.id("add-to-cart-" + id);

        driver.findElement(addButton).click();
    }


    public void removeProduct(String productName) {

        String id = getProductId(productName);

        By removeButton =
                By.id("remove-" + id);

        driver.findElement(removeButton).click();
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

        if (productName.equals(
                "Test.allTheThings() T-Shirt (Red)")) {

            return "test.allthethings()-t-shirt-(red)";
        }

        throw new IllegalArgumentException(
                "Unknown product: " + productName
        );
    }


    public void sortBy(String value) {

        Select select =
                new Select(
                        driver.findElement(sortDropdown)
                );

        select.selectByValue(value);
    }


    public List<Double> getSortedPrices() {

        return getProductPrices();
    }


    public void openCart() {

        WebElement cart =
                driver.findElement(By.cssSelector("a.shopping_cart_link"));

        cart.click();
    }


    public int getCartCount() {

        if (driver.findElements(cartBadge).isEmpty()) {
            return 0;
        }

        return Integer.parseInt(
                driver.findElement(cartBadge).getText()
        );
    }


    public void logout() {

        driver.findElement(menuButton).click();

        driver.findElement(logoutButton).click();
    }
}
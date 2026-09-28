package com.shopEase.tests;

import com.shopEase.base.BaseTest;
import com.shopEase.pages.InventoryPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductTests extends BaseTest {

    private static final String BACKPACK =
            "Sauce Labs Backpack";

    private static final String BIKE_LIGHT =
            "Sauce Labs Bike Light";

    private static final String TSHIRT =
            "Sauce Labs Bolt T-Shirt";

    private static final String JACKET =
            "Sauce Labs Fleece Jacket";

    // TC016
    @Test
    public void TC016_productsPageDisplayed() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        Assert.assertEquals(
                inventory.getPageTitle(),
                "Products"
        );
    }

    // TC017
    @Test
    public void TC017_productListDisplayed() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        Assert.assertEquals(
                inventory.getProductCount(),
                6
        );
    }

    // TC018
    @Test
    public void TC018_addBackpack() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);

        Assert.assertEquals(
                inventory.getCartCount(),
                1
        );
    }

    // TC019
    @Test
    public void TC019_addBikeLight() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BIKE_LIGHT);

        Assert.assertEquals(
                inventory.getCartCount(),
                1
        );
    }

    // TC020
    @Test
    public void TC020_addTShirt() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(TSHIRT);

        Assert.assertEquals(
                inventory.getCartCount(),
                1
        );
    }

    // TC021
    @Test
    public void TC021_addFleeceJacket() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(JACKET);

        Assert.assertEquals(
                inventory.getCartCount(),
                1
        );
    }

    // TC022
    @Test
    public void TC022_addMultipleProducts() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.addProduct(BIKE_LIGHT);
        inventory.addProduct(TSHIRT);

        Assert.assertEquals(
                inventory.getCartCount(),
                3
        );
    }

    // TC023
    @Test
    public void TC023_sortAToZ() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.sortBy("az");

        List<String> actual =
                inventory.getProductNames();

        List<String> expected =
                new ArrayList<>(actual);

        Collections.sort(expected);

        Assert.assertEquals(
                actual,
                expected
        );
    }

    // TC024
    @Test
    public void TC024_sortZToA() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.sortBy("za");

        List<String> actual =
                inventory.getProductNames();

        List<String> expected =
                new ArrayList<>(actual);

        expected.sort(
                Collections.reverseOrder()
        );

        Assert.assertEquals(
                actual,
                expected
        );
    }

    // TC025
    @Test
    public void TC025_sortLowToHigh() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.sortBy("lohi");

        List<Double> actual =
                inventory.getProductPrices();

        List<Double> expected =
                new ArrayList<>(actual);

        Collections.sort(expected);

        Assert.assertEquals(
                actual,
                expected
        );
    }

    // TC026
    @Test
    public void TC026_sortHighToLow() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.sortBy("hilo");

        List<Double> actual =
                inventory.getProductPrices();

        List<Double> expected =
                new ArrayList<>(actual);

        expected.sort(
                Collections.reverseOrder()
        );

        Assert.assertEquals(
                actual,
                expected
        );
    }

    // TC027
    @Test
    public void TC027_openProductCart() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.openCart();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("cart")
        );
    }

    // TC028
    @Test
    public void TC028_productImagesValidation() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        Assert.assertTrue(
                inventory.areAllImagesLoaded()
        );
    }

    // TC029
    @Test
    public void TC029_productPriceValidation() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        double price =
                inventory.getProductPrice(
                        BACKPACK
                );

        Assert.assertEquals(
                price,
                29.99,
                0.001
        );
    }

    // TC030
    @Test
    public void TC030_productDescriptionValidation() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        String description =
                inventory.getProductDescription(
                        BACKPACK
                );

        Assert.assertFalse(
                description.isBlank()
        );
    }
}
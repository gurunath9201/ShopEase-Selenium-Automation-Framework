package com.shopEase.tests;

import com.shopEase.base.BaseTest;
import com.shopEase.pages.CartPage;
import com.shopEase.pages.InventoryPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    private static final String BACKPACK =
            "Sauce Labs Backpack";

    private static final String BIKE =
            "Sauce Labs Bike Light";

    // TC031
    @Test
    public void TC031_openEmptyCart() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        Assert.assertEquals(
                cart.getItemCount(),
                0
        );
    }

    // TC032
    @Test
    public void TC032_addOneProduct() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        Assert.assertEquals(
                cart.getItemCount(),
                1
        );

        Assert.assertTrue(
                cart.containsProduct(BACKPACK)
        );
    }

    // TC033
    @Test
    public void TC033_addMultipleProducts() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.addProduct(BIKE);

        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        Assert.assertEquals(
                cart.getItemCount(),
                2
        );
    }

    // TC034
    @Test
    public void TC034_removeProduct() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        cart.removeProduct(BACKPACK);

        Assert.assertEquals(
                cart.getItemCount(),
                0
        );
    }

    // TC035
    @Test
    public void TC035_removeAllProducts() {

        standardLogin();

        InventoryPage inventory = new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.addProduct(BIKE);

        inventory.openCart();

        CartPage cart = new CartPage(driver);

        cart.removeProduct(BACKPACK);
        cart.removeProduct(BIKE);

        Assert.assertEquals(cart.getItemCount(), 0);
    }
    
    // TC036
    @Test
    public void TC036_continueShopping() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        cart.continueShopping();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("inventory")
        );
    }

    // TC037
    @Test
    public void TC037_cartUrlValidation() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.openCart();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("cart")
        );
    }

    // TC038
    @Test
    public void TC038_productNameInCart() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        Assert.assertTrue(
                cart.containsProduct(BACKPACK)
        );
    }

    // TC039
    @Test
    public void TC039_productPriceInCart() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        Assert.assertEquals(
                cart.getProductPrice(BACKPACK),
                "$29.99"
        );
    }

    // TC040
    @Test
    public void TC040_productQuantityValidation() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        Assert.assertEquals(
                cart.getItemCount(),
                1
        );
    }

    // TC041
    @Test
    public void TC041_checkoutFromCart() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        cart.checkout();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains(
                                "checkout-step-one"
                        )
        );
    }

    // TC042
    @Test
    public void TC042_addRemoveAddProduct() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        cart.removeProduct(BACKPACK);

        cart.continueShopping();

        inventory.addProduct(BACKPACK);
        inventory.openCart();

        Assert.assertTrue(
                cart.containsProduct(BACKPACK)
        );
    }

    // TC043
    @Test
    public void TC043_cartAfterLogout() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);

        inventory.logout();

        Assert.assertFalse(
                driver.getCurrentUrl()
                        .contains("cart")
        );
    }

    // TC044
    @Test
    public void TC044_cartNavigation() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open Cart
        inventory.openCart();

        // Wait for Cart page
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.className("title"),
                        "Your Cart"
                )
        );

        Assert.assertEquals(
                driver.findElement(By.className("title")).getText(),
                "Your Cart"
        );

        // Continue Shopping
        CartPage cart =
                new CartPage(driver);

        cart.continueShopping();

        // Wait for Products page
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.className("title"),
                        "Products"
                )
        );

        Assert.assertEquals(
                driver.findElement(By.className("title")).getText(),
                "Products"
        );

        // Open Cart again
        inventory.openCart();

        // Wait for Cart page again
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.className("title"),
                        "Your Cart"
                )
        );

        Assert.assertEquals(
                driver.findElement(By.className("title")).getText(),
                "Your Cart"
        );
    }

    // TC045
    @Test
    public void TC045_multipleProductCartPersistence() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.addProduct(BIKE);

        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        Assert.assertEquals(
                cart.getItemCount(),
                2
        );

        driver.navigate().refresh();

        Assert.assertEquals(
                cart.getItemCount(),
                2
        );
    }
}
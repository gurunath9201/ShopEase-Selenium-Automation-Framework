package com.shopEase.tests;

import com.shopEase.base.BaseTest;
import com.shopEase.pages.CartPage;
import com.shopEase.pages.CheckoutPage;
import com.shopEase.pages.InventoryPage;
import com.shopEase.utils.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    private static final String BACKPACK =
            "Sauce Labs Backpack";

    private CheckoutPage openCheckout() {

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        cart.checkout();

        return new CheckoutPage(driver);
    }

    private void fillValidDetails(
            CheckoutPage checkout) {

        checkout.enterFirstName(
                ConfigReader.get("firstName")
        );

        checkout.enterLastName(
                ConfigReader.get("lastName")
        );

        checkout.enterPostalCode(
                ConfigReader.get("postalCode")
        );
    }

    // TC046
    @Test
    public void TC046_checkoutPage() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        Assert.assertTrue(
                checkout.isFirstNameDisplayed()
        );

        Assert.assertTrue(
                checkout.isLastNameDisplayed()
        );

        Assert.assertTrue(
                checkout.isPostalCodeDisplayed()
        );
    }

    // TC047
    @Test
    public void TC047_validCheckout() {

        standardLogin();

        InventoryPage inventory = new InventoryPage(driver);

        inventory.addProduct(BACKPACK);
        inventory.openCart();

        CartPage cart = new CartPage(driver);
        cart.checkout();

        CheckoutPage checkout = new CheckoutPage(driver);

        fillValidDetails(checkout);

        checkout.clickContinue();
        checkout.clickFinish();

        Assert.assertEquals(
                checkout.getSuccessMessage(),
                "Thank you for your order!"
        );
    }

    // TC048
    @Test
    public void TC048_emptyFirstName() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        checkout.enterLastName(
                "Mule"
        );

        checkout.enterPostalCode(
                "416410"
        );

        checkout.clickContinue();

        Assert.assertTrue(
                checkout.getErrorMessage()
                        .contains(
                                "First Name is required"
                        )
        );
    }

    // TC049
    @Test
    public void TC049_emptyLastName() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        checkout.enterFirstName(
                "Gurunath"
        );

        checkout.enterPostalCode(
                "416410"
        );

        checkout.clickContinue();

        Assert.assertTrue(
                checkout.getErrorMessage()
                        .contains(
                                "Last Name is required"
                        )
        );
    }

    // TC050
    @Test
    public void TC050_emptyPostalCode() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        checkout.enterFirstName(
                "Gurunath"
        );

        checkout.enterLastName(
                "Mule"
        );

        checkout.clickContinue();

        Assert.assertTrue(
                checkout.getErrorMessage()
                        .contains(
                                "Postal Code is required"
                        )
        );
    }

    // TC051
    @Test
    public void TC051_allFieldsEmpty() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        checkout.clickContinue();

        Assert.assertTrue(
                checkout.getErrorMessage()
                        .contains(
                                "First Name is required"
                        )
        );
    }

    // TC052
    @Test
    public void TC052_validFirstName() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        checkout.enterFirstName(
                "Gurunath"
        );

        Assert.assertTrue(
                checkout.isFirstNameDisplayed()
        );
    }

    // TC053
    @Test
    public void TC053_validLastName() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        checkout.enterLastName(
                "Mule"
        );

        Assert.assertTrue(
                checkout.isLastNameDisplayed()
        );
    }

    // TC054
    @Test
    public void TC054_validPostalCode() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        checkout.enterPostalCode(
                "416410"
        );

        Assert.assertTrue(
                checkout.isPostalCodeDisplayed()
        );
    }

    // TC055
    @Test
    public void TC055_continueCheckout() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        fillValidDetails(checkout);

        checkout.clickContinue();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains(
                                "checkout-step-two"
                        )
        );
    }

 // TC056
    @Test
    public void TC056_orderOverview() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.addProduct(BACKPACK);

        inventory.openCart();

        CartPage cart =
                new CartPage(driver);

        cart.checkout();

        CheckoutPage checkout =
                new CheckoutPage(driver);

        fillValidDetails(checkout);

        checkout.clickContinue();

        Assert.assertTrue(
                checkout.getSubtotal().contains("Item total")
        );

        Assert.assertTrue(
                checkout.getTax().contains("Tax")
        );

        Assert.assertTrue(
                checkout.getTotal().contains("Total")
        );
    }

    // TC057
    @Test
    public void TC057_finishOrder() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        fillValidDetails(checkout);

        checkout.clickContinue();
        checkout.clickFinish();

        Assert.assertEquals(
                checkout.getSuccessMessage(),
                "Thank you for your order!"
        );
    }

    // TC058
    @Test
    public void TC058_orderConfirmation() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        fillValidDetails(checkout);

        checkout.clickContinue();
        checkout.clickFinish();

        Assert.assertEquals(
                checkout.getSuccessMessage(),
                "Thank you for your order!"
        );

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains(
                                "checkout-complete"
                        )
        );
    }

    // TC059
    @Test
    public void TC059_multipleProductCheckout() {

        standardLogin();

        InventoryPage inventory = new InventoryPage(driver);

        inventory.addProduct(BACKPACK);

        inventory.addProduct("Sauce Labs Bike Light");

        inventory.openCart();

        CartPage cart = new CartPage(driver);

        Assert.assertEquals(
                cart.getItemCount(),
                2
        );

        cart.checkout();

        CheckoutPage checkout = new CheckoutPage(driver);

        fillValidDetails(checkout);

        checkout.clickContinue();

        checkout.clickFinish();

        Assert.assertEquals(
                checkout.getSuccessMessage(),
                "Thank you for your order!"
        );
    }
    // TC060
    @Test
    public void TC060_checkoutRegression() {

        standardLogin();

        CheckoutPage checkout =
                openCheckout();

        fillValidDetails(checkout);

        checkout.clickContinue();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains(
                                "checkout-step-two"
                        )
        );

        checkout.clickFinish();

        Assert.assertEquals(
                checkout.getSuccessMessage(),
                "Thank you for your order!"
        );
    }
}
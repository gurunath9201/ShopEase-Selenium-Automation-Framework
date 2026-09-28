package com.shopEase.tests;

import com.shopEase.base.BaseTest;
import com.shopEase.pages.InventoryPage;
import com.shopEase.pages.LoginPage;
import com.shopEase.utils.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    // TC001
    @Test
    public void TC001_validLogin() {

        LoginPage login =
                new LoginPage(driver);

        login.login(
                ConfigReader.get("standardUser"),
                ConfigReader.get("password")
        );

        InventoryPage inventory =
                new InventoryPage(driver);

        Assert.assertEquals(
                inventory.getPageTitle(),
                "Products"
        );
    }

    // TC002
    @Test
    public void TC002_invalidUsername() {

        LoginPage login =
                new LoginPage(driver);

        login.login(
                "invalid_user",
                ConfigReader.get("password")
        );

        Assert.assertTrue(
                login.getErrorMessage()
                        .contains(
                                "Username and password"
                        )
        );
    }

    // TC003
    @Test
    public void TC003_invalidPassword() {

        LoginPage login =
                new LoginPage(driver);

        login.login(
                ConfigReader.get("standardUser"),
                "wrong_password"
        );

        Assert.assertTrue(
                login.getErrorMessage()
                        .contains(
                                "Username and password"
                        )
        );
    }

    // TC004
    @Test
    public void TC004_bothCredentialsInvalid() {

        LoginPage login =
                new LoginPage(driver);

        login.login(
                "invalid_user",
                "wrong_password"
        );

        Assert.assertTrue(
                login.getErrorMessage()
                        .contains(
                                "Username and password"
                        )
        );
    }

    // TC005
    @Test
    public void TC005_emptyUsername() {

        LoginPage login =
                new LoginPage(driver);

        login.login(
                "",
                ConfigReader.get("password")
        );

        Assert.assertTrue(
                login.getErrorMessage()
                        .contains(
                                "Username is required"
                        )
        );
    }

    // TC006
    @Test
    public void TC006_emptyPassword() {

        LoginPage login =
                new LoginPage(driver);

        login.login(
                ConfigReader.get("standardUser"),
                ""
        );

        Assert.assertTrue(
                login.getErrorMessage()
                        .contains(
                                "Password is required"
                        )
        );
    }

    // TC007
    @Test
    public void TC007_bothFieldsEmpty() {

        LoginPage login =
                new LoginPage(driver);

        login.login("", "");

        Assert.assertTrue(
                login.getErrorMessage()
                        .contains(
                                "Username is required"
                        )
        );
    }

    // TC008
    @Test
    public void TC008_lockedUser() {

        LoginPage login =
                new LoginPage(driver);

        login.login(
                ConfigReader.get("lockedUser"),
                ConfigReader.get("password")
        );

        Assert.assertTrue(
                login.getErrorMessage()
                        .toLowerCase()
                        .contains("locked out")
        );
    }

    // TC009
    @Test
    public void TC009_standardUser() {

        standardLogin();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("inventory")
        );
    }

    // TC010
    @Test
    public void TC010_problemUser() {

        loginAs(
                ConfigReader.get("problemUser")
        );

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("inventory")
        );
    }

    // TC011
    @Test
    public void TC011_performanceUser() {

        loginAs(
                ConfigReader.get("performanceUser")
        );

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("inventory")
        );
    }

    // TC012
    @Test
    public void TC012_passwordMasking() {

        LoginPage login =
                new LoginPage(driver);

        Assert.assertEquals(
                login.getPasswordType(),
                "password"
        );
    }

    // TC013
    @Test
    public void TC013_loginPageUrl() {

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains(
                                "saucedemo.com"
                        )
        );
    }

    // TC014
    @Test
    public void TC014_logout() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.logout();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains(
                                "saucedemo.com"
                        )
        );
    }

    // TC015
    @Test
    public void TC015_loginAfterLogout() {

        standardLogin();

        InventoryPage inventory =
                new InventoryPage(driver);

        inventory.logout();

        LoginPage login =
                new LoginPage(driver);

        login.login(
                ConfigReader.get("standardUser"),
                ConfigReader.get("password")
        );

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("inventory")
        );
    }
}
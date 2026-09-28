package com.shopEase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By username =
            By.id("user-name");

    private By password =
            By.id("password");

    private By loginButton =
            By.id("login-button");

    private By errorMessage =
            By.cssSelector(
                    "[data-test='error']"
            );

    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    public void enterUsername(
            String username) {

        driver.findElement(
                this.username
        ).clear();

        driver.findElement(
                this.username
        ).sendKeys(username);
    }

    public void enterPassword(
            String password) {

        driver.findElement(
                this.password
        ).clear();

        driver.findElement(
                this.password
        ).sendKeys(password);
    }

    public void clickLogin() {

        driver.findElement(
                loginButton
        ).click();
    }

    public void login(
            String username,
            String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {

        return driver.findElement(
                errorMessage
        ).getText();
    }

    public String getPasswordType() {

        return driver.findElement(
                password
        ).getAttribute("type");
    }

    public boolean isLoginButtonDisplayed() {

        return driver.findElement(
                loginButton
        ).isDisplayed();
    }
}
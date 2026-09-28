package com.shopEase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;

    private By firstName =
            By.id("first-name");

    private By lastName =
            By.id("last-name");

    private By postalCode =
            By.id("postal-code");

    private By continueButton =
            By.id("continue");

    private By finishButton =
            By.id("finish");

    private By cancelButton =
            By.id("cancel");

    private By error =
            By.cssSelector(
                    "[data-test='error']"
            );

    private By completeHeader =
            By.className(
                    "complete-header"
            );

    private By subtotal =
            By.className(
                    "summary_subtotal_label"
            );

    private By tax =
            By.className(
                    "summary_tax_label"
            );

    private By total =
            By.className(
                    "summary_total_label"
            );

    public CheckoutPage(WebDriver driver) {

        this.driver = driver;
    }

    public void enterFirstName(
            String value) {

        driver.findElement(
                firstName
        ).clear();

        driver.findElement(
                firstName
        ).sendKeys(value);
    }

    public void enterLastName(
            String value) {

        driver.findElement(
                lastName
        ).clear();

        driver.findElement(
                lastName
        ).sendKeys(value);
    }

    public void enterPostalCode(
            String value) {

        driver.findElement(
                postalCode
        ).clear();

        driver.findElement(
                postalCode
        ).sendKeys(value);
    }

    public void clickContinue() {

        driver.findElement(
                continueButton
        ).click();
    }

    public void clickFinish() {

        driver.findElement(
                finishButton
        ).click();
    }

    public void clickCancel() {

        driver.findElement(
                cancelButton
        ).click();
    }

    public String getErrorMessage() {

        return driver.findElement(
                error
        ).getText();
    }

    public String getSuccessMessage() {

        return driver.findElement(
                completeHeader
        ).getText();
    }

    public String getSubtotal() {

        return driver.findElement(
                subtotal
        ).getText();
    }

    public String getTax() {

        return driver.findElement(
                tax
        ).getText();
    }

    public String getTotal() {

        return driver.findElement(
                total
        ).getText();
    }

    public boolean isFirstNameDisplayed() {

        return driver.findElement(
                firstName
        ).isDisplayed();
    }

    public boolean isLastNameDisplayed() {

        return driver.findElement(
                lastName
        ).isDisplayed();
    }

    public boolean isPostalCodeDisplayed() {

        return driver.findElement(
                postalCode
        ).isDisplayed();
    }
}
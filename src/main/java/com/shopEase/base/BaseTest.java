package com.shopEase.base;

import com.shopEase.pages.LoginPage;
import com.shopEase.utils.ConfigReader;
import com.shopEase.utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options =
                new ChromeOptions();

        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));

        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(60));

        driver.get(
                ConfigReader.get("url")
        );
    }

    protected void loginAs(String username) {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                username,
                ConfigReader.get("password")
        );
    }

    protected void standardLogin() {

        loginAs(
                ConfigReader.get("standardUser")
        );
    }

    @AfterMethod
    public void tearDown(
            ITestResult result) {

        if (result.getStatus()
                == ITestResult.FAILURE) {

            ScreenshotUtil.capture(
                    driver,
                    result.getName()
            );
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
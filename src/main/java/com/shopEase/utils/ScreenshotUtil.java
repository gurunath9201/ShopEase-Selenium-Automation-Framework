package com.shopEase.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    public static void capture(
            WebDriver driver,
            String testName) {

        try {

            TakesScreenshot screenshot =
                    (TakesScreenshot) driver;

            File source =
                    screenshot.getScreenshotAs(
                            OutputType.FILE
                    );

            File destination =
                    new File(
                            "screenshots/"
                                    + testName
                                    + ".png"
                    );

            destination.getParentFile().mkdirs();

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
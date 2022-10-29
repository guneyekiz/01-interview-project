package com.InterviewTask.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * WebDriverFactory class:
 * is used to create WenDriver(Selenium) abject and return it.
 * it will open the browser and maximize it
 * method:
 * getDriver(String browserType)
 * It will check browser type and returns object:
 * if browserType is "chrome":
 * it will set uo chrome driver, and return new ChromeDriver
 * if browserType is "firefox":
 * it will set uo chrome driver, and return new FireFoxDriver
 * <p>
 * EX:
 * WebDriver driver = WebDriverFactory.getDriver("chrome");
 * and use driver as normal
 */
public class WebDriverFactory {

    public static WebDriver getDriver(String browserType) {

        if (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }else{
            System.out.println("Invalid BrowserType = " + browserType);
            return null;// null means no object. empthy
        }

    }


}

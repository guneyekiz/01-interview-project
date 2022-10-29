package com.InterviewTask.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class BrowserUtils {
    /**
     * this method is used to pause the code for given seconds
     * It is static method So we can call:
     * BrowserUtils.sleep(5);
     *
     * @param seconds
     */
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Exception happened in sleep method");
        }
    }


    public static void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollBy(0," + pixels + ")");
    }

    public static void scrollUp(int minesPixels) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollBy(0," + minesPixels + ")");
    }

    public static void scrollUntilElement(WebElement targetElement) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true)", targetElement);
    }

    public static void clickJs( WebElement targetBtn){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();",targetBtn);
    }

}

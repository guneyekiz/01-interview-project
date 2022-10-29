package com.InterviewTask.tests;

import com.InterviewTask.pages.herokuapp.PageClass;
import com.InterviewTask.utils.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task extends TestBase {


    @Test
    public void addElements() {
        //Line 20 to 25 is only setup
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); //Implicit wait implemented
        driver.get(ConfigurationReader.getProperty("hero.url"));
        PageClass page = new PageClass();// POM implemented
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(page.addElementBtn)); //Explicit wait implemented

        //between line 29 to 35 I assert text of the webElement and print the result for you to read
        String expectedText1 = "Add/Remove Elements";
        Assert.assertEquals(page.addRemoveElementsText.getText(), expectedText1);
        System.out.println("Actual Result = [" + page.addRemoveElementsText.getText() + "] , Expected Result = [" + expectedText1 + "]");

        String expectedText2 = "Add Element";
        Assert.assertEquals(page.addElementBtn.getText(), expectedText2);
        System.out.println("Actual Result = [" + page.addElementBtn.getText() + "] , Expected Result = [" + expectedText2 + "]");

        //Line 38,46 is asserting if elements are displaying or not
        Assert.assertTrue(page.addRemoveElementsText.isDisplayed());
        Assert.assertTrue(page.addElementBtn.isDisplayed());
        try {
            if (!page.deleteBtn.isDisplayed()) {
                Assert.fail("Delete button displayed before we are adding elements!");
            }
        } catch (NoSuchElementException ignored) {

        }


        //Line 50 We are adding the number of the element we want to add
        int howManyElement = 5;


        //Line 54 to 58 we are adding the elements
        for (int i = 0; i < howManyElement; i++) {

            page.addElementBtn.click();

        }

        //line 60 to 64 we are asserting delete button text and printing for you to read
        String expectedText3 = "Delete";
        Assert.assertTrue(page.deleteBtn.isDisplayed());
        Assert.assertEquals(page.deleteBtn.getText(), expectedText3);
        System.out.println("Actual Result = [" + page.deleteBtn.getText() + "] , Expected Result = [" + expectedText3 + "]");


        //stored all the elements in the list, since class is not unique it will store multiple elements
        List<WebElement> countElement = driver.findElements(By.xpath("//*[@class=\"added-manually\"]"));

        int actualResult = countElement.size();


        System.out.println("Actual Result = " + actualResult + ",  Expected Result = " + howManyElement);

        Assert.assertEquals(actualResult, howManyElement);

    }

    @Test
    public void deleteElements() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Implicit wait implemented
        driver.get(ConfigurationReader.getProperty("hero.url"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        PageClass page = new PageClass();// POM implemented
        wait.until(ExpectedConditions.elementToBeClickable(page.addElementBtn)); //Explicit wait implemented

        int howManyElement = 5;

        //Line 91 to 95  we are adding the elements
        for (int i = 0; i < howManyElement; i++) {

            page.addElementBtn.click();

        }

        //stored all the elements in the list, since class is not unique it will store multiple elements
        List<WebElement> countElement = driver.findElements(By.xpath("//*[@class=\"added-manually\"]"));

        Assert.assertEquals(countElement.size(), howManyElement);


        for (int i = 0; i < howManyElement; i++) {
            page.deleteBtn.click();
        }

        countElement = driver.findElements(By.xpath("//*[@class=\"added-manually\"]"));
        if (countElement.size() != 0) {
            Assert.fail("countElementSize should be zero but it is " + countElement.size());
        }

        try {
            if (page.deleteBtn.isDisplayed()) {
                Assert.fail("Unable to delete all elements");
            }
        } catch (NoSuchElementException ignored) {

        }


    }




}

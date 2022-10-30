package com.InterviewTask.tests;

import com.InterviewTask.pages.herokuapp.PageClass;
import com.InterviewTask.utils.ConfigurationReader;
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
        //basic setup
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); //Implicit wait implemented
        driver.get(ConfigurationReader.getProperty("hero.url"));
        PageClass page = new PageClass();// POM implemented
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(page.addElementBtn)); //Explicit wait implemented

        String actualTitle = "The Internet";
        Assert.assertEquals(actualTitle, driver.getTitle());
        //text of the webElements are asserted and printed the result for you to read
        String expectedText1 = "Add/Remove Elements";
        Assert.assertEquals(page.addRemoveElementsText.getText(), expectedText1);
        System.out.println("Actual Result = [" + page.addRemoveElementsText.getText() + "] , Expected Result = [" + expectedText1 + "]");

        String expectedText2 = "Add Element";
        Assert.assertEquals(page.addElementBtn.getText(), expectedText2);
        System.out.println("Actual Result = [" + page.addElementBtn.getText() + "] , Expected Result = [" + expectedText2 + "]");

        //asserting if elements are displaying or not
        Assert.assertTrue(page.addRemoveElementsText.isDisplayed());
        Assert.assertTrue(page.addElementBtn.isDisplayed());
        try {
            if (!page.deleteBtn.isDisplayed()) {
                Assert.fail("Delete button displayed before we are adding elements!");
            }
        } catch (NoSuchElementException ignored) {

        }


        //We are adding the number of the element we want to add
        int howManyElement = 5;


        //Elements are added
        for (int i = 0; i < howManyElement; i++) {

            page.addElementBtn.click();

        }

        //delete button text is asserted, and printed for you to read
        String expectedText3 = "Delete";
        Assert.assertTrue(page.deleteBtn.isDisplayed());
        Assert.assertEquals(page.deleteBtn.getText(), expectedText3);//we could also loop and assert all delete btn elements
        System.out.println("Actual Result = [" + page.deleteBtn.getText() + "] , Expected Result = [" + expectedText3 + "]");


        //stored all the elements in the list, since class is not unique it will store multiple elements
        List<WebElement> countElements = page.deleteALlElementsButton;
        int actualResult = countElements.size();


        System.out.println("Actual Result = " + actualResult + ",  Expected Result = " + howManyElement);

        Assert.assertEquals(actualResult, howManyElement);

    }

    @Test//end-to-end version, (elements are added and deleted)
    public void deleteElements() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Implicit wait implemented
        driver.get(ConfigurationReader.getProperty("hero.url"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        PageClass page = new PageClass();// POM implemented
        wait.until(ExpectedConditions.elementToBeClickable(page.addElementBtn)); //Explicit wait implemented

        String actualTitle = "The Internet";
        Assert.assertEquals(actualTitle, driver.getTitle());

        int howManyElement = 5;

        //Elements are added
        for (int i = 0; i < howManyElement; i++) {

            page.addElementBtn.click();

        }

        //stored all the elements in the list, since class is not unique it will store multiple elements
        List<WebElement> countElements = page.deleteALlElementsButton;

        Assert.assertEquals(countElements.size(), howManyElement);


        for (int i = 0; i < howManyElement; i++) {
            page.deleteBtn.click();
        }

        countElements = page.deleteALlElementsButton;
        if (countElements.size() != 0) {
            Assert.fail("countElementSize should be zero but it is " + countElements.size());
        }

        try {
            if (page.deleteBtn.isDisplayed()) {
                Assert.fail("Unable to delete all elements");
            }
        } catch (NoSuchElementException ignored) {

        }


    }


    @Test
    public void boundaryTestAddElement() {

        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS); //Implicit wait implemented
        driver.get(ConfigurationReader.getProperty("hero.url"));
        PageClass page = new PageClass();// POM implemented
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(page.addElementBtn)); //Explicit wait implemented


        int howManyElement = 2000;

        //Elements are added
        for (int i = 0; i < howManyElement; i++) {

            page.addElementBtn.click();
            wait.until(ExpectedConditions.elementToBeClickable(page.addElementBtn)); //Explicit wait implemented

        }

        List<WebElement> countElements = page.deleteALlElementsButton;
        int actualResult = countElements.size();
        Assert.assertEquals(actualResult, howManyElement);

        int countDeleteBtnInLoop = 0;
        //Asserting all delete button text
        for (WebElement countElement : countElements) {
            System.out.println(++countDeleteBtnInLoop + " - " + countElement.getText());
            if (!countElement.getText().equals("Delete")) {
                Assert.fail();
            }

        }
        Assert.assertEquals(countDeleteBtnInLoop, howManyElement);

    }

}

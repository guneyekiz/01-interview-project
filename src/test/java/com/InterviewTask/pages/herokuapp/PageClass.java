package com.InterviewTask.pages.herokuapp;

import com.InterviewTask.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageClass {

    protected WebDriver driver;

    public PageClass() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//button[@onclick=\"addElement()\"]")
    public WebElement addElementBtn;

    @FindBy(xpath = "//button[@class=\"added-manually\"]")
    public WebElement deleteBtn;

    @FindBy(xpath = "//div[@id=\"content\"]/h3")
    public WebElement addRemoveElementsText;














}

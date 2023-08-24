package com.liverpool.page_object.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexLoc {

    WebDriver driver;

    public IndexLoc(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "mainSearchbar")
    public WebElement searchBarInp;
}

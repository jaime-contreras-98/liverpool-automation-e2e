package com.liverpool.page_object.pages;

import com.liverpool.helpers.BaseComponents;
import com.liverpool.page_object.locators.IndexLoc;
import com.liverpool.page_object.locators.ResultsLoc;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IndexPage extends BaseComponents {

    WebDriver driver;
    IndexLoc indexLoc;
    WebDriverWait wait;

    public IndexPage(WebDriver driver){
        super(driver);
        this.driver = driver;

        indexLoc = new IndexLoc(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchProduct(String nameProduct, By elementWait){
        indexLoc.searchBarInp.sendKeys(nameProduct + Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(elementWait));
    }

    public void searchForProduct(String nameProduct){
        indexLoc.searchBarInp.sendKeys(nameProduct + Keys.ENTER);

        waitForElement(By.cssSelector("p.a-plp-results-title"));
    }

}

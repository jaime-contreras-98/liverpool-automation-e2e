package com.liverpool.tests;

import com.liverpool.data.Constants;
import com.liverpool.helpers.BaseComponents;
import com.liverpool.page_object.locators.IndexLoc;
import com.liverpool.page_object.locators.ProductsLoc;
import com.liverpool.page_object.locators.ResultsLoc;
import com.liverpool.page_object.pages.IndexPage;
import com.liverpool.page_object.pages.ResultsPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LiverpoolTests {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public BaseComponents base;
    //locators
    public IndexLoc indexLoc;
    public ResultsLoc resultsLoc;
    public ProductsLoc productsLoc;
    //páges
    public IndexPage indexPage;
    public ResultsPage resultsPage;

    @BeforeTest
    public void beforeAllTests() throws IOException {
        // init
        base = new BaseComponents(driver);
        driver = base.initializeDriver(); // init driver
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        base.initializeTest(Constants.URL);

        // PAGES
        indexLoc = new IndexLoc(driver);
        resultsLoc = new ResultsLoc(driver);
        productsLoc = new ProductsLoc(driver);

        // LOCATORS
        indexPage = new IndexPage(driver);
        resultsPage = new ResultsPage(driver);
    }

    @Test
    public void liverpoolTest1(){
        indexPage.searchProduct(Constants.PS, resultsLoc.resultsProductsLbl);

        int totalPs5Products = resultsPage.iterateInList(resultsLoc.productsNameList, Constants.PS5[0], Constants.PS5[1]);
        Assert.assertTrue(totalPs5Products >= Constants.minimumAvailableProducts);

        List<WebElement> ps5Elements = driver.findElements(resultsLoc.getPs5Elements);
        resultsPage.iterateListAndClickOnElement(ps5Elements, Constants.PS5[2]);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productsLoc.productTitleLbl));

        String productName = resultsPage.getElementText(productsLoc.productTitleLbl);
        String productPrice = resultsPage.getElementText(productsLoc.productPriceLbl).split("00")[0].trim();

        Assert.assertEquals(Constants.PS5[2], productName);
        Assert.assertEquals(Constants.PS5_PRICE, productPrice);
    }

    @Test
    public void liverpoolTest2() {
        indexPage.searchProduct(Constants.TV, resultsLoc.resultsProductsLbl);

        Assert.assertTrue(resultsLoc.showAllSizesBtn.isDisplayed() && resultsLoc.priceFilters.isDisplayed());

        resultsLoc.showAllSizesBtn.click();

        WebElement check = resultsPage.iterateOnListAndCheck(resultsLoc.sizeCheckboxesList, "div > label", "div > div > input", Constants.SIZE);
        Assert.assertTrue(check.isSelected());

        actions.pause(4000).perform(); //fluentwait

        resultsPage.iterateOnListAndCheck(resultsLoc.priceRangesList, "label", "input", Constants.PRICE);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(resultsLoc.priceCheckboxesSection));

        String totalProductsTxt = resultsLoc.totalProductsLbl.getText().split(" ")[0];
        int numProductsTxt = Integer.parseInt(totalProductsTxt);

        Assert.assertTrue(numProductsTxt <= Constants.minimumAvailableTVs);
    }

    @Test
    public void liverpoolTest3() {
        resultsPage.iterateOnListAndHover(resultsLoc.mainDivsSectionsList, "li > span",Constants.CATEGORY);
        resultsPage.iterateOnListAndHover(resultsLoc.categoriesSectionsList, "li",Constants.BEAUTY);
        resultsPage.iterateListAndClickOnElement(resultsLoc.bellezaItemsList, Constants.MEN_FRAGRANCE);

        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsLoc.menPerfumeLbl));

        String perfumesLbl = driver.findElement(resultsLoc.menPerfumeLbl).getText();
        Assert.assertEquals(perfumesLbl , Constants.MEN_FRAGRANCE);

        resultsPage.sortBy(Constants.FILTERS[0]);

        actions.pause(2000).perform();
        resultsLoc.showMoreMarcasBtn.click();

        actions.pause(2000).perform();
        resultsPage.iterateOnList();
        actions.pause(2000).perform();

        String diorFilterTxt = driver.findElement(resultsLoc.filterProductLbl).getText();
        Assert.assertEquals(diorFilterTxt, Constants.TRADEMARK[1]);
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}

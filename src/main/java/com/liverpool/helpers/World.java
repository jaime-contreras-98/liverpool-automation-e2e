package com.liverpool.helpers;

import com.liverpool.page_object.locators.IndexLoc;
import com.liverpool.page_object.locators.ProductsLoc;
import com.liverpool.page_object.locators.ResultsLoc;
import com.liverpool.page_object.pages.IndexPage;
import com.liverpool.page_object.pages.ResultsPage;
import com.liverpool.step_definitions.ResultsSteps;
import org.openqa.selenium.WebDriver;

public class World {

    public WebDriver driver;
    public BaseComponents baseComps;
    // LOCATORS
    public IndexLoc indexLoc;
    public ProductsLoc productsLoc;
    public ResultsLoc resultsLoc;
    // PAGES
    public IndexPage indexPage;
    public ResultsPage resultsPage;

}

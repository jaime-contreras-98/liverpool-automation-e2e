package com.liverpool.step_definitions;

import com.liverpool.data.Constants;
import com.liverpool.helpers.BaseComponents;
import com.liverpool.helpers.World;
import com.liverpool.page_object.locators.ProductsLoc;
import com.liverpool.page_object.locators.ResultsLoc;
import com.liverpool.page_object.pages.ResultsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class ResultsSteps {

    private World world;

    public ResultsSteps(World world) {
        this.world = world;
        this.world.resultsPage = new ResultsPage(world.driver);
        this.world.resultsLoc = new ResultsLoc(world.driver);
        this.world.productsLoc = new ProductsLoc(world.driver);
    }

    @When("validate the amount of products is greater than {int}")
    public void i_validate_the_amount_of_products_is_greater_than(int numProducts) {
        int total_products = this.world.resultsPage.iterateInList(this.world.resultsLoc.productElements, Constants.PS5[0], Constants.PS5[1]);

        Assert.assertTrue(total_products >= numProducts);
    }

    @When("I click on the first product with name {string}")
    public void i_click_on_product_on_list_with_name(String productName){
        List<WebElement> productElementsList = null;

        if(productName.equalsIgnoreCase("Playstation 5 825 GB")){
            productElementsList = world.resultsLoc.ps5ItemsList; //List<WebElement> ps5ItemsList;
        } else if(productName.equalsIgnoreCase("perfumes hombre")){
            productElementsList = world.resultsLoc.bellezaItemsList; //List<WebElement> bellezaItemsList;
        }

        world.resultsPage.iterateListAndClickOnElement(productElementsList, productName);
    }

    /*
    @Then("validate product {string}")
    public void i_validate_product_property(String productProperty){
        String getProductProperty = "";

        switch(productProperty){
            case "price":
                getProductProperty = world.resultsPage.getElementText(world.productsLoc.productTitleLbl);
                Assert.assertEquals(Constants.PS5[2], getProductProperty);
                break;
            case "product":
                getProductProperty = world.resultsPage.getElementText(world.productsLoc.productPriceLbl).split("00")[0].trim();
                Assert.assertEquals(Constants.PS5_PRICE, getProductProperty);
                break;

            default:
                System.out.println("No Element was found.");
        }
    }
     */

    @When("I validate the {string} element is being displayed")
    public void i_validate_presence_of_element(String object) {
        boolean elementPresence = false;
        world.baseComps.waitForElement(By.xpath("(//div[@class='m-plp__filterSection '] /div[@class='plp-filter-options active'])[6]"));

        if(object.equalsIgnoreCase("price section")){
            elementPresence = world.resultsPage.isElementPresent(world.resultsLoc.priceFilters);
        } else if(object.equalsIgnoreCase("all sizes button")){
            elementPresence = world.resultsPage.isElementPresent(world.resultsLoc.showAllSizesBtn);
        }
        Assert.assertTrue(elementPresence);
    }

    @When("I click on {string} filter checkbox and validate is checked")
    public void i_check_on_numInches_filter(String numInches){
        WebElement check = world.resultsPage.iterateOnListAndCheck(world.resultsLoc.sizeCheckboxes,
                "div > label", "div > div > input", numInches);

        Assert.assertTrue(check.isSelected());
    }

    @When("I click on {string} filter on {string}")
    public void i_click_on_filter(String sectionFilter, String filterName){
        if(sectionFilter.equalsIgnoreCase("prices")){
            world.resultsPage.iterateOnListAndCheck(world.resultsLoc.priceRanges, "label", "input", filterName);
        }
    }

    @Then("I validate product results number is less or equal than {int}")
    public void i_validate_products_results_number_amount(int number){
        String totalProductsTxt = world.resultsLoc.totalProductLbl.getText().split(" ")[0];
        int numProductsTxt = Integer.parseInt(totalProductsTxt);

        Assert.assertTrue(numProductsTxt <= number);
    }

    @When("I hover on element with text {string}")
    public void i_hover_and_click_on_element(String elementName){
        if(elementName.equalsIgnoreCase("categorías")){
            world.resultsPage.iterateOnListAndHover(world.resultsLoc.mainDivsSections, "li > span",elementName);
        } else if(elementName.equalsIgnoreCase("belleza")){
            world.resultsPage.iterateOnListAndHover(world.resultsLoc.categoriesSections, "li",elementName);
        }
    }

    @When("^I sort products by \"([^\"]*)\"$")
    public void i_sort_products_by_string(String sortBy){
        world.resultsPage.sortBy(sortBy);
    }

    @When("^I iterate and click on element (.+) from list")
    public void i_iterate_and_click_on_element_from_list(String perfume){
        if(perfume.equals("DIOR")){
            world.resultsPage.iterateOnList();
        }
    }

}

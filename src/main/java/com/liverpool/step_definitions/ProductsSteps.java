package com.liverpool.step_definitions;

import com.liverpool.helpers.World;
import com.liverpool.page_object.locators.ProductsLoc;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.testng.Assert;

public class ProductsSteps {

    private World world;

    public ProductsSteps(World world){
        this.world = world;
        this.world.productsLoc = new ProductsLoc(world.driver);
    }

    @When("I validate using {string} with locator {string} is being displayed")
    public void i_validate_the_element_is_being_displayed(String locatorForm, String locator){
        By locatorType = null;

        switch(locatorForm){
            case "xpath":
                locatorType = By.xpath(locator);
                break;
            case "css":
                locatorType = By.cssSelector(locator);
                break;
            case "id":
                locatorType = By.id(locator);
                break;
        }

        try{
            world.baseComps.waitForElement(locatorType);
        } catch(NoSuchElementException | ElementNotInteractableException |TimeoutException e){
            System.out.println(e.getMessage());
        }
    }

    @Then("validate product {string} txt is equal to {string}")
    public void i_validate_product_property_with(String productProperty, String result){
        String getProductProperty = "";

        switch(productProperty){
            case "name":
                getProductProperty = world.baseComps.getElementText(world.productsLoc.productTitleLbl);
                break;
            case "price":
                getProductProperty = world.baseComps.getElementText(world.productsLoc.productPriceLbl).split("00")[0].trim();
                break;
            case "men perfume":
                getProductProperty = world.baseComps.getElementText(world.resultsLoc.menPerfumeLbl);
                break;
            case "dior perfume":
                getProductProperty = world.baseComps.getElementText(world.resultsLoc.filterProductLbl);
                break;
            default:
                System.out.println("No Element was found.");
        }

        Assert.assertEquals(getProductProperty, result);
    }
}

package com.liverpool.step_definitions;

import com.liverpool.helpers.BaseComponents;
import com.liverpool.helpers.World;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class BaseSteps{

    private World world;

    public BaseSteps(World world) {
        this.world = world;
        this.world.baseComps = new BaseComponents(world.driver);
    }

    @Given("I initialize my browser")
    public void i_initialize_my_browser() throws IOException {
        world.driver = world.baseComps.initializeDriver();
    }

    @When("^I go to (.+)")
    public void i_go_to_page_string(String page){
        world.baseComps.initializeTest(page);
    }

    @Then("I close my browser")
    public void i_close_my_browser() {
        world.driver.quit();
    }

    @When("^I click on element using (.+) with locator (.+)")
    public void i_click_on_element(String locatorForm, String locator){
        By locatorType = null;
        WebElement elementToBeClicked = null;

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
            elementToBeClicked = world.driver.findElement(locatorType);
            elementToBeClicked.click();
        } catch(NoSuchElementException | ElementNotInteractableException e){
            System.out.println(e.getMessage());
        }
    }

    @When("I wait {int} seconds")
    public void i_wait_seconds(int numSeconds) throws InterruptedException {
        int seconds = numSeconds * 1000;
        Thread.sleep(seconds);
    }

    @When("^I confirm element with (.+) using locator (.+) is not visible")
    public void i_wait_for_element_to_appear(String locatorForm, String locator){
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
            world.baseComps.elementIsNotVisible(locatorType);
        } catch(NoSuchElementException e){
            System.out.println("No such element exception!" + e.getMessage());
        }
    }
}

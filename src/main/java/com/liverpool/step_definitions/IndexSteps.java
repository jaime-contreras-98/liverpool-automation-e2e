package com.liverpool.step_definitions;

import com.liverpool.helpers.World;
import com.liverpool.page_object.pages.IndexPage;
import io.cucumber.java.en.Given;

public class IndexSteps {

    private World world;

    public IndexSteps(World world) {
        this.world = world;
        this.world.indexPage = new IndexPage(world.driver);
    }

    @Given("^I search for product (.+)")
    public void i_search_for_product(String productName){
        world.indexPage.searchForProduct(productName);
    }
}

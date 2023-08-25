package com.liverpool.page_object.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductsLoc {

    WebDriver driver;

    public ProductsLoc(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public By productTitleLbl = By.cssSelector("div[class='product-header-container liverpool'] > h1");
    public By productPriceLbl = By.cssSelector("div[class='m-product__price-dw-promotion'] p");
}

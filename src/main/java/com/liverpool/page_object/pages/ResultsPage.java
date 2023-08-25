package com.liverpool.page_object.pages;

import com.liverpool.data.Constants;
import com.liverpool.helpers.BaseComponents;
import com.liverpool.page_object.locators.ResultsLoc;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ResultsPage extends BaseComponents {

    WebDriver driver;
    Actions actions;
    ResultsLoc resultsLoc;

    public ResultsPage (WebDriver driver){
        super(driver);

        this.driver = driver;
        this.actions = new Actions(driver);
        this.resultsLoc = new ResultsLoc(driver);
    }

    public void sortBy(String buttonName){
        resultsLoc.sortByBtn.click();

        driver.findElement(By.xpath("//main[@class='col-lg-9 m-column_mainContent'] //button[contains(text(),'" + buttonName + "')]")).click();
    }

    public int iterateInList(List<WebElement> list, String data1, String data2){
        int totalProducts = 0;

        for(WebElement loc: list){
            String productTxt = loc.getText();

            if(productTxt.contains(data1) || productTxt.contains(data2)){
                totalProducts++;
            }
        }
        return totalProducts;
    }

    public void iterateListAndClickOnElement(List<WebElement> list, String elementName){
        for(WebElement element: list){
            String elementTxt = element.getText();

            if(elementTxt.contains(elementName)){
                element.click();
                break;
            }
        }
    }

    // AÑADIR NO SUCH ELEMENT EXCEPTION
    public WebElement iterateOnListAndCheck(List<WebElement> myList, String subLocator1, String subLocator2,String sectionName) {
        WebElement stream = myList.stream().filter(prodName -> prodName.findElement(By.cssSelector(subLocator1)).getText().contains(sectionName)).findFirst().orElse(null);
        WebElement hello = stream.findElement(By.cssSelector(subLocator2));
        hello.click();

        return hello;
    }

    public WebElement iterateOnListAndHover(List<WebElement> myList, String subLocator, String categoryName) {
        WebElement stream = myList.stream().filter(prodName -> prodName.findElement(By.cssSelector(subLocator)).getText().equalsIgnoreCase(categoryName)).findFirst().orElse(null);
        actions.moveToElement(stream).perform();

        return stream;
    }

    public void iterateOnList(){
        String locator = "//div[@class='m-plp__filterSection '] //h3";
        int contador=0;

        for(WebElement sideBar: resultsLoc.sideBarElementsList){
            String sideBarTxt = sideBar.getText();

            if(sideBarTxt.equalsIgnoreCase(Constants.TRADEMARK[0])){

                for(WebElement marcaList: resultsLoc.marcasPerfumesList){
                    String markTxt = marcaList.getText();


                    if(markTxt.contains(Constants.TRADEMARK[1])){
                        driver.findElement(By.xpath(locator + "/parent::button /parent::div /parent::div /div[2] //div[contains(@id, 'MarcascountViewMore" + contador +"')] /div /div")).click();
                        break;
                    }
                    contador++;
                }
            }
        }
    }
    /*
    public String numberOfArticles() {
        String txt = "";

        try{
            List<WebElement> sizesList = driver.findElements(By.cssSelector("div[class='o-aside d-aside--margin'] > div:nth-child(23) div[id]"));

            WebElement stream = sizesList.stream().filter(prodName -> prodName.findElement(By.cssSelector("div label")).getText().contains("55 pulgadas")).findFirst().orElse(null);
            txt = stream.getText().split(" ")[2].replace("(","").replace(")","");
        } catch(NoSuchElementException e){
           System.out.println(e.toString() + ": No such element found.");
        }
        return txt;
    }
     */

}

package com.liverpool.page_object.locators;

import com.liverpool.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsLoc {

    WebDriver driver;

    public ResultsLoc(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public By resultsProductsLbl = By.cssSelector("p.a-plp-results-title");
    public By getPs5Elements = By.xpath("//figure //h5[contains(text(),'"+ Constants.PS5[2] +"')]");
    public By subCategoryNameLbl = By.cssSelector("a[class='a-desktop__categoryTitle categoryLevel-2undefined']");
    public By menPerfumeLbl = By.cssSelector("h2.a-title-section-leftMenu");
    public By filterProductLbl = By.cssSelector("div[class*='plp-filters-container'] div[class*='filterSelection']");

    @FindBy(xpath = "//figure //h5")
    public List<WebElement> productElements;

    @FindBy(xpath="(//div[@class='m-plp__filterSection '] /div[@class='plp-filter-options active'])[4]")
    public WebElement sizeFilters;

    @FindBy(xpath = "(//div[@class='m-plp__filterSection '] /div[@class='plp-filter-options active'])[6]")
    public WebElement priceFilters;

    @FindBy(css ="div[class='o-aside d-aside--margin'] > div:nth-child(27) div[id]")
    public List<WebElement> sizeCheckboxes;

    @FindBy(css = "div[class='o-aside d-aside--margin'] > div:nth-child(26)  div[class*='m-radioButton'] ")
    public List<WebElement> priceRanges;

    @FindBy(css ="p[class='a-plp-results-title']")
    public WebElement totalProductLbl;

    @FindBy(css="div[class='o-aside d-aside--margin'] > div:nth-child(27) a")
    public WebElement showAllSizesBtn;

    @FindBy(css="li[class='m-navDesktop__section pt-2 pb-2 mr-xl-5'] > span")
    public WebElement categoriesBtn;

    @FindBy(css="ul[class='m-navDesktop__menuList']")
    public List<WebElement> mainDivsSections;

    @FindBy(css="div[class='m-megamenu__category_menu-item']")
    public List<WebElement> categoriesSections;

    @FindBy(css="div#CAT5020010 .col-lg-4.pb-3 > ul > li")
    public List<WebElement> bellezaItemsList;

    @FindBy(xpath="//figure //h5[contains(text(),'Consola PlayStation 5 825 GB')]")
    public List<WebElement> ps5ItemsList;

    @FindBy(css="a[class='a-desktop__categoryTitle categoryLevel-2undefined']")
    public WebElement beautyItemLbl;

    @FindBy(xpath="//ul /li /a[contains(text(),'Perfumes Hombre')]")
    public WebElement menPerfumeBtn;

    @FindBy(css="div[class='row '] a#sortby")
    public WebElement sortByBtn;

    @FindBy(css="div[class='row '] button:nth-child(1)")
    public WebElement relevanceBtn;

    @FindBy(css="a#Marcas")
    public WebElement showMoreMarcasBtn;

    @FindBy(xpath ="//div[@class='m-plp__filterSection '] //h3")
    public List<WebElement> sideBarElementsList;

    @FindBy(xpath="//div[@class='m-plp__filterSection '] //h3 /parent::button /parent::div /parent::div /div[2] //div[contains(@id, 'MarcascountViewMore')]")
    public List<WebElement> marcasPerfumesList;

}

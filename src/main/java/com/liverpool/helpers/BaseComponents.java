package com.liverpool.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseComponents {

    WebDriver driver;
    WebDriverWait wait;

    public BaseComponents(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//liverpool//data//init.properties");
        prop.load(file);

        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome"))
            driver = new ChromeDriver();
        else if (browserName.equalsIgnoreCase("firefox"))
            driver = new FirefoxDriver();
         else if (browserName.equalsIgnoreCase("edge"))
            driver = new EdgeDriver();
        else
            driver = new InternetExplorerDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return driver;
    }

    public void initializeTest(String page) {
        driver.manage().window().maximize();
        driver.get(page);
    }

    public void waitForElement(By element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void elementIsNotVisible(By element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public boolean isElementPresent(WebElement elementDisplayed) {
        boolean isElementPresent = false;

        if(elementDisplayed.isDisplayed()){
            isElementPresent = true;
        }

        return isElementPresent;
    }

    public String getElementText(By element){
        return driver.findElement(element).getText();
    }

    /*
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");

        FileUtils.copyFile(source, file);

        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }
     */
}

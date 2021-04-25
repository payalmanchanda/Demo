package com.demo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Homepage {
    private WebDriver driver;
    private By btnSearch = By.id("search-button");
    private By txtSearchBox = By.id("search-input");
    private By lblResults = By.xpath("//ul[@id='search-results']/li");
    private By lblErrorResult = By.xpath("//ul[@id='search-results']/div");

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Get error results:-
     *
     * @return
     */
    public String getErrorResult(String text) {
        searchQuery(text);
        return driver.findElement(lblErrorResult).getText();
    }


    /**
     * Visibility of elements
     *
     * @return
     */
    public boolean validateButtonVisibility() {

        boolean btnSearchVisible = driver.findElement(btnSearch).isDisplayed();
        boolean btnSearchBoxVisible = driver.findElement(txtSearchBox).isDisplayed();

        return btnSearchBoxVisible && btnSearchVisible;

    }


    /**
     * Validate no of results found:-
     *
     * @param text
     */
    public int resultsFound(String text) {

        searchQuery(text);
        return getResults();
    }

    /**
     * Search results
     *
     * @param text
     */
    private void searchQuery(String text) {

        WebElement searchBox = driver.findElement(txtSearchBox);
        searchBox.clear();
        searchBox.sendKeys(text);
        driver.findElement(btnSearch).click();

    }

    private int getResults() {

        return driver.findElements(lblResults).size();
    }

    public void launchUrl(String url) {

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }
}

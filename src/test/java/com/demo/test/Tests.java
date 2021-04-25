package com.demo.test;

import com.demo.pageobjects.Homepage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends BaseClass {

    Homepage homepage;
    String url = "https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_csharp_search/862b0faa506b8487c25a3384cfde8af4/static/attachments/reference_page.html";

    @Test
    public void test_ExistenceOfButton() {
        homepage = new Homepage(driver);
        homepage.launchUrl(url);
        Assert.assertTrue(homepage.validateButtonVisibility(), "Validate Search button and" +
                " search text box are visible on the page");

    }

    @Test
    public void test_SearchEmptyisForbidden() {
        if (homepage == null){
            homepage = new Homepage(driver);
            homepage.launchUrl(url);
        }
        Assert.assertEquals(homepage.getErrorResult(""), "Provide some query");
    }

    @Test
    public void test_AtleastOneIslandReturn() {
        if (homepage == null){
            homepage = new Homepage(driver);
            homepage.launchUrl(url);
        }
        Assert.assertTrue(homepage.resultsFound("isla") >= 1,
                "Atleast one result is returned for search text isla");
    }

    @Test
    public void test_NoResultsFoundForAQuery() {
        if (homepage == null){
            homepage = new Homepage(driver);
            homepage.launchUrl(url);
        }
        Assert.assertEquals(homepage.getErrorResult("Castle"), "No results");
    }

    @Test
    public void test_GettingSingleResultForQuery() {
        if (homepage == null){
            homepage = new Homepage(driver);
            homepage.launchUrl(url);
        }
        Assert.assertTrue(homepage.resultsFound("Port Royal") == 1,
                "We're getting one result for search query Port Royal");
    }


}

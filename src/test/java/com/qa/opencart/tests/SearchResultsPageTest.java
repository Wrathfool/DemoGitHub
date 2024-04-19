package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchResultsPageTest extends BaseTest {
    @BeforeClass
    public void searchResultsPageSetup() {
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test(priority = 1, dataProvider = "getProductDataCount")
    public void searchResultsTest(String searchKey, int productCount) {
        searchResultsPage = accountsPage.doSearch(searchKey);
        Assert.assertEquals(searchResultsPage.getSearchProductCount(),productCount);
    }

    @DataProvider
    public Object[][] getProductDataCount(){
        return new Object[][]{
                {"macbook", 3},
                {"imac", 1},
                {"samsung", 2}
        };
    }


}

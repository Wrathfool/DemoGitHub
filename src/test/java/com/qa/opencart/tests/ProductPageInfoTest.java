package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductPageInfoTest extends BaseTest {
    @BeforeClass
    public void ProductInfoPageTestSetup() {
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }


    @DataProvider
    public Object[][] getProductSearchData() {
        return new Object[][]{
                {"macbook", "MacBook Pro"},
                {"imac", "iMac"},
                {"samsung", "Samsung SyncMaster 941BW"},
                {"samsung", "Samsung Galaxy Tab 10.1"}
        };
    }

    @Test(dataProvider = "getProductSearchData")
    public void productHeaderTest(String searchKey, String productName) {
        searchResultsPage = accountsPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        String actHeader = productInfoPage.getProductHeader();
        Assert.assertEquals(actHeader, productName);
    }


    @DataProvider(name = "imagesData")
    public Object[][] getProductImagesData() {
        return new Object[][]{
                {"macbook", "MacBook Pro", 4},
                {"imac", "iMac", 3},
                {"samsung", "Samsung SyncMaster 941BW", 1},
                {"samsung", "Samsung Galaxy Tab 10.1", 7}
        };
    }

    @Test(dataProvider = "imagesData")
    public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
        searchResultsPage = accountsPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        Assert.assertEquals(productInfoPage.getProductImagesCount(), imagesCount);
    }

    @Test
    public void productInfoTest() {
        searchResultsPage = accountsPage.doSearch("iMac");
        productInfoPage = searchResultsPage.selectProduct("iMac");
        Map<String, String> productActDetailsMap = productInfoPage.getProductDetailsMap();
        softAssert.assertEquals(productActDetailsMap.get("Brand"), "Apple");
        softAssert.assertEquals(productActDetailsMap.get("Product Code"), "Product 14");
        softAssert.assertEquals(productActDetailsMap.get("Availability"), "Out Of Stock");
        softAssert.assertEquals(productActDetailsMap.get("productprice"), "$122.00");
        softAssert.assertEquals(productActDetailsMap.get("ExTaxPrice"), "$100.00");
        softAssert.assertAll();
    }
}

package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By searchProducts = By.cssSelector("div.product-thumb");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public int getSearchProductCount() {
        return elementUtil.waitForElementsVisible(searchProducts, 10).size();
    }

    public ProductInfoPage selectProduct(String productName){
        System.out.println("Selecting the product: " + productName);
        elementUtil.waitForElementVisible(By.linkText(productName),10).click();
        return new ProductInfoPage(driver);
    }


}

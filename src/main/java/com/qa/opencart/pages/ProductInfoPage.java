package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {

    private WebDriver driver;
    private ElementUtil elementUtil;
    private Map<String, String> productMap = new HashMap<String, String>();

    private By productHeader = By.tagName("h1");
    private By images = By.cssSelector("ul.thumbnails img");
    private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");


    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String getProductHeader() {
        String header = elementUtil.doGetElementText(productHeader);

//        String header = elementUtil.waitForElementVisible(productHeader,10).getText();
        System.out.println("Product information page header: " + header);
        return header;
    }

    public int getProductImagesCount() {
        return elementUtil.waitForElementsVisible(images, 10).size();
    }

    private void getProductMetaData() {
        List<WebElement> productMetaDataList = elementUtil.getElements(productMetaData);

//        Adding the following metadata to hashmap
//        Brand: Apple
//        Product Code: Product 18
//        Reward Points: 800
//        Availability: In Stock

        for (WebElement element : productMetaDataList) {
            String text = element.getText();
            String metaKey = text.split(":")[0].trim();
            String metaValue = text.split(":")[1].trim();
            productMap.put(metaKey, metaValue);
        }
    }

    private void getProductPriceData() {
        List<WebElement> productPriceList = elementUtil.getElements(productPriceData);

//        Adding the following price values to hashmap
//        $2,000.00
//        Ex Tax: $2,000.00

        String price = productPriceList.get(0).getText();
        String exTaxPrice = productPriceList.get(1).getText().split(":")[1].trim();
        productMap.put("productprice", price);
        productMap.put("ExTaxPrice", exTaxPrice);
    }

    public Map<String, String> getProductDetailsMap() {
        productMap.put("header", getProductHeader());
        productMap.put("productimages", String.valueOf(getProductImagesCount()));
        getProductMetaData();
        getProductPriceData();
        return productMap;
    }
}

package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By logoutLink = By.linkText("Logout");
    private By myAccountLink = By.linkText("My Account");
    private By headers = By.cssSelector("div#content h2");
    private By search = By.name("search");
    private By searchButton = By.cssSelector("div#search button");

    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String getAccountsPageTitle() {

        String title = elementUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, 5);
        System.out.println("Account page title: " + title);
        return title;
    }

    public String getAccountsPageUrl() {
        String url = elementUtil.waitForURLContains(AppConstants.ACCOUNT_PAGE_URL_FRACTION, 5);
        System.out.println("Account Page Url: " + url);
        return url;
    }

    public boolean isLogoutLinkExist() {
        return elementUtil.waitForElementVisible(logoutLink, 10).isDisplayed();
    }

    public boolean isMyAccountLinkExist() {
        return elementUtil.waitForElementVisible(myAccountLink, 10).isDisplayed();
    }

    public List<String> getAccountsPageHeadersList() {
        List<WebElement> headersElementList = elementUtil.getElements(headers);
        List<String> headersList = new ArrayList<String>();
        for (WebElement e : headersElementList) {
            String header = e.getText();
            headersList.add(header);
        }
        return headersList;
    }

    public SearchResultsPage doSearch(String searchKey) {
        System.out.println("This is your search key: " + searchKey);
        elementUtil.doSendKeys(search, searchKey);
        elementUtil.doClick(searchButton);
        return new SearchResultsPage(driver);
    }
}

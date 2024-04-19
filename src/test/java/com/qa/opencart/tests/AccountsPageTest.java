package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AccountsPageTest extends BaseTest {

    @BeforeClass
    public void accountPageSetup(){
        accountsPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test
    public void accountPageTitleTest(){
        String actTitle = accountsPage.getAccountsPageTitle();
        Assert.assertEquals(actTitle, AppConstants.ACCOUNT_PAGE_TITLE);
    }

    @Test
    public void accountPageUrlTest(){
        String actUrl = accountsPage.getAccountsPageUrl();
        Assert.assertTrue(actUrl.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION));
    }

    @Test
    public void isLogoutLinkExist(){
        Assert.assertTrue(accountsPage.isLogoutLinkExist());
    }

    @Test
    public void isMyAccountLinkExist(){
        Assert.assertTrue(accountsPage.isMyAccountLinkExist());
    }

    @Test
    public void accountsPageHeaderTest(){
       List<String> actHeaderList = accountsPage.getAccountsPageHeadersList();
       System.out.println(actHeaderList);
    }

    @Test
    public void searchTest(){
        accountsPage.doSearch("macbook");
    }
}

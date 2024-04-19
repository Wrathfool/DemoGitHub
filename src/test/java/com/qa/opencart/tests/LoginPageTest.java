package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void loginPageTitleTest(){
        String actTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
    }

    @Test(priority = 2)
    public void loginPageURLTest(){
        String actUrl = loginPage.getLoginPageUrl();
        Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
    }

    @Test(priority = 3)
    public void forgotPwdLinkExistTest(){
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @Test(priority = 4)
    public void loginTest(){
//        String accPageTitle = loginPage.doLogin("AmeyaGodse@gmail.com","Test123");
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertEquals(accountsPage.getAccountsPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE);
    }
}

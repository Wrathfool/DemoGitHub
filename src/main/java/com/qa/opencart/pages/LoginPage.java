package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    //1. Private By Locators
    //2. Public constructor
    //3. Public Page Actions/Methods

    private WebDriver driver;
    private ElementUtil elementUtil;
    private By emailId = By.id("input-email");
    private By password = By.id("input-password");
    private By loginButton = By.xpath("//input[@value='Login']");
    private By forgotPwdLink = By.linkText("Forgotten Password");
    private By register = By.linkText("Register");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String getLoginPageTitle() {

        String title = elementUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, 5);
        System.out.println(title);
        return title;
    }

    public String getLoginPageUrl() {
        String url = elementUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, 5);
        System.out.println(url);
        return url;
    }

    public boolean isForgotPwdLinkExist() {
        return elementUtil.isElementDisplayed(forgotPwdLink);
    }

    public AccountsPage doLogin(String username, String pwd) {
        System.out.println("Your user credentials: " + username + " - " + pwd);
        elementUtil.waitForElementVisible(emailId, 5).sendKeys(username);
        elementUtil.doSendKeys(password, pwd);
        elementUtil.doClick(loginButton);
        return new AccountsPage(driver);
    }

    public RegistrationPage navigateToRegisterPage() {
        elementUtil.waitForElementVisible(register, 10).click();
        return new RegistrationPage(driver);
    }
}

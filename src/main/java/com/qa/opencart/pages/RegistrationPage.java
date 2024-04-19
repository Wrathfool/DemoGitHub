package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.name("telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");
    private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='1']");
    private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='0']");
    private By agreeCheckBox = By.name("agree");
    private By btnContinue = By.xpath("//input[@value='Continue']");
    private By successMsg = By.cssSelector("div#content h1");
    private By logoutLink = By.linkText("Logout");
    private By registerLink = By.linkText("Register");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String userRegistration(String firstName, String lastName, String email,
                                   String telephone, String password, String subscribe) {

        elementUtil.waitForElementVisible(this.firstName, 10).sendKeys(firstName);
        elementUtil.doSendKeys(this.lastName, lastName);
        elementUtil.doSendKeys(this.email, email);
        elementUtil.doSendKeys(this.telephone, telephone);
        elementUtil.doSendKeys(this.password, password);
        elementUtil.doSendKeys(this.confirmPassword, password);


        if (subscribe.equalsIgnoreCase("yes")) {
            elementUtil.doClick(subscribeYes);
        } else {
            elementUtil.doClick(subscribeNo);
        }

        elementUtil.doClick(agreeCheckBox);
        elementUtil.doClick(btnContinue);

        System.out.println("User created \n First Name: " + firstName + "\n Last Name: " + lastName +
                "\n Email: " + email + "\n Telephone: " + "\n Password: " + password + "\n Subscribe: " + subscribe.toUpperCase());

        String userRegistrationSuccessMsg = elementUtil.waitForElementVisible(successMsg, 5).getText();

        elementUtil.doClick(logoutLink);
        elementUtil.doClick(registerLink);

        return userRegistrationSuccessMsg;
    }


}

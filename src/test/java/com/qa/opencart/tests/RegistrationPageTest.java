package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.StringUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationPageTest extends BaseTest {

    @BeforeClass
    public void registrationPageSetup() {
        registrationPage = loginPage.navigateToRegisterPage();
    }


    @DataProvider(name = "userRegistrationData")
    public Object[][] getUserRegistrationData(){
        return new Object[][]{
                {"Tester1", "Tests", " 9876543210", "tester123", "nO"},
                {"Tester2", "Tests", " 9876543210", "tester123", "yEs"},
                {"Tester3", "Tests", " 9876543210", "tester123", "YEs"}
        };
    }

    @Test(dataProvider = "userRegistrationData")
    public void registerUserTest(String firstName, String lastName, String telephone, String password, String subscribe) {
        String actSuccessMsg = registrationPage.userRegistration(firstName, lastName,
                StringUtil.getRandomEmailId(), telephone, password, subscribe);
        Assert.assertEquals(actSuccessMsg, AppConstants.USER_REGISTRATION_SUCCESS_MESSAGE);
    }
}

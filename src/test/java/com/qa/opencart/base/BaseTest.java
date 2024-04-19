package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

    DriverFactory df;
    WebDriver driver;
    protected Properties prop;

    protected LoginPage loginPage;
    protected AccountsPage accountsPage;
    protected SearchResultsPage searchResultsPage;
    protected ProductInfoPage productInfoPage;
    protected RegistrationPage registrationPage;
    protected SoftAssert softAssert;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browserName){
        df= new DriverFactory();
        prop =  df.initProp();

        if(browserName!= null){
            prop.setProperty("browser",browserName);
        }
        driver = df.initDriver(prop);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}

package com.qa.opencart.factory;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class DriverFactory {
    //    WebDriver driver;
    Properties prop;
    OptionsManager optionsManager;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    public WebDriver initDriver(Properties prop) {

        String browserName = prop.getProperty("browser");

//        System.out.println("Browser Name: " + browserName);
        Log.info("Browser Name: " + browserName);

        optionsManager = new OptionsManager(prop);

        switch (browserName.toLowerCase().trim()) {

            case "chrome":
//                driver = new ChromeDriver(optionsManager.getChromeOptions());
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                break;

            case "firefox":
//                driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                break;

            case "edge":
//                driver = new EdgeDriver(optionsManager.getEdgeOptions());
                tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                break;

            case "safari":
//                driver = new SafariDriver();
                tlDriver.set(new SafariDriver());
                break;

            default:
                System.out.println("Enter the correct browser name " + browserName);
                throw new BrowserException("No Browser found with the name supplied: " + browserName);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));

//        return driver;
        return getDriver();
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public Properties initProp() {

        FileInputStream fileInputStream = null;
        prop = new Properties();
        String envName = System.getProperty("env");
        System.out.println("Running Tests on Env: " + envName);


        try {
            if (envName == null) {
                System.out.println("Env name is null! Running the code on QA env");
                fileInputStream = new FileInputStream("./src/test/resources/config/config.qa.properties");
            } else {
                switch (envName.toLowerCase().trim()) {
                    case "qa":
                        fileInputStream = new FileInputStream("./src/test/resources/config/config.qa.properties");
                        break;

                    case "dev":
                        fileInputStream = new FileInputStream("./src/test/resources/config/config.dev.properties");
                        break;

                    case "stage":
                        fileInputStream = new FileInputStream("./src/test/resources/config/config.stage.properties");
                        break;

                    case "prod":
                        fileInputStream = new FileInputStream("./src/test/resources/config/config.properties");
                        break;

                    default:
                        System.out.println("Pass the correct env name: " + envName);
                        throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND + ": " + envName);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getScreenshot(String methodName) {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileHandler.copy(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}

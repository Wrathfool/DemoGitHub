package com.qa.opencart.pages;

import com.qa.opencart.logger.Log;
import org.openqa.selenium.By;

public class CartPage {
    int i =10;
    int y =100;

    private By name = By.id("name");

    public void getName(){
        Log.info("Name is Painfoool");
    }
}

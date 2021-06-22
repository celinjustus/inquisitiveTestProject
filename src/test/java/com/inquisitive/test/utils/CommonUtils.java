package com.inquisitive.test.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import java.util.Set;

public class CommonUtils {
    public static long IMPLICIT_WAIT=10;
    public static WebDriverWait wait;
    private static int timeout = 100;

    public static void switchWindow(WebDriver driver){
        Set<String> windows = driver.getWindowHandles();
        for (String handle: windows){
            driver.switchTo().window(handle);
        }
    }

    public static Select getSelectOptions(WebElement element) {
        return new Select(element);
    }
    public static void setOption(WebElement element,String value)
    {
        getSelectOptions(element).selectByVisibleText(value);
    }

    public static void waitForElementToBeClickable(WebElement element,WebDriver driver) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            throw new TestException("The following element is not clickable: " + element);
        }
    }

}

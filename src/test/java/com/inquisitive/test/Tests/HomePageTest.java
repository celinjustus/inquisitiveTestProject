package com.inquisitive.test.Tests;

import com.inquisitive.test.Pages.CreateAnAccount;
import com.inquisitive.test.Pages.HomePage;
import com.inquisitive.test.app.setup.base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTest extends base {
    private final static Logger logger = LogManager.getLogger(HomePageTest.class);
    HomePage homepage;
    CreateAnAccount createAccount;

    @Test
    public void launchHomePageTest() throws IOException {
        logger.info("Starting Testcase " + this.getClass().getName());
        try {
            homepage=new HomePage(driver);
            homepage.clickOnJoinInquisitiveButton();
            createAccount=homepage.clickJoin(driver);
            Thread.sleep(5000);
            String expected=homepage.getCreateYourAccountText();
            Assert.assertEquals(expected,"Create your account");
        } catch (Exception e) {
            e.printStackTrace();
            getScreenShot(driver);
            logger.error("Testcase failed!" +  e);
            driver.quit();
            setup();
            Assert.fail("Testcase failed!");
        }

    }
}

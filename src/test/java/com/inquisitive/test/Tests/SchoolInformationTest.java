package com.inquisitive.test.Tests;

import com.inquisitive.test.Pages.CreateAnAccount;
import com.inquisitive.test.Pages.HomePage;
import com.inquisitive.test.Pages.SchoolInformation;
import com.inquisitive.test.Pages.UserDetails;
import com.inquisitive.test.app.setup.base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class SchoolInformationTest extends base {
    private final static Logger logger = LogManager.getLogger(SchoolInformationTest.class);
    HomePage homepage;
    CreateAnAccount createAccount;
    UserDetails userDetails;
    SchoolInformation schoolinfo;

    @BeforeMethod
    public void navigateToSchoolInfoPage(){
        homepage=new HomePage(driver);
        homepage.clickOnJoinInquisitiveButton();
        createAccount=homepage.clickJoin(driver);
        userDetails=createAccount.createUserAccount("celin.test@inquisitive.com","Welcome@123");
        schoolinfo=userDetails.enterUserDetails("Mrs","celin","justus");
    }
    @Test
    public void enterSchoolInfoDetailsTest() throws IOException {
        try {
            schoolinfo.enterSchoolInformation("New South Wales","Inquisitive Test School 95, Mosman, 2088, NSW","Principal");
            String expected=schoolinfo.getchoosePlanText();
            System.out.println("aaa"+expected);
            Assert.assertTrue(expected.contains("Choose the right plan for you"));
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

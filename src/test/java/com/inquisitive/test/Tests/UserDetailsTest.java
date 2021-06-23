package com.inquisitive.test.Tests;

import com.inquisitive.test.AssertTest.AssertUserDetails;
import com.inquisitive.test.Pages.CreateAnAccount;
import com.inquisitive.test.Pages.HomePage;
import com.inquisitive.test.Pages.UserDetails;
import com.inquisitive.test.app.setup.base;
import com.inquisitive.test.utils.App;
import com.inquisitive.test.utils.ExcelUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserDetailsTest extends base {
    private final static Logger logger = LogManager.getLogger(UserDetailsTest.class);
    HomePage homepage;
    CreateAnAccount createAccount;
    UserDetails userDetails;

    @BeforeMethod
    public void navigateToUserDetailsPage(){
        homepage=new HomePage(driver);
        homepage.clickOnJoinInquisitiveButton();
        createAccount=homepage.clickJoin(driver);
        userDetails=createAccount.createUserAccount("celin.test@inquisitive.com","Welcome@123");
    }
    @Test(dataProvider="enterUser")
    public void enterUserDetailsTest(String testcaseName,String title,String firstName, String LastName) throws IOException {
        try {
            userDetails.enterUserDetails(title,firstName,LastName);
            AssertUserDetails assertUserDetails= new AssertUserDetails();
            assertUserDetails.ValidateUserDetails(testcaseName,userDetails);
            driver.get(App.getProperty("app.url"));
        } catch (Exception e) {
            e.printStackTrace();
            getScreenShot(driver);
            logger.error("Testcase failed!" +  e);
            driver.quit();
            setup();
            Assert.fail("Testcase failed!");
        }
    }
    @DataProvider(name="enterUser")
    public Object[][] getData() throws Exception {
        String excelPath="";
        ExcelUtil excelUtil=new ExcelUtil("testData/UserData.xlsx","UserDetails");
        Object data[][]= excelUtil.testData("UserDetails");
        return data;
    }

}

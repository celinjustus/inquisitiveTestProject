package com.inquisitive.test.Tests;

import com.inquisitive.test.AssertTest.AssertCreateAccount;
import com.inquisitive.test.Pages.CreateAnAccount;
import com.inquisitive.test.Pages.HomePage;
import com.inquisitive.test.app.setup.base;
import com.inquisitive.test.utils.App;
import com.inquisitive.test.utils.ExcelUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class CreateAnAccountTest extends base{
    private final static Logger logger = LogManager.getLogger(CreateAnAccountTest.class);

    HomePage homepage;
    CreateAnAccount createAccount;

    @BeforeMethod
    public void navigateToCreateAccountPage(){
        homepage=new HomePage(driver);
        homepage.clickOnJoinInquisitiveButton();
        createAccount=homepage.clickJoin(driver);
    }
    @Test(dataProvider="createAnUser")
    public void createAnUserAccountTest(String testcaseName,String email,String password) {
        try {
            createAccount.createUserAccount(email,password);
            Thread.sleep(5000);
            AssertCreateAccount assertCreateAccount=new AssertCreateAccount();
            assertCreateAccount.ValidateResults(testcaseName, createAccount);
            driver.get(App.getProperty("app.url"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Testcase failed!" +  e);
            driver.quit();
            setup();
            Assert.fail("Testcase failed!");
        }
    }
    @DataProvider(name="createAnUser")
    public Object[][] getData() throws Exception {
        String excelPath="";
        ExcelUtil excelUtil=new ExcelUtil("testData/CreateanAccuntData.xlsx","CreateAccount");
        Object data[][]= excelUtil.testData("CreateAccount");
        return data;
    }

}

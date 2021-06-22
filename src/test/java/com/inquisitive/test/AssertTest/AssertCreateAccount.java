package com.inquisitive.test.AssertTest;

import com.inquisitive.test.Pages.CreateAnAccount;
import com.inquisitive.test.Tests.CreateAnAccountTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AssertCreateAccount {
    private final static Logger logger = LogManager.getLogger(AssertCreateAccount.class);
    public SoftAssert softassertion = new SoftAssert();

    public void ValidateResults(String testcaseName, CreateAnAccount createAccount) {
        if (testcaseName.equalsIgnoreCase("IncorrectEmail")) {
            String expected= createAccount.getinvalidEmailErrorMessage();
            softassertion.assertEquals(expected, "Email address is not valid.", "Error message is not matching");
        } else if (testcaseName.equalsIgnoreCase("IncorrectPassword")) {
            String expected= createAccount.getInvalidPasswordErrorMessage();
            softassertion.assertEquals(expected, "Password must be 8 or more characters.", "Error message is not matching");
        } else if (testcaseName.equalsIgnoreCase("InvalidEducationEmail")) {
            String expected= createAccount.getInvalidEducationEmailErrorMessage();
            softassertion.assertTrue(expected.contains("Must be your education email address."));
        } else if (testcaseName.equalsIgnoreCase("CorrectEmail")) {
            String expected = createAccount.getyourDetailsText();
            System.out.println(expected);
            softassertion.assertEquals(expected, "Your details", "Error message is not matching");
        }
        softassertion.assertAll();
    }
}

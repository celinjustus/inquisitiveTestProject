package com.inquisitive.test.AssertTest;


import com.inquisitive.test.Pages.UserDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AssertUserDetails {
    private final static Logger logger = LogManager.getLogger(AssertUserDetails.class);
    public SoftAssert softassertion1 = new SoftAssert();

    public void ValidateUserDetails(String testcaseName, UserDetails userDetails) {
        if (testcaseName.equalsIgnoreCase("CorrectUser")) {
            String expected=userDetails.gethelloText();
            softassertion1.assertTrue(expected.contains("Hello"));
        } else if (testcaseName.equalsIgnoreCase("FirstNameMissing")) {
            String expected= userDetails.getFirstNameErrorMessage();
            softassertion1.assertEquals(expected, "First name is required.", "Error message is not matching");
        } else if (testcaseName.equalsIgnoreCase("LastNameMissing")) {
            String expected= userDetails.getLastNameErrorMessage();
            softassertion1.assertEquals(expected, "Last name is required.", "Error message is not matching");
        }
        softassertion1.assertAll();
    }
}

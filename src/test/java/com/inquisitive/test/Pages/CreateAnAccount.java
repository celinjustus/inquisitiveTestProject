package com.inquisitive.test.Pages;

import com.inquisitive.test.app.setup.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CreateAnAccount extends base {
    private WebDriver driver;
    @FindBy(how= How.ID, using="email")
    private WebElement emailTextBox;

    @FindBy(how= How.ID, using="password")
    private WebElement passwordTextBox;

    @FindBy(how= How.XPATH, using="//div[@class='joinstyle__JoinButtonsStyled-nal2h-6 eVpTBI']/button[1]")
    private WebElement continueButton;

    @FindBy(how= How.TAG_NAME, using="h1")
    private WebElement yourDetailsText;
    @FindBy(how= How.ID, using="join-account-error-email")
    private WebElement invalidEmail;
    @FindBy(how= How.ID, using="join-account-error-password")
    private WebElement invalidPassword;
    @FindBy(how= How.XPATH, using="//div[@data-selector='error-non-education-email']")
    private WebElement invalidEducationErrorMessage;

    public CreateAnAccount(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Enter email and password on Create Account Screen
    public UserDetails createUserAccount(String email,String password){
        emailTextBox.clear();
        emailTextBox.sendKeys(email);
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
        continueButton.submit();
        return new UserDetails(driver);
    }
    public String getyourDetailsText(){
        return yourDetailsText.getText();
    }

    public String getinvalidEmailErrorMessage(){
        return invalidEmail.getText();
    }

    public String getInvalidPasswordErrorMessage(){
        return invalidPassword.getText();
    }

    public String getInvalidEducationEmailErrorMessage(){
        return invalidEducationErrorMessage.getText();
    }
}

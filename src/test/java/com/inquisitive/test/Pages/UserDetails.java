package com.inquisitive.test.Pages;

import com.inquisitive.test.app.setup.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import static com.inquisitive.test.utils.CommonUtils.setOption;

public class UserDetails extends base {
    private WebDriver driver;
    @FindBy(how= How.ID, using="title")
    private WebElement titleDropBox;

    @FindBy(how= How.ID, using="firstName")
    private WebElement firstNameTextBox;

    @FindBy(how= How.ID, using="lastName")
    private WebElement lastNameTextBox;

    @FindBy(how= How.XPATH, using="//div[@class='joinstyle__JoinButtonsStyled-nal2h-6 eVpTBI']/button[1]")
    private WebElement continueButton;

    @FindBy(how= How.TAG_NAME, using="h1")
    private WebElement helloText;

    @FindBy(how= How.XPATH, using="//div[@data-selector='error-firstName']")
    private WebElement invalidFirstNameErrorMessage;

    @FindBy(how= How.XPATH, using="//div[@data-selector='error-lastName']")
    private WebElement invalidLastNameErrorMessage;

    public UserDetails(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Enter User details
    public SchoolInformation enterUserDetails(String title,String fName, String lname){
        setOption(titleDropBox,title);
        firstNameTextBox.clear();
        firstNameTextBox.sendKeys(fName);
        lastNameTextBox.clear();
        lastNameTextBox.sendKeys(lname);
        continueButton.submit();
        return new SchoolInformation(driver);
    }
    public String gethelloText(){
        return helloText.getText();
    }
    public String getFirstNameErrorMessage(){
        return invalidFirstNameErrorMessage.getText();
    }
    public String getLastNameErrorMessage(){
        return invalidLastNameErrorMessage.getText();
    }

}

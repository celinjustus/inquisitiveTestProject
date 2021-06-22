package com.inquisitive.test.Pages;

import com.inquisitive.test.app.setup.base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static com.inquisitive.test.utils.CommonUtils.setOption;
import static com.inquisitive.test.utils.CommonUtils.waitForElementToBeClickable;

public class SchoolInformation extends base {
    private WebDriver driver;
    @FindBy(how= How.ID, using="state")
    private WebElement stateDropBox;

    @FindBy(how= How.ID, using="schoolId")
    private WebElement SearchSchoolBox;

    @FindBy(how= How.ID, using="position")
    private WebElement positionDropBox;

    @FindBy(how= How.XPATH, using="//div[@class='joinstyle__JoinButtonsStyled-nal2h-6 eVpTBI']/button[1]")
    private WebElement continueButton;

    @FindBy(how= How.XPATH, using="//div[@class='join-planstyle__JoinPlanWrapperStyled-sc-1ujqubt-0 iCKzZl']/h1")
    private WebElement choosePlanText;

      public SchoolInformation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
        //Enter School information
    public void enterSchoolInformation(String state, String searchSchool, String position) {
        setOption(stateDropBox,state);
        setOption(positionDropBox,position);
        SearchSchoolBox.sendKeys(searchSchool);
        waitForElementToBeClickable(SearchSchoolBox,driver);
        SearchSchoolBox.sendKeys(Keys.ARROW_DOWN);
        waitForElementToBeClickable(SearchSchoolBox,driver);
        SearchSchoolBox.sendKeys(Keys.ENTER);
        waitForElementToBeClickable(SearchSchoolBox,driver);
        continueButton.submit();
    }

    public String getchoosePlanText(){
        return choosePlanText.getText();
    }
}

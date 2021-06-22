package com.inquisitive.test.Pages;

import com.inquisitive.test.app.setup.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static com.inquisitive.test.utils.CommonUtils.switchWindow;

public class HomePage extends base {
    @FindBy(how= How.XPATH, using="//button[@data-selector='btn-join-inquisitive']")
    private WebElement joinInquisitiveButton;

    @FindBy(how= How.XPATH, using="//div[@class='join-modalstyles__JoinActionsContainerStyled-sc-1h7pj1g-0 exqyXK']/*")
    private WebElement joinButton;

    @FindBy(how= How.TAG_NAME, using="h1")
    private WebElement CreateYourAccountText;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickOnJoinInquisitiveButton(){
        joinInquisitiveButton.click();
    }
    public CreateAnAccount clickJoin(WebDriver driver){
        switchWindow(driver);
        joinButton.click();
        return new CreateAnAccount(driver);
    }

    public String getCreateYourAccountText(){
        return CreateYourAccountText.getText();
    }
}

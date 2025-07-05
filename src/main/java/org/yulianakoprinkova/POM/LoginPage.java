package org.yulianakoprinkova.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private static final String USER = "demoUser111";
    private static final String PASS = "demoUser111";

    @FindBy(css = "p.h4")
    private WebElement loginFormTitle;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameInputField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInputField;
    @FindBy(css = "input")
    private WebElement rememberMeCheckBox;
    @FindBy (xpath = "//div/span")
    private WebElement rememberMeCheckBoxText;
    @FindBy (xpath = "//p/span")
    private WebElement notAMemberText;
    @FindBy(id = "sign-in-button")
    private WebElement loginSubmitButton;
    @FindBy(css = "i")
    private WebElement logoutLink;
    @FindBy (id = "toast-container")
    private WebElement toastContainer;

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    public void loginWithTestUserCredentials(){
        provideUser(USER);
        providePass(PASS);
        clickOnLoginFormSubmitButton();
    }

    public void provideUser(String userNameText){
        wait.until(ExpectedConditions.visibilityOf(usernameInputField));
        usernameInputField.clear();
        usernameInputField.sendKeys(userNameText);
    }

    public void providePass(String pass){
        wait.until(ExpectedConditions.visibilityOf(passwordInputField));
        passwordInputField.clear();
        passwordInputField.sendKeys(pass);
    }

    public void clickOnRememberMeCheckBox(){
        wait.until(ExpectedConditions.visibilityOf(rememberMeCheckBox));
        rememberMeCheckBox.click();
    }

    public void clickOnLoginFormSubmitButton(){
        wait.until(ExpectedConditions.visibilityOf(loginSubmitButton));
        loginSubmitButton.click();
    }

    public void clickOnLogOutButton () {
        wait.until(ExpectedConditions.visibilityOf(loginSubmitButton));
        wait.until(ExpectedConditions.invisibilityOf(toastContainer));
        logoutLink.click();
    }

    public String getUserNamePlaceholderText(){
        return getElementPlaceholderValue(usernameInputField);
    }

    public String getPasswordPlaceholderText(){
        return getElementPlaceholderValue(passwordInputField);
    }

    public String getRememberMeText(){
        return getElementText(rememberMeCheckBoxText);
    }

    public String getLoginFormHeaderText(){
        return getElementText(loginFormTitle);
    }

    public String getLoginFormNotAMemberText() {
        return getElementText(notAMemberText);
    }

    public String getLoginPopupSuccessMessageText() {
        return getElementText(toastContainer);
    }

    public String getSignInButtonLabel(){
        return getElementText(loginSubmitButton);
    }

    public boolean isLoginFormHeaderTextShown(){
        return isElementPresent(loginFormTitle);
    }
}

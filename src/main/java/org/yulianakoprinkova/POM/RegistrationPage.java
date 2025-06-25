package org.yulianakoprinkova.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrationPage extends BasePage {

    public static final String REG_PAGE_SUFIX = "/users/register";

    //2.LOCATORS
    //Registration form
    @FindBy(css = "h4")
    private WebElement regFormTitle;

    @FindBy(xpath = "//input[contains(@name, \"username\")]")
    private WebElement regUsernameInputField;

    @FindBy (xpath = "//input[contains(@type, \"email\")]")
    private WebElement regEmailInputField;

    @FindBy (xpath = "//input[contains(@formcontrolname, \"birthDate\")]")
    private WebElement regBirthDateInputField;

    @FindBy (id = "defaultRegisterFormPassword" )
    private WebElement regPasswordInputField;

    @FindBy (id = "defaultRegisterPhonePassword")
    private WebElement regConfirmPasswordInputField;

    @FindBy (xpath = "//textarea")
    private WebElement publicInfoTextArea;

    @FindBy (id = "sign-in-button" )
    private WebElement registrationFormSubmitButton;

    @FindBy (id = "toast-container")
    private WebElement toastContainerSuccessRegMessage;

    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    public void navigateToRegistrationPageByURL(){
        navigateTo(REG_PAGE_SUFIX);
    }

    // methods to fill out Registration page form
    public String newUsername() {
        int randomNumberForUsername = (int)(Math.random() * 101);
        String username = ("User" + randomNumberForUsername);
        return username;
    }

    public void provideUserName(){
        String provideUsername = newUsername();
        typeText(regUsernameInputField,provideUsername);
    }

    public String newValidEmail() {
        int randomNumberForEmail = (int)(Math.random() * 101);
        String email = "user" + randomNumberForEmail + "@gmail.com";
        return email;
    }

    public void provideEmail(){
        typeText(regEmailInputField, newValidEmail());
    };

    public void provideBDayInfo(){
        typeText(regBirthDateInputField,"22022022");
    };

    public void providePass(){
        typeText(regPasswordInputField,"22022022!A");
    };

    public void providePassConfirm(){
        typeText(regConfirmPasswordInputField,"22022022!A");
    };

    public void providePublicInfo(){
        typeText(publicInfoTextArea,"Public profile");
    };

    public void clickOnSubmitBtn(){
        clickOn(registrationFormSubmitButton);
    }

    //Getters for Registration page
    public String getRegFormHeaderText(){
        return getElementText(regFormTitle);
    }

    public String getRegPageTitle () {
        return driver.getTitle();
    }

    public String getToastContainerText() {
        return getElementText(toastContainerSuccessRegMessage);
    }

    // Verification methods for Registration page
    public boolean isRegFormSuccessMessageShown(){
        return isElementPresent(toastContainerSuccessRegMessage);
    }





}

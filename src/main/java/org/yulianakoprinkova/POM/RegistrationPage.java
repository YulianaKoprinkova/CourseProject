package org.yulianakoprinkova.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrationPage extends BasePage {

    public static final String REG_PAGE_SUFIX = "/users/RegisterTests";

    //2.LOCATORS
    //Registration form
    @FindBy(css = "h4")
    private WebElement regFormTitle;

    @FindBy(xpath = "//input[contains(@name, \"username\")]") // = driver.findElement(By.xpath(USERNAME_REG_INPUT_FIELD_XPATH));
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

    //USER ACTIONS ON WEB ELEMENTS

    //REGISTRATION FORM TITLE

    public String getRegFormHeaderText(){
        return getElementText(regFormTitle);
    }

    //FILL OUT REGISTRATION FIELDS

    public void provideUserName(){
        String providedDemoUser = demoUsername();
        typeText(regUsernameInputField,providedDemoUser);
    };

    public void provideEmail(){
        typeText(regEmailInputField,randomValidEmail());
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


    //Support methods for reg page
    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }


    //Support utils for test data gen
    public String demoUsername() {
        String username = "Demo" + getCurrentTime();
        return username;
    }

    public String randomValidEmail() {
        String email = "demo" + getCurrentTime() + "@gmail.com";
        return email;
    }

    public String getRegPageTitle () {
        return driver.getTitle();
    }



    // VERIFICATION METHODS FOR REGISTRATION PAGE
    public boolean isRegFormSuccessMessageShown(){
        return isElementPresent(toastContainerSuccessRegMessage);
    }

    public String getToastContainerText() {
        return getElementText(toastContainerSuccessRegMessage);
    }



}

package org.yulianakoprinkova.LoginTests;

import org.yulianakoprinkova.POM.HomePage;
import org.yulianakoprinkova.POM.LoginPage;
import org.yulianakoprinkova.BaseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginHappyPathTests extends BaseTest {

    private static final String LOGIN_FORM_TITLE = "Sign in";
    private static final String LOGIN_FORM_CHECKBOX_TEXT = "Remember me";
    private static final String LOGIN_FORM_BUTTON_TEXT = "Sign in";
    private static final String LOGIN_FORM_USERNAME_PLACEHOLDER_TEXT = "Username or email";
    private static final String LOGIN_FORM_PASSWORD_PLACEHOLDER_TEXT = "Password";
    private static final String LOGIN_FORM_NOT_A_MEMBER = "Not a member?";
    public static final String LOGIN_SUCCESSFUL_MSG = "Successful login!";
    public static final String LOGOUT_SUCCESSFUL_MSG = "Successful logout!";
    public static final String USER = "demoUser111";
    public static final String PASS = "demoUser111";

    @Test (priority = 1)
    public void verifyTheUserCanLogin() {

        log.info("STEP 1: The Skillo Home Page is opened by unlogged user");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2. Verify that the LoginTests link is presented on the home page");
        boolean isShownNavBarLoginLink = homePage.isLoginLinkShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 3: The user is navigating to the Login page by clicking on the navigation bar Login link");
        homePage.clickOnLoginNavBar();

        log.info("STEP 4.: The user gets successfully to the LoginTests page");
        LoginPage loginPage = new LoginPage(super.driver,log);

        log.info("STEP 5. The user enters a username");
        loginPage.provideUser(USER);

        log.info("STEP 6. The user enters a password");
        loginPage.providePass(PASS);

        log.info("STEP 7. The user clicks on the remember-me checkbox");
        loginPage.clickOnRememberMeCheckBox();

        log.info("STEP 8. The user clicks on the Sign In Button");
        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 9. Assert if there is successful Login message shown");
        String popupMessage = loginPage.getLoginPopupSuccessMessageText();
        Assert.assertEquals(popupMessage, LOGIN_SUCCESSFUL_MSG);
    }

    @Test (priority = 2)
    public void verifyLoginPageForm() {

        log.info("STEP 1: The Skillo Home Page is opened by unlogged user");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2. Verify that the Login link is presented on the home page");
        boolean isShownNavBarLoginLink = homePage.isLoginLinkShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 3: The user is navigating to the Login page by clicking on the navigation bar Login link");
        homePage.clickOnLoginNavBar();

        log.info("STEP 4.: The user gets successfully to the Login page");
        LoginPage loginPage = new LoginPage(super.driver,log);

        log.info("STEP 5. Assert if the Login form title is shown");
        Assert.assertTrue(loginPage.isLoginFormHeaderTextShown());

        log.info("STEP 6.: Assert the Login form has the correct title");
        String actualLoginFormHeaderText = loginPage.getLoginFormHeaderText();
        Assert.assertEquals(actualLoginFormHeaderText,LOGIN_FORM_TITLE);

        log.info("STEP 7. Assert the username field placeholder text is correct ");
        String actualUsernameInputFieldPlaceholderText = loginPage.getUserNamePlaceholderText();
        Assert.assertEquals(actualUsernameInputFieldPlaceholderText,LOGIN_FORM_USERNAME_PLACEHOLDER_TEXT);

        log.info("STEP 8. Assert the password field placeholder text is correct ");
        String actualPasswordInputFieldPlaceholderText = loginPage.getPasswordPlaceholderText();
        Assert.assertEquals(actualPasswordInputFieldPlaceholderText,LOGIN_FORM_PASSWORD_PLACEHOLDER_TEXT);

        log.info("STEP 9. Assert the remember-me checkbox text is correct");
        String rememberMeCheckBoxText = loginPage.getRememberMeText();
        Assert.assertEquals(rememberMeCheckBoxText, LOGIN_FORM_CHECKBOX_TEXT);

        log.info("STEP 10. Assert that the Sign In Button label is correct");
        String actualLoginButtonText = loginPage.getSignInButtonLabel();
        Assert.assertEquals(actualLoginButtonText, LOGIN_FORM_BUTTON_TEXT);

        log.info("STEP 14. Assert not-a-member text on form");
        String actualNotAMemberText = loginPage.getLoginFormNotAMemberText();
        Assert.assertEquals(actualNotAMemberText, LOGIN_FORM_NOT_A_MEMBER);
    }

    @Test (priority=2)
    public void verifyUserCanLogout() {

        log.info("STEP 1: The Skillo Home page is opened ");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2: The user is navigating to the Login page by clicking on the navigation bar Login link");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: The registered user opens the Login page and logs in ");
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.loginWithTestUserCredentials();

        log.info("STEP 4. The user clicks on the log out link");
        loginPage.clickOnLogOutButton();

        log.info("STEP 5. Assert if there is successful logout message shown");
        String popupMessage = loginPage.getLoginPopupSuccessMessageText();
        Assert.assertEquals(popupMessage, LOGOUT_SUCCESSFUL_MSG);
    }
}

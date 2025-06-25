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
    public static final String LOGIN_SUCCESSFUL_MSG = "Successful LoginTests!";
    public static final String LOGIN_NOT_SUCCESSFUL_MSG = "Wrong username or password!";
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

        log.info("STEP 3: The user is navigating to the Login page by clicking on the navigation bar LoginTests link");
        homePage.clickOnLoginNavBar();

        log.info("STEP 4.: The user gets successfully to the LoginTests page");
        LoginPage loginPage = new LoginPage(super.driver,log);

        log.info("STEP 5. Assert if the LoginTests form title is shown");
        Assert.assertTrue(loginPage.isLoginFormHeaderTextShown());

        log.info("STEP 6.: The LoginTests form has the correct title");
        String actualLoginFormHeaderText = loginPage.getLoginFormHeaderText();
        Assert.assertEquals(actualLoginFormHeaderText,LOGIN_FORM_TITLE);

        log.info("STEP 7. Assert the username field placeholder text is correct ");
        String actualUsernameInputFieldPlaceholderText = loginPage.getUserNamePlaceholderText();
        Assert.assertEquals(actualUsernameInputFieldPlaceholderText,LOGIN_FORM_USERNAME_PLACEHOLDER_TEXT);

        log.info("STEP 8. The user enters a username");
        loginPage.provideUser(USER);

        log.info("STEP 9. Assert the password field placeholder text is correct ");
        String actualPasswordInputFieldPlaceholderText = loginPage.getPasswordPlaceholderText();
        Assert.assertEquals(actualPasswordInputFieldPlaceholderText,LOGIN_FORM_PASSWORD_PLACEHOLDER_TEXT);

        log.info("STEP 10. The user enters a password");
        loginPage.providePass(PASS);

        log.info("STEP 11. Assert the remember-me checkbox text");
        String rememberMeCheckBoxText = loginPage.getRememberMeText();
        Assert.assertEquals(rememberMeCheckBoxText, LOGIN_FORM_CHECKBOX_TEXT);

        log.info("STEP 12. The user clicks on the remember-me checkbox");
        loginPage.clickOnRememberMeCheckBox();

        log.info("STEP 13. Assert that the Sign In Button label is correct");
        String actualLoginButtonText = loginPage.getSignInButtonLabel();
        Assert.assertEquals(actualLoginButtonText, LOGIN_FORM_BUTTON_TEXT);

        log.info("STEP 14. Assert not-a-member text on form");
        String actualNotAMemberText = loginPage.getLoginFormNotAMemberText();
        Assert.assertEquals(actualNotAMemberText, LOGIN_FORM_NOT_A_MEMBER);

        log.info("STEP 15. The user clicks on the Sign In Button");
        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 16. Assert if there is successful LoginTests message shown");
        String popupMessage = loginPage.getLoginPopupSuccessMessageText();
        Assert.assertEquals(popupMessage, LOGIN_SUCCESSFUL_MSG);
    }

    @Test (priority=2)
    public void verifyUserCanLogout() throws InterruptedException {
        log.info("STEP 1: The Skillo Home page is opened ");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2: The user is navigating to the Login page by clicking on the navigation bar LoginTests link");
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

package org.yulianakoprinkova.RegisterTests;

import org.yulianakoprinkova.POM.HomePage;

import org.yulianakoprinkova.POM.RegistrationPage;
import org.yulianakoprinkova.BaseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterHappyPathTests extends BaseTest{

    private static final String REGISTER_PAGE_TITLE = "ISkillo";
    private static final String REGISTER_FORM_TITLE = "Sign up";
    private static final String REGISTER_SUCCESSFUL_MSG = "Successful register!";
    private static final String HOMEPAGE_TITLE = "ISkillo";

    @Test
    public void verifyTheUserCanRegisterWithValidCredentials()  {

        log.info("STEP 1: Navigating to Registration Page ");
        RegistrationPage regPage = new RegistrationPage(super.driver, log);
        regPage.navigateToRegistrationPageByURL();

        log.info("STEP 2. Verify the user is on the Registration page ");
        String actualRegPageTitle = regPage.getRegPageTitle();
        Assert.assertEquals(actualRegPageTitle, REGISTER_PAGE_TITLE);

        log.info("STEP 3. Verify the registration form title ");
        String actualRegFormHeaderText = regPage.getRegFormHeaderText();
        Assert.assertEquals(actualRegFormHeaderText, REGISTER_FORM_TITLE);

        log.info("STEP 4. Provide registration username");
        regPage.provideUserName();

        log.info("STEP 5. Provide registration email");
        regPage.provideEmail();

        log.info("STEP 6. Provide registration birthdate");
        regPage.provideBDayInfo();

        log.info("STEP 7. Provide new password");
        regPage.providePass();

        log.info("STEP 8. Confirm password");
        regPage.providePassConfirm();

        log.info("STEP 9. Provide public info");
        regPage.providePublicInfo();

        log.info("STEP 10. Click on Sign In button");
        regPage.clickOnSubmitBtn();

        log.info("STEP 11. Assert if successful registration popup message is shown");
        Assert.assertTrue(true, REGISTER_SUCCESSFUL_MSG);

        log.info("STEP 12. Assert correct text of successful register message is shown");
        String registerPopupMessage = regPage.getToastContainerText();
        Assert.assertEquals(registerPopupMessage, REGISTER_SUCCESSFUL_MSG);

        log.info("STEP 11. Assert if user is on home page for registered users: ");

        log.info("STEP 11.1 Assert page title");
        HomePage homePage = new HomePage(driver, log);
        String HomePageTitle = homePage.getTitle();
        Assert.assertEquals(HomePageTitle,HOMEPAGE_TITLE);

        log.info("STEP 11.2. Assert if Logout Link is shown");
        Boolean LogoutLinkShown = homePage.isLogoutLinkVisible();
        Assert.assertEquals(true, LogoutLinkShown);

        log.info("STEP 11.3. Assert if New Post Link is shown");
        Boolean newPostLinkIsShown = homePage.isNewPostLinkShown();
        Assert.assertEquals(true, newPostLinkIsShown);

        log.info("STEP 11.4. Assert if Profile Link is shown");
        Boolean ProfileLinkIsShown = homePage.isProfileVisible();
        Assert.assertEquals(true, ProfileLinkIsShown);

    }

}

package org.yulianakoprinkova.ProfileTests;

import org.testng.Assert;
import org.yulianakoprinkova.POM.HomePage;
import org.yulianakoprinkova.POM.LoginPage;
import org.yulianakoprinkova.BaseTests.BaseTest;
import org.testng.annotations.Test;
import org.yulianakoprinkova.POM.ProfilePage;
import static org.yulianakoprinkova.LoginTests.LoginHappyPathTests.USER;

public class ProfileTests extends BaseTest {

    @Test
    public void viewProfileInfo(){
        log.info("STEP 1: Navigating to Home page ");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Navigating to Login page ");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Logging with registered user credentials ");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUserCredentials();

        log.info("STEP 4: Go to Profile page");
        homePage.clickOnProfileLink();
        ProfilePage profilePage = new ProfilePage(super.driver,log);

        log.info("STEP 5: Assert if username is visible on Profile page");
        Assert.assertTrue(profilePage.isUserNameVisible());

        log.info("STEP 6: Assert if username is correct");
        String userName = profilePage.getUsername();
        Assert.assertEquals(userName,USER);
    }
}


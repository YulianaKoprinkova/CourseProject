package org.yulianakoprinkova.ProfileTests;

import org.yulianakoprinkova.POM.HomePage;
import org.yulianakoprinkova.POM.LoginPage;
import org.yulianakoprinkova.BaseTests.BaseTest;
import org.testng.annotations.Test;

import java.io.File;

public class ProfileTests extends BaseTest {

    File postPicture = new File("src/test/resources/upload/profilePic.png");

    @Test
    public void updateProfileInfo(){
        log.info("STEP 1: Navigating to Home page ");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Navigating to Login page ");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Logging with registered user credentials ");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUserCredentials();




    }
}


package org.yulianakoprinkova.PostTests;

import org.yulianakoprinkova.POM.*;
import org.yulianakoprinkova.BaseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import static org.yulianakoprinkova.LoginTests.LoginHappyPathTests.USER;
import static org.yulianakoprinkova.POM.HomePage.SUCCESSFUL_LOGOUT_MSG;

public class PostTests extends BaseTest {

    private static final String CAPTION = "Test caption";
    File postPicture = new File("src/test/resources/upload/testPic.png");

    @Test(priority = 1)
    public void verifyUserCanCreatePost() {

        log.info("STEP 1: Navigating to Home page ");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Navigating to Login page ");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Logging with registered user credentials ");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUserCredentials();

        log.info("STEP 4: Click on New Post navigation bar ");
        homePage.clickOnNewPostNavBar();
        PostPage postPage = new PostPage(super.driver, log);

        log.info("STEP 5: Upload picture ");
        postPage.uploadPicture(postPicture);

        log.info("STEP 6: Provide post caption");
        postPage.providePostCaption(CAPTION);

        log.info("STEP 7: Click on Create Post Button");
        postPage.clickCreatePostButton();

        log.info("STEP 8: Check if new post is shown on Profile page");
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        boolean isMorePostShown = profilePage.getPostCount() > 0;
        Assert.assertTrue(isMorePostShown);

        log.info("STEP 9: Check if the image in the new post is visible");
        profilePage.clickPost(0);
        PostModal postModal = new PostModal(super.driver, log);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");

       log.info("STEP 10: Check if the user name on the post is correct");
        String postUserTxt = postModal.getPostUser();
        Assert.assertEquals(postUserTxt, USER);
    }

    @Test (priority = 2)
    public void verifyUserCanCommentOnOwnPost() {

        log.info("STEP 1: Navigating to Home page ");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Navigating to Login page ");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Logging with registered user credentials ");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUserCredentials();

        log.info("STEP 4. Navigating to Profile page.");
        homePage.clickOnProfileLink();

        log.info("STEP 5: Click on post on Profile page.");
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickPost(0);

        log.info("STEP 6: Enter a comment on user's post");
        profilePage.providePostComment();
    }

    @Test(priority = 3)
    public void verifyUserCanDeletePost() {
        log.info("STEP 1: Navigating to Home page ");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Navigating to Login page ");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Logging with registered user credentials ");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUserCredentials();

        log.info("STEP 4. Navigating to Profile page.");
        homePage.clickOnProfileLink();

        log.info("STEP 5: Click on post on Profile page.");
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickPost(0);

        log.info("STEP 6: Click on Delete Post Button.");
        profilePage.clickOnDeleteButton();

        log.info("STEP 7: Confirm deleting post");
        profilePage.clickOnYesButton();

        log.info("STEP 8: Check if Delete message is visible");
        profilePage.isDeletedMessageVisible();
    }
}


package org.yulianakoprinkova.PostTests;

import org.yulianakoprinkova.POM.*;
import org.yulianakoprinkova.BaseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;

public class PostTests extends BaseTest {

    public static final String TEST_USER = "testingDemos";
    public static final String TEST_PASSWORD = "testing";
    public static final String CAPTION = "Testing the create post CAPTION";
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

        log.info("STEP 1: Navigating to Home page ");
        PostPage postPage = new PostPage(super.driver, log);

        postPage.uploadPicture(postPicture);

        //postPage.providePostCaption(CAPTION);
        //postPage.clickCreatePostButton();

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        boolean isMorePostShown = profilePage.getPostCount() > 0;
        Assert.assertTrue(isMorePostShown);
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(super.driver, log);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");

        String postUserTxt = postModal.getPostUser();
        Assert.assertEquals(postUserTxt, TEST_USER);
    }

    @Test (priority = 1)
    public void verifyUserCanLikePost() {
        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("The user has navigated to the Login page.");
        loginPage.navigateToLoginPage();

        log.info("The user has logged in with username and password.");
        loginPage.loginWithTestUserCredentials();

        log.info("The user has navigated to the Profile page.");
        homePage.clickOnProfileLink();

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickPost(0);
        log.info("The user has clicked on the first post.");

        profilePage.ClickOnLikeButton();
        log.info("The user has clicked on the like button.");
        profilePage.isLikeMessageVisible();

    }

    @Test (priority = 3)
    public void verifyUserCanDislikePost() {
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.navigateTo("posts/all");
    }

    @Test(priority = 4)
    public void verifyUserCanDeletePost() {
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();
        homePage.clickOnLoginNavBar();

        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("The user has navigated to the Login page.");
        loginPage.navigateToLoginPage();

        log.info("The user has logged in with username and password.");
        loginPage.loginWithTestUserCredentials();

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickPost(0);
        log.info("The user has clicked on the first post.");

        profilePage.ClickOnDeleteButton();
        log.info("The user has clicked on Delete post button.");

        profilePage.ClickOnYesButton();
        log.info("The user has confirmed the deletion.");

        profilePage.isDeletedMessageVisible();
    }
}

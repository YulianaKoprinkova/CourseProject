package org.yulianakoprinkova.HomePageTests;

import org.yulianakoprinkova.POM.HomePage;
import org.yulianakoprinkova.POM.LoginPage;
import org.yulianakoprinkova.BaseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.yulianakoprinkova.POM.HomePage.*;

public class HomePageTests extends BaseTest {

    @Test (priority = 1)
    public void HomePageForLoggedUser() {

        log.info("STEP 1: The Skillo Home page is opened ");
        HomePage homePageUnlogged = new HomePage(super.driver, log);
        homePageUnlogged.openHomePage();

        log.info("STEP 2: The user is navigating to the Login page by clicking on the navigation bar Login link");
        homePageUnlogged.clickOnLoginNavBar();

        log.info("STEP 3: The registered user opens the Login page and logs in ");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUserCredentials();

        log.info("STEP 4. Assert if Login popup message text is correct");
        String logInSuccessMessage = loginPage.getLoginPopupSuccessMessageText();
        Assert.assertEquals(logInSuccessMessage, SUCCESSFUL_LOGIN_MSG);

        log.info("STEP 5. Assert the user is on the Home page for registered users after logging in ");
        HomePage homePageLogged = new HomePage(super.driver, log);
        String pageTitle = homePageLogged.getTitle();
        Assert.assertEquals(pageTitle, HOMEPAGE_TITLE);

        log.info("STEP 6. Assert Logout Link is shown on page ");
        boolean isLogoutLinkShown = homePageLogged.isLogoutLinkShown();
        Assert.assertTrue(isLogoutLinkShown);

        log.info("STEP 7. Assert Logout Link is clickable ");
        boolean isLogoutLinkClickable = homePageLogged.isLogoutLinkClickable();
        Assert.assertTrue(isLogoutLinkClickable);

        log.info("STEP 8: Assert ISkillo icon is shown on page");
        boolean isHomeIconShown = homePageLogged.isHomeIconShown();
        Assert.assertTrue(isHomeIconShown);

        log.info("STEP 9: Assert Home link is shown on page ");
        boolean isHomeLinkShown = homePageLogged.isHomeLinkShown();
        Assert.assertTrue(isHomeLinkShown);

        log.info("STEP 10: Assert Home link is clickable ");
        boolean isHomeLinkClickable = homePageLogged.isHomeLinkClickable();
        Assert.assertTrue(isHomeLinkClickable);

        log.info("STEP 11. Assert if Profile Link is shown on page ");
        boolean isProfileLinkShown = homePageLogged.isProfileShown();
        Assert.assertTrue(isProfileLinkShown);

        log.info("STEP 12. Assert if Profile Link is clickable ");
        boolean isProfileLinkClickable = homePageLogged.isProfileLinkClickable();
        Assert.assertTrue(isProfileLinkClickable);

        log.info("STEP 13. Assert if New Post Link is shown on page ");
        boolean isNewPostLinkShown = homePageLogged.isNewPostLinkShown();
        Assert.assertTrue(isNewPostLinkShown);

        log.info("STEP 14. Assert if New Post Link is clickable ");
        boolean isNewPostLinkClickable = homePageLogged.isNewPostLinkClickable();
        Assert.assertTrue(isNewPostLinkClickable);

        log.info("STEP 15. Logout user");
        homePageUnlogged.clickOnLogoutLink();

        log.info("STEP 16. Check if logout popup message is correct");
        String logoutMessage = homePageLogged.getPopUpMsg();
        Assert.assertEquals(logoutMessage,SUCCESSFUL_LOGOUT_MSG);
}

    @Test (priority = 30)
    public void searchBarTest () {

        log.info("STEP 1: The Skillo Home page is opened ");
        HomePage homePage = new HomePage(super.driver, log);
        homePage.openHomePage();

        log.info("STEP 2: The user is navigating to the Login page by clicking on the navigation bar LoginTests link");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: The registered user opens the Login page and logs in ");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUserCredentials();

        log.info("STEP 4. Assert Search Bar is shown on Home page ");
        boolean isSearchFieldShown = homePage.isSearchBarShown();
        Assert.assertTrue(isSearchFieldShown);

        log.info("STEP 5. Assert Search Bar is clickable ");
        boolean searchFieldClickable = homePage.isSearchBarClickable();
        Assert.assertTrue(searchFieldClickable);

        log.info("STEP 6. Assert the Search Bar placeholder text is correct ");
        String actualPlaceholderText = homePage.getSearchBarPlaceholderText();
        Assert.assertEquals(actualPlaceholderText, SEARCH_BAR_PLACEHOLDER_TEXT);
    }

    @Test (priority = 2)
    public void followUnfollowUser() {

        log.info("STEP 1: The Skillo Home page is opened ");
        HomePage homePageLogged = new HomePage(super.driver, log);
        homePageLogged.openHomePage();

        log.info("STEP 2: The user is navigating to the Login page by clicking on the navigation bar LoginTests link");
        homePageLogged.clickOnLoginNavBar();

        log.info("STEP 3: The user opens the Login page and logs in ");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUserCredentials();

        log.info("STEP 5. Unfollow/follow another user ");
        homePageLogged.clickOnFollowUnfollowButton();

        log.info("STEP 6. Assert popup message text for followed / unfollowed user ");
        String popupMessageText = homePageLogged.getPopUpMsg();
        if (homePageLogged.buttonTextCheck() == "Follow") {
            Assert.assertEquals(popupMessageText,FOLLOW_SUCCESSFUL_MSG);
        } else Assert.assertEquals(popupMessageText, UNFOLLOW_SUCCESSFUL_MSG);

    }

    @Test (priority = 4)
    public void likePost () {

        log.info("STEP 1: The Skillo Home page is opened ");
        HomePage homePageLogged = new HomePage(super.driver, log);
        homePageLogged.openHomePage();

        log.info("STEP 2: The user is navigating to the Login page by clicking on the navigation bar LoginTests link");
        homePageLogged.clickOnLoginNavBar();

        log.info("STEP 3: The user opens the Login page and logs in ");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUserCredentials();

        log.info("STEP 4. Count posts on Home page");
        int postsNumber = (homePageLogged.getPostCount(super.driver));
        Assert.assertTrue(postsNumber>0);

        log.info("STEP 5. Assert there is another user profile link shown");
        boolean anotherUserProfileLinkShown = homePageLogged.isPostUserShown();
        Assert.assertTrue(anotherUserProfileLinkShown);

        log.info("STEP 6. Assert if other user profile link is clickable ");
        boolean anotherUserProfileLinkClickable = homePageLogged.isPostUserClickable();
        Assert.assertTrue(anotherUserProfileLinkClickable);

        log.info("STEP 7. Like post ");
        homePageLogged.clickOnHeartButton();

        log.info("STEP 8. Assert successful like popup message text ");
        String likePopupMessageText = homePageLogged.getPopUpMsg();
        Assert.assertEquals(likePopupMessageText, POST_LIKED_MSG);

        log.info("STEP 9. Dislike post ");
        homePageLogged.clickOnHeartButton();

        log.info("STEP 10. Assert successful dislike popup message text ");
        String dislikePopupMessageText = homePageLogged.getPopUpMsg();
        Assert.assertEquals(dislikePopupMessageText, POST_DISLIKED_MSG);
        }

    @Test (priority = 2)
    public void HomePageUnloggedUser (){

        log.info("STEP 1: The Skillo Home Page is opened by unlogged user");
        HomePage homePageUnlogged = new HomePage(super.driver, log);
        homePageUnlogged.openHomePage();

        log.info("STEP 2: Verify the user is on the correct page");
        String actualHomePageTitle = homePageUnlogged.getTitle();
        Assert.assertEquals(actualHomePageTitle,HOMEPAGE_TITLE);

        log.info("STEP 3: Assert ISkillo icon shows on page");
        boolean isHomeIconShown = homePageUnlogged.isHomeIconShown();
        Assert.assertTrue(isHomeIconShown);

        log.info("STEP 4: Assert Home link is shown");
        boolean isHomeLinkShown = homePageUnlogged.isHomeLinkShown();
        Assert.assertTrue(isHomeLinkShown);

        log.info("STEP 5: Assert Home link is clickable");
        boolean isHomeLinkClickable = homePageUnlogged.isHomeLinkClickable();
        Assert.assertTrue(isHomeLinkClickable);

        log.info("STEP 6: Assert Login link is shown");
        boolean isLoginLinkShown = homePageUnlogged.isLoginLinkShown();
        Assert.assertTrue(isLoginLinkShown);

        log.info("STEP 7: Assert Login link is clickable");
        boolean isLoginLinkClickable = homePageUnlogged.isLoginLinkClickable();
        Assert.assertTrue(isLoginLinkClickable);

        log.info(("STEP 8. Count posts on homepage"));
        homePageUnlogged.getPostCount(super.driver);
    }
}

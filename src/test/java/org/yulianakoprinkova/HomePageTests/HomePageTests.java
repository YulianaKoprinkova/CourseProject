package org.yulianakoprinkova.HomePageTests;

import org.yulianakoprinkova.POM.HomePage;
import org.yulianakoprinkova.POM.LoginPage;
import org.yulianakoprinkova.BaseTests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.yulianakoprinkova.POM.HomePage.*;


public class HomePageTests extends BaseTest {

    @Test (priority = 1)
    public void HomePageLoggedUser () {

        log.info("STEP 1: The Skillo Home page is opened ");
        HomePage homePageUnlogged = new HomePage(super.driver, log);
        homePageUnlogged.openHomePage();

        log.info("STEP 2: The user is navigating to the Login page by clicking on the navigation bar LoginTests link");
        homePageUnlogged.clickOnLoginNavBar();

        log.info("STEP 3: The registered user opens the Login page and logs in ");
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.loginWithTestUserCredentials();

        log.info("STEP 4. Assert Login popup message text is correct");
        String logInSuccessMessage = loginPage.getLoginPopupSuccessMessageText();
        Assert.assertEquals(logInSuccessMessage, SUCCESSFUL_LOGIN_MSG);

        log.info("STEP 5. Assert the user is on the Home page for registered users after logging in ");
        HomePage homePageLogged = new HomePage(super.driver, log);
        String pageTitle = homePageLogged.getTitle();
        Assert.assertEquals(pageTitle, HOMEPAGE_TITLE);

        log.info("STEP 6. Assert Logout Link is shown on page ");
        Boolean logoutLinkShown = homePageLogged.isLogoutLinkShown();
        Assert.assertTrue(logoutLinkShown);

        log.info("STEP 7. Assert Logout Link is clickable ");
        Boolean logoutLinkClickable = homePageLogged.isLogoutLinkClickable();
        Assert.assertTrue(logoutLinkClickable);

        log.info("STEP 8: Assert ISkillo icon is shown on page");
        Boolean isHomeIconShown = homePageLogged.isHomeIconShown();
        Assert.assertTrue(isHomeIconShown);

        log.info("STEP 9: Assert Home link is shown on page ");
        Boolean isHomeLinkShown = homePageLogged.isHomeLinkShown();
        Assert.assertTrue(isHomeLinkShown);

        log.info("STEP 10: Assert Home link is clickable ");
        Boolean isHomeLinkClickable = homePageLogged.isHomeLinkClickable();
        Assert.assertTrue(isHomeLinkClickable);

        log.info("STEP 11. Assert if Profile Link is shown on page ");
        Boolean isProfileLinkShown = homePageLogged.isProfileShown();
        Assert.assertTrue(isProfileLinkShown);

        log.info("STEP 12. Assert if Profile Link is clickable ");
        Boolean isProfileLinkClickable = homePageLogged.isProfileLinkClickable();
        Assert.assertTrue(isProfileLinkClickable);

        log.info("STEP 13. Assert if New Post Link is shown on page ");
        Boolean newPostLinkShown = homePageLogged.isNewPostLinkShown();
        Assert.assertTrue(newPostLinkShown);

        log.info("STEP 14. Assert if New Post Link is clickable ");
        Boolean newPostLinkClickable = homePageLogged.isNewPostLinkClickable();
        Assert.assertTrue(newPostLinkClickable);

        log.info("STEP 15. Assert if Search Bar is shown on page ");
        Boolean searchFieldShown = homePageLogged.isSearchBarShown();
        Assert.assertTrue(searchFieldShown);

        log.info("STEP 17. Assert if Search Bar is clickable ");
        Boolean searchFieldClickable = homePageLogged.isSearchBarClickable();
        Assert.assertTrue(searchFieldClickable);

        log.info("STEP 18. Assert the Search Bar placeholder text is correct ");
        String actualPlaceholderText = homePageLogged.getSearchBarPlaceholderText();
        Assert.assertEquals(actualPlaceholderText,SEARCH_BAR_PLACEHOLDER_TEXT);

        log.info("STEP 19. Assert if there is another user profile link shown");
        Boolean anotherUserProfileLinkShown = homePageLogged.isPostUserShown();
        Assert.assertTrue(anotherUserProfileLinkShown);

        log.info("STEP 20. Assert if other user profile link is clickable ");
        Boolean anotherUserProfileLinkClickable = homePageLogged.isPostUserClickable();
        Assert.assertTrue(anotherUserProfileLinkClickable);

        //check if Yoda is followed

        log.info("STEP 21. Count posts on Home page");
         if (homePageLogged.getPostCount(super.driver)==0) {

             log.info("STEP 22. Check if button text is follow or unfollow ");
             if (homePageLogged.buttonTextCheck().equals("Follow")) {

                 log.info("STEP 23. Follow another user");
                 homePageLogged.clickOnFollowUnfollowButton();

                 log.info("STEP 24. Assert successful follow popup message text");
                 String followPopupMessageText = homePageLogged.getPopUpMsg();
                 Assert.assertEquals(followPopupMessageText, FOLLOW_SUCCESSFUL_MSG);
             }

             else {

                 log.info("STEP 23. Unfollow another user ");
                 homePageLogged.clickOnFollowUnfollowButton();

                 log.info("STEP 24. Assert successful unfollow popup message text ");
                 String unfollowPopupMessageText = homePageLogged.getPopUpMsg();
                 Assert.assertEquals(unfollowPopupMessageText, UNFOLLOW_SUCCESSFUL_MSG);

             }
         }

         else {

             log.info("STEP 22. Like Yoda's post ");
             homePageLogged.clickOnHeartButton();

             log.info("STEP 23. Assert successful like popup message text ");
             String likePopupMessageText = homePageLogged.getPopUpMsg();
             Assert.assertEquals(likePopupMessageText, POST_LIKED_MSG);

             log.info("STEP 24. Dislike Yoda's post ");
             homePageLogged.clickOnHeartButton();

             log.info("STEP 25. Assert successful dislike popup message text ");
             String dislikePopupMessageText = homePageLogged.getPopUpMsg();
             Assert.assertEquals(dislikePopupMessageText, POST_DISLIKED_MSG);

             log.info("STEP 26. Unfollow Yoda ");
             homePageLogged.clickOnFollowUnfollowButton();
         }

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
        Boolean isHomeIconShown = homePageUnlogged.isHomeIconShown();
        Assert.assertTrue(isHomeIconShown);

        log.info("STEP 4: Assert Home link is shown");
        Boolean isHomeLinkShown = homePageUnlogged.isHomeLinkShown();
        Assert.assertTrue(isHomeLinkShown);

        log.info("STEP 5: Assert Home link is clickable");
        Boolean isHomeLinkClickable = homePageUnlogged.isHomeLinkClickable();
        Assert.assertTrue(isHomeLinkClickable);

        log.info("STEP 6: Assert Login link is shown");
        Boolean isLoginLinkShown = homePageUnlogged.isLoginLinkShown();
        Assert.assertTrue(isLoginLinkShown);

        log.info("STEP 7: Assert Login link is clickable");
        Boolean isLoginLinkClickable = homePageUnlogged.isLoginLinkClickable();
        Assert.assertTrue(isLoginLinkClickable);

        log.info(("STEP 8. Count posts on homepage"));
        homePageUnlogged.getPostCount(super.driver);
    }
}

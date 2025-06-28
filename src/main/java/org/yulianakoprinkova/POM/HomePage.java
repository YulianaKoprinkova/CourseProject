package org.yulianakoprinkova.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {

    public static final String HOMEPAGE_TITLE = "ISkillo";
    public static final String HOME_PAGE_SUFIX = "/posts/all";
    public static final String FOLLOW_SUCCESSFUL_MSG = "You followed!";
    public static final String UNFOLLOW_SUCCESSFUL_MSG = "You unfollowed!";
    public static final String POST_LIKED_MSG = "Post liked";
    public static final String POST_DISLIKED_MSG = "Post disliked";
    public static final String SEARCH_BAR_PLACEHOLDER_TEXT = "Search";
    public static final String SUCCESSFUL_LOGIN_MSG = "Successful login!";
    public static final String SUCCESSFUL_LOGOUT_MSG = "Successful logout!";


    @FindBy (id = "homeIcon")
    private WebElement homeIcon;

    @FindBy(id = "nav-link-home")
    private WebElement navBarHomeLink;

    @FindBy(css = "p.h4")
    private WebElement loginFormTitle;

    @FindBy(id = "nav-link-login")
    private WebElement navBarLoginLink;

    @FindBy(id = "nav-link-new-post")
    private WebElement navBarNewPostLink;

    @FindBy (id = "nav-link-profile")
    private WebElement navBarProfileLink;

    @FindBy(id="search-bar")
    private WebElement searchBarInput;

    @FindBy(css = "i")
    private WebElement navBarLogoutLink;

    @FindBy(id="toast-container")
    private WebElement toastContainer;

    @FindBy(xpath = "//a[contains(@class,'post-user')]")
    private WebElement postUser;

    @FindBy(xpath = "//button[contains(@class,'btn')]")
    private WebElement followUnfollowButton;

    @FindBy(xpath="//div/div/i")
    private WebElement heartButton;



    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    //NAVIGATE TO HOMEPAGE

    public void openHomePage(){

        navigateTo(HOME_PAGE_SUFIX);
    }

    //USER ACTIONS ON WEB ELEMENTS IN HOME PAGE

    public void clickOnHomeNavBar(){
        wait.until(ExpectedConditions.visibilityOf(navBarHomeLink));
        navBarHomeLink.click();
    }

    public void clickOnLoginNavBar(){
        wait.until(ExpectedConditions.visibilityOf(navBarLoginLink));
        navBarLoginLink.click();
    }

    public void clickOnNewPostNavBar(){
        wait.until(ExpectedConditions.visibilityOf(navBarNewPostLink));
        navBarNewPostLink.click();
    }

    public void clickOnProfileLink (){
        wait.until(ExpectedConditions.visibilityOf(navBarProfileLink));
        clickOn(navBarProfileLink);
    }

    public void clickOnNewPostLink (){
        wait.until(ExpectedConditions.visibilityOf(navBarNewPostLink));
        clickOn(navBarNewPostLink);
    }

    public String buttonTextCheck() {
        wait.until(ExpectedConditions.visibilityOf(followUnfollowButton));
        return getElementText(followUnfollowButton);
    }

    public void clickOnFollowUnfollowButton () {
        wait.until(ExpectedConditions.visibilityOf(followUnfollowButton));
        clickOn(followUnfollowButton);
    }

    public void clickOnLogoutLink(){
        wait.until(ExpectedConditions.visibilityOf(navBarLogoutLink));
        navBarLogoutLink.click();
    }

    public void clickOnHeartButton(){
        wait.until(ExpectedConditions.visibilityOf(heartButton));
        heartButton.click();
    }

    //Getters

    public String getTitle(){
        String title = driver.getTitle();
        return title;
    }

    public int getPostCount (WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long initialHeight = (long) js.executeScript("return document.body.scrollHeight");
        while(true) {
            // Do infinite scrolling
            js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            long currentHeight = (long) js.executeScript("return document.body.scrollHeight");
            if (initialHeight == currentHeight) {
                break;
            }
            initialHeight = currentHeight;
        }

        int postCount = driver.findElements(By.xpath("//div/app-post-detail")).size();
        System.out.println("The visible posts on the homepage are : " + postCount);
        return postCount;
    }

    public String getSearchBarPlaceholderText(){

        return getElementPlaceholderValue(searchBarInput);
    }

    public String getPopUpMsg() {

        return getElementText(toastContainer);
    }


    //verification methods

    public boolean isHomeIconShown () {
        return isElementPresent(homeIcon);
    }

    public boolean isHomeLinkShown() {

        return  isElementPresent(navBarHomeLink);
    }

    public boolean isHomeLinkClickable() {

        return  isElementClickable(navBarHomeLink);
    }

    public boolean isLoginLinkShown(){

        return isElementPresent(navBarLoginLink);
    }

    public boolean isLoginLinkClickable(){

        return isElementClickable(navBarLoginLink);
    }

    public boolean isNewPostLinkShown(){

        return isElementPresent(navBarNewPostLink);
    }

    public boolean isNewPostLinkClickable(){
        return isElementClickable(navBarNewPostLink);
    }


    public boolean isProfileShown(){

        return isElementPresent(navBarProfileLink);
    }

    public boolean isProfileLinkClickable(){

        return isElementClickable(navBarProfileLink);
    }

    public boolean isLogoutLinkShown(){

        return isElementPresent(navBarLogoutLink);
    }

    public boolean isLogoutLinkClickable(){

        return isElementClickable(navBarLogoutLink);
    }

    public boolean isSearchBarShown(){

        return isElementPresent(searchBarInput);
    }

    public boolean isSearchBarClickable(){

        return isElementClickable(searchBarInput);
    }

    public boolean isPostUserShown () {
        return isElementPresent(postUser);
    }

    public boolean isPostUserClickable(){

        return isElementClickable(postUser);
    }

}

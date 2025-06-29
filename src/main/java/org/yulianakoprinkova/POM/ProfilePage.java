package org.yulianakoprinkova.POM;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;

public class ProfilePage  extends BasePage {

    @FindBy (xpath = "//label[contains(@class,'delete-ask')]")
    private WebElement deletePostButton;
    @FindBy (xpath = "//button[contains(@class,'btn btn-primary btn-sm')]")
    private WebElement yesButton;
    @FindBy (xpath = "//div[contains(@aria-label,'Post Deleted!')]")
    private WebElement confirmDeletionMessage;
    @FindBy(xpath = "//input[contains(@formcontrolname,'content')]")
    private WebElement postCommentField;
    @FindBy(xpath="//h2")
    private WebElement userName;

    public ProfilePage (WebDriver driver, Logger log) {
        super(driver,log);
        PageFactory.initElements(driver,this);
    }

    public void providePostComment() {
        wait.until(ExpectedConditions.visibilityOf(postCommentField));
        postCommentField.sendKeys("test comment"+ "\n");
        log.info("CONFIRMATION # The user has provided a comment on their post");
    }

    public void clickOnDeleteButton() {
        wait.until(ExpectedConditions.visibilityOf(deletePostButton));
        clickOn(deletePostButton);
    }

    public void clickOnYesButton() {
        wait.until(ExpectedConditions.visibilityOf(yesButton));
        clickOn(yesButton);
    }

    public String getUsername() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

    public int getPostCount() {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        return posts.size();
    }

    public void clickPost(int postIndex) {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        posts.get(postIndex).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }

    public boolean isUserNameVisible(){
        return isElementPresent(userName);
    }

    public boolean isDeletedMessageVisible() {
        boolean isDeletedMessageVisible = false;
        try {
            isDeletedMessageVisible = wait.until(ExpectedConditions.visibilityOf(confirmDeletionMessage)).isDisplayed();
            log.info("CONFIRMATION # The Post Deleted! message is displayed.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The Post Deleted! message is not displayed!");
            isDeletedMessageVisible = false;
        }
        return isDeletedMessageVisible;
    }
}
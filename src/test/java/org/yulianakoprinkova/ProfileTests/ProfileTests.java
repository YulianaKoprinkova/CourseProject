package org.yulianakoprinkova.ProfileTests;


import org.yulianakoprinkova.POM.LoginPage;
import org.yulianakoprinkova.BaseTests.BaseTest;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest {

    @Test
    public void updateProfileInfo(){
        LoginPage logPage = new LoginPage(super.driver,log);
        logPage.loginWithTestUserCredentials();


    }
}


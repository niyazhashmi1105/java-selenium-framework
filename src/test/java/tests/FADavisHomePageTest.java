package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.annotations.Test;

public class FADavisHomePageTest extends BaseTest {

    public FADavisHomePageTest(){
        super();
    }

    @Test
    public void verifyLoginToApplication(){

        faDavisHomePage.openURL(ConfigReader.getBaseUrl());
        faDavisHomePage.clickOnInstructor();
        faDavisHomePage.clickLoginBtn();
        //faDavisHomePage.clickOnAgreeContinueBtn();
        faDavisHomePage.doLogin("davisedge@learningmate.com","password");
    }
}

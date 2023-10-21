package ecommerce;

import ecommerce.pageObjects.android.FormPage;
import ecommerce.testutils.BasicSetup;
import io.appium.java_client.android.Activity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BasicSetup {


    @BeforeMethod(alwaysRun = true)
    public void Presetup(){
        Activity activity=new Activity(packageName,activityName);
        driver.startActivity(activity);
   }

    @Test
    public void InvalidLogin() throws  InterruptedException{
        FormPage formPage=new FormPage(driver);
        formPage.selectCountry("Argentina");
        formPage.selectGender("female");
        formPage.submitDetails();
        Assert.assertEquals(formPage.errorMessageDisplay(),"Please enter your name");
    }
    @Test(dataProvider = "getData")
    public void ValidLogin(String countryName,String name,String gender){
        FormPage formPage=new FormPage(driver);
        formPage.selectCountry(countryName);
        formPage.enterText(name);
        formPage.selectGender(gender);
        formPage.submitDetails();
    }
}


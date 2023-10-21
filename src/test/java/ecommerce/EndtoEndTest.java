package ecommerce;

import ecommerce.pageObjects.android.CartPage;
import ecommerce.pageObjects.android.FormPage;
import ecommerce.pageObjects.android.ProductCatalogue;
import ecommerce.testutils.BasicSetup;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class EndtoEndTest extends BasicSetup {

    @Test(dataProvider = "getDataFromJson",groups ={"smoke"} )
    public void cartItems(HashMap<String,String>input) throws InterruptedException {
        FormPage formPage = new FormPage(driver);
        formPage.selectCountry(input.get("country"));
        formPage.enterText(input.get("name"));
        formPage.selectGender(input.get("gender"));
        formPage.submitDetails();
//    public void cartItems(String country,String name,String gender) throws InterruptedException {
//        FormPage formPage = new FormPage(driver);
//        formPage.selectCountry(country);
//        formPage.enterText(name);
//        formPage.selectGender(gender);
//        formPage.submitDetails();

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        productCatalogue.addItemsByIndex(0);
        productCatalogue.addItemsByIndex(1);
        productCatalogue.clickCartButton();

        CartPage cartPage = new CartPage(driver);
        String cartItemTotal = cartPage.calculateTotal();
        String totalBill = cartPage.getTotalBill();
        Assert.assertEquals(cartItemTotal, totalBill);
        cartPage.clickNotifyCheckBox();
        cartPage.VerifyTermsAndConditionDisplayed();
        cartPage.clickPurchase();
//        Thread.sleep(6000);
//        //to find out the context name and switch to Hybrid App
//        Set<String> contexts=driver.getContextHandles();
//        for(String contextName:contexts){
//            System.out.println(contextName);
//        }
//        driver.context("WEBVIEW_com.androidsample.generalstore");
//        driver.findElement(By.name("q")).sendKeys("Manjari LinkedIn");
//        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }
}

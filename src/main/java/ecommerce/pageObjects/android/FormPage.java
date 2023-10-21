package ecommerce.pageObjects.android;

import ecommerce.AndroidUtils.CommanMethods;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends CommanMethods {
    AndroidDriver driver;
    public FormPage(AndroidDriver driver){
        //this.driver refers to the above local variable driver
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countrySelector;
    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement textBox;
    @AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleCheckBox;
    @AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
    private WebElement maleCheckBox;
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement submit;
    @AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
    private WebElement errorToast;

    public void selectCountry(String countryName){
        countrySelector.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }
    public void enterText(String name){
        textBox.sendKeys(name);
        driver.hideKeyboard();
    }
    public void selectGender(String gender){
        if(gender.equalsIgnoreCase("female")){
            femaleCheckBox.click();;
        }
        else{
            maleCheckBox.click();
        }
    }
    public void submitDetails(){
        submit.click();
    }
    public String errorMessageDisplay(){
        return errorToast.getAttribute("name");
    }

    public void setActivity(String packageName,String activityName){
        Activity activity=new Activity(packageName,activityName);
        driver.startActivity(activity);
    }
}

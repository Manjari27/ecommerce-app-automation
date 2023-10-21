package ecommerce.pageObjects.android;

import ecommerce.AndroidUtils.CommanMethods;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends CommanMethods {
    AndroidDriver driver;
    public CartPage(AndroidDriver driver){
        //this.driver refers to the above local variable driver
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productsInCart;
    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrice;
    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalPurchasedPrice;
    @AndroidFindBy(xpath = "//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']")
    private WebElement notifyCheckBox;
    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    private WebElement termsAndPolicy;
    @AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle")
    private WebElement termsAndConditionsTitle;
    @AndroidFindBy(id="android:id/button1")
    private WebElement closeTermsAndCondition;
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedBtn;

    public String calculateTotal(){
        double amount=0;
        String totalAmount="";
        for(int i=0;i<productsInCart.size();i++){
            String sum=productPrice.get(i).getText();
            sum=sum.substring(1);
            amount=amount+convertStringToDouble(sum);
            totalAmount=String.valueOf(amount);
        }
        return totalAmount;
    }
    public String getTotalBill(){
        return totalPurchasedPrice.getText().substring(1).trim();
    }

    public void clickNotifyCheckBox(){
        notifyCheckBox.click();

    }
    public void VerifyTermsAndConditionDisplayed(){
        longPressGesture(termsAndPolicy);
        termsAndConditionsTitle.isDisplayed();
        closeTermsAndCondition.click();
    }
    public void clickPurchase(){
        proceedBtn.click();
    }

}

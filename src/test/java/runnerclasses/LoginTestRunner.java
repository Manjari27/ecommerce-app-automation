package runnerclasses;

import ecommerce.LoginTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class LoginTestRunner extends LoginTest {
    @DataProvider
    public Object[][] getData(){
        return new Object[][] {{"Argentina","Manjari","female"}};
    }

}

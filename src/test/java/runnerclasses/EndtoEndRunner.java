package runnerclasses;
import ecommerce.EndtoEndTest;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EndtoEndRunner extends EndtoEndTest {
    @BeforeMethod(alwaysRun = true)
    public void setActivity(){
        Activity activity=new Activity(packageName,activityName);
        driver.startActivity(activity);
    }
    @DataProvider
    public Object[][] getDataFromJson() throws IOException {
        List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir")+"//src//test//java//TestData//testdata.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
        //return new Object[][] {{"Argentina","Manjari","female"}};
    }
}

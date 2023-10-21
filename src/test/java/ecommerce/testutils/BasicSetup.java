package ecommerce.testutils;

import com.google.common.collect.ImmutableMap;
import ecommerce.AndroidUtils.AppiumUtils;
import ecommerce.AndroidUtils.CommanMethods;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BasicSetup extends AppiumUtils {
    public AppiumDriverLocalService service;
    public AndroidDriver driver;
    public String packageName = "com.androidsample.generalstore";
    public String activityName = "com.androidsample.generalstore.SplashActivity";

    @BeforeClass(alwaysRun = true)
    public void appiumSetup() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\manjara\\IdeaProjects\\ecommerce-mobile-automation\\src\\main\\java\\ecommerce\\resources\\data.properties");
        prop.load(fis);
        String ip=System.getProperty("ipAddress")!=null?System.getProperty("ipAddress"):prop.getProperty("ipAddress");
        service = startAppiumServer(ip, Integer.parseInt(prop.getProperty("port")));
        UiAutomator2Options options = new UiAutomator2Options();
        //options.setDeviceName("Galaxy Tab Active3");
        options.setDeviceName(prop.getProperty("androidDevice"));
        options.setApp("C:\\Users\\manjara\\IdeaProjects\\ecommerce-mobile-automation\\src\\test\\java\\ecommerce\\applications\\General-Store.apk");
        options.setChromedriverExecutable("C:\\Users\\manjara\\Desktop\\Automation Training\\chromedriver.exe");
        driver = new AndroidDriver(service.getUrl(), options);
        //driver=new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        service.stop();
    }
}

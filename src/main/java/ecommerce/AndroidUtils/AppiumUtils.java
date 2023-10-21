package ecommerce.AndroidUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils {
    public AppiumDriverLocalService service;

    String appiumPath=getPathDirectory("npm.cmd list -g") + "\\node_modules\\appium";

    String nodePath=getPathDirectory("where node");

    public double convertStringToDouble(String str) {
        double val = Double.parseDouble(str);
        return val;
    }

    public List<HashMap<String, String>> getJsonData(String jsonFilepath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilepath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    public void waitForElementtoAppear(WebElement ele, AndroidDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
    }
    public String getPathDirectory(String runCommand) {
        String path = null;
        try {
            Process p = Runtime.getRuntime().exec(runCommand);
            InputStream in = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            path = br.readLine();
        } catch (IOException e) {
            System.out.println("catch block");
        }
        return path;
    }
    public boolean checkIfServerIsRunnning() {
        boolean isServerRunning = false;
        try (ServerSocket serverSocket = new ServerSocket();) {
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        }
        return isServerRunning;
    }
    public void assertService(AppiumDriverLocalService service) {
        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node wasn't started!");
        }
    }

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int portNumber) {
        if (!checkIfServerIsRunnning()) {
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withIPAddress(ipAddress);
            builder.usingPort(portNumber);
            builder.usingDriverExecutable(new File(nodePath));
            builder.withAppiumJS(new File(appiumPath));
            //To use it on Appium Server 1.x, uncomment the below code & run
            //builder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub");
            builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
            builder.withArgument(GeneralServerFlag.USE_DRIVERS, "uiautomator2");
            builder.withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromedriver_autodownload");
            builder.withArgument(GeneralServerFlag.USE_PLUGINS, "images");
            //Start the server with the builder
            try {
                service = AppiumDriverLocalService.buildService(builder);
                service.start();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Appium Server already running on Port - ");
        }
        assertService(service);
        return service;
  }

  public String getScreenshotPath(String testcaseName, AppiumDriver driver) throws IOException {
        File source=driver.getScreenshotAs(OutputType.FILE);
        String dest=System.getProperty("user.dir")+"//TextExecutionResults//Screenshots//"+testcaseName+".png";
        FileUtils.copyFile(source, new File(dest));
        //FileUtils.copyFile(source, new File(dest));
        return dest;
  }
}


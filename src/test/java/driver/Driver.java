package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ReadPropertyFile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import static configuration.Environment.ENVIRONMENT;

public class Driver {

    private static WebDriver driver;

    public static void init() {
        String USERNAME = ENVIRONMENT.browserstackUsername();
        String ACCESSKEY = ENVIRONMENT.browserstackAccessKey();
        String URL = "https://" + USERNAME + ":" + ACCESSKEY + "@hub.browserstack.com/wd/hub";

        Properties prop = ReadPropertyFile.load();
        System.out.println("PLATFORM CONTEXT: " + prop.getProperty("context"));

        switch (prop.getProperty("context")) {
            case "GALAXY S8": {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("browser", prop.getProperty("browser"));
                caps.setCapability("os_version", prop.getProperty("os_version"));
                caps.setCapability("device", prop.getProperty("device"));
                caps.setCapability("real_mobile", prop.getProperty("real_mobile"));
                caps.setCapability("browserstack.local", prop.getProperty("browserstack.local"));
                caps.setCapability("browserstack.appium_version", prop.getProperty("browserstack.appium_version"));
                try {
                    driver = new RemoteWebDriver(new URL(URL), caps);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            }
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Integer.parseInt(ReadPropertyFile.load().getProperty("timeout")), TimeUnit.SECONDS);
                driver.manage().window().maximize();
                break;
        }
    }

    public static WebDriver get() {
        return driver;
    }

    public static void close(){
        if(driver!=null){
            driver.quit();
        }
    }
}
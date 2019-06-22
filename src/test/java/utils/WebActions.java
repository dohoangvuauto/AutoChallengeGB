package utils;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebActions {

    public static Actions actions(){
        return new Actions(Driver.get());
    }

    public static void moveToElement(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView({behavior: \"smooth\",block: \"center\"});",element);
        waitForClickable(element);
        WebActions.waitFor(1);
    }

    public static void goToUrl(String url){
        Driver.get().navigate().to(url);
    }
    public static void waitFor(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickAfter(WebElement element, double second){
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
    }

    public static void waitForVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.get(), 20);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.get(), 20);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitThenClick(WebElement element){
        waitForClickable(element).click();
    }

    public static void waitThenClick(WebElement element, double time){
        waitFor(time);
        waitForClickable(element).click();
    }

    public static void clickThenWait(WebElement element, double time){
        waitForClickable(element).click();
        waitFor(time);
    }

    public static List<WebElement> getElementsByXpath(String xpath, String param){
       return Driver.get().findElements(By.xpath(xpath.replace("$",param)));
    }

    public static WebElement getElementByXpath(String xpath, String param){
        return Driver.get().findElement(By.xpath(xpath.replace("$",param)));
    }

    public static void clickByJs(WebElement element){
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].click();",element);
    }

    public static boolean isMobile(){
        String context = System.getProperty("context");
        return !context.equalsIgnoreCase("windows");
    }
}

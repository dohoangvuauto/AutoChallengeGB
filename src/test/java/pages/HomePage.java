package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebActions;

public class HomePage {

    @FindBy(xpath = "//a[@href='/ph/travel-insurance' and text()='COMPARE NOW']")
    private static WebElement travel;

    @FindBy(name = "product-form-submit")
    private static WebElement showMyResults;

    public static void goToTravel(){
        WebActions.moveToElement(travel);
        travel.click();
    }

    public static void clickOnShowMyResults(){
        WebActions.waitThenClick(showMyResults);
    }

}

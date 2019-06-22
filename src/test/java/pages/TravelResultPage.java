package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebActions;

import java.util.ArrayList;
import java.util.List;

public class TravelResultPage {

    @FindBy(xpath = "//button[.='cancel']")
    private static WebElement cancelTips;

    @FindBy(xpath = "//button[.='Got it']")
    private static WebElement gotItHelp;

    @FindBy(xpath = "//div[@class='checkbox checkbox-primary']")
    private static List<WebElement> insures;

    @FindBy(xpath = "//a[.='CLEAR ALL']")
    private static WebElement clearAllFilter;

    @FindBy(xpath = "//div[@data-gb-name='travel-plan']")
    private static List<WebElement> planResults;

    @FindBy(id = "collapseSeemoreBtn")
    private static WebElement seeMore;

    @FindBy(xpath = "//label[@for='gb_radio_3']")
    private static WebElement lowToHighPrice;

    @FindBy(xpath = "//label[@for='gb_radio_4']")
    private static WebElement highToLowPrice;

    @FindBy(xpath = "//div[@class='policy-price']/span[@class='value']")
    private static List<WebElement> resultPrice;

    @FindBy(xpath = "//div[@class='select-component']")
    private static WebElement destination;

    @FindBy(xpath = "//div[@class='field field-select']//span[@class='filter-option clearfix']")
    private static WebElement selectCountry;

    @FindBy(xpath = "//div[@data-gb-name='travel-nav-data']//small")
    private static WebElement resultBar;

    @FindBy(xpath = "(//div[@class='field'][div[contains(@class,'date-picker')]])[1]")
    private static WebElement travelStartDate;

    @FindBy(xpath = "//div[@class='datepicker-days']//tbody//td[@class='day' or contains(@class,'active')]")
    private static List<WebElement> datePickerDays;

    @FindBy(xpath = "//div[@class='datepicker-days']//th[@class='datepicker-switch']")
    private static WebElement monthYearSelect;

    @FindBy(xpath = "//div[@class='datepicker-months']//th[@class='datepicker-switch']")
    private static WebElement yearSelect;

    @FindBy(xpath = "//th[@class='datepicker-switch' and text()='2010-2019']")
    private static WebElement nextYearRange;

    @FindBy(xpath = "//div[@class='datepicker-months']//tbody//td/span[not(contains(@class,'disabled'))]")
    private static List<WebElement> datePickerMonths;

    @FindBy(xpath = "//div[@class='datepicker-years']//tbody//td/span[not(contains(@class,'disabled'))]")
    private static List<WebElement> datePickerYears;

    @FindBy(xpath = "//div[a[span[.='Edit search']]]")
    private static WebElement editSearch;  //mobile

    @FindBy(xpath = "//div[@data-gb-name='filter-bar']")
    private static WebElement filterBar;  //mobile

    @FindBy(xpath = "//div[@data-gb-name='filter-bar']//h5[@class='item-title collapsed']")
    private static List<WebElement> filterCollapsed; //mobile

    @FindBy(xpath = "//div[@data-gb-name='sort-bar']")
    private static WebElement sortBar;  //mobile

    @FindBy(xpath = "//div[@data-gb-name='sort-bar']//h5[@class='item-title collapsed']")
    private static List<WebElement> sortCollapsed; //mobile

    @FindBy(xpath = "//div[@data-gb-name='edit-detail-bar']")
    private static WebElement detailBar;  //mobile

    @FindBy(xpath = "//div[@data-gb-name='edit-detail-bar']//h5[@class='item-title collapsed']")
    private static List<WebElement> detailCollapsed; //mobile

    @FindBy(xpath = "//button[.='UPDATE RESULTS']")
    private static WebElement updateResults;  //mobile



    @FindBy(xpath = "(//div[@class='field'][div[contains(@class,'date-picker')]])[2]")
    private static WebElement travelEndDate;

    private static String countryXpath = "(//div[@class='dropdown-menu open']/ul/li[a[span[.='$']]])[1]";

    private static String minPriceSliderXpath = "//label[contains(text(),'$')]/following-sibling::div//div[contains(@class,'min-slider')]";

    private static String maxPriceSliderXpath = "//label[contains(text(),'$')]/following-sibling::div//div[contains(@class,'max-slider')]";

    private static String priceCaseNameXpath = "//p[text()='$']/following-sibling::p//span";

    private static WebElement getSlider(String caseName){
        return WebActions.getElementByXpath(".//label[contains(text(),'$')]/following-sibling::div",caseName);
    }

    private static String resultXpath = "//div[@data-insuer-name='$']";

    public static void cancelTipsPopup(){
        WebActions.waitThenClick(cancelTips);
    }

    public static void closeHelpPopup(){
        WebActions.waitThenClick(gotItHelp);
    }

    public static void selectInsurerCheckbox(String insurerName){
        goToFilter();
        switch (insurerName){
            case "FPG Insurance":
                WebActions.waitThenClick(TravelResultPage.insures.get(0));
                break;
            case "Malayan Insurance":
                WebActions.waitThenClick(TravelResultPage.insures.get(1));
                break;
            case "Pacific Cross":
                WebActions.waitThenClick(TravelResultPage.insures.get(2));
                break;
            case "Pioneer Insurance":
                WebActions.waitThenClick(TravelResultPage.insures.get(3));
                break;
            case "Prudential Guarantee":
                WebActions.waitThenClick(TravelResultPage.insures.get(4));
                break;
            case "Standard Insurance":
                WebActions.waitThenClick(TravelResultPage.insures.get(5));
                break;
            case "STARR":
                WebActions.waitThenClick(TravelResultPage.insures.get(6));
                break;
            default:
                System.out.println("Wrong insurer name");
                break;
        }
        updateResults();
    }

    private static void clearAllFilter(){
        WebActions.clickThenWait(clearAllFilter,1);
    }

    public static void verifyResultWithInsurer(String insurerName){
        List<WebElement> specificResults = WebActions.getElementsByXpath(resultXpath, insurerName);
        System.out.println("Found " + specificResults.size() + " travel plan(s) with " + insurerName);

        Assert.assertEquals(planResults.size(), specificResults.size());
    }

    public static void clickOnSeeMore(){
        WebActions.waitThenClick(seeMore);
    }


    public static void slideToRange(String caseName, int minValue, int maxValue){
        if(WebActions.isMobile()){
           goToFilter();
        } else {
            try {
                clickOnSeeMore();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        WebElement slideBar = getSlider(caseName).findElement(By.className("slider-track"));
        WebActions.moveToElement(slideBar);
        Dimension sliderSize = slideBar.getSize();

        int sliderFullWidth = sliderSize.getWidth(); //177

        WebElement minPriceSlider = WebActions.getElementByXpath(minPriceSliderXpath,caseName);
        WebElement maxPriceSlider = WebActions.getElementByXpath(maxPriceSliderXpath,caseName);

        double maxPrice = Integer.parseInt(minPriceSlider.getAttribute("aria-valuemax")); //150000

        double minPercentage = minValue/maxPrice;
        double minDistantSlide = minPercentage*sliderFullWidth;

        double maxPercentage = maxValue/maxPrice;
        double maxDistantSlide = maxPercentage*sliderFullWidth - sliderFullWidth;

        WebActions.actions().dragAndDropBy(minPriceSlider, (int)minDistantSlide, 0).build().perform();
        WebActions.actions().dragAndDropBy(maxPriceSlider, (int)maxDistantSlide, 0).build().perform();

        updateResults();
    }

    public static void checkAtLeast3CardDisplayed() {
        Assert.assertTrue(planResults.size()>=3);
    }

    public static void checkRangePriceFilter(String caseName, int minValue, int maxValue){
        List<WebElement> rangePriceResults = WebActions.getElementsByXpath(priceCaseNameXpath, caseName);
        for ( WebElement resultPrice : rangePriceResults
             ) {
            int price = Integer.parseInt(resultPrice.getText().substring(1).replace(",",""));
            Assert.assertTrue(price >= minValue);
            Assert.assertTrue(price <= maxValue);
        }
    }

    public static void sortByPriceHighToLow(){
        goToSort();
        highToLowPrice.click();
        updateResults();
    }

    public static void sortByPriceLowToHigh(){
        goToSort();
        lowToHighPrice.click();
        updateResults();
    }

    public static void checkSortByPriceOrder(String priceOrder){
        List<Integer> rs = new ArrayList<Integer>();
        for (WebElement sPrice : resultPrice
             ) {
           int price = Integer.parseInt(sPrice.getText().replace(",",""));
           rs.add(price);
        }
        if (priceOrder.equalsIgnoreCase("high to low")) {
            for (int i = 0; i < rs.size()-1; i++) {
                Assert.assertTrue(rs.get(i) > rs.get(i + 1));
            }
        }
        if (priceOrder.equalsIgnoreCase("low to high")) {
            for (int i = 0; i < rs.size()-1; i++) {
                Assert.assertTrue(rs.get(i) < rs.get(i + 1));
            }
        }
    }

    public static void selectDestination(String countryName){

        if(!WebActions.isMobile()) {
            WebActions.moveToElement(destination);
            WebActions.clickThenWait(destination,1);
            WebActions.getElementByXpath(countryXpath,countryName).click();
        }
        else {
            goToDetails();
            WebActions.waitFor(3);
            selectCountry.click();
            WebActions.getElementByXpath(countryXpath,countryName).click();
            updateResults();
        }
        WebActions.waitFor(1);
    }

    public static void checkDestinationChanged(String countryName){
        Assert.assertTrue(resultBar.getText().contains(countryName));
    }

    public static void selectDatePicker(String day, String month, String year){

        //select Year
        monthYearSelect.click();

        if(year.equals("2019")||(year).equals("2020")){
            System.out.println("nothing change");
        } else
        {
            WebActions.clickThenWait(yearSelect,1);
            WebActions.clickThenWait(nextYearRange,1);
        }
        WebActions.waitFor(1);

        for (WebElement cell : datePickerYears) {
            if (cell.getText().equals(year)) {
                WebActions.waitFor(1);
                cell.click();
                break;
            }
        }
        WebActions.waitFor(1);

        //select Month
        for (WebElement cell : datePickerMonths) {
            if (cell.getText().equalsIgnoreCase(month)) {
                cell.click();
                break;
            }
        }
        WebActions.waitFor(1);
        //select Day
        for (WebElement cell : datePickerDays) {
            if (cell.getText().equals(day)) {
                cell.click();
                break;
            }
        }
    }

    public static void selectStartDate(String day, String month, String year) {
        WebActions.waitFor(2);
        goToDetails();
        WebActions.waitForVisible(travelStartDate);
        WebActions.moveToElement(travelStartDate);
        WebActions.clickThenWait(travelStartDate, 1);
        selectDatePicker(day,month,year);
        updateResults();
    }

    public static void selectEndDate(String day, String month, String year) {
        goToDetails();
        WebActions.waitForVisible(travelEndDate);
        WebActions.moveToElement(travelEndDate);
        WebActions.clickThenWait(travelEndDate, 1);
        selectDatePicker(day,month,year);
        updateResults();
    }

    public static void checkStartEndDate(String startEndDate){
        Assert.assertTrue(resultBar.getText().contains(startEndDate));
    }

    public static void clickOnEditSearch() {
        if(WebActions.isMobile()) {
            editSearch.click();
            WebActions.waitFor(1);
        }
    }

    public static void goToFilter(){
        if(WebActions.isMobile()) {
            editSearch.click();
            if(isFilterCollapsed()) {
                WebActions.waitFor(1);
                filterBar.click();
            }
        }
    }

    public static void goToSort(){
        if(WebActions.isMobile()) {
            editSearch.click();
            if(isSortCollapsed()) {
                WebActions.waitFor(1);
                sortBar.click();
            }
        }
    }

    public static void goToDetails(){
        if(WebActions.isMobile()) {
            editSearch.click();
            if(isDetailCollapsed()) {
                WebActions.waitFor(1);
                detailBar.click();
            }
        }
    }

    public static void updateResults(){
        if(WebActions.isMobile()) {
            updateResults.click();
            WebActions.waitFor(1);
        }
    }

    private static boolean isFilterCollapsed(){
        return filterCollapsed.size() > 0;
    }

    private static boolean isSortCollapsed(){
        return sortCollapsed.size()>0;
    }

    private static boolean isDetailCollapsed(){
        return detailCollapsed.size()>0;
    }


}

package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.Driver;
import org.junit.Assert;
import pages.HomePage;
import pages.TravelResultPage;
import utils.WebActions;

import static configuration.Environment.ENVIRONMENT;

public class StepDefinications {
    @Given("User is on {string}")
    public void userIsOn(String pageName) {
        switch (pageName) {
            case "Home Page":
                WebActions.goToUrl(ENVIRONMENT.url());
                break;
            default:
                System.out.println("Wrong page name");
                break;
        }
        WebActions.waitFor(1);
    }

    @When("User click on Travel section")
    public void userClickOnTravelSection() {
        HomePage.goToTravel();
    }


    @And("User click on Show My Results")
    public void userClickOnShowMyResults() {
        HomePage.clickOnShowMyResults();
    }

    @Then("User is redirected to {string}")
    public void userIsRedirectedTo(String pageName) {
        switch (pageName){
            case "Travel Result Page":
                Assert.assertTrue(Driver.get().getCurrentUrl().contains(ENVIRONMENT.travelResultsUrl()));
                break;
            default:
                System.out.println("Wrong page name");
                break;
        }
    }


    @When("User select insurer checkbox is {string}")
    public void userSelectInsurerCheckboxAs(String insurerName) {
        TravelResultPage.selectInsurerCheckbox(insurerName);
    }

    @When("User close tip and help popups")
    public void userCloseTipsPopups() {
        TravelResultPage.cancelTipsPopup();
        TravelResultPage.closeHelpPopup();
    }

    @Then("Page show result(s) with insurer {string} only")
    public void pageShowResultSHasOnly(String insurerName) {
        TravelResultPage.verifyResultWithInsurer(insurerName);
    }

    @When("User click on See More button")
    public void userClickOnSeeMoreButton() {
        TravelResultPage.clickOnSeeMore();
    }

    @When("User click on editSearch button")
    public void userClickOnEditSearchButton(){
        TravelResultPage.clickOnEditSearch();
    }

    @And("User set range of {string} from {int} to {int}")
    public void userSelectRangeOfIs(String caseName, int minValue, int maxValue) {
        TravelResultPage.slideToRange(caseName, minValue, maxValue);
    }

    @And("There are at least {int} cards displayed")
    public void thereAreAtLeastCardsDisplayed(int number) {
        TravelResultPage.checkAtLeast3CardDisplayed();
    }

    @Then("Page show result(s) with range of {string} from {int} to {int} only")
    public void pageShowResultsWithRangeOfFromToOnly(String caseName, int min, int max) {
        TravelResultPage.checkRangePriceFilter(caseName,min,max);
    }

    @When("User sort result(s) by price {string}")
    public void userSortResultsByPrice(String priceOrder) {
        if(priceOrder.equalsIgnoreCase("high to low")){
            TravelResultPage.sortByPriceHighToLow();
        }
    }

    @Then("Page show result(s) sorted by price {string}")
    public void pageShowResultsHasPrice(String priceOrder) {
        TravelResultPage.checkSortByPriceOrder(priceOrder);
    }

    @When("User change details of destination into {string}")
    public void userChangeDetailsOfDestinationInto(String countryName) {
        TravelResultPage.selectDestination(countryName);
    }

    @Then("Page show result(s) with destination is {string} only")
    public void pageShowResultsWithDestinationIsOnly(String countryName) {
        TravelResultPage.checkDestinationChanged(countryName);
    }

    @When("User select travel start date with day {string} and month {string} and year {string}")
    public void userSelectTravelStartDateOf(String day, String month, String year) {
        TravelResultPage.selectStartDate(day,month,year);
    }

    @When("User select travel end date with day {string} and month {string} and year {string}")
    public void userSelectTravelEndDateWithDayAndMonthAndYear(String day, String month, String year) {
        TravelResultPage.selectEndDate(day,month,year);
    }

    @Then("Page show result(s) with start-end date {string}")
    public void pageShowResultsWithStartEndDateFromTo(String startEndDate) {
        TravelResultPage.checkStartEndDate(startEndDate);
    }
}
package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import driver.Driver;
import utils.PageObjects;
import utils.WebActions;

public class Hooks {
    @Before
    public void beforeScenario(Scenario scenario) {
        Driver.init();
        PageObjects.initPages();
        System.out.println("_____________ BEFORE SCENARIO _____________");
        System.out.println("SCENARIO: " + scenario.getName());
        System.out.println(" ");
    }

    @BeforeStep
    public void beforeSteps() {
        WebActions.waitFor(1);
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println(" ");
        System.out.println("_____________ AFTER SCENARIO _____________");
        System.out.println("STATUS: " + scenario.getStatus());
        System.out.println(" ");
        if (Driver.get() != null) {
            Driver.close();
        }
    }
}
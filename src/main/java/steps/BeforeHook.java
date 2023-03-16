package steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScenarioStorage;

public class BeforeHook {
    @Before(order = 1)
    public void getScenario(Scenario scenario) {
        ScenarioStorage.putScenario(scenario);
    }
}

package gatling;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {
    HttpProtocolBuilder req = http.baseUrl("https://example.com");

    ScenarioBuilder scenario = scenario("HelloWorld")
            .exec(http("TOO_Home")
            .get("/")
            .check(
                   status().is(201),
                   status().is(500)
            ))
            .pause(3);
    {
        setUp(scenario.injectOpen(atOnceUsers(1)).protocols(req));
    }
}

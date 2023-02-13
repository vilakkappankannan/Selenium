package demo;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class BasicSimulation extends Simulation {
    HttpProtocolBuilder req = http.baseUrl("http://localhost:8082/eligibility/test");

    ScenarioBuilder scenario = scenario("HelloWorld")
            .exec(http("TOO_Home")
                    .post("/")
                    .header("accept", "application/json")
                    .header("content-type","application/json")
                    .header("cache-control", "no-store")
                    .check(
                            status().is(200),
                            status().is(500)
                    ))
            .pause(3);
    {
        setUp(scenario.injectOpen(atOnceUsers(1)).protocols(req));
    }
}

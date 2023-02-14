package demo;

import io.gatling.core.body.RawFileBody;
import io.gatling.core.body.StringBody;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {
    HttpProtocolBuilder req = http.baseUrl("http://localhost:8082/eligibility/test");

    ScenarioBuilder scenario = scenario("HelloWorld")
            .exec(http("Eligibility")
                    .post("")
                    .header("accept", "application/json")
                    .header("content-type","application/json")
                    .header("cache-control", "no-store")
                    .asJson()
                            .body(CoreDsl.ElFileBody("json/request/EligibilityRequest.json"))
                    .check(HttpDsl.status().is(200))
                            .check(CoreDsl.responseTimeInMillis().lte(3000))
                    )
            .pause(3);

    {
        setUp(scenario.injectOpen(
                CoreDsl.nothingFor(5),
                CoreDsl.atOnceUsers(1),
                CoreDsl.rampUsers(2).during(10))

                .protocols(req));
    }
}

package demo;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.csv;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {
//    HttpProtocolBuilder req = http.baseUrl("http://localhost:8082/eligibility/test");

    Config config = ConfigFactory.load("performance.properties");
    String baseUrl = config.getString("BASE_URL");

    HttpProtocolBuilder req = http.baseUrl(baseUrl);

            ScenarioBuilder scenario = CoreDsl.scenario(config.getString("SCENARIO_NAME"))
                    .feed(CoreDsl.csv("data/eligibility.csv").eager().random())
            .exec(HttpDsl.http("Eligibility API")
                    .post("")
                    .header("Accept", config.getString("ACCEPT"))
                    .header("Content-Type", config.getString("CONTENT_TYPE"))
                    .header("Cache-Control", config.getString("CACHE_CONTROL"))
                    .asJson()
                            .body(CoreDsl.ElFileBody("json/eligibilityTemplate.json"))
                    .check(HttpDsl.status().is(200))
                            .check(CoreDsl.responseTimeInMillis().lte(3000))
                    )
            .pause(3);

    {
        setUp(scenario.injectOpen(
//                CoreDsl.nothingFor(5),
                CoreDsl.atOnceUsers(1)
//                CoreDsl.rampUsers(2).during(10)
                )
                .protocols(req))


        //assertion
            .assertions(CoreDsl.global().responseTime().percentile3().lte(100))    // for all requests - percentile3 => 95 - ms
            .assertions(CoreDsl.forAll().responseTime().max().lte(100))            // stats for individual requests - max response time in ms
            .assertions(CoreDsl.forAll().failedRequests().percent().lte(0.01));       // for all failed requests percentage < 0.01%

    }
}

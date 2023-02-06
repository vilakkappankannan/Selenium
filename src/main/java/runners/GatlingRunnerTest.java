package runners;

import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;
import org.junit.jupiter.api.Test;

public class GatlingRunnerTest {


//   @Test
    public void testGatlingPerformanceSimulationExecute() {

        GatlingPropertiesBuilder properties = new GatlingPropertiesBuilder();
//        properties.simulationClass(GatlingPerformanceSimulation.class.getCanonicalName());
//        System.setProperty("gatling.conf.file", "gatling/gatling.conf");
        properties.resourcesDirectory("gatling");
        properties.noReports();
       Integer result =  Gatling.fromMap(properties.build());
       System.out.println(result);
    }

}

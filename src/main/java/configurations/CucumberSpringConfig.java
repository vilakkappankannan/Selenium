package configurations;

import com.vk.Main;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

//package com.uk.vk.configurations;
//
//
//import com.uk.vk.paymentservice.PaymentServiceApplication;
//import org.springframework.boot.test.context.SpringBootContextLoader;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.test.context.ContextConfiguration;
//
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
//@ContextConfiguration(classes = PaymentServiceApplication.class, loader = SpringBootContextLoader.class)
//public class CucumberSpringContextConfiguration {
//
////    private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringContextConfiguration.class);
//
//    /**
//     * Need this method so the cucumber will recognize this class as glue and load spring context configuration
//     */
////    @Before
////    public void setUp() {
////        LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
////    }
//
//}
@CucumberContextConfiguration
@SpringBootTest(classes = Main.class)
public class CucumberSpringConfig{

}

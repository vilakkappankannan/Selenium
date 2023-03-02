package steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import configurations.PropertiesConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class CucumberSteps {
    public HashMap<Object, Object> map = new HashMap<Object, Object>();
    private static Response response;
    private ResultActions resultActions;
    final PropertiesConfig propertiesConfig = new PropertiesConfig();

    @Given("a maximal request {word}")
    public void endPoint(String apiURI) throws IOException {

        //Base Url
        RestAssured.baseURI = propertiesConfig.readProperties().getProperty("baseURI");
        RestAssured.basePath = propertiesConfig.readProperties().getProperty(apiURI + ".version") + propertiesConfig.readProperties().getProperty(apiURI + ".uri");
        log.info("\n ****************** End Point ****************** " + '\n' + baseURI + basePath);

    }

    @When("{string} api is called")
    public void apiCalled(String requestType) throws IOException {

        Resource resource = new ClassPathResource("json/request/EligibilityRequest-" + requestType + ".json");
        File file = resource.getFile();
        String request1 = new String(Files.readAllBytes(file.toPath()));
        log.info("\n ****************** Request Body ****************** " + '\n' + request1);

        //Response Body
        response = given()
                .contentType(ContentType.JSON)
                .body(request1)
                .when()
                .post()
                .then()
                .statusCode(200).contentType(ContentType.JSON).
                extract().response();
    }

    @Then("the {string} response is returned")
    public void responseValidation(String responseType) throws IOException {

        Resource resource = new ClassPathResource("json/response/EligibilityResponse-" + responseType + ".json");
        File file = resource.getFile();
        var responseBody = response.getBody();
        log.info("\n ****************** Response Body ****************** " + '\n' + responseBody.asPrettyString());

        //        resultActions.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json(responseBody));
    }

    @And("compare the file {string} is equal to {string}")
    public void compareTheFileIsEqualTo(String actual, String expected) throws IOException {
        Resource resource = new ClassPathResource("json/response/actual.json");
        File file = resource.getFile();
        String content = new String(Files.readAllBytes(file.toPath()));
        log.info("\n ****************** Actual data ****************** " + '\n' + content);

        Resource resource1 = new ClassPathResource("json/response/expected.json");
        File file1 = resource1.getFile();
        String content1 = new String(Files.readAllBytes(file1.toPath()));
        log.info("\n ****************** Expected data ****************** " + '\n' + content1);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualTree = mapper.readTree(content);
        JsonNode expectedTree = mapper.readTree(content1);
        assertEquals(actualTree, expectedTree);
    }

}

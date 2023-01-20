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
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

@Slf4j
public class CucumberSteps {
    public HashMap<Object,Object> map=new HashMap<Object,Object>();
    RequestSpecification request;
    private Properties properties;
    private static Response response;

    private PropertiesConfig propertiesConfig = new PropertiesConfig();

    @Given("a maximal request {word}")
    public void a_maximal_request(String apiURI) throws IOException {

        //Base Url
        RestAssured.baseURI= propertiesConfig.readProperties().getProperty("baseURI");
        RestAssured.basePath= propertiesConfig.readProperties().getProperty(apiURI+".version")+propertiesConfig.readProperties().getProperty(apiURI+".uri");
        log.info("requestURl : " + '\n' + baseURI+basePath);

    }

    @When("{string} api is called")
    public void api_is_called(String string) {

        //Request Body
        JSONObject requestParams = new JSONObject();
        requestParams.put("cardNumber", "1234567");
        requestParams.put("cvv", "124");

        log.info("requestBody : " + '\n' + requestParams.toString());

        //Response Body
        response = given()
                .contentType(ContentType.JSON)
                .body(requestParams.toString())
                .when()
                .post()
                .then()
                .statusCode(200).contentType(ContentType.JSON).
                extract().response();
    }
    @Then("Validate the {string} response")
    public void validate_the_response(String string) throws IOException {

        ResponseBody responseBody = response.getBody();
        log.info("responseBody : " + '\n' + responseBody.asPrettyString());
    }

    @And("compare the file {string} is equal to {string}")
    public void compareTheFileIsEqualTo(String actual, String expected) throws IOException {
        Resource resource = new ClassPathResource("json/response/actual.json");
        File file = resource.getFile();
        String content = new String(Files.readAllBytes(file.toPath()));
        System.out.println(content);

        Resource resource1 = new ClassPathResource("json/response/expected.json");
        File file1 = resource1.getFile();
        String content1 = new String(Files.readAllBytes(file1.toPath()));
        System.out.println(content1);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualTree =mapper.readTree(content);
        JsonNode expectedTree=mapper.readTree(content1);
        assertEquals(actualTree, expectedTree);
    }
    @Given("scenario data")
    public void scenarioData() {
    }

    @When("the {string} api is called with the {string} request and data")
    public void theApiIsCalledWithTheRequestAndData(String arg0, String arg1) {
    }

    @Then("validate the response against the {string} file and expected data")
    public void validateTheResponseAgainstTheFileAndExpectedData(String arg0) {
    }
}

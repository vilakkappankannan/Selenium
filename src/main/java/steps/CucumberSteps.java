package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.vk.module.EligibilityRequest;
import com.vk.module.SecureContext;
import configurations.PropertiesConfig;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.ResultActions;
import utils.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
public class CucumberSteps {
    public HashMap<Object, Object> map = new HashMap<Object, Object>();
    private static Response response;
    private ResultActions resultActions;
    final PropertiesConfig propertiesConfig = new PropertiesConfig();
    private Map<String, Object> savedVariables;
    private EligibilityRequest eligibilityRequest;
    private String eligibilityResponse;
    private Map<String, JsonTemplate> activeTemplates;
    public static final String NO_TEMPLATES_WITH_NAME = "There were no json templates in the active templates with name ";

    @Before
    public void setup() {
        savedVariables = new HashMap<>();

    }

    @Given("a maximal request {word}")
    public void endPoint(String apiURI) throws IOException {

        //Base Url
        RestAssured.baseURI = propertiesConfig.readProperties().getProperty("baseURI");
        RestAssured.basePath = propertiesConfig.readProperties().getProperty(apiURI + ".version") + propertiesConfig.readProperties().getProperty(apiURI + ".uri");
        log.info("\n ****************** End Point ****************** " + '\n' + baseURI + basePath);

    }
    @When("{string} api is called with data")
    public void api_is_called_with_data(String requestType, DataTable dataTable) throws IOException {

       List<List<String>> data = dataTable.asLists(String.class);

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

        eligibilityResponse = response.getBody().asPrettyString();

        //Display output in cucumber report
        ReportHelper.addRequestResponseToHtmlReport("Eligibility API", "http://localhost:8082/eligibility/test", request1, String.valueOf(response));

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

    @And("the {string} json field {string} is decrypted and saved as {string}")
    public void theJsonFieldIsDecryptedAndSavedAs(String jsonResponseKey, String fieldIndex, String saveVariableName) {


//        JsonObject
        String decryptedValue = doDecrypt(fieldIndex);
        System.out.println("Testingvk" + decryptedValue);


    }
    public String doDecrypt(String input) {
        return new String(Base64.getDecoder().decode(input));
    }


    @And("{} the response")
    public String decryptTheResponse(String input) {

        byte[] actualByte= Base64.getDecoder().decode(input);
        String decrypt= new String(actualByte);
        System.out.println("Testvk" +decrypt);
        return decrypt;

    }

    @When("a valid request with data")
    public void aValidRequestWithData(DataTable table) throws IOException, ParseException, TemplateNotSatisfiedException, TemplateValueNotFoundException, FitnessFunctionMisconfigurationException, RandomDataGeneratorException {

        List<List<String>> data = table.asLists(String.class);
        setTheRequestTemplate(String.valueOf("EligibilityRequest"));
        theTemplateIsSatisfiedGivenTheFollowingMap("EligibilityRequest", table);
    }
    public void setTheRequestTemplate(String templateName) throws IOException, org.json.simple.parser.ParseException {
        activeTemplates.put(templateName, new JsonTemplate(JsonUtils.getRequestTemplateJson(templateName)));
        log.info("Successfully loaded request template name: " + templateName);
    }
    public void theTemplateIsSatisfiedGivenTheFollowingMap(String templateName, DataTable templateData)
            throws FitnessFunctionMisconfigurationException, TemplateValueNotFoundException,
            TemplateNotSatisfiedException, RandomDataGeneratorException, JsonProcessingException, io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException, TemplateNotSatisfiedException {
        JsonTemplate jsonTemplate = activeTemplates.get(templateName);
        if (jsonTemplate == null) {
            throw new FitnessFunctionMisconfigurationException(NO_TEMPLATES_WITH_NAME + templateName);
        }
        var mapData = templateData.entries().get(0);

        for (Map.Entry<String, String> entrySet : mapData.entrySet()) {

            // This is where we sub in random data and replace any random data placeholders
            // in our example
            // data
            Object replacementValue = resolveVariable(entrySet.getValue());

            // end random data substitution and carry on inserting data to the template
            jsonTemplate.replaceInTemplate(entrySet.getKey(), replacementValue);
        }
        // we dont need to get the template here, just call this method to test if we
        // are fully
        // satisfied or not
        Assertions.assertDoesNotThrow(jsonTemplate::getSatisfiedJson);

        ObjectMapper mapper = new ObjectMapper();
        eligibilityRequest = mapper.readValue(jsonTemplate.getSatisfiedJson().toString(), EligibilityRequest.class);

    }
    private Object resolveVariable(String variable) throws RandomDataGeneratorException {
        if (variable != null && variable.matches(TestConstants.VARIABLE_REGEX)) {

            String scrubbedReplacementValue = variable.substring(2, variable.length() - 2);
            // See if this is a saved variable first before we try to use the data generator
            if (savedVariables.containsKey(scrubbedReplacementValue)) {
                return savedVariables.get(scrubbedReplacementValue);
            } else {
                return RandomDataUtils.generateRandomData(scrubbedReplacementValue);
            }
        }
        return variable;
    }


}

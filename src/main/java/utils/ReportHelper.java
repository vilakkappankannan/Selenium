package utils;

public class ReportHelper {
    public static void addRequestResponseToHtmlReport(String apiName, String baseUrl, String request, String response) {

        if (request != null) {
//            ScenarioStorage.getScenario().log("\n ****************** REQUEST HEADERS ****************** \n" + headers);
            ScenarioStorage.getScenario().log("\n ****************** REQUEST BODY FOR " + apiName + " ****************** \n" + baseUrl + "\n" + request);
        }
        if (response != null) {
            ScenarioStorage.getScenario().log("\n ****************** RESPONSE BODY FOR " + apiName + " ***************** \n" + response);
        }
    }
    public static void addDecryptedResponseToHtmlReport(String apiName, String decryptedResponse) {
        ScenarioStorage.getScenario().log("\n ****************** DECRYPTED RESPONSE FOR " + apiName + " ******************* \n" + decryptedResponse);
    }
}

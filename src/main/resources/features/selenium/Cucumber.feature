Feature: Smoke Tests

  @smoke
  Scenario Outline: API Test
    Given a maximal request <apiURI>
    When "valid" api is called with data
      | requestId | 458789 |
    Then the "success" response is returned
#    And the "eligibilityResponse" json field "secureContext.encrypt" is decrypted and saved as "decrypted-response"
#    And compare the file "actual" is equal to "expected"

    Examples:
      | apiURI      |
      | Eligibility |


#  @smoke
#  Scenario: Json Test
#    Given scenario data
#      | cardNumber | 123456 |
#    When the "Eligibility" api is called with the "<request>" request and data
#      | cvv | 123 |
#    Then validate the response against the "success" file and expected data
#      | httpStatus | 200 |
#
#    Examples:
#      | request |
#      | Request |

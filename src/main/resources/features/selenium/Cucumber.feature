Feature: Smoke Tests

  @smoke
  Scenario Outline: API Test
    Given a maximal request <apiURI>
    When "valid" api is called
    Then the "success" response is returned
#    And compare the file "actual" is equal to "expected"

    Examples:
      | apiURI      |
      | Eligibility |


##  @smoke
#  Scenario Outline: Json Test
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

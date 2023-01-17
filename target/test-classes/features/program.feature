@LMSApplication
Feature: Program Feature

  @GAllProgram
  Scenario: Verify can send get request to retrieve all program data
    Given User sets request with authorization
    When User sends GET request "/allPrograms"
    Then User should get status code "200", valid response body and json schema validated for successful GET request

  @NegativeScenario
  Scenario: Verify can send get request to retrieve all program data
    Given User sets request with authorization
    When User sends GET request "/allProgram"
    Then User should get status code "404", valid response body and json schema validated for successful GET request

  @CreateNewUser
  Scenario Outline: Verify that user can send post request
    Given User sets request with authorization
    When User sends POST request to create program "<SheetName>" and <Rownumber>
    Then User should get status code "201"

    Examples: 
      | SheetName | Rownumber |
      | create    |         0 |
      | create    |         1 |

  @NegativeScenario
  Scenario Outline: Verify that user can send post request
    Given User sets request with authorization
    When User sends POST request with data using "<SheetName>" and <Rownumber>
    Then User should get status code "400" for duplicate entry

    Examples: 
      | SheetName | Rownumber |
      | postdata  |         0 |

  @GETProgramId
  Scenario: Verify  can send get request to retrieve single Program dara
    Given User sets request with authorization
    When User sends GET request for single Programid data
    Then User should get status code "200" for getProgramId and Name

  @UpdateProgramById
  Scenario Outline: Update By Program Id
    Given User sets request with authorization
    When User sends put request to "/putprogram/" and "<SheetName>" and <Rownumber>
    Then User should get status code update validation "200"

    Examples: 
      | SheetName | Rownumber |
      | putdataId |         0|

  @UpdateProgramByName
  Scenario Outline: Update By Program Name
    Given User sets request with authorization
    When User sends put request to "/program/" byName "<SheetName>" and <Rownumber>
    Then User should get status code update validation "200"

    Examples: 
      | SheetName   | Rownumber |
      | putdataName |         0|

  @DeleteProgramById
  Scenario: Delete Program By Id
    Given User sets request with authorization
    When User sends Delete program by id "/deletebyprogid/"
    Then User should validate status code "200"

  @DeleteProgramByName
  Scenario: Delete Program By Name
    Given User sets request with authorization
    When User sends Delete program by Name "/deletebyprogname/"
    Then User should validate status code "200"

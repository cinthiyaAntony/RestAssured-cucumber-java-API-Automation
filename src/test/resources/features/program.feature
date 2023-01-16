@LMSApplication
Feature: Program Feature

  @ViewAllProgram
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
  Scenario: Verify that user can send post request
    Given User sets request with authorization
    When User sends POST request with data 
    Then User should get status code "201"

  @NegativeScenario
  Scenario Outline: Verify that user can send post request
    Given User sets request with authorization
    When User sends POST request with data using "<SheetName>" and <Rownumber>
    Then User should get status code "400" for duplicate entry

    Examples: 
      | SheetName   | Rownumber |
      | postdata    |         0 |

    @ViewSingleProgramId
  Scenario: Verify  can send get request to retrieve single Program dara
    Given User sets request with authorization
    When User sends GET request for single Programid data
    Then User should get status code "200" for getProgramId and Name
    
 @ViewSingleProgramNAME
  Scenario: Verify  can send get request to retrieve single Program dara
    Given User sets request with authorization
    When User sends GET request for single ProgramName data
    Then User should get status code "200" for getProgramId and Name

    @UpdateProgramById  
    Scenario Outline: Get Batches By Program Id
    Given User sets request with authorization
    When User sends put request to "/putprogram/" and "<SheetName>" and <Rownumber>
    Then User should get status code update validation "200"
    
     Examples: 
      | SheetName   | Rownumber |
      | putdata     |         1 |

    @DeleteProgramById
    Scenario: Delete Program By Id
    Given User sets request with authorization
    When  User sends Delete program by id "/deletebyprogid/"
    Then User should validate status code "200" 
    
       @DeleteProgramByName
    Scenario: Delete Program By Id
    Given User sets request with authorization
    When  User sends Delete program by Name "/deletebyprogname/"
    Then User should validate status code "200" 
Feature: Batch Requests

  @ViewAllBatch
  Scenario: Verify can send get request to retrieve all Batch data
    Given A Service with LMS API
    When Get request to "/allPrograms"
    Then Validate response code 200

  @CreateNewProgram
  Scenario: Cretae new Program for Batch
    Given A Service with LMS API
    When post request for program to "/saveprogram"
    Then Validate response code 201

  @CreateNewBatch
  Scenario: Cretae Batch
    Given A Service with LMS API
    When post request to "/batches"
    Then Validate response code 201

  @ViewBatchByID
  Scenario: Get Batches By Id
    Given A Service with LMS API
    When Get request by batch ID to "/batches/batchId/"
    Then Validate response code 200
    And Validate batch Id is displayed as 0

  @ViewBatchByName
  Scenario: Get Batches By Batch Name
    Given A Service with LMS API
    When Get request by batch name to "/batches/batchName/"
    Then Validate response code 200
    And Validate batch Name is displayed as "value"

  @ViewBatchByProgramID
  Scenario: Get Batches By Program Id
    Given A Service with LMS API
    When Get request by program ID to "/batches/program/"
    Then Validate response code 200
    And Validate Program Id is displayed as 0

  @UpdateBatchByID
  Scenario: Update Batch By Id
    Given A Service with LMS API
    When put request to "/batches/"
    Then Validate response code 200

  @DeleteBatchByID
  Scenario: Delete Batch By Id
    Given A Service with LMS API
    When Delete request to "/batches/"
    Then Validate response code 200

  @DeleteProgramByName
  Scenario: Delete Program by name
    Given A Service with LMS API
    When Delete program request to "/deletebyprogname/"
    Then Validate response code 200

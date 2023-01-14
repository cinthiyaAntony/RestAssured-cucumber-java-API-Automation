Feature: Batch Requests

  Scenario: Get All batches
    Given A Service with LMS API
    When Get request to "/allPrograms"
    Then Validate response code 200
    
      Scenario: Cretae new Program for Batch
    Given A Service with LMS API
    When post request for program to "/saveprogram"
    Then Validate response code 201

  Scenario: Cretae Batch
    Given A Service with LMS API
    When post request to "/batches"
    Then Validate response code 201

  Scenario: Get Batches By Id
    Given A Service with LMS API
    When Get request by batch ID to "/batches/batchId/"
    Then Validate response code 200
    And Validate batch Id is displayed as 0

  Scenario: Get Batches By Batch Name
    Given A Service with LMS API
    When Get request by batch name to "/batches/batchName/"
    Then Validate response code 200
    And Validate batch Name is displayed as "null"

  Scenario: Get Batches By Program Id
    Given A Service with LMS API
    When Get request by program ID to "/batches/program/"
    Then Validate response code 200
    And Validate Program Id is displayed as 0

  Scenario: Update Batch By Id
    Given A Service with LMS API
    When put request to "/batches/batchId/"
    Then Validate response code 200

  Scenario: Delete Batch By Id
    Given A Service with LMS API
    When Delete request to "/batches/"
    Then Validate response code 200

Feature: Batch Requests

  Scenario Outline: Get All batches
    Given A Service with LMS API
    When Get request to <URI>
    Then Validate response code <statusCode>

    Examples: 
      | URI          | statusCode |
      | /allPrograms |        200 |

  Scenario Outline: Get Batches By Id
    Given A Service with LMS API
    When Get request to <URI>
    Then Validate response code <statusCode>
    And Validate batch Id is displayed as <batchId>

    Examples: 
      | URI                  | statusCode | batchId |
      | /batches/batchId/588 |        200 |     588 |

  Scenario Outline: Get Batches By Batch Name
    Given A Service with LMS API
    When Get request to <URI>
    Then Validate response code <statusCode>
    And Validate batch Name is displayed as <batchName>

    Examples: 
      | URI                                          | statusCode | batchName                 |
      | /batches/batchName/Jan23-NinjaSpark-SDET-001 |        200 | Jan23-NinjaSpark-SDET-001 |

  Scenario Outline: Get Batches By Program Id
    Given A Service with LMS API
    When Get request to <URI>
    Then Validate response code <statusCode>
    And Validate Program Id is displayed as <programId>

    Examples: 
      | URI                   | statusCode | programId |
      | /batches/program/1447 |        200 |      1447 |

  Scenario Outline: Cretae Batch
    Given A Service with LMS API
    When post request to <URI>
    Then Validate response code <statusCode>

    Examples: 
      | URI      | statusCode |
      | /batches |        201 |

  Scenario Outline: Update Batch By Id
    Given A Service with LMS API
    When put request to <URI>
    Then Validate response code <statusCode>

    Examples: 
      | URI                  | statusCode |
      | /batches/batchId/588 |        200 |

  Scenario Outline: Delete Batch By Id
    Given A Service with LMS API
    When Delete request to <URI>
    Then Validate response code <statusCode>

    Examples: 
      | URI          | statusCode |
      | /batches/225 |        200 |

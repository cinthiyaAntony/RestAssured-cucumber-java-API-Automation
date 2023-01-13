Feature: Batch Requests

  Scenario Outline: Get All batches
    Given Get call to <URI>
    Then Response is <statusCode>

    Examples: 
      | URI          | statusCode |
      | /allPrograms |        200 |

  Scenario Outline: Get Batches By Id
    Given Get call to <URI>
    Then Response is <statusCode>

    Examples: 
      | URI                  | statusCode |
      | /batches/batchId/483 |        200 |

  Scenario Outline: Get Batches By Batch Name
    Given Get call to <URI>
    Then Response is <statusCode>

    Examples: 
      | URI                                 | statusCode |
      | /batches/batchName/SDET NinjaSparks |        200 |

  Scenario Outline: Get Batches By Program Id
    Given Get call to <URI>
    Then Response is <statusCode>

    Examples: 
      | URI                  | statusCode |
      | /batches/program/307 |        200 |

  Scenario Outline: Cretae Batch
    Given post request to <URI>
    Then Response is <statusCode>

    Examples: 
      | URI      | statusCode |
      | /batches |        201 |

  Scenario Outline: Update Batch By Id
    Given put request to <URI>
    Then Response is <statusCode>

    Examples: 
      | URI          | statusCode |
      | /batches/495 |        200 |
      
       Scenario Outline: Delete Batch By Id
    Given Delete request to <URI>
    Then Response is <statusCode>

    Examples: 
      | URI          | statusCode |
      | /batches/495 |        200 |
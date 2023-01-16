# RestAssured-cucumber-java-API-Automation

OVERVIEW:

API framework is developed using REST Assured and Cucumber. REST Assured is a Java library that provides a domain-specific language (DSL) for writing powerful, 
maintainable tests for RESTful APIs. Cucumber is an open source library,which supports behavior driven development. To be more precise, 
Cucumber can be defined as a testing framework, driven by plain English text. 


Required Setup:

Java should be installed and configured.
Maven should be installed and configured.
Add all required dependencies


key features of this framework:
1. Feature file has reading request details from json and excel file.
2. Validate response body using json schema
3. Generates logs, with detailed request and response details.
4. It generates Extent and Allure report , will be generated both HTML & PDF file format.
5. Test execution can be triggered form command line.
6. Integration to CI/CD pipeline.

Running Test:

Open the command prompt and navigate to the folder in which pom.xml file is present. Run the below Maven command.

mvn clean test

Logs:
log/myLog.log

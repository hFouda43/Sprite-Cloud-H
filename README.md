# Sprite-Cloud-H
***
## General Info
An UI and API automation ptoject for the following environments:
* http://www.uitestingplayground.com/
* https://petstore.swagger.io/

## Used Languages and Tools
***
A list of technologies used within the project:
* Java: version 11.0.17
* Apache Maven: version 11.0.17
* Testng: version 7.7.0
* Selenium-java: 4.7.2
* Restassured: version 5.3.0

## Installation
***
#### Running the tests locally:
> Prerequisites:
* Java 8 or higher
* git
> Steps to run:
* Clone the repo
  * git clone https://github.com/hFouda43/Sprite-Cloud-H.git
  * cd Sprite-Cloud-H
* Run the tests using mvn
  * mvn package -B --file pom.xml
#### Running the tests in a CI/CD environment:
* From Github, Go to "Actions" tab.
* From the left panel, Under "Actions"->"All workflows"; click on "Sprite Cloud Project CI with Maven".
* In the displayed grid, click on "Run Workflow" and then select "Master branch" then click on "Run Workflow".
## Results
***
* Calliope Pro results: https://app.calliope.pro/profiles/4636/reports
* Logs for API tests: https://github.com/hFouda43/Sprite-Cloud-H/tree/master/testresults/api/logs
* Local Runs Reports: https://github.com/hFouda43/Sprite-Cloud-H/tree/master/testresults/reports
## Calliope platform Enhancements and features
***
> Improvement point:
* It would be better if we can tell the user what are the expected formats for each fomatter type
> Feature:
* Support of other formats like html files
* Sending a mail with failure notification when user attempts to import a file via API call and request fails
## Test cases Selection approach:
***
* Test cases that are more likely to be used by end users
* Test cases that are covering critical functionalities/modules
#### Why are they the most importent?
* Critical modules should always take a priority in implementation to guarantee stability of the project
* Heavily used functionalities should also be covered in early stages to guarantee that more likely used functions are free of regressions with new releases

## Next Steps:
* More scenarios to be included (covering both positive and negative tests) to increase the maturity level of the application
* Add more tags to the tests to define different test suites that should run in different project stages (i.e: sanity, smoke, regression,...etc)
* Implement a method to generate screenshots for UI tests in order to attach it to the generated report


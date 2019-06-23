# GoBear - WebApp Automation - Travel Insurance

## Summary
>Automated Testing Framework using Page Factory design pattern and integrate with Cucumber-Appium to test the functionality of GoBear application on cross-platform and multi-platform browsers with BDD.

## Installation and Requirements

1. IntelliJ IDEA and JDK 1.8 or above
2. Install Cucumber plugin
3. Google Chrome latest
4. Install Maven on Windows
5. <Install Appium via npm (optional)>

## Structure and Description

1.main/resources/environment: contain environment config files (uat, test, staging, production...)
 
2.test/resources/context:contain plaform profiles as capabilities.properties file (windows, galaxys8, iphone8plus...) 

3.test/resources/features: contain feature files (scenario test suite)

4.test/java/com.serenity.cucumber.appium.epos:
- configuration: build static ENVIRONMENT and Environment Config to read data from ${evnName}.env.properties file
- driver: class Driver will manage WebDriver and integrate with Browserstack.
- hooks: define cucumber hooks like before and after methods for each scenario
- pages: Page Object reporistory. Page class contains specific web elements and methods for its screen
- runner: Cucumber Runners with JUnit - so you can debug on these classes
- stepdefs: map and define steps of scenarios in feature files
- utils: Build entity of custom services as WebActions, ReadPropertyFile, PageObjects, ApiActions, DbActions...(TBD)

## Usage

Run test cases with specific tag with Cucumber Options for windows desktop by CMD:

```
mvn clean verify -Dcucumber.options="--tags '@smoke'" -Denv=uat -P windows
```

Or mobile web view - Browserstack (optional)

```
mvn clean verify "-Dcucumber.options=--tags '~@wipMobile'" -Denv=uat -P galaxys8
```

Or run specific test by using following command: (optional)

```
mvn clean verify -Dtest=TestLeftSidemenu -Denv=uat -P iphone8plus
```

- **-Dtest:** test class - cucumber runner class
- **-Denv:** environment - environment name
- **-P:** context - platform name

Or you can create config maven Run file and execute it with IntelliJ IDE

After test scenarios executed, go to **tagert/cucumber/** and we've got test report:
- **index.html:** full test report

## Version
* **V 1.0**

## Author
 **Hoang Vu** - dohoangvu.auto@gmail.com
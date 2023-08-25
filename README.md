# liverpool-java-selenium-cucumber
```
This project is a Java + Selenium automation framework for Front-End E2E tests. 

Framework is using Page-Object-Model and Page-Factory for locators and Inversion of Control Container with World class. 
```

## How to execute tests
``` 
You have 2 options of running your tests: Cucumber or TestNG Test. 

Before execution and cloning project, compile typing on PS/CMD console:

mvn clean compile 

If compilation process was ok, you are ready to execute tests :)

MAKE SURE YOU HAVE:
 - Java 11 or higher
 - Maven 3.xxx or higher
```

### TestNG
```
For TestNG, open/create your test on folder test/java/com/liverpool/tests/***.java

- Click on execute button beside @Test on file, you can run a single test or all class tests (this button appears after compiling).
- On console, based on your .pom config, run:

mvn clean install -PTestNGTests

You can add/modify this config by editing testng-tests.xml
```

### Cucumber
```
For Cucumber, open/create your cucumber on folder test/java/com/liverpool/features/***.feature

Similar to TestNG, you can:

- Click on execute button beside Scenario on file, you can run a single test or all class tests (this button appears after compiling).
- Right click on TestNGTestRunner and Run, you can add your test tags on tags=@xxx, change step_def or glue.
- On console, based on your .pom config, run:

mvn clean install -PCucumberReportTests

You can add/modify this config by editing testng-cucumber.xml
```

## After execution
```
Framework will generate 2 reports:

The first one is going to be a 'reports' folder, it will include screenshots(only when test failed) and 'extent_results.html' (ExtentReports).

The second one is going to be at 'target' folder, called 'cucumber.html', which is part of a cucumber plugin.
```

## How to add new tests/steps
```
All configuration classes/data will be on src/main/java/com/liverpol/***

For a new page locator, create your class on page_object/locators/***.java
Make sure you create WebDriver object and initiliaze PageFactory on constructor. Also you can use By objects.

For a new page class, create your class on page_object/pages/***.java
Make sure you create WebDriver object, inheritates from BaseComponents(here we store waits, driver init and more) and your pageLocator object you need.

Now you can create a new Test class using your new pages and new locators :)

Then add your new classes to World class (just for Cucumber).

For cucumber, create a new feature file on com/liverpool/features, you can reuse the steps defined before.
Please check the created files for a better reference.

For step_definitions, it is very similar to page or pageLocator classes. 
Here you will use World object classs and inside constructor, init the classes you need (page_locator and page class).

Now you are ready to create new steps :)
```

package com.liverpool;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/com/liverpool/features", glue="com/liverpool/step_definitions", monochrome = true, tags= "@Liverpool-Tests",
                    plugin={"html:target/cucumber.html"}) // otro reporte
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}

package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "StepDefTdd",
        tags = "@TDD",
        plugin = {"pretty", "html:target/RegressionReportTdd.html"}

)
public class RunTestLoginTdd {
}
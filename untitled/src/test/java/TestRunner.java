import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        //for other scenarios change the tag
        features = {"src/test/resources/features/"},
        glue = {"stepDefs"},
        //tags = "@add",
        plugin = {"json:target/cucumber.json"}
)
public class TestRunner  extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}

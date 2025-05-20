package Data;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "petData")
    public Object[][] petDataProvider() {
        return new Object[][]{
                {"20", "tocky"},
                {"30", "rocky"},
                {"40", "kitty"}
        };
    }
}

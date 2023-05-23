package base.browserSetUp;
import org.testng.annotations.AfterClass;

import static base.browserSetUp.DriverOption.clearOption;

public class BaseDriver {
    @AfterClass
    public void clearDriver() {
        clearOption();
        DriverManager.getDriver().quit();
        DriverManager.removeDriver();
    }
}

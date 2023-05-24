package base.browserSetUp;
import base.actions.Actions;
import org.testng.annotations.AfterClass;

import static base.browserSetUp.DriverOption.clearOption;

public class BaseDriver extends Actions {
    @AfterClass
    public void clearDriver() {
        clearOption();
        DriverManager.getDriver().quit();
        DriverManager.removeDriver();
    }
}

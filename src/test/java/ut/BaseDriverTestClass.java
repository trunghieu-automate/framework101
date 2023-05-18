package ut;

import base.browserSetUp.BaseDriver;
import base.browserSetUp.DriverManager;
import org.testng.annotations.Test;

public class BaseDriverTestClass extends BaseDriver {

    @Test
    public void initChromeBrowserWithOptionThenSuccess(){
        initDriver("chrome");
        DriverManager.getDriver().get("https://selenium.dev");
        clearDriver();
    }


    @Test
    public void initEdgeBrowserThenSuccess(){
        initDriver("edge");
        DriverManager.getDriver().get("https://selenium.dev");
        clearDriver();
    }
}

package ut;

import base.browserSetUp.ChromeDriverRun;
import base.browserSetUp.DriverManager;
import org.testng.annotations.Test;

public class ChromeDriverRunTest extends ChromeDriverRun {
    @Test
    public void startBrowserThenChromeStart(){
        startChrome();
        DriverManager.getDriver().get("https://bing.com");
    }
}

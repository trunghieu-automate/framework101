package ut;

import base.browserSetUp.DriverManager;
import base.browserSetUp.EdgeDriverRun;
import org.testng.annotations.Test;

public class EdgeDriverRunTest extends EdgeDriverRun {
    @Test
    public void startBrowserThenEdgeStart(){
        startBrowser();
        DriverManager.getDriver().get("https://bing.com");
        clearBrowser();
    }
}

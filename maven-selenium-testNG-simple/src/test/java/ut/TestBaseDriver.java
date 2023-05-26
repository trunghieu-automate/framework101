package ut;

import base.browserSetUp.BaseTest;
import org.testng.annotations.Test;

public class TestBaseDriver extends BaseTest {
    @Test
    public void startChromeBrowser(){
        startChrome();
        driver.get("https://bing.com");
        closeBrowser();
    }

    @Test
    public void startEdgeBrowser(){
        startEdge();
        driver.get("https://bing.com");
        closeBrowser();
    }
}

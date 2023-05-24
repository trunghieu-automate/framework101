package ut;

import base.browserSetUp.ChromeDriverRun;
import org.testng.annotations.Test;

public class PrintAllLinkTest extends ChromeDriverRun {
    @Test
    public void getAllLinkTest(){
        startChrome();
        open("https://www.bing.com/news");
        printAllHref();
        printAllLinkTitle();
    }
}

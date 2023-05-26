package base.browserSetUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseTest {
    public WebDriver driver;

    private void getBrowserType(String browser){
        switch (browser){
            case "chrome" -> {
                System.setProperty("webdriver.edge.driver", "browsers/chromedriver.exe");
            }
            case "edge" -> {
                System.setProperty("webdriver.edge.driver", "browsers/msedgedriver.exe");
            }
        }
    }

    public void startChrome(){
        getBrowserType("chrome");
        driver = new ChromeDriver(DriverOption.getChromeOption());
    }

    public void startEdge(){
        getBrowserType("edge");
        driver = new EdgeDriver(DriverOption.getEdgeOption());
    }

    public void closeBrowser(){
        driver.quit();
    }
}

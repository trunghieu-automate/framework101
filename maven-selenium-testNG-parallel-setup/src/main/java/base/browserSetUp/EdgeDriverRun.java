package base.browserSetUp;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class EdgeDriverRun extends BaseDriver{
    public void startEdge(){
        DriverManager.setDriver(new ChromeDriver(DriverOption.getChromeOption()));
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
}

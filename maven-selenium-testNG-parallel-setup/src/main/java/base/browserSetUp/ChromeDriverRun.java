package base.browserSetUp;

import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ChromeDriverRun extends BaseDriver{
    public void startChrome(){
        DriverManager.setDriver(new ChromeDriver(DriverOption.getChromeOption()));
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
}

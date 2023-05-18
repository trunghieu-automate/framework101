package base.browserSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;
import java.util.Objects;

import static base.browserSetUp.DriverOption.clearOption;

public class BaseDriver{
    public static void initDriver(String browser) {
        if (Objects.isNull(DriverManager.getDriver())) {
            try {
                //Use a switch expression to assign the driver object based on the browser type
                WebDriver driver = switch (browser.toLowerCase()) {
                    case "chrome" -> new ChromeDriver(DriverOption.getChromeOption());
                    case "edge" -> new EdgeDriver(DriverOption.getEdgeOption());
                    //Add a default case to throw an exception
                    default -> throw new IllegalArgumentException("Invalid browser type: " + browser);
                };
                //Use the driver variable to set and get the driver from the DriverManager class
               DriverManager.setDriver(driver);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public static void clearDriver() {
        clearOption();
        DriverManager.getDriver().quit();
        DriverManager.removeDriver();
    }
}

package base.browserSetUp;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal <WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver drivers){
        driver.set(drivers);
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void removeDriver(){
        driver.remove();
    }
}

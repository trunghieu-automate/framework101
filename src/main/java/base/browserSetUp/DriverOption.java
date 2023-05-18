package base.browserSetUp;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class DriverOption {

    //TODO:
    // Get browser options from config file
    // Setting option globally
    // Setting proxy globally
    private DriverOption() {
    }

    private static final Properties prop = new Properties();
    private static final ArrayList<String> chromiumArgs = new ArrayList<>();
    private static final ChromeOptions chromeOptions = new ChromeOptions();
    private static final EdgeOptions edgeOptions = new EdgeOptions();

    // opts should be headless,...
    public static ChromeOptions getChromeOption() {
        getBrowserArgs();
        printCurrentArgs();
        chromeOptions.addArguments(chromiumArgs);
        return chromeOptions;
    }

    public static EdgeOptions getEdgeOption() {
        getBrowserArgs();
        printCurrentArgs();
        edgeOptions.addArguments(chromiumArgs);
        return edgeOptions;
    }

    public static void clearOption() {
        chromiumArgs.clear();
    }

    private static Proxy getProxy() {
        //TODO: Take proxy with port, user, pass from properties file
        String host = "100.100.1.1:9889";
        String user = "user";
        String password = "pass";
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password.toCharArray());
            }
        };
        Authenticator.setDefault(authenticator);
        // Create a proxy object and set the proxy type and details
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setHttpProxy(host);
        proxy.setSslProxy(host);
        return proxy;
    }
    private static void getProperties() {
        try{
            FileInputStream input = new FileInputStream("config.properties");
            prop.load(input);
            // Get a set of all keys
            for (String key : prop.stringPropertyNames()) {
                System.out.println(key + " = " + prop.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getBrowserArgs(){
        getProperties();
        String argsOther = prop.getProperty("browserArgs");
        String[] options = argsOther.split(",");
        chromiumArgs.addAll(Arrays.stream(options).map(String::strip).toList());
    }

    private static void printCurrentArgs() {
        System.out.println("Current options: " + chromiumArgs);
    }
}



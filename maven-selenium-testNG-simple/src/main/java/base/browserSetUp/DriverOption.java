package base.browserSetUp;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.internal.thread.ThreadUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DriverOption {

    /**
     * TODO:
     * - change preferences
     * - change localState
     * - add extension
     **/
    private DriverOption() {
    }

    private static final Properties prop = new Properties();
    // use Set interface to forbid duplicates
    private static final Collection<String> chromiumArgs = new HashSet<>();
    private static final ChromeOptions chromeOptions = new ChromeOptions();
    private static final EdgeOptions edgeOptions = new EdgeOptions();

    // opts should be headless,...
    public static ChromeOptions getChromeOption() {
        getBrowserArgs();
        printCurrentArgs();
        chromeOptions.addArguments(Set.copyOf(chromiumArgs).stream().toList());
        return chromeOptions;
    }

    public static EdgeOptions getEdgeOption() {
        getBrowserArgs();
        printCurrentArgs();
        edgeOptions.addArguments(Set.copyOf(chromiumArgs).stream().toList());
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
        // Create a proxy object and set the proxy type and details
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setHttpProxy(user + ":" + password + "@" + host);
        proxy.setSslProxy(user + ":" + password + "@" + host);
        return proxy;
        //Set proxy by: chromeOption.setProxy(getProxy())
    }

    private static void getProperties() {
        try {
            FileInputStream input = new FileInputStream("config.properties");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getBrowserArgs() {
        getProperties();
        String argsOther = prop.getProperty("browserArgs");
        if (Objects.isNull(argsOther)) {
            chromiumArgs.clear();
        } else {
            String[] options = argsOther.split(",");
            chromiumArgs.addAll(Arrays.stream(options).map(String::strip).collect(Collectors.toSet()));
        }
    }

    private static void printCurrentArgs() {
        System.out.println(ThreadUtil.currentThreadInfo() + ", with browser options: " + chromiumArgs);
    }
}



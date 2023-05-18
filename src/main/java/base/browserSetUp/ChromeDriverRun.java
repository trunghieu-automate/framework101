package base.browserSetUp;

public class ChromeDriverRun extends BaseDriver{
    public void startBrowser(){
        initDriver("chrome");
    }

    public void clearBrowser(){
        clearDriver();
    }
}

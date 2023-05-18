package base.browserSetUp;

public class EdgeDriverRun extends BaseDriver{
    public void startBrowser(){
        initDriver("edge");
    }

    public void clearBrowser(){
        clearDriver();
    }
}

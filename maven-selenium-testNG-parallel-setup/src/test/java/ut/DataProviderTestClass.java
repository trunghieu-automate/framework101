package ut;

import base.browserSetUp.DriverManager;
import base.browserSetUp.EdgeDriverRun;
import base.utils.ExcelUtils;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static base.utils.ExcelUtils.getDataForSpecificTest;


public class DataProviderTestClass extends EdgeDriverRun {
    @BeforeClass
    public void startTest() {
        startEdge();
        DriverManager.getDriver().get("https://google.com");
    }

    @Test(dataProvider = "Testcase 01")
    public void Location_001(String address, String city, String country, String postcode) {
        setValue(By.id("APjFqb"), address);
        setValue(By.id("APjFqb"), city);
        setValue(By.id("APjFqb"), country);
        setValue(By.id("APjFqb"), postcode);
    }

    @Test(dataProvider = "Testcase 02")
    public void Location_002(String address, String city, String country, String postcode) {
        setValue(By.id("APjFqb"), address);
        setValue(By.id("APjFqb"), city);
        setValue(By.id("APjFqb"), country);
        setValue(By.id("APjFqb"), postcode);
    }

    @Test(dataProvider = "Testcase 03")
    public void Location_003(String address, String city, String country, String postcode) {
        setValue(By.id("APjFqb"), address);
        setValue(By.id("APjFqb"), city);
        setValue(By.id("APjFqb"), country);
        setValue(By.id("APjFqb"), postcode);
    }

    @Test(dataProvider = "Testcase 04")
    public void Location_004(String address, String city, String country, String postcode) {
        setValue(By.id("APjFqb"), address);
        setValue(By.id("APjFqb"), city);
        setValue(By.id("APjFqb"), country);
        setValue(By.id("APjFqb"), postcode);
    }


    @DataProvider(name = "Testcase 01")
    public Object[][] getDataForTestcase01() {
        return getDataForSpecificTest("testDatas/locations-sample-data.xlsx", "locations", "Location_001");
    }

    @DataProvider(name = "Testcase 02")
    public Object[][] getDataForTestcase02() {
        return getDataForSpecificTest("testDatas/locations-sample-data.xlsx", "locations", "Location_002");
    }

    @DataProvider(name = "Testcase 03")
    public Object[][] getDataForTestcase03() {
        return getDataForSpecificTest("testDatas/locations-sample-data.xlsx", "locations", "Location_003");
    }

    @DataProvider(name = "Testcase 04")
    public Object[][] getDataForTestcase04() {
        return getDataForSpecificTest("testDatas/locations-sample-data.xlsx", "locations", "Location_004");
    }
}

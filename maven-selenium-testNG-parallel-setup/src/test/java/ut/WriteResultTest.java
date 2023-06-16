package ut;

import base.browserSetUp.DriverManager;
import base.browserSetUp.EdgeDriverRun;
import base.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class WriteResultTest extends EdgeDriverRun {
    @BeforeClass
    public void startTest() {
        startEdge();
        DriverManager.getDriver().get("https://google.com");
    }

    @Test
    public void Location_001(Method methodName) {
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Google");
        ExcelUtils.updateTestResultToExcelFile("testDatas/locations-sample-data.xlsx", methodName.getName(), "pass");
    }

    @Test
    public void Location_002(Method methodName) {
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Google");
        ExcelUtils.updateTestResultToExcelFile("testDatas/locations-sample-data.xlsx", methodName.getName(), "pass");
    }

    @Test
    public void Location_003(Method methodName) {
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Google");
        ExcelUtils.updateTestResultToExcelFile("testDatas/locations-sample-data.xlsx", methodName.getName(), "pass");
    }

    @Test
    public void Location_004(Method methodName) {
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Google");
        ExcelUtils.updateTestResultToExcelFile("testDatas/locations-sample-data.xlsx", methodName.getName(), "pass");
    }

    @Test
    public void ASP_001(Method methodName) {
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Google");
        ExcelUtils.updateTestResultToExcelFile("testDatas/locations-sample-data.xlsx", methodName.getName(), "pass");
    }

    @Test
    public void ASP_002(Method methodName) {
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Google");
        ExcelUtils.updateTestResultToExcelFile("testDatas/locations-sample-data.xlsx", methodName.getName(), "pass");
    }

    @Test
    public void ASP_003(Method methodName) {
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Google");
        ExcelUtils.updateTestResultToExcelFile("testDatas/locations-sample-data.xlsx", methodName.getName(), "pass");
    }


    @Test
    public void ASP_999(Method methodName) {
        Assert.assertEquals(DriverManager.getDriver().getTitle(), "Google");
        ExcelUtils.updateTestResultToExcelFile("testDatas/locations-sample-data.xlsx", methodName.getName(), "pass");
    }
}


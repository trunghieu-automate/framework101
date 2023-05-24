package base.actions;

import base.browserSetUp.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Stream;

public class Actions {
    public synchronized static void setValue(By locator, String text){
        clearTextBox(locator)
                .toList()
                .get(0)
                .sendKeys(text);
    }

    public synchronized static void appendText(By locator, String text){
        findBy(locator)
                .toList()
                .get(0)
                .sendKeys(text);
    }

    public synchronized static void clearAllTextBox(){
        DriverManager.getDriver()
                .findElements(By.xpath("//input[@type='text'] or //input[@type='textarea']"))
                .forEach(WebElement::clear);
    }

    private synchronized static Stream<WebElement> clearTextBox(By locator){
        return findBy(locator).peek(WebElement::clear);
    }
    private synchronized static void clear(By locator){
        findBy(locator).toList().get(0).clear();
    }

    private synchronized static Stream<WebElement> findBy(By locator){
        return DriverManager.getDriver().findElements(locator).stream();
    }

    public synchronized static String getText(By locator){
        return findBy(locator).toList().get(0).getText();
    }

    public synchronized static void clickBtnBy(By locator){
        findBy(locator).toList().get(0).click();
    }

    private synchronized static List<WebElement> getAllLink(){
        return findBy(By.xpath("//a[@href]")).toList();
    }

    public synchronized static void printAllHref(){
        getAllLink().stream().map(e -> e.getAttribute("href")).map(String::strip).forEach(System.out::println);
    }

    public synchronized static void printAllLinkTitle(){
        getAllLink().stream().map(WebElement::getText).map(String::strip).forEach(System.out::println);
    }
}

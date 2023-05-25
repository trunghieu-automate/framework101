package base.actions;

import base.browserSetUp.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.stream.Collectors;
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
                .findElements(By.xpath("//input[@type='text' or @type='textarea']"))
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

    private synchronized static Stream<WebElement> getAllLink(){
        return findBy(By.xpath("//a[@href]"));
    }

    private synchronized static Stream<String> getAllHref(){
        return getAllLink()
                .map(e -> e.getAttribute("href"))
                .map(String::strip);
    }

    private synchronized static Stream<String> getAllLinkTitle(){
        return getAllLink()
                .map(WebElement::getText)
                .map(String::strip);
    }
    public synchronized static void printAllLinkAndTitle(){
        toMapOfTitleAndHref()
                .forEach((s, s2) -> {
                    System.out.println(s + " :: " + s2);
                });

    }
    private synchronized static Stream<WebElement> getLinkThatHaveTitle(){
        return findBy(By.xpath("//a[@title and @href]"));
    }

    private synchronized static Map<String, String> toMapOfTitleAndHref(){
        return getLinkThatHaveTitle()
                .collect(Collectors
                .toMap(k-> k.getAttribute("title"), v -> v.getAttribute("href"), (k1, k2) -> k1));
    }

}

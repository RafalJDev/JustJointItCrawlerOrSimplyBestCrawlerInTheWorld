package web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Jaszczynski.Rafal on 01.04.2018.
 */
public class SourcePageExractor {

  public static String getPageSource(WebDriver webDriver, String className) {
    WebElement element = webDriver.findElement(By.className(className));
    return (String) ((JavascriptExecutor) webDriver).executeScript("return arguments[0].innerHTML;", element);
  }
}

package web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Timer;

/**
 * Created by Jaszczynski.Rafal on 01.04.2018.
 */
public class Waiter {

  public static void waitTillPageLoads(WebDriver webDriver) {
    Wait<WebDriver> wait = new WebDriverWait(webDriver, 10000);

    wait.until(driver1 -> ((JavascriptExecutor) webDriver)
       .executeScript("return document.readyState")
       .equals("complete"));
  }
}

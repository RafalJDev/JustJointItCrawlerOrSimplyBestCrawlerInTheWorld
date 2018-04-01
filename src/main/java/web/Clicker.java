package web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Jaszczynski.Rafal on 01.04.2018.
 */
public class Clicker {

  public static void click(WebDriver webDriver, String className, int elementNumber) {
    ((JavascriptExecutor) webDriver)
       .executeScript(
          "document.getElementsByClassName"
             + "(\"" + className + "\")"
             + "[" + elementNumber + "]"
             + ".click();");

    sleep();
  }

  private static void sleep() {
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
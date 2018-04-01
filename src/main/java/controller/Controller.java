package controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import web.ChromeOpener;
import web.SourcePageExractor;

/**
 * Created by Jaszczynski.Rafal on 01.04.2018.
 */
public class Controller {

  private WebDriver webDriver;

  public Controller() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\jaszc\\Desktop\\chromedriver.exe");
    webDriver = new ChromeDriver();
  }

  public void runIt() {
    ChromeOpener.openChrome(webDriver, "https://justjoin.it/krakow/java/");

    try {
      Thread.sleep(3500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String pageSource = SourcePageExractor.getPageSource(webDriver);
    System.out.println(pageSource);
  }
}

package web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Jaszczynski.Rafal on 01.04.2018.
 */
public class ChromeOpener {

  public static void openChrome(WebDriver webDriver ,String url) {
    webDriver.get(url);
  }
}

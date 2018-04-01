package controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import skill.Skill;
import web.ChromeOpener;
import web.Clicker;
import web.SourcePageExractor;
import web.Waiter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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
    openChrome();

    long offersCount = getNumberOfOffers();

    for (int i = 0; i < offersCount - 1; i++) {
      String skillsHtml = getSkillsHtml(i);

      break;
    }
  }

  public String getSkillsHtml(int offerIndex) {
    Clicker.click(webDriver, "item-row", offerIndex);

    String offerPageSource = getPageSource("skills-container");
    System.out.println(offerPageSource);
    return offerPageSource;
  }

  public long getNumberOfOffers() {
    String[] splitedLines = getPageSource("offers-list").split("\\n");

    return Arrays.stream(splitedLines)
       .filter(getStringPredicate())
       .count();
  }

  public String getPageSource(String className) {
    return SourcePageExractor.getPageSource(webDriver, className);
  }

  public void openChrome() {
    ChromeOpener.openChrome(webDriver, "https://justjoin.it/krakow/java/");
    Waiter.waitTillPageLoads(webDriver);
  }

  //predicate for learning purpose
  private Predicate<String> getStringPredicate() {
    return line -> line.contains("item-row");
  }
}
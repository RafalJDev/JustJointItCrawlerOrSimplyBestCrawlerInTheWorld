package controller;

import job.data.JobOffer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import job.SkillFinder;
import web.ChromeOpener;
import web.Clicker;
import web.SourcePageExractor;
import web.Waiter;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by Jaszczynski.Rafal on 01.04.2018.
 */
public class Controller {

  private WebDriver webDriver;
  private SkillFinder skillFinder = new SkillFinder();
  private List<JobOffer> jobOfferList;

  public Controller() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\jaszc\\Desktop\\chromedriver.exe");
    webDriver = new ChromeDriver();
  }

  public void runIt() {
    openChrome();

    long offersCount = getNumberOfOffers();

    jobOfferList = new ArrayList<>();
    for (int i = 0; i < offersCount - 1; i++) {
      getSkillsForCurrentOffer(i);
    }
    printEachOffer(jobOfferList);
  }

  public void getSkillsForCurrentOffer(int i) {
    String skillsHtml = getSkillsHtml(i);

    JobOffer jobOffer = new JobOffer(skillFinder.findSkills(skillsHtml));
    jobOfferList.add(jobOffer);

    Clicker.click(webDriver, "material-icons waves-effect waves back", 0);
  }

  private void printEachOffer(List<JobOffer> jobOfferList) {
    jobOfferList.stream()
       .forEach(jobOffer -> {
         jobOffer.printSkills();
         System.out.println();
       });
  }

  public void prepareSkillStatistics() {
    Map<String, List<Integer>> skillsStatistics = new HashMap<>();
//    jobOfferList.stream()
  }

  public String getSkillsHtml(int offerIndex) {
    Clicker.click(webDriver, "item-row", offerIndex);

    String offerPageSource = getPageSource("skills-container");
//    System.out.println(offerPageSource);
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
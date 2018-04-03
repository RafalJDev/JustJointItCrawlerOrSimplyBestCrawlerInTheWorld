package controller;

import job.data.JobOffer;
import job.data.Skill;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import job.SkillFinder;
import web.ChromeOpener;
import web.Clicker;
import web.SourcePageExractor;
import web.Waiter;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
    for (int i = 0; i < offersCount - 1 - 10; i++) { //TODO REMOVE TEMPORARY LIMIT
      getSkillsForCurrentOffer(i);
    }
    printEachOffer(jobOfferList);

    prepareSkillStatistics(jobOfferList);
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

  public void prepareSkillStatistics(List<JobOffer> jobOfferList) {
    //java stream to variable for learning purposes
    Function<JobOffer, List<Skill>> jobOfferListFunction = jobOffer -> jobOffer.getSkillList();
    Function<List<Skill>, Stream<? extends Skill>> listStreamFunction = skills -> skills.stream();
    Collector<Skill, ?, List<Skill>> skillListCollector = toList();
    Consumer<Skill> println = System.out::println;

    Map<String, List<Integer>> skillStatisticsMap = new HashMap<>();
    Consumer<Skill> skillConsumer = skill -> {
      String skillName = skill.getName();
      Integer levelValue = skill.getLevelValue();
      if (!skillStatisticsMap.containsKey(skillName)) {
        skillStatisticsMap.put(skillName, new ArrayList<>());
      }
      List<Integer> skillLevelList = skillStatisticsMap.get(skillName);
      skillLevelList.add(levelValue);
      skillStatisticsMap.put(skillName, skillLevelList);
    };

    jobOfferList.stream()
       .map(jobOfferListFunction)
       .flatMap(listStreamFunction)
       .collect(skillListCollector)
       .forEach(skillConsumer);
//       .forEach(println);

    Comparator<Map.Entry<String, List<Integer>>> valueComparator =
       (e1, e2) -> {
         int size1 = e1.getValue().size();
         int size2 = e2.getValue().size();
         if (size1 > size2) {
           return 1;
         } else if (size1 == size2) {
           return 0;
         }
         return -1;
       };

    Map<String, List<Integer>> sortedSkillStatisticsMap =
       skillStatisticsMap.entrySet()
          .stream()
          .sorted(valueComparator)
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
             (e1, e2) -> e1, LinkedHashMap::new));

    sortedSkillStatisticsMap.forEach((skillName, levelValueList) -> {
      System.out.println("SkillName: " + skillName);
      System.out.println("Skill occurence: " + levelValueList.size());

      ToIntFunction<Integer> intValue = Integer::intValue;

      System.out.println("Skill averge level value: "
         + (double) levelValueList.stream().mapToInt(intValue).sum() / (double) levelValueList.size());
      System.out.println("---------------");
    });
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
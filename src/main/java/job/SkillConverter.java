package job;

/**
 * Created by Jaszczynski.Rafal on 02.04.2018.
 */
public class SkillConverter {

  public static int convertToInt(String levelDescription) {
    int levelValue;
    switch (levelDescription) {
      case "nice to have":
        levelValue = 1;
        break;
      case "junior":
        levelValue = 2;
        break;
      case "regular":
        levelValue = 3;
        break;
      case "advanced":
        levelValue = 4;
        break;
      case "master":
        levelValue = 5;
        break;
      default:
        throw new IllegalArgumentException("Illegal level description: " + levelDescription);
    }
    return levelValue;
  }
}

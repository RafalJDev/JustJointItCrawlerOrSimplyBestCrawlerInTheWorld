package job.data;

/**
 * Created by Jaszczynski.Rafal on 01.04.2018.
 */
public class Skill {

  private String name;
  private Integer levelValue;

  public Skill(String name, Integer levelValue) {
    this.name = name;
    this.levelValue = levelValue;
  }

  public String getName() {
    return name;
  }

  public Integer getLevelValue() {
    return levelValue;
  }

  @Override
  public String toString() {
    return "Skill{" +
       "name='" + name + '\'' +
       ", level='" + levelValue + '\'' +
       '}';
  }
}

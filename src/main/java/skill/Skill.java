package skill;

/**
 * Created by Jaszczynski.Rafal on 01.04.2018.
 */
public class Skill {

  private String name;
  private Integer level;

  public Skill(String name, String level) {
    this.name = name;
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }
}

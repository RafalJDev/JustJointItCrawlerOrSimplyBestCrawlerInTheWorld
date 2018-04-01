package job.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaszczynski.Rafal on 01.04.2018.
 */
public class JobOffer {

  private String cityName;
  private List<Skill> skillList = new ArrayList<>();

  public JobOffer(List<Skill> skillList) {
    this.skillList = skillList;
  }

  public JobOffer(String cityName, List<Skill> skillList) {
    this.cityName = cityName;
    this.skillList = skillList;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public List<Skill> getSkillList() {
    return skillList;
  }

  public void setSkillList(List<Skill> skillList) {
    this.skillList = skillList;
  }

  public void printSkills() {
    skillList.stream().forEach(System.out::println);
  }
}

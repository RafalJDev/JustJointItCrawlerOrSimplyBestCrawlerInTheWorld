package skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

/**
 * Created by Jaszczynski.Rafal on 01.04.2018.
 */
public class SkillFinder {

  public List<Skill> findSkills(String skillsHtml) {
    List<String> collectedSkills = Arrays.stream(skillsHtml.split("\\n"))
       .filter(line -> line.contains("class=\"name\"") || line.contains("class=\"level-title\""))
       .map(getStringStringFunction())
       .collect(toList());

    List<Skill> skillList = new ArrayList<>();
    for (int i = 0; i < collectedSkills.size() - 2; i += 2) {
      String skillName = collectedSkills.get(i);
      String skillLevel = collectedSkills.get(i + 1);

      Skill skill = new Skill(skillName, skillLevel);
      skillList.add(skill);
    }
    return skillList;
  }

  private Function<String, String> getStringStringFunction() {
    return line -> {
      line.replaceAll("", "");
      return line.replaceAll("", "");
    };
  }

}
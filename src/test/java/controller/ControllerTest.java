package controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jaszczynski.Rafal on 01.04.2018.
 */
public class ControllerTest {

  Controller controller;

  @Before
  public void setUp() throws Exception {
    controller = new Controller();
    controller.openChrome();
  }

  @Test
  public void givenClassName_WhenGettingSource_ThenCorrect() {

    String pageSource = controller.getPageSource("offers-list");

    boolean containsAngleBracketLeft = pageSource.contains("<");
    assertTrue(containsAngleBracketLeft);

    boolean containsAngleBracketRight = pageSource.contains(">");
    assertTrue(containsAngleBracketRight);
  }

}
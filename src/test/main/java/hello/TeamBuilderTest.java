package hello;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.Before;
import org.junit.Test;


public class TeamBuilderTest {
  private TeamBuilder teamBuilder;

  @Before
  public void setup() {
    teamBuilder = new TeamBuilder();
  }

  @Test
  public void doesThisEven() {

    final String[] args = new String [] {"0001", "0010", "1100", "1100"};
    final int[] testIntegers = teamBuilder.specialLocation(args);
    assertThat(testIntegers).isNotEmpty();
  }

  @Test
  public void allZeroes() {
    final String[] args = new String [] {"000","001","110"};
    final int[] testIntegers = teamBuilder.specialLocation(args);
    assertThat(testIntegers).isNotEmpty();
  }

  @Test
  public void example_0() {
    final String[] args = new String [] {"010","000","110"};
    final int[] testIntegers = teamBuilder.specialLocation(args);
    assertThat(testIntegers).containsSequence(1, 1);
  }

  @Test
  public void example_1() {
    final String[] args = new String [] {"0010","1000","1100","1000"};
    final int[] testIntegers = teamBuilder.specialLocation(args);
    assertThat(testIntegers).containsSequence(1, 3);
  }

  @Test
  public void example_2() {
    final String[] args = new String [] {"01000","00100","00010","00001","10000"};
    final int[] testIntegers = teamBuilder.specialLocation(args);
    assertThat(testIntegers).containsSequence(5, 5);
  }

  @Test
  public void example_3() {
    final String[] args =
      new String [] {"0110000","1000100","0000001","0010000","0110000","1000010","0001000"};
    final int[] testIntegers = teamBuilder.specialLocation(args);
    assertThat(testIntegers).containsSequence(1, 3);
  }

  @Test
  public void pathLength() {
    final String[] args = new String [] {"0"};
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> teamBuilder.specialLocation(args));
  }

  @Test
  public void characterLength() {
    final String[] args = new String [] {"010", "1100"};
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> teamBuilder.specialLocation(args));
  }

  @Test
  public void charactersOther() {
    final String[] args = new String [] {"0123", "1109", "0871", "0100"};
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> teamBuilder.specialLocation(args));
  }
}

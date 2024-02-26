package befaster.solutions.CHK;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {

    @Test
    public void normalCases() {
        assertThat(new CheckoutSolution().checkout("A"), equalTo(50));
        assertThat(new CheckoutSolution().checkout("B"), equalTo(30));
        assertThat(new CheckoutSolution().checkout("C"), equalTo(20));
        assertThat(new CheckoutSolution().checkout("AB"), equalTo(80));
        assertThat(new CheckoutSolution().checkout("ABCDDD"), equalTo(145));
    }

    @Test
    public void offerCases() {
        assertThat(new CheckoutSolution().checkout("ABCADA"), equalTo(195));
        assertThat(new CheckoutSolution().checkout("ABCADAA"), equalTo(245));
        assertThat(new CheckoutSolution().checkout("AABAACAA"), equalTo(300));
        assertThat(new CheckoutSolution().checkout("AABAACBAA"), equalTo(315));
        assertThat(new CheckoutSolution().checkout("AAABB"), equalTo(175));
        assertThat(new CheckoutSolution().checkout("ABB"), equalTo(95));
    }

    @Test
    public void CHK_R2() {
        assertThat(new CheckoutSolution().checkout("EEB"), equalTo(80));
        assertThat(new CheckoutSolution().checkout("EE"), equalTo(80));
        assertThat(new CheckoutSolution().checkout("BBEE"), equalTo(110));
    }

    @Test
    public void CHK_R3() {
        assertThat(new CheckoutSolution().checkout("FFF"), equalTo(20));
        assertThat(new CheckoutSolution().checkout("FFFFFF"), equalTo(40));
        assertThat(new CheckoutSolution().checkout("FFFFF"), equalTo(40));
    }

    @Test
    public void CHK_R4() {
        assertThat(new CheckoutSolution().checkout("RRRQ"), equalTo(150));
        assertThat(new CheckoutSolution().checkout("VVVVV"), equalTo(220));
        assertThat(new CheckoutSolution().checkout("KKJK"), equalTo(250));
        assertThat(new CheckoutSolution().checkout("AAAAAPPPPPUUUUEEBRRRQAAAHHHHHHHHHHVVVBBNNNMFFFKKQQQVVHHHHH"), equalTo(1610));
        assertThat(new CheckoutSolution().checkout("PPPPQRUVPQRUVPQRUVSU"), equalTo(740));
    }

    @Test
    public void CHK_R5() {
//        assertThat(new CheckoutSolution().checkout("ZZZ"), equalTo(45));
//        assertThat(new CheckoutSolution().checkout("ZZZYST"), equalTo(90));
        assertThat(new CheckoutSolution().checkout("ZZZYSXZ"), equalTo(107));
    }

    @Test
    public void edgeCases() {
        assertThat(new CheckoutSolution().checkout(""), equalTo(0));
        assertThat(new CheckoutSolution().checkout("z"), equalTo(-1));
        assertThat(new CheckoutSolution().checkout("ABCzDDD"), equalTo(-1));
    }
}



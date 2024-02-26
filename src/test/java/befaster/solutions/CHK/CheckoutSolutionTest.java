package befaster.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution;

    @Test
    public void normalCases() {
        assertThat(new CheckoutSolution().checkout("AB"), equalTo(80));
        assertThat(new CheckoutSolution().checkout("B"), equalTo(30));
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
    public void edgeCases() {
        assertThat(new CheckoutSolution().checkout(""), equalTo(0));
        assertThat(new CheckoutSolution().checkout("Z"), equalTo(-1));
        assertThat(new CheckoutSolution().checkout("ABCZDDD"), equalTo(-1));
    }
}


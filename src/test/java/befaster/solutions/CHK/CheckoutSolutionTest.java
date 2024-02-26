package befaster.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setUp() {
        checkoutSolution = new CheckoutSolution();
    }

    @Test
    public void normalCases() {
        assertThat(checkoutSolution.checkout("AB"), equalTo(80));
        assertThat(checkoutSolution.checkout("B"), equalTo(30));
        assertThat(checkoutSolution.checkout("ABCDDD"), equalTo(145));
    }

    @Test
    public void offerCases() {
//        assertThat(checkoutSolution.checkout("ABCADA"), equalTo(195));
//        assertThat(checkoutSolution.checkout("ABCADAA"), equalTo(245));
//        assertThat(checkoutSolution.checkout("AABAACAA"), equalTo(300));
//        assertThat(checkoutSolution.checkout("AABAACBAA"), equalTo(315));
        assertThat(checkoutSolution.checkout("AAABB"), equalTo(175));
//        assertThat(checkoutSolution.checkout("ABB"), equalTo(95));
    }

    @Test
    public void edgeCases() {
        assertThat(checkoutSolution.checkout(""), equalTo(0));
        assertThat(checkoutSolution.checkout("Z"), equalTo(-1));
        assertThat(checkoutSolution.checkout("ABCZDDD"), equalTo(-1));
    }
}

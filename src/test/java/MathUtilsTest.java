import org.example.MathUtils;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MathUtilsTest {
    @Test
    public void add_ReturnsSum_WhenGivenThreeNumbers() {
        MathUtils MathUtils = new MathUtils();
        assertEquals(12, MathUtils.add(2, 3,7));
    }

    @Test
    public void subtract_ReturnsDifference_WhenGivenTwoNumbers() {
        MathUtils MathUtils = new MathUtils();
        assertEquals(2, MathUtils.subtract(5, 3));
    }
    @Test
    public void Multiply_VerifyResultIsPositive_WhenGivenTwoNumbers() {
        MathUtils mathUtil = new MathUtils();
        int result = mathUtil.multiply(4, 3);
        assertTrue("Multiplication result should be positive", result > 0);
    }
//    @Test(expected = ArithmeticException.class)
//    public void divide_ThrowsArithmeticException_WhenDividingByZero() {
//        MathUtils.divide(10, 0);
//    }



}

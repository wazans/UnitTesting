import org.example.Calculator;
import org.example.DataService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class calculatorTestDataServiceMock {
    @Test
    public void testAdditionWithMocking() {
        DataService dataService = mock(DataService.class);
        when(dataService.getData()).thenReturn(5);

        Calculator calculator = new Calculator(dataService);
        int result = calculator.performCalculation();

        assertEquals(10, result);
    }
}

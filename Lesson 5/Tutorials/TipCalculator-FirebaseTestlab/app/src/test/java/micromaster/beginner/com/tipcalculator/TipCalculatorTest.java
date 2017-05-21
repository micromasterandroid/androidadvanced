package micromaster.beginner.com.tipcalculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Byron on 2/14/2017.
 */
public class TipCalculatorTest {

    private TipCalculator tipCalculator;

    @Before
    public void setUp() throws Exception {
        tipCalculator = new TipCalculator();
    }

    @Test
    public void calculateTip_15() throws Exception {
        Double billAmount = 100.0;
        Double tipToCalculate = 0.15;
        String totalExpected = "115.0";

        String totalAmount = tipCalculator.calculateTip(billAmount, tipToCalculate);

        //expected: 100 + (100*15%) = 115.0
        assertEquals(totalExpected, totalAmount);
    }

    @Test
    public void calculateTip_20() throws Exception {
        Double billAmount = 100.0;
        Double tipToCalculate = 0.20;
        String totalExpected = "120.0";

        String totalAmount = tipCalculator.calculateTip(billAmount, tipToCalculate);

        //expected: 100 + (100*20%) = 120.0
        assertEquals(totalExpected, totalAmount);
    }

    @Test
    public void calculateTip_30() throws Exception {
        Double billAmount = 100.0;
        Double tipToCalculate = 0.30;
        String totalExpected = "130.0";

        String totalAmount = tipCalculator.calculateTip(billAmount, tipToCalculate);

        //expected: 100 + (100*30%) = 130.0
        assertEquals(totalExpected, totalAmount);
    }

    @Test
    public void calculateTip_40() throws Exception {
        Double billAmount = 100.0;
        Double tipToCalculate = 0.40;
        String totalExpected = "140.0";

        String totalAmount = tipCalculator.calculateTip(billAmount, tipToCalculate);

        //expected: 100 + (100*40%) = 140.0
        assertEquals(totalExpected, totalAmount);
    }

    @Test
    public void parseTip() throws Exception {
        Double doubleBillExpected = 125.0;
        String stringBillValue = "125.0";

        Double billResult = tipCalculator.parseBillValue(stringBillValue);

        //expected: (String)"125.0" -> (Double)125.0
        assertEquals(doubleBillExpected, billResult);
    }

    @Test
    public void parseTip_null() throws Exception {
        Double doubleBillExpected = 125.0;
        String stringBillValue = "abc";

        Double billResult = tipCalculator.parseBillValue(stringBillValue);

        //expected: (String)"abc" -> null
        assertNull(billResult);
    }

}
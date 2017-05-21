package micromaster.beginner.com.tipcalculator;

/**
 * Created by Byron on 2/14/2017.
 */

public class TipCalculator {

    public String calculateTip(double billAmount, double tipToCalculate) {
        billAmount += billAmount * tipToCalculate;
        return Double.toString(billAmount);
    }

    public static Double parseBillValue(String tipValue) {
        try {
            return Double.parseDouble(tipValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

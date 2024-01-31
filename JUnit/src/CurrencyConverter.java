// Exchange rate last updated 13:25 CST 9/29/2023.
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.BitSet;

/**
 * Provides JUnit tests to test functionality of classes and methods.
 *
 * Note: The exchange rate for USD to EUR was last updated 13:25 CST on 9/29/2023.
 *
 * This class offers methods to convert amounts from USD to EUR and to calculate the change to be given in EUR based on item price and amount paid.
 */
public class CurrencyConverter {

    private final static BigDecimal EXCHANGE_RATE_USD_EUR = new BigDecimal("0.95"); // 1 USD = 0.95 EUR
    private final static int[] USD_VALUES = {2000, 1000, 500, 100, 25, 10, 5, 1}; // list of valid USD denominations
    private final static int[] EURO_VALUES = {2000, 1000, 500, 100, 50, 20, 10, 5, 1}; // list of valid Euro denominations

    /**
     * Converts a given array of USD bills to its equivalent value in EUR.
     *
     * @param bills An array representing the numbers of each USD bill denomination.
     * @return The total amount in USD.
     */
    public static BigDecimal usdBillsToAmount (int[] bills) {
        BigDecimal usdAmount = new BigDecimal("0");
        for (int i = 0; i < bills.length; ++i) {
            usdAmount = usdAmount.add(BigDecimal.valueOf(bills[i] * USD_VALUES[i]));
        }
        return usdAmount;
    }

    /**
     * Converts a given amount in USD to its equivalent value in EUR.
     *
     * @param usd The amount in USD to convert.
     * @return The converted amount in EUR.
     */
    public static BigDecimal convertUsdToEur (BigDecimal usd) {
        return usd.multiply(EXCHANGE_RATE_USD_EUR).multiply(new BigDecimal("0.01"));
    }

    /**
     * Converts a given array of USD bills to its equivalent in EUR.
     *
     * @param bills An array representing the number of each USD bill denomination.
     * @return The converted amount in EUR.
     */
    public static BigDecimal convertUsdToEur (int[] bills) {
        return convertUsdToEur(usdBillsToAmount(bills).multiply(new BigDecimal("0.01")));
    }

    /**
     * Calculates the change to be given in EUR based on item price and amount paid.
     *
     * This method determines the number of each EUR bill and coin denomination to be given as change.
     * If the amount paid is less than the item price, an IllegalArgumentException is thrown.
     *
     * @param itemPriceEur The price of the item in EUR.
     * @param cashPaidEur The amount paid in EUR.
     * @return An array representing the number of each EUR bill and coin denomination to be given as change.
     * @throws IllegalArgumentException if cashPaidEur is less than itemPriceEur or if itemPriceEur is negative.
     */
    public static int[] calculateChange (BigDecimal itemPriceEur, BigDecimal cashPaidEur) {
        if (cashPaidEur.doubleValue() < itemPriceEur.doubleValue()) {
            throw new IllegalArgumentException("cashPaidEur must be >= itemPriceEur");
        }

        if (itemPriceEur.doubleValue() < 0.0) {
            throw new IllegalArgumentException("itemPriceEur must be > 0.00");
        }

        BigDecimal changeOwed = cashPaidEur.subtract(itemPriceEur).multiply(new BigDecimal(100)); // subtracts the two values (in cents)
        int[] changeOwedEuros = new int[EURO_VALUES.length]; // creates an array with the same length as EURO_VALUES

        for (int i = 0; i < EURO_VALUES.length; ++i) {
            changeOwedEuros[i] = changeOwed.divide(BigDecimal.valueOf(EURO_VALUES[i]), RoundingMode.DOWN).intValue();
            changeOwed = changeOwed.subtract(BigDecimal.valueOf(changeOwedEuros[i] * EURO_VALUES[i]));
        }

        return changeOwedEuros;
    }
}

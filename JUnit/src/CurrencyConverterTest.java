import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class CurrencyConverterTest {

    @Test
    void testUsdToEur() {
        int[] usdBills = {0, 1, 0, 0, 0, 0, 0, 0}; // 1 $10 bill
        BigDecimal expectedEur = new BigDecimal("9.5"); // 10 * 0.95 = 9.50
        BigDecimal actualEur = CurrencyConverter.convertUsdToEur(usdBills);
        assertEquals(expectedEur.doubleValue(), actualEur.doubleValue());
    }

    @Test
    void testCalculateChange() {
        BigDecimal itemPriceEur = new BigDecimal("5");
        BigDecimal moneyPaidEur = new BigDecimal("20");
        int[] expectedChange = {0, 1, 1, 0, 0, 0, 0, 0, 0}; // 20, 10, 5, 1, 50, 20, 10, 5, 1
        int[] actualChange = CurrencyConverter.calculateChange(itemPriceEur, moneyPaidEur);
        assertArrayEquals(expectedChange, actualChange);
    }

    @Test
    void testCalculateChangeExact() {
        BigDecimal itemPriceEur = new BigDecimal("20");
        BigDecimal moneyPaidEur = new BigDecimal("20");
        int[] expectedChange = {0, 0, 0, 0, 0, 0, 0, 0, 0}; // 20, 10, 5, 1, 50, 20, 10, 5, 1
        int[] actualChange = CurrencyConverter.calculateChange(itemPriceEur, moneyPaidEur);
        assertArrayEquals(expectedChange, actualChange);
    }

    @Test
    void testCalculateChangeCoins() {
        BigDecimal itemPriceEur = new BigDecimal("10.35");
        BigDecimal moneyPaidEur = new BigDecimal("15");
        int[] expectedChange = {0, 0, 0, 4, 1, 0, 1, 1, 0}; // 20, 10, 5, 1, 50, 20, 10, 5, 1
        int[] actualChange = CurrencyConverter.calculateChange(itemPriceEur, moneyPaidEur);
        assertArrayEquals(expectedChange, actualChange);
    }

    @Test
    void testCalculateChangeCoinsExact() {
        BigDecimal itemPriceEur = new BigDecimal("11.36");
        BigDecimal moneyPaidEur = new BigDecimal("11.36");
        int[] expectedChange = {0, 0, 0, 0, 0, 0, 0, 0, 0}; // 20, 10, 5, 1, 50, 20, 10, 5, 1
        int[] actualChange = CurrencyConverter.calculateChange(itemPriceEur, moneyPaidEur);
        assertArrayEquals(expectedChange, actualChange);
    }

    @Test
    void testCalculateChangeNotEnough() {
        BigDecimal itemPriceEur = new BigDecimal("20");
        BigDecimal moneyPaidEur = new BigDecimal("10");
        assertThrows(IllegalArgumentException.class, ()->CurrencyConverter.calculateChange(itemPriceEur, moneyPaidEur));
    }

    @Test
    void testUsdToEuroMultipleBills() {
        int[] usdBills = {0, 2, 3, 4, 0, 0, 0, 0}; // 2 $10, 3 $5, and 4 $1
        BigDecimal expectedEur = new BigDecimal("37.05"); // $39 * 0.95 = 37.05
        BigDecimal actualEur = CurrencyConverter.convertUsdToEur(usdBills);
        assertEquals(expectedEur.doubleValue(), actualEur.doubleValue());
    }

    @Test
    void testUsdToEuroWithCoins() {
        int[] usdBills = {0, 0, 0, 0, 2, 8, 1, 13}; // 2 Quarters, 8 Dimes, 1 Nickel, and 13 Pennies
        BigDecimal expectedEur = new BigDecimal("1.406"); // $0.48 * 0.95 = 1.406
        BigDecimal actualEur = CurrencyConverter.convertUsdToEur(usdBills);
        assertEquals(expectedEur.doubleValue(), actualEur.doubleValue());
    }

    @Test
    void testUsdToEuroWithBillsAndCoins() {
        int[] usdBills = {0, 1, 2, 4, 0, 8, 0, 3}; // 1 $10, 2 $5, 4 $1, 8 Dimes, and 3 Pennies
        BigDecimal expectedEur = new BigDecimal("23.5885"); // $24.83 * 0.95 = 23.5885
        BigDecimal actualEur = CurrencyConverter.convertUsdToEur(usdBills);
        assertEquals(expectedEur.doubleValue(), actualEur.doubleValue());
    }

    @Test
    void testOnlyCoins() {
        int[] usdBills = {0, 0, 0, 0, 1, 3, 5, 7}; // 1 Quarter, 3 Dimes, 5 Nickels, 7 Pennies
        BigDecimal expectedEur = new BigDecimal("0.8265"); // $0.87 * 0.95 = 0.8265
        BigDecimal actualEur = CurrencyConverter.convertUsdToEur(usdBills);
        assertEquals(expectedEur.doubleValue(), actualEur.doubleValue());
    }

    @Test
    void testChangeCalculationWithLargeBills() {
        int[] usdBills = {8, 5, 4, 0, 0, 0, 0, 0}; // 8 $20, 5 $10, and 4 $5
        BigDecimal itemPriceEur = new BigDecimal("200");
        BigDecimal moneyPaidEur = CurrencyConverter.convertUsdToEur(usdBills);
        int[] expectedChange = {0, 1, 1, 3, 1, 0, 0, 0, 0}; // 20, 10, 5, 1, 50, 20, 10, 5, 1
        int[] actualChange = CurrencyConverter.calculateChange(itemPriceEur, moneyPaidEur);
        assertArrayEquals(expectedChange, actualChange);
    }

    @Test
    void testChangeCalculationWithNoCoins() {
        int[] usdBills = {1, 2, 0, 10, 0, 0, 0, 0}; // 8 $20, 5 $10, and 4 $5
        BigDecimal itemPriceEur = new BigDecimal("45");
        BigDecimal moneyPaidEur = CurrencyConverter.convertUsdToEur(usdBills);
        int[] expectedChange = {0, 0, 0, 2, 1, 0, 0, 0, 0}; // 20, 10, 5, 1, 50, 20, 10, 5, 1
        int[] actualChange = CurrencyConverter.calculateChange(itemPriceEur, moneyPaidEur);
        assertArrayEquals(expectedChange, actualChange);
    }

    @Test
    void testChangeCalculationWithExactCoins() {
        BigDecimal itemPriceEur = new BigDecimal("0.45");
        BigDecimal moneyPaidEur = new BigDecimal("1");
        int[] expectedChange = {0, 0, 0, 0, 1, 0, 0, 1, 0}; // 20, 10, 5, 1, 50, 20, 10, 5, 1
        int[] actualChange = CurrencyConverter.calculateChange(itemPriceEur, moneyPaidEur);
        assertArrayEquals(expectedChange, actualChange);
    }

    @Test
    void testChangeCalculationWithZeroEuros() {
        BigDecimal itemPriceEur = new BigDecimal("0");
        BigDecimal moneyPaidEur = new BigDecimal("1.20");
        int[] expectedChange = {0, 0, 0, 1, 0, 1, 0, 0, 0}; // 20, 10, 5, 1, 50, 20, 10, 5, 1
        int[] actualChange = CurrencyConverter.calculateChange(itemPriceEur, moneyPaidEur);
        assertArrayEquals(expectedChange, actualChange);
    }

    @Test
    void testZeroUsd() {
        int[] usdBills = {0, 0, 0, 0, 0, 0, 0, 0}; // 0
        BigDecimal expectedEur = new BigDecimal("0"); // 10 * 0.95 = 9.50
        BigDecimal actualEur = CurrencyConverter.convertUsdToEur(usdBills);
        assertEquals(expectedEur.doubleValue(), actualEur.doubleValue());
    }

    @Test
    void testNegativeItemPrice() {
        BigDecimal itemPriceEur = new BigDecimal("-20");
        BigDecimal moneyPaidEur = new BigDecimal("10");
        assertThrows(IllegalArgumentException.class, ()->CurrencyConverter.calculateChange(itemPriceEur, moneyPaidEur));
    }

    @Test
    void testChangeIntoLargeAmountOfCoins() {
        BigDecimal itemPriceEur = new BigDecimal("0.01");
        BigDecimal moneyPaidEur = new BigDecimal("1");
        int[] expectedChange = {0, 0, 0, 0, 1, 2, 0, 1, 4}; // 20, 10, 5, 1, 50, 20, 10, 5, 1
        int[] actualChange = CurrencyConverter.calculateChange(itemPriceEur, moneyPaidEur);
        assertArrayEquals(expectedChange, actualChange);
    }


}

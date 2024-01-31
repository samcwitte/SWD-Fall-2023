import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Main class for S4_ChangeComputation.
 *
 * This program allows a user to enter the price of an item in Euros (EUR) and the amount the customer paid (in USD).
 * It then calculates and displays the change owed back in EUR.
 */

public class Main {

    /**
     * The main method for the program.
     *
     * The user is prompted to enter the price of an item in Euros and the amount paid in various USD denominations.
     * The program then calculates the change to be given in Euros and displays it.
     *
     * @param args (not used).
     */
    public static void main(String[] args) {
        String[] EuroNames = {"20 Euro bills", "10 Euro bills", "5 Euro bills", "1 Euro bills", "50 cent Euro coins", "20 cent Euro coins", "10 cent Euro coins", "5 cent Euro coins", "1 cent Euro coins"};
        String[] UsdNames = {"20 Dollar bills", "10 Dollar bills", "5 Dollar bills", "1 Dollar bills", "Quarters", "Dimes", "Nickels", "Pennies"};
        Scanner scnr = new Scanner(System.in);
        int[] usdPaid = {0, 0, 0, 0, 0, 0, 0, 0};

        System.out.println("Enter the price of the item in Euros: ");
        BigDecimal itemPriceEur = scnr.nextBigDecimal();

        for (int i = 0; i < UsdNames.length; ++i) {
            System.out.print("Enter the amount of " + UsdNames[i] + " paid with: ");
            usdPaid[i] = scnr.nextInt();
        }
        BigDecimal cashPaidUsd = CurrencyConverter.usdBillsToAmount(usdPaid);

        BigDecimal cashPaidEur = CurrencyConverter.convertUsdToEur(cashPaidUsd);

        int[] changeInEuros = CurrencyConverter.calculateChange(itemPriceEur, cashPaidEur);

        System.out.println("Change to be given: €" + cashPaidEur.subtract(itemPriceEur));
        for (int i = 0; i < changeInEuros.length; ++i) {
            System.out.println(EuroNames[i] + ": " + changeInEuros[i]);
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

public class BinaryToDecimalConverter {

    public static void main(String[] args) {
        BinaryToDecimalConverter conv = new BinaryToDecimalConverter();
        conv.start();
    }

    private void start() {
        try {
            String userInput = getInput();
            new BinaryChecker().checkIfBinary(userInput);
            if (new BigInteger(userInput).compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) { // if the number is too big, use the algorithm that can handle it
                BigInteger number = new BigInteger(userInput);
                System.out.println(convertBinaryToDecimalBigInteger(number));
            } else {
                long number = Long.parseLong(userInput);
                System.out.println(convertBinaryToDecimal(number));
            }
        } catch (NullPointerException ex) {
            System.out.println("type a number next time, jackass.");
        } catch (NumberFormatException ex) {
            System.out.println("type a number next time, jackass.");
        } catch (NotABinaryNumberException ex) {
            System.out.println("not a binary number.");
        }
    }

    /**
     * gives ya a string of the input.
     * might return a null
     */

    private static String getInput() {
        String inputLine = null;
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            inputLine = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return inputLine;
    }

    /**
     * converts a binary number into decimal using base-q expansion representation of the number.
     * only works with numbers that fit into a long variable.
     *
     * @param number a binary number to be converted
     */
    private static long convertBinaryToDecimal(long number) {
        ArrayList<Long> list = new ArrayList<>(); // a list of separate digits of the number being converted
        LinkedList<Long> stack = new LinkedList<>(); // a stack used to put the digits onto the list in the right order after separating them

        while (number > 0) { // get separate digits of the number, put them on a stack
            stack.push(number % 10);
            number = number / 10;
        }

        while (!stack.isEmpty()) { // put digits onto the list in correct order
            list.add(stack.pop());
        }

        long decimalNumber = 0;
        int digits = list.size() - 1;
        for (long item : list) { // actual conversion
            decimalNumber += item << digits;
            digits--;
        }
        return decimalNumber;
    }

    /**
     * converts a binary number into decimal using base-q expansion representation of the number.
     * works with numbers of any size.
     *
     * @param number a binary number to be converted
     */
    private static BigInteger convertBinaryToDecimalBigInteger(BigInteger number) {
        ArrayList<BigInteger> list = new ArrayList<>(); // a list of separate digits of the number being converted
        LinkedList<BigInteger> stack = new LinkedList<>(); // a stack used to put the digits onto the list in the right order after separating them

        while (number.compareTo(BigInteger.valueOf(0)) > 0) { // get separate digits of the number, put them on a stack
            BigInteger digit = number.mod(BigInteger.valueOf(10));
            stack.push(digit);
            number = number.divide(BigInteger.valueOf(10));
        }

        while (!stack.isEmpty()) { // put digits onto the list in correct order
            list.add(stack.pop());
        }

        BigInteger decimalNumber = BigInteger.valueOf(0);
        int digits = list.size() - 1;
        BigInteger base = BigInteger.valueOf(2);
        for (BigInteger item : list) {  // action conversion
            BigInteger powerOfTwo = base.pow(digits);
            decimalNumber = decimalNumber.add(item.multiply(powerOfTwo));
            digits--;
        }
        return decimalNumber;
    }
}
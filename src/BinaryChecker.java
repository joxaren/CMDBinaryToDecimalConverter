import java.util.Arrays;

public class BinaryChecker {

    public void checkIfBinary(String input) throws NotABinaryNumberException {
        int[] numArr = toIntArray(input);
        boolean isNotBinaryNumber = searchForFirstElementGreaterThan(numArr, 1);
        if (isNotBinaryNumber) {
            throw new NotABinaryNumberException( input + "");
        }
    }

    /**
     * searches a sorted int [] array for the first element greater than @param target.
     * returns true if it finds such an element, and returns false if it doesn't
     *
     * @param array  an int [] array
     * @param target target number
     */
    private boolean searchForFirstElementGreaterThan(int[] array, int target) {
        int low = 0;
        int high = array.length;
        while (low != high) {
            int mid = (low + high) / 2;
            if (array[mid] <= target) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * turns a string into an int [] array and sorts it.
     * can't detect if the string contains characters
     * so beware
     *
     * @param s the string to be turned into an array
     */
    private static int[] toIntArray(String s) {
        int[] num = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            num[i] = s.charAt(i) - '0';
        }
        Arrays.sort(num);
        return num;
    }
}
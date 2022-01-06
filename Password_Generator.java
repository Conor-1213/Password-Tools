import java.util.ArrayList;
import java.util.List;

public class Password_Generator {

    public static int lengthGenerator() {
        int passwordLength = (int) (Math.random() * (20 - 12) + 12);
        return passwordLength;
    }

    public static int indexGenerator(int passwordLength) {
        int index = (int) (Math.random() * ((passwordLength)));
        return index;
    }

    public static int quantityGenerator(int min, int max) {
        int qty = (int) (Math.random() * ((max - min)) + min);
        return qty;
    }

    public static boolean isSpecialIndex(int i, int numOfIndices, int[] specialIndices) {
        for (int j = 0; j < numOfIndices; j++) {
            if (i == specialIndices[j]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int passwordLength = lengthGenerator();
        int numOfNum = quantityGenerator(1, 3);
        int numOfSpecChar = quantityGenerator(1, 3);
        int numOfChar = passwordLength - numOfNum - numOfSpecChar;
        int numOfUpperCase = quantityGenerator(0, 3);
        int[] whereUpperCase = new int[numOfUpperCase];

        char c = 0;
        String s;
        String[] specChars = {"!", ",", ";", "$", "*", ":"};
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List<String> password = new ArrayList<>();
        String passwordString = "";

        for (int i = 0; i < numOfUpperCase; i++) {
            whereUpperCase[i] = indexGenerator(numOfChar);
        }

        for (int i = 0; i < numOfChar; i++) {
            c = (char) quantityGenerator(97, 122);
            if (isSpecialIndex(i, numOfUpperCase, whereUpperCase)) {
                s = String.valueOf(c);
                password.add(s.toUpperCase());
            } else {
                password.add(String.valueOf(c));
            }
        }

        for (int i = 0; i < numOfNum; i++) {
            password.add(indexGenerator(numOfChar), nums[quantityGenerator(0, 9)]);
        }

        for (int i = 0; i < numOfSpecChar; i++) {
            password.add(indexGenerator(numOfChar + numOfNum), specChars[quantityGenerator(0, 5)]);
        }

        for (int i = 0; i < passwordLength; i++) {
            passwordString = passwordString + password.get(i);
        }

        System.out.println(passwordString);
    }
}

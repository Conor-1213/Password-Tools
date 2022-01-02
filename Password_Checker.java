import java.util.InputMismatchException;
import java.util.Scanner;

public class Password_Checker {

    public static int getReqLength() {
        Scanner scan = new Scanner(System.in);
        System.out.println("How long must the password be?");
        try {
            int reqLength = scan.nextInt();
            return reqLength;
        } catch (InputMismatchException e) {
            System.out.println("Something went wrong. Please enter an integer value.");
            return getReqLength();
        }
    }

    public static String getSpecChars() {
        Scanner scan = new Scanner(System.in);
        System.out.println("One of which special characters must the password include? Please enter them with no spaces in between.");
        String specChars = scan.nextLine();
        return specChars;
    }

    public static boolean getNumReq() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Must the password include numbers? Please enter yes or no.");
        String response = scan.nextLine();
        boolean isYes = response.equalsIgnoreCase("yes");
        boolean isNo = response.equalsIgnoreCase("no");
        if (!isYes & !isNo) {
            System.out.println("Something went wrong.");
            getNumReq();
        } else {
            if (response.equalsIgnoreCase("yes")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean getCaseReq() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Must the password include at least one uppercase letter? Please enter yes or no.");
        String response = scan.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            return true;
        } else if (response.equalsIgnoreCase("no")) {
            return false;
        } else {
            System.out.println("Something went wrong.");
            return getCaseReq();
        }
    }

    public static String getPassword() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your password.");
        String password = scan.nextLine();
        int passwordLength = password.length();
        //Vets Password Here -- Cannot Contain Spaces, Would Complicate Later Code
        for (int i = 0; i < passwordLength; i++) {
            String passwordSub = password.substring(i, i + 1);
            if (passwordSub.equals(" ")) {
                System.out.println("Your password must not contain spaces.");
                getPassword();
            }
        }
        return password;
    }

    public static void checkLength(String password, int reqLength) {
        int length = password.length();
        if (length < reqLength) {
            System.out.println("Your password is too short.");
        } else {
            System.out.println("Your password meets the length requirement.");
        }
    }

    public static boolean isNum(String passwordSub) {
        try {
            int intVal = Integer.parseInt(passwordSub);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean hasNum(boolean[] whereNum, int length) {
        for (int i = 0; i < length; i++) {
            if (whereNum[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean allNum(boolean[] whereNum, int length) {
        for (int i = 0; i < length; i++) {
            if (!(whereNum[i])) {
                return false;
            }
        }
        return true;
    }

    public static void checkNum(String password, boolean numReq) {
        if (numReq) {
            int length = password.length();
            boolean[] whereNum = new boolean[length];
            for (int i = 0; i <= length - 1; i++) {
                whereNum[i] = isNum(password.substring(i, i + 1));
            }

            boolean hasNum = hasNum(whereNum, length);
            boolean allNum = allNum(whereNum, length);

            if (allNum) {
                System.out.println("Your password must not contain only numbers.");
            } else if (hasNum) {
                System.out.println("Your password meets the number requirement.");
            } else {
                System.out.println("Your password must contain at least one number.");
            }
        } else {
            System.out.print("");
        }
    }

    public static boolean isSpecChar(String passwordSub, String specChars) {
        String[] specCharsAr = toArray(specChars);
        boolean isSpecChar = false;

        for (int j = 0; j < specChars.length(); j++) {
            if (passwordSub.equalsIgnoreCase(specCharsAr[j])) {
                isSpecChar = true;
                return isSpecChar;
            }
        }

        return isSpecChar;
    }

    public static boolean isLetter(String passwordSub, String specChars) {
        boolean isNum = Character.isDigit(passwordSub.charAt(0));

        boolean isSpecChar = isSpecChar(passwordSub, specChars);

        if (!isNum & !isSpecChar) {
            return true;
        } else {
            return false;
        }
    }

    public static void checkCase(String password, boolean caseReq, String specChars) {
        if (caseReq) {
            int length = password.length();
            boolean hasCase = false;
            for (int i = 0; i < length; i++) {
                String passwordSub = password.substring(i, i + 1);
                boolean isLetter = isLetter(passwordSub, specChars);

                if ((passwordSub.equals(passwordSub.toUpperCase()) & isLetter)) {
                    hasCase = true;
                    break;
                }
            }
            if (hasCase) {
                System.out.println("Your password meets the case requirement.");
            } else {
                System.out.println("Your password must contain at least one uppercase letter.");
            }
        } else {
            System.out.print("");
        }
    }

    public static String[] toArray(String specChars) {
        int specCharsLength = specChars.length();
        String[] specCharsAr = new String[specCharsLength];

        for (int i = 0; i < specCharsLength; i++) {
            specCharsAr[i] = specChars.substring(i, i + 1);
        }

        return specCharsAr;
    }

    public static void checkSpecChars(String password, String specChars) {
        if (specChars.equals("")) {
            System.out.print("");
        } else {
            String[] specCharsAr = toArray(specChars);
            int passLength = password.length();
            int specCharsLength = specChars.length();

            boolean hasSpecChar = false;
            for (int i = 0; i < passLength; i++) {
                String passwordSub = password.substring(i, i + 1);
                for (int j = 0; j < specCharsLength; j++) {
                    if (passwordSub.equals(specCharsAr[j])) {
                        hasSpecChar = true;
                        break;
                    }
                }
            }

            if (hasSpecChar) {
                System.out.println("Your password meets the special character requirement.");
            } else {
                System.out.println("Your password must contain at least one special character.");
            }

        }
    }

    public static void main(String[] args) {
        int reqLength = getReqLength();
        boolean numReq = getNumReq();
        boolean caseReq = getCaseReq();
        String specChars = getSpecChars();

        String password = getPassword();

        checkLength(password, reqLength);
        checkNum(password, numReq);
        checkCase(password, caseReq, specChars);
        checkSpecChars(password, specChars);
    }
}


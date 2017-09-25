package com.mifinity.util;

/**
 * Class util for Credit/Debit card
 * @author juliocesaradias
 */

public class CCUtils {
	
	public static final int INVALID = -1;
    public static final int VISA = 0;
    public static final int MASTERCARD = 1;
    public static final int AMERICAN_EXPRESS = 2;
    public static final int DINERS_CLUB = 4;

    private static final String[] cardNames = { "Visa", "Mastercard",
            "American Express", "Diner's CLub", };

    public static boolean validCC(String number) throws Exception {
        if (getCardID(number) != -1)
            return validCCNumber(number);
        return false;
    }

    public static int getCardID(String number) {
        int valid = INVALID;

        String digit1 = number.substring(0, 1);
        String digit2 = number.substring(0, 2);
        String digit3 = number.substring(0, 3);

        if (isNumber(number)) {
            /**
             * VISA
             */
        		if (digit1.equals("4")) {
                if (number.length() == 13 || number.length() == 16)
                    valid = VISA;
            }
            /**
             * MASTER
             */
            else if (digit2.compareTo("51") >= 0 && digit2.compareTo("55") <= 0) {
                if (number.length() == 16)
                    valid = MASTERCARD;
            }
            /**
             * AMEX
             */
            else if (digit2.equals("34") || digit2.equals("37")) {
                if (number.length() == 15)
                    valid = AMERICAN_EXPRESS;
            }
			/**
			 * DINERS
			 */
            else if (digit2.equals("36")
                    || digit2.equals("38")
                    || (digit3.compareTo("300") >= 0 && digit3.compareTo("305") <= 0)) {
                if (number.length() == 14)
                    valid = DINERS_CLUB;
            }
        }
        return valid;
    }

    public static boolean isNumber(String n) {
        try {
            double d = Double.parseDouble(n);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getCardName(int id) {
        return (id > -1 && id < cardNames.length ? cardNames[id] : "");
    }

    public static boolean validCCNumber(String n) {
        try {
            int j = n.length();

            String[] s1 = new String[j];
            for (int i = 0; i < n.length(); i++)
                s1[i] = "" + n.charAt(i);

            int checksum = 0;

            for (int i = s1.length - 1; i >= 0; i -= 2) {
                int k = 0;

                if (i > 0) {
                    k = Integer.valueOf(s1[i - 1]).intValue() * 2;
                    if (k > 9) {
                        String s = "" + k;
                        k = Integer.valueOf(s.substring(0, 1)).intValue()
                                + Integer.valueOf(s.substring(1)).intValue();
                    }
                    checksum += Integer.valueOf(s1[i]).intValue() + k;
                } else
                    checksum += Integer.valueOf(s1[0]).intValue();
            }
            return ((checksum % 10) == 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
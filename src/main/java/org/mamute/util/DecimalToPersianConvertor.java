package org.mamute.util;

public class DecimalToPersianConvertor {
    public static String decimalToPersian(String number) {
        char[] arabicChars = {'٠', '١', '٢', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};
        StringBuilder builder = new StringBuilder();


        for (int i = 0; i < number.length(); i++) {
            if (Character.isDigit(number.charAt(i))) {
                builder.append(arabicChars[(int) (number.charAt(i)) - 48]);
            } else {
                builder.append(number.charAt(i));
            }
        }
        return builder.toString();
    }

}

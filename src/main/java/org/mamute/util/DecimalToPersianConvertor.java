package org.mamute.util;

public class DecimalToPersianConvertor {
    public static String decimalToPersian(String str) {
        if (str == null) return "";
        String number = str.replaceAll("[^0-9]", "");

        char[] arabicChars = {'٠', '١', '٢', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};
        StringBuilder builder = new StringBuilder();


        for (int i = 0; i < number.length(); i++) {
            if (Character.isDigit(number.charAt(i))) {
                builder.append(arabicChars[(int) (number.charAt(i)) - 48]);
            } else {
                builder.append(number.charAt(i));
            }
        }
        return str.replaceAll("[0-9]+", builder.toString());
    }

}

package kz.nkoldassov.nerdapi.util;

public class RandomStrUtil {

    public static String generateStr(int size) {
        String alphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

        StringBuilder sb = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            int ch = (int)(alphaNumericStr.length() * Math.random());
            sb.append(alphaNumericStr.charAt(ch));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateStr(64));
    }
}

package Online.JavaLearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String id_str = br.readLine();
        String regex_arabic = "\\h*[0-9]{1,2}\\h* [\\+\\-\\*\\/] \\h*[0-9]{1,2}\\h*";
        String regex_roman = "\\h*(I|II|III|IV|V|VI|VII|VIII|IX|X)\\h* [\\+\\-\\*\\/] \\h*(I|II|III|IV|V|VI|VII|VIII|IX|X)\\h*";
        boolean arabic = Pattern.matches(regex_arabic, id_str);
        boolean roman = Pattern.matches(regex_roman, id_str);

        try {
            if (arabic == false && roman == false) {
                throw new MyException();
            } else {
                ArrayList<String> id_list = Misc.getList(id_str,roman);
                Misc.calc(id_list);
            }
        }
        catch (MyException exc) {
            System.out.println(exc);
        }
    }
}

class Misc {
    public static String romanToArabic(String roman) {
        String num;
        switch (roman) {
            case ("I"):
                num = "1";
                break;
            case ("II"):
                num = "2";
                break;
            case ("III"):
                num = "3";
                break;
            case ("IV"):
                num = "4";
                break;
            case ("V"):
                num = "5";
                break;
            case ("VI"):
                num = "6";
                break;
            case ("VII"):
                num = "7";
                break;
            case ("VIII"):
                num = "8";
                break;
            case ("IX"):
                num = "9";
                break;
            case ("X"):
                num = "10";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + roman);
        }
        return num;
    }

    public static void calc(ArrayList<String> arr) {
        int num1 = Integer.parseInt(arr.get(0));
        int num2 = Integer.parseInt(arr.get(2));
        String op = arr.get(1);
        switch (op) {
            case ("+"):
                System.out.println(num1 + num2);
                break;
            case ("-"):
                System.out.println(num1 - num2);
                break;
            case ("*"):
                System.out.println(num1 * num2);
                break;
            case ("/"):
                System.out.println((double) num1 / num2);
                break;

        }
    }

    public static ArrayList<String> getList(String id_str, boolean isRoman) {
        String[] id_arr = id_str.split(" ");
        ArrayList<String> id_list = new ArrayList<>();
        for (String id_el : id_arr) if (!id_el.equals("")) id_list.add(id_el);
        if (isRoman) {
            id_list.set(0,romanToArabic(id_list.get(0)));
            id_list.set(2,romanToArabic(id_list.get(2)));
        }
        return id_list;
    }
}

class MyException extends Exception {
    public String toString() {
        return "Incorrect input";
    }
}

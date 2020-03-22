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
                Misc.calc(id_list,roman);
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

    public static void calc(ArrayList<String> arr, boolean isRoman) {
        int num1 = Integer.parseInt(arr.get(0));
        int num2 = Integer.parseInt(arr.get(2));
        String op = arr.get(1);
        switch (op) {
            case ("+"):
                System.out.println(Convert.convert(num1 + num2,isRoman));
                break;
            case ("-"):
                System.out.println(Convert.convert(num1 - num2,isRoman));
                break;
            case ("*"):
                System.out.println(Convert.convert(num1 * num2,isRoman));
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


class Convert{

    static String convert(int i, boolean isRoman) {
        String rom="";
        if (!isRoman) rom = Integer.toString(i);
        else{
            int cel=0;
            int ost=0;
            if (i==100) rom="C";
            else if (i>50){
                rom="L";
                cel=i/10-5;
                ost=i%10;
                if (cel<=3) rom += Append(cel,"X");
                else rom="XC";
                rom = Append_Edinicy(rom,ost);
            }
            else if (i==50) rom="L";
            else if (i>10){
                cel=i/10;
                ost=i%10;
                if (cel<=3) rom += Append(cel,"X");
                else rom="XL";
                rom = Append_Edinicy(rom,ost);
            }
            else if (i==10) rom="X";
            else{
                ost=i;
                rom = Append_Edinicy(rom,ost);
            }
        }

        return rom;
    }

    static String Append(int cel,String sym){
        String rom="";
        for (int i=1;i<=cel;i++) rom += sym;
        return rom;
    }

    static String Append_Edinicy(String rom, int ost){
        if (ost<=3) rom += Append(ost,"I");
        else if (ost==4) rom += "IV";
        else if (ost==5) rom += "V";
        else if (ost<9) rom += "V" + Append(ost-5,"I");
        else rom += "IX";
        return rom;
    }
}
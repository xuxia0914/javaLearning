package cn.leetcode.xux.general.midium;

/**
 * 给你一个整数，把它转化为罗马数字，输入保证在1到3999之间。
 *
 * 关于罗马数字介绍：
 *  1、计数方法：① 罗马数字就有下面七个基本符号：Ⅰ（1）、Ⅴ（5）、Ⅹ（10）、L（50）、C（100）、D（500）、M（1000）。
 *              ② 相同的数字连写，所表示的数等于这些数字相加得到的数，如：Ⅲ = 3；XX=20；CC=200；MMM=3000；
 *              ③ 小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数， 如：Ⅷ = 8；Ⅻ = 12；
 *              ④ 小的数字，（限于Ⅰ、X 和C）在大的数字的左边，所表示的数等于大数减小数得到的数，如：Ⅳ= 4；Ⅸ= 9；
 *              ⑤  正常使用时连写的数字重复不得超过三次；
 */
public class IntToRomanNum {

    public static String solution1(int num) {
        String res = "";
        char[] roman = new char[]{'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int[] value = new int[]{1000, 500, 100, 50, 10, 5, 1};
        for (int n = 0; n < 7; n += 2) {
            int x = num / value[n];
            if (x < 4) {
                for (int i = 1; i <= x; ++i) res += roman[n];
            } else if (x == 4) {
                res = res + roman[n] + roman[n - 1];
            } else if (x > 4 && x < 9) {
                res += roman[n - 1];
                for (int i = 6; i <= x; ++i) res += roman[n];
            } else if (x == 9) {
                res = res + roman[n] + roman[n - 2];
            }
            num %= value[n];
        }
        return res;
    }

    public static String solution2(int num) {
        String res = "";
        int[] val = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] str = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < val.length; ++i) {
            while (num >= val[i]) {
                num -= val[i];
                res += str[i];
            }
        }
        return res;
    }

    public static String solution3(int num) {
        String[] thou = new String[]{"", "M", "MM", "MMM"};
        String[] hun = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] ten = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] sing = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thou[num / 1000] + hun[(num % 1000) / 100] + ten[(num % 100) / 10] + sing[num % 10];
    }

}

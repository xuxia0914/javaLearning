package cn.leetcode.xux.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 * For example, the above binary watch reads "3:25".
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
 * Example:
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * Note:
 * The order of output does not matter.
 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
public class BinaryWatch {

    public static List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        if(num<0||num>8) {
            return res;
        }
        int j;
        List<Integer> upNums = new ArrayList<>();
        List<Integer> bottomNums = new ArrayList<>();
        for(int i=0;i<4&i<=num;i++) {
            j = num-i;
            if(j>5) {
                continue;
            }
            if(j<0) {
                break;
            }
            upNums = ups.get(i);
            bottomNums = bottoms.get(j);
            for(int m : upNums) {
                for(int n : bottomNums) {
                    res.add(m + ":" + (n<10?"0":"") + n);
                }
            }
        }
        return res;
    }

    static List<List<Integer>> ups = new ArrayList<>();
    static List<List<Integer>> bottoms = new ArrayList<>();
    static{
        for(int i=0;i<4;i++) {
            List<Integer> list = new ArrayList<>();
            ups.add(list);
        }
        int n1, tmp1;
        for(int i=0;i<12;i++) {
            n1=0;
            tmp1=i;
            while(tmp1>0) {
                tmp1 = tmp1 & (tmp1-1);
                n1++;
            }
            ups.get(n1).add(i);
        }

        for(int i=0;i<6;i++) {
            List<Integer> list = new ArrayList<>();
            bottoms.add(list);
        }
        int n2, tmp2;
        for(int i=0;i<60;i++) {
            n2=0;
            tmp2=i;
            while(tmp2>0) {
                tmp2 = tmp2 & (tmp2-1);
                n2++;
            }
            bottoms.get(n2).add(i);
        }
    }

    public static void main(String[] args) {
        System.out.println(readBinaryWatch(1));
    }

}

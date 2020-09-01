package cn.xux.algorithm.leetcode.vip.midium;

import java.util.Arrays;

/**
 * 681. Next Closest Time 下一个最近时间点
 * Given a time represented in the format "HH:MM",
 * form the next closest time by reusing the current digits.
 * There is no limit on how many times a digit can be reused.
 *
 * You may assume the given input string is always valid.
 * For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 *
 * Example 1:
 *
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39,
 * which occurs 5 minutes later.  It is not 19:33,
 * because this occurs 23 hours and 59 minutes later.
 * Example 2:
 *
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
 * It may be assumed that the returned time is next day's time since it is
 * smaller than the input time numerically.
 */
public class NextClosestTime {

    public static void main(String[] args) {
        System.out.println(new NextClosestTime().nextClosestTime("19:34"));
    }

    public String nextClosestTime(String time) {
        int[] digits = new int[]{
                time.charAt(0)-'0',
                time.charAt(1)-'0',
                time.charAt(3)-'0',
                time.charAt(4)-'0'
        };
        Arrays.sort(digits);
        String[] strs = time.split(":");
        int min = Integer.parseInt(strs[1]);
        int hour = Integer.parseInt(strs[0]);
        int bigger = findBigger(digits, min%10);
        if(bigger!=-1) {
            return strs[0]+":"+(min/10)+digits[bigger];
        }
        bigger = findBigger(digits, min/10);
        if(bigger!=-1&&digits[bigger]<6) {
            return strs[0]+":"+digits[bigger]+digits[0];
        }
        bigger = findBigger(digits, hour%10);
        if(bigger!=-1&&hour/10*10+digits[bigger]<24) {
            return ""+hour/10+digits[bigger]+":"+digits[0]+digits[0];
        }
        bigger = findBigger(digits, hour/10);
        if(bigger!=-1&&digits[bigger]*10+digits[0]<24) {
            return ""+digits[bigger]*10+digits[0]+":"+digits[0]+digits[0];
        }
        return ""+digits[0]+digits[0]+":"+digits[0]+digits[0];
    }

    private int findBigger(int[] digits, int tar) {
        for(int i=0;i<digits.length;i++) {
            if(digits[i]>tar) {
                return i;
            }
        }
        return -1;
    }

}

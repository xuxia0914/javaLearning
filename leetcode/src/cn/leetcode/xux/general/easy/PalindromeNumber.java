package cn.leetcode.xux.general.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 * Example 1:
 * Input: 121
 * Output: true
 * Example 2:
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 * Follow up:
 * Coud you solve it without converting the integer to a string?
 */
public class PalindromeNumber {

    public static boolean solution(Integer num) {
        if(num<0) {
            return false;
        }
        List<Integer> list = new ArrayList<Integer>();
        int i=1, j=num/i;  //除数和商
        while(j>0) {
            list.add(j%10);
            j=j/10;
        }
        for(int k=0;k<list.size()/2;k++) {
            if(list.get(k)!=list.get(list.size()-1-k)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /*System.out.println(solution(121));
        System.out.println(solution(1221));
        System.out.println(solution(21214));
        System.out.println(solution(-121));*/
        System.out.println(solution(1410110141));
    }

}

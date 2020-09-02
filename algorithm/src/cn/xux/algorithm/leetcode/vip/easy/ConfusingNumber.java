package cn.xux.algorithm.leetcode.vip.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 1056 易混淆数
 * 给定一个数字 N，当它满足以下条件的时候返回 true：
 * 原数字旋转 180° 以后可以得到新的数字。
 * 如 0, 1, 6, 8, 9 旋转 180° 以后，得到了新的数字 0, 1, 9, 8, 6 。
 * 2, 3, 4, 5, 7 旋转 180° 后，得到的不是数字。
 * 易混淆数 (confusing number) 在旋转180°以后，
 * 可以得到和原来不同的数，
 * 且新数字的每一位都是有效的。
 *
 * 示例
 * 输入：6
 * 输出：true
 * 解释：把 6 旋转 180° 以后得到 9，9 是有效数字且 9!=6 。
 *
 * 输入：89
 * 输出：true
 * 解释: 把 89 旋转 180° 以后得到 68，86 是有效数字且 86!=89 。
 *
 * 输入：11
 * 输出：false
 * 解释：把 11 旋转 180° 以后得到 11，11 是有效数字但是值保持不变，所以 11 不是易混淆数字。
 *
 * 输入：25
 * 输出：false
 * 解释：把 25 旋转 180° 以后得到的不是数字。
 *
 * 提示
 * 0 <= N <= 10^9
 * 可以忽略掉旋转后得到的前导零，例如，如果我们旋转后得到 0008 那么该数字就是 8 。
 */
public class ConfusingNumber {

    public static boolean confusingNumber(int N) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        map.put(1,1);
        map.put(6,9);
        map.put(8,8);
        map.put(9,6);
        String s = String.valueOf(N);
        int left = 0;
        int right = s.length()-1;
        boolean ans = false;
        while(left<right) {
            int l = s.charAt(left)-'0';
            int r = s.charAt(right)-'0';
            if(!map.containsKey(l)||!map.containsKey(r)) {
                return false;
            }
            if(map.get(l)!=r) {
                ans = true;
            }
        }
        if(left==right&&(s.charAt(left)-'0'==6||s.charAt(left)-'0'==9)) {
            ans = true;
        }
        return ans;
    }

}

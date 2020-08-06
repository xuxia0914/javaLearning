package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 * Example 1:
 * Input: 1
 * Output: true
 * Example 2:
 * Input: 10
 * Output: false
 * Example 3:
 * Input: 16
 * Output: true
 * Example 4:
 * Input: 24
 * Output: false
 * Example 5:
 * Input: 46
 * Output: true
 * Note:
 * 1 <= N <= 10^9
 */
public class ReorderedPowerOfTwo {
    /**
     * Runtime: 3 ms, faster than 31.46% of Java online submissions for Reordered Power of 2.
     * Memory Usage: 34.3 MB, less than 11.81% of Java online submissions for Reordered Power of 2.
     * @param N
     * @return
     */
    public boolean reorderedPowerOf2(int N) {
        Map<Integer, List<char[]>> map = new HashMap<>();
        for(int i=0;i<31;i++) {
            String s = String.valueOf((int)Math.pow(2, i));
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            List<char[]> list = map.getOrDefault(cs.length, new ArrayList<char[]>());
            list.add(cs);
            map.put(cs.length, list);
        }

        String s = String.valueOf(N);
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        List<char[]> list = map.getOrDefault(cs.length, new ArrayList<char[]>());
        for(char[] tmp : list) {
            if(Arrays.equals(tmp, cs)) {
                return true;
            }
        }
        return false;
    }

}

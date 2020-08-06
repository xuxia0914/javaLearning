package cn.xux.algorithm.leetcode.general.hard;

import java.util.*;

/**
 * 600. 不含连续1的非负整数
 * 给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
 *
 * 示例 1:
 * 输入: 5
 * 输出: 5
 * 解释:
 * 下面是带有相应二进制表示的非负整数<= 5：
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * 其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。
 * 说明: 1 <= n <= 109
 */
public class NonNegativeIntegersWithoutConsecutiveOnes {

    public static void main(String[] args) {
        System.out.println(new NonNegativeIntegersWithoutConsecutiveOnes()
                .findIntegers(4));
    }

    Map<String, Integer> mem = new HashMap<>();

    public int findIntegers(int num) {
        List<Integer> list = new ArrayList<>();
        while(num>0) {
            list.add(num%2);
            num /= 2;
        }
        int[] arr = new int[list.size()];
        for(int i=0;i<list.size();i++) {
            arr[i] = list.get(list.size()-1-i);
        }
        return findIntegers(arr, 0, 0, false);
    }

    private int findIntegers(int[] arr, int idx, int pre, boolean less) {
        if(idx==arr.length) {
            return 1;
        }
        String key = idx+"#"+pre+"#"+less;
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        int ans = findIntegers(arr, idx+1, 0, less || arr[idx] == 1);
        if(pre==0&&(arr[idx]==1||less)) {
            ans +=  findIntegers(arr, idx+1, 1, less);
        }
        mem.put(key, ans);
        return ans;
    }

    //TLE
    public int findIntegers1(int num) {
        dfs(0, 0, 0, num);
        return dfs(0, 0, 0, num);
    }

    private int dfs(int curr, int bit, int pre, int num) {
        if(bit==32||(int)Math.pow(2, bit)>num) {
            if(curr<=num) {
                return 1;
            }else {
                return 0;
            }
        }
        String key = curr+"#"+bit+"#"+pre;
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        int ans = dfs(curr, bit+1, 0, num);
        if(pre==0) {
            ans += dfs(curr+(int)Math.pow(2, bit), bit+1, 1, num);
        }
        mem.put(key, ans);
        return ans;
    }

}

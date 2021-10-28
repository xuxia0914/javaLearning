package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 869. 重新排序得到 2 的幂
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 *
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：1
 * 输出：true
 * 示例 2：
 *
 * 输入：10
 * 输出：false
 * 示例 3：
 *
 * 输入：16
 * 输出：true
 * 示例 4：
 *
 * 输入：24
 * 输出：false
 * 示例 5：
 *
 * 输入：46
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= N <= 10^9
 */
public class ReorderedPowerOfTwo {

    static List[] map = new List[11];

    static {
        for(int i=0;i<31;i++) {
            int curr = 1<<i;
            int x = String.valueOf(curr).length();
            if(map[x]==null) {
                map[x] = new ArrayList<int[]>();
            }
            int[] cnt = new int[10];
            while(curr>0) {
                cnt[curr%10]++;
                curr /= 10;
            }
            map[x].add(cnt);
        }
    }

    public boolean reorderedPowerOf2(int n) {
        int x = String.valueOf(n).length();
        int[] cnt = new int[10];
        while(n>0) {
            cnt[n%10]++;
            n /= 10;
        }
        for(int[] tar : (List<int[]>)map[x]) {
            boolean flag = true;
            for(int i=0;i<10;i++) {
                if(cnt[i]!=tar[i]) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                return true;
            }
        }
        return false;
    }

}

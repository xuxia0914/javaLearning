package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 967. 连续差相同的数字
 * 返回所有长度为 N 且满足其每两个连续位上的数字之间的差的绝对值为 K 的非负整数。
 * 请注意，除了数字 0 本身之外，答案中的每个数字都不能有前导零。
 * 例如，01 因为有一个前导零，所以是无效的；但 0 是有效的。
 * 你可以按任何顺序返回答案。
 *
 * 示例 1：
 * 输入：N = 3, K = 7
 * 输出：[181,292,707,818,929]
 * 解释：注意，070 不是一个有效的数字，因为它有前导零。
 *
 * 示例 2：
 * 输入：N = 2, K = 1
 * 输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 * 提示：
 * 1 <= N <= 9
 * 0 <= K <= 9
 */
public class NumbersWithSameConsecutiveDifferences {

    List<Integer> result = new ArrayList<>();

    public int[] numsSameConsecDiff(int N, int K) {
        if(N==1) {
            result.add(0);
        }
        for(int i=1;i<=9;i++) {
            helper(i, (int)Math.pow(10, N-1), K);
        }
        int[] ans = new int[result.size()];
        for(int i=0;i<ans.length;i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }

    public void helper(int curr, int min, int k) {
        if(curr>=min) {
            result.add(curr);
            return;
        }
        int last = curr;
        while(last>=10) {
            last %= 10;
        }
        if(last-k>=0) {
            helper(curr*10+last-k, min, k);
        }
        if(k!=0&&last+k<10) {
            helper(curr*10+last+k, min, k);
        }
    }

}

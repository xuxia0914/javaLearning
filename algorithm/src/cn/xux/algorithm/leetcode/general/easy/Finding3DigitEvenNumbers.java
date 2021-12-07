package cn.xux.algorithm.leetcode.general.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 2094. 找出 3 位偶数
 * 给你一个整数数组 digits ，其中每个元素是一个数字（0 - 9）。数组中可能存在重复元素。
 * <p>
 * 你需要找出 所有 满足下述条件且 互不相同 的整数：
 * <p>
 * 该整数由 digits 中的三个元素按 任意 顺序 依次连接 组成。
 * 该整数不含 前导零
 * 该整数是一个 偶数
 * 例如，给定的 digits 是 [1, 2, 3] ，整数 132 和 312 满足上面列出的全部条件。
 * <p>
 * 将找出的所有互不相同的整数按 递增顺序 排列，并以数组形式返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = [2,1,3,0]
 * 输出：[102,120,130,132,210,230,302,310,312,320]
 * 解释：
 * 所有满足题目条件的整数都在输出数组中列出。
 * 注意，答案数组中不含有 奇数 或带 前导零 的整数。
 * 示例 2：
 * <p>
 * 输入：digits = [2,2,8,8,2]
 * 输出：[222,228,282,288,822,828,882]
 * 解释：
 * 同样的数字（0 - 9）在构造整数时可以重复多次，重复次数最多与其在 digits 中出现的次数一样。
 * 在这个例子中，数字 8 在构造 288、828 和 882 时都重复了两次。
 * 示例 3：
 * <p>
 * 输入：digits = [3,7,5]
 * 输出：[]
 * 解释：
 * 使用给定的 digits 无法构造偶数。
 * 示例 4：
 * <p>
 * 输入：digits = [0,2,0,0]
 * 输出：[200]
 * 解释：
 * 唯一一个不含 前导零 且满足全部条件的整数是 200 。
 * 示例 5：
 * <p>
 * 输入：digits = [0,0,0]
 * 输出：[]
 * 解释：
 * 构造的所有整数都会有 前导零 。因此，不存在满足题目条件的整数。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 */
public class Finding3DigitEvenNumbers {

    public int[] findEvenNumbers(int[] digits) {
        int[] cnt = new int[10];
        for (int d : digits) cnt[d]++;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++)
            if (cnt[i] > 0) {
                cnt[i]--;
                for (int j = 0; j < 10; j++)
                    if (cnt[j] > 0) {
                        cnt[j]--;
                        for (int k = 0; k < 10; k += 2) if (cnt[k] > 0)
                            list.add(i * 100 + j * 10 + k);
                        cnt[j]++;
                    }
                cnt[i]++;
            }
        int n = list.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) ans[i] = list.get(i);
        return ans;
    }

}

package cn.xux.algorithm.leetcode.general.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1505. 最多 K 次交换相邻数位后得到的最小整数
 * 给你一个字符串 num 和一个整数 k 。
 * 其中，num 表示一个很大的整数，字符串中的每个字符依次对应整数上的各个 数位 。
 * 你可以交换这个整数相邻数位的数字 最多 k 次。
 * 请你返回你能得到的最小整数，并以字符串形式返回。
 * <p>
 * 示例 1：
 * 输入：num = "4321", k = 4
 * 输出："1342"
 * 解释：4321 通过 4 次交换相邻数位得到最小整数的步骤如上图所示。
 * <p>
 * 示例 2：
 * 输入：num = "100", k = 1
 * 输出："010"
 * 解释：输出可以包含前导 0 ，但输入保证不会有前导 0 。
 * <p>
 * 示例 3：
 * 输入：num = "36789", k = 1000
 * 输出："36789"
 * 解释：不需要做任何交换。
 * <p>
 * 示例 4：
 * 输入：num = "22", k = 22
 * 输出："22"
 * <p>
 * 示例 5：
 * 输入：num = "9438957234785635408", k = 23
 * 输出："0345989723478563548"
 * <p>
 * 提示：
 * 1 <= num.length <= 30000
 * num 只包含 数字 且不含有 前导 0 。
 * 1 <= k <= 10^9
 */
public class MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits {

    public static void main(String[] args) {
        System.out.println(new MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits()
                // .minInteger("4321", 4));    // 1342
                .minInteger("294984148179", 11));    // "124498948179"
    }

    // 贪心 + 线段树(记录当前位置的数字向前移动的次数)
    public String minInteger(String num, int k) {
        int n = num.length();
        Queue<Integer>[] bucket = new Queue[10];
        for (int i = 0; i < 10; i++) {
            bucket[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            bucket[num.charAt(i) - '0'].offer(i);
        }
        // 使用线段树存储每个区间的元素向前移动的总次数
        BIT bit = new BIT(n);
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (bucket[j].isEmpty() || bucket[j].peek() - i > k) {
                    continue;
                }
                int pos = bucket[j].peek();
                // 查询当前位置之后的数字有多少个移动到了当前位置的前面
                // 即当前数字已经向后移动了几位
                int move = bit.query(pos);
                // pos + move即为当前元素的实际位置
                if (pos + move - i > k) {
                    continue;
                }
                // 符合条件，把当前元素移动到目标位置i
                // 然后更新k， bit 和 bucket，执行下一个目标位置
                ans[i] = (char) (j + '0');
                k -= pos + move - i;
                bit.update(pos);
                bucket[j].poll();
                break;
            }
        }
        return new String(ans);
    }

    class BIT {

        int[] tree;
        int n;

        public BIT(int n) {
            this.n = n;
            this.tree = new int[n << 1];
        }

        public void update(int x) {
            int curr = x + n;
            while (curr > 0) {
                tree[curr]++;
                curr >>= 1;
            }
        }

        public int query(int x) {
            int ans = 0;
            int left = x + n;
            int right = (n << 1) - 1;
            while (left <= right) {
                if ((left & 1) == 1) {
                    ans += tree[left++];
                }
                if ((right & 1) == 0) {
                    ans += tree[right--];
                }
                left >>= 1;
                right >>= 1;
            }
            return ans;
        }

    }

    //TLE
    public String minInteger1(String num, int k) {
        String ans = num;
        int len = num.length();
        int idx = 0;
        while (k > 0 && idx < len) {
            if (isDesc(ans)) {
                return ans;
            }
            int minIdx = idx;
            char min = ans.charAt(minIdx);
            for (int i = 1; i <= k && idx + i < len; i++) {
                if (ans.charAt(idx + i) < min) {
                    minIdx = idx + i;
                    min = ans.charAt(idx + i);
                }
            }
            if (minIdx != idx) {
                ans = ans.substring(0, idx) + ans.charAt(minIdx)
                        + ans.substring(idx, minIdx) + ans.substring(minIdx + 1);
                k -= minIdx - idx;
            }
            idx++;
        }
        return ans;
    }

    private boolean isDesc(String num) {
        for (int i = 1; i < num.length(); i++) {
            if (num.charAt(i) > num.charAt(i - 1)) {
                return false;
            }
        }
        return false;
    }

}

package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1802. 有界数组中指定下标处的最大值
 * 给你三个正整数 n、index 和 maxSum 。
 * 你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
 * nums.length == n
 * nums[i] 是 正整数 ，其中 0 <= i < n
 * abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
 * nums 中所有元素之和不超过 maxSum
 * nums[index] 的值被 最大化
 * 返回你所构造的数组中的 nums[index] 。
 * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
 *
 * 示例 1：
 * 输入：n = 4, index = 2,  maxSum = 6
 * 输出：2
 * 解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
 *
 * 示例 2：
 * 输入：n = 6, index = 1,  maxSum = 10
 * 输出：3
 *
 * 提示：
 * 1 <= n <= maxSum <= 109
 * 0 <= index < n
 */
public class MaximumValueAtAGivenIndexInABoundedArray {

    public int maxValue(int n, int index, int maxSum) {
        int l = 1;
        int r = maxSum-n+1;
        while(l<r) {
            int mid = (l+r+1)/2;
            if(check(n, index, maxSum, mid)) {
                l = mid;
            }else {
                r = mid-1;
            }
        }
        return l;
    }

    /**
     * 用贪心算法算出当结果为mid时，数组的最小和sum，再比较sum和maxSum判断mid是否符合条件
     * 要求最小和，数组的形式有两种(只展示index左边部分，右半部分同理)：
     * 第一种: 1,1...1,2,3,4...value
     * 第二种：x(x>1),x+1,x+2...value
     * 再分别用等差数列求和算出数组的和
     */
    private boolean check(int n, int index, int maxSum, int mid) {
        int left = mid-index;
        long sum = 0;
        if(left<1) {
            // 第一种情况
            sum += ((long)1+mid)*mid/2+index-mid+1;
        }else {
            // 第二种情况
            sum += ((long)left+mid)*(index+1)/2;
        }
        int right = mid-n+1+index;
        if(right<1) {
            sum += ((long)1+mid)*mid/2+(n-index-mid);
        }else {
            sum += ((long)right+mid)*(n-index)/2;
        }
        // mid加了两次要减掉一次再比较
        return sum-mid<=maxSum;
    }

    /**
     * 解题思路
     *     过程模拟，从index开始往两边扩充：维护一个[l,r]范围，
     *     每次往范围内每个位置+1，通过这种方式维护一个向上生长的“三角形”。
     *     时间复杂度O(logM)，其中M为maxSum-n。
     *     空间复杂度O(1)。
     */
    public int maxValue1(int n, int index, int maxSum) {
        int l = index, r = index;
        int ans = 1;
        // 整个数组一开始全部填充为1，
        // rest记录先全部填充1后，剩下1的个数
        int rest = maxSum - n;
        while (l > 0 || r < n - 1) {
            int len = r - l + 1;
            if (rest >= len) {
                // 当前[l,r]范围全部+1
                rest -= len;
                ans++;
                // 往左右两边扩
                l = Math.max(0, l - 1);
                r = Math.min(n - 1, r + 1);
            } else {
                break;
            }
        }
        // 扩大到整个数组之后，剩余的值“雨露均沾”一下
        ans += rest / n;
        return ans;
    }

}

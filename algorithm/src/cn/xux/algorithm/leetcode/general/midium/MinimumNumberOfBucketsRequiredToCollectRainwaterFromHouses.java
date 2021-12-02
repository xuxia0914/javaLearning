package cn.xux.algorithm.leetcode.general.midium;

/**
 * 2086. 从房屋收集雨水需要的最少水桶数
 * 给你一个下标从 0 开始的字符串 street 。
 * street 中每个字符要么是表示房屋的 'H' ，要么是表示空位的 '.' 。
 * <p>
 * 你可以在 空位 放置水桶，从相邻的房屋收集雨水。
 * 位置在 i - 1 或者 i + 1 的水桶可以收集位置为 i 处房屋的雨水。
 * 一个水桶如果相邻两个位置都有房屋，那么它可以收集 两个 房屋的雨水。
 * <p>
 * 在确保 每个 房屋旁边都 至少 有一个水桶的前提下，请你返回需要的 最少 水桶数。
 * 如果无解请返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：street = "H..H"
 * 输出：2
 * 解释：
 * 我们可以在下标为 1 和 2 处放水桶。
 * "H..H" -> "HBBH"（'B' 表示放置水桶）。
 * 下标为 0 处的房屋右边有水桶，下标为 3 处的房屋左边有水桶。
 * 所以每个房屋旁边都至少有一个水桶收集雨水。
 * 示例 2：
 * <p>
 * 输入：street = ".H.H."
 * 输出：1
 * 解释：
 * 我们可以在下标为 2 处放置一个水桶。
 * ".H.H." -> ".HBH."（'B' 表示放置水桶）。
 * 下标为 1 处的房屋右边有水桶，下标为 3 处的房屋左边有水桶。
 * 所以每个房屋旁边都至少有一个水桶收集雨水。
 * 示例 3：
 * <p>
 * 输入：street = ".HHH."
 * 输出：-1
 * 解释：
 * 没有空位可以放置水桶收集下标为 2 处的雨水。
 * 所以没有办法收集所有房屋的雨水。
 * 示例 4：
 * <p>
 * 输入：street = "H"
 * 输出：-1
 * 解释：
 * 没有空位放置水桶。
 * 所以没有办法收集所有房屋的雨水。
 * 示例 5：
 * <p>
 * 输入：street = "."
 * 输出：0
 * 解释：
 * 没有房屋需要收集雨水。
 * 所以需要 0 个水桶。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= street.length <= 105
 * street[i] 要么是 'H' ，要么是 '.' 。
 */
public class MinimumNumberOfBucketsRequiredToCollectRainwaterFromHouses {

    public static void main(String[] args) {
        System.out.println(new MinimumNumberOfBucketsRequiredToCollectRainwaterFromHouses()
                .minimumBuckets(".H.H."));
    }

    public int minimumBuckets(String street) {
        int n = street.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (street.charAt(i) == 'H') {
                if (i + 1 < n && street.charAt(i + 1) == '.') {
                    ++ans;
                    // 直接跳过后续的两个位置
                    i += 2;
                } else if (i - 1 >= 0 && street.charAt(i - 1) == '.') {
                    ++ans;
                } else {
                    return -1;
                }
            }
        }
        return ans;
    }

}

package cn.xux.algorithm.leetcode.general.hard;

import java.util.PriorityQueue;

/**
 * 786. 第 K 个最小的素数分数
 * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。
 * 数组 arr 由 1 和若干 素数  组成，且其中所有整数互不相同。
 * <p>
 * 对于每对满足 0 < i < j < arr.length 的 i 和 j ，
 * 可以得到分数 arr[i] / arr[j] 。
 * <p>
 * 那么第 k 个最小的分数是多少呢?
 * 以长度为 2 的整数数组返回你的答案,
 * 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,2,3,5], k = 3
 * 输出：[2,5]
 * 解释：已构造好的分数,排序后如下所示:
 * 1/5, 1/3, 2/5, 1/2, 3/5, 2/3
 * 很明显第三个最小的分数是 2/5
 * 示例 2：
 * <p>
 * 输入：arr = [1,7], k = 1
 * 输出：[1,7]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= arr.length <= 1000
 * 1 <= arr[i] <= 3 * 104
 * arr[0] == 1
 * arr[i] 是一个 素数 ，i > 0
 * arr 中的所有数字 互不相同 ，且按 严格递增 排序
 * 1 <= k <= arr.length * (arr.length - 1) / 2
 */
public class KThSmallestPrimeFraction {

    public static void main(String[] args) {
        new KThSmallestPrimeFraction().kthSmallestPrimeFraction(
                new int[]{1,2,3,5}, 3
        );
    }

    // 时间 nlogk  空间 k
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        double left = 0.0;
        double right = 1.0;
        while (left < right) {
            double mid = (left + right) / 2;
            // cnt 统计arr中分数小于mid的组合数
            int cnt = 0;
            // 使用双指针统计，i为左指针，j为右指针
            int i = 0;
            int j = 1;
            // 记录小于mid的最大分数对应的分子x和分母y
            int x = 0;
            int y = 1;
            while (j < n) {
                // i跳转到第一个使arr[i]/arr[j]>=mid的位置
                while (i < j && (double) arr[i] / arr[j] < mid) {
                    i++;
                }
                if (i > 0 && (double) arr[i - 1] / arr[j] > (double) x / y) {
                    x = arr[i - 1];
                    y = arr[j];
                }
                if((cnt=cnt+i)>k) {
                    break;
                }
                j++;
            }
            if(cnt<k) {
                left = mid;
            }else if(cnt>k) {
                right = mid;
            }else {
                return new int[]{x, y};
            }
        }
        return null;
    }

    // 时间 nlogk  空间 k
    public int[] kthSmallestPrimeFraction1(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (o1, o2) -> o1[1] * o2[0] - o1[0] * o2[1]
        );
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                queue.offer(new int[]{arr[i], arr[j]});
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }
        return queue.poll();
    }

}

package cn.xux.algorithm.leetcode.general.hard;

import java.util.Arrays;

/**
 * 2071. 你可以安排的最多任务数目
 * 给你 n 个任务和 m 个工人。每个任务需要一定的力量值才能完成，
 * 需要的力量值保存在下标从 0 开始的整数数组 tasks 中，
 * 第 i 个任务需要 tasks[i] 的力量才能完成。
 * 每个工人的力量值保存在下标从 0 开始的整数数组 workers 中，
 * 第 j 个工人的力量值为 workers[j] 。每个工人只能完成 一个 任务，
 * 且力量值需要 大于等于 该任务的力量要求值（即 workers[j] >= tasks[i] ）。
 * <p>
 * 除此以外，你还有 pills 个神奇药丸，可以给 一个工人的力量值 增加 strength 。
 * 你可以决定给哪些工人使用药丸，但每个工人 最多 只能使用 一片 药丸。
 * <p>
 * 给你下标从 0 开始的整数数组tasks 和 workers 以及两个整数 pills 和 strength ，
 * 请你返回 最多 有多少个任务可以被完成。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
 * 输出：3
 * 解释：
 * 我们可以按照如下方案安排药丸：
 * - 给 0 号工人药丸。
 * - 0 号工人完成任务 2（0 + 1 >= 1）
 * - 1 号工人完成任务 1（3 >= 2）
 * - 2 号工人完成任务 0（3 >= 3）
 * 示例 2：
 * <p>
 * 输入：tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5
 * 输出：1
 * 解释：
 * 我们可以按照如下方案安排药丸：
 * - 给 0 号工人药丸。
 * - 0 号工人完成任务 0（0 + 5 >= 5）
 * 示例 3：
 * <p>
 * 输入：tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength = 10
 * 输出：2
 * 解释：
 * 我们可以按照如下方案安排药丸：
 * - 给 0 号和 1 号工人药丸。
 * - 0 号工人完成任务 0（0 + 10 >= 10）
 * - 1 号工人完成任务 1（10 + 10 >= 15）
 * 示例 4：
 * <p>
 * 输入：tasks = [5,9,8,5,9], workers = [1,6,4,2,6], pills = 1, strength = 5
 * 输出：3
 * 解释：
 * 我们可以按照如下方案安排药丸：
 * - 给 2 号工人药丸。
 * - 1 号工人完成任务 0（6 >= 5）
 * - 2 号工人完成任务 2（4 + 5 >= 8）
 * - 4 号工人完成任务 3（6 >= 5）
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == tasks.length
 * m == workers.length
 * 1 <= n, m <= 5 * 10^4
 * 0 <= pills <= m
 * 0 <= tasks[i], workers[j], strength <= 109
 */
public class MaximumNumberOfTasksYouCanAssign {

    public static void main(String[] args) {
        System.out.println(new MaximumNumberOfTasksYouCanAssign().maxTaskAssign(
                new int[]{5181, 2717, 7678, 7730, 5931, 8066, 2266, 5873, 3645, 6636, 3308, 2848, 2082, 7158, 5398, 4030, 4942, 1723, 6614, 5165, 8086, 7526, 9503, 2051, 5305, 6606, 7514, 5078, 1149, 5782, 4717, 5969, 4966, 1292, 4370, 3863, 4111, 1140, 2980, 5295, 5347, 8700, 2833, 6750, 2352, 7604, 6305, 2697, 7501, 7719, 7955, 7901, 1779, 6850, 6456, 1040, 9230, 2712, 8129, 9875, 9385, 1814, 8167, 2960, 9191, 3588, 7339, 2255, 5314, 2873, 3294, 5375, 6745, 5984, 9717, 4983, 2558, 8075, 7988, 6490, 4499, 7236, 2097, 8097, 2923, 2972, 8609, 8993, 6354, 6502, 3340, 1666, 1281, 9703, 8869, 5274, 8150, 5270, 3437, 3171, 7423, 5865, 1995, 7002, 8550, 9908, 7114, 8777, 1250, 5855, 3501, 9316, 5380, 3877},
                new int[]{2167, 4646, 1582, 1102, 2113, 1258, 4341, 3193, 3136, 4096, 3311, 1501, 3499, 1815, 1282, 4914, 772, 4785, 2632, 1223, 3479, 3010, 3505, 1613, 4257, 1192, 2918, 2664, 4274, 4036, 1039, 1250, 4713, 3443, 4514, 4117, 3400, 3825, 1782, 3552, 2386, 865, 2290, 3618, 793, 1297, 908, 2187, 3273, 4531, 3859, 605, 4274, 3951, 583, 1135, 2802, 3585, 727, 2359, 4011, 4071, 2035, 4775, 764, 4702, 2050, 3304, 3876, 3772, 4946, 4371, 1993, 4746, 1124, 1221, 1368, 831, 2337, 506, 951, 3874, 3094, 2744, 4258, 4704, 3229, 1015, 4876, 1893, 3098, 4464, 4189, 4201, 3986, 3673, 4126, 2424, 4280, 2780, 1748, 1650, 1591, 753, 3392, 2498, 835, 608, 1746, 1243, 3778, 1382, 4207, 1909, 832, 4501, 781, 1274, 973, 4966, 1873, 2512, 3644, 3244, 1120, 4979, 3945, 1481, 2172, 4410, 3572, 4597, 3414, 4306, 4714, 4047, 3239, 4557, 3226, 3273, 4997, 3374},
                139, 2075
        ));
    }

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int left = 0;
        int right = Math.min(tasks.length, workers.length);
        Arrays.sort(tasks);
        Arrays.sort(workers);
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(tasks, workers, pills, strength, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[] ts, int[] ws, int p, int s, int x) {
        boolean[] finished = new boolean[x];
        int i = 0;
        for (int j = ws.length - x; j < ws.length; j++) {
            while (finished[i]) {
                i++;
            }
            if (ts[i] > ws[j]) {
                if (p == 0 || ts[i] > ws[j] + s) {
                    return false;
                }
                p--;
                int curr = ws[j] + s;
                int left = i;
                int right = x - 1;
                while (left < right) {
                    int mid = (left + right + 1) >> 1;
                    if (ts[mid] > curr) {
                        right = mid - 1;
                    } else {
                        left = mid;
                    }
                }
                while (finished[left]) {
                    left--;
                }
                if (finished[left]) {
                    return false;
                }
                finished[left] = true;
            } else {
                i++;
            }
        }
        return true;
    }

}
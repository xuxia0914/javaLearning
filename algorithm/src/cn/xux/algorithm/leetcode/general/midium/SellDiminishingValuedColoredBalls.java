package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 1648. 销售价值减少的颜色球
 * 你有一些球的库存 inventory ，里面包含着不同颜色的球。
 * 一个顾客想要 任意颜色 总数为 orders 的球。
 * 这位顾客有一种特殊的方式衡量球的价值：每个球的价值是目前剩下的 同色球 的数目。
 * 比方说还剩下 6 个黄球，那么顾客买第一个黄球的时候该黄球的价值为 6 。
 * 这笔交易以后，只剩下 5 个黄球了，所以下一个黄球的价值为 5 （也就是球的价值随着顾客购买同色球是递减的）
 * 给你整数数组 inventory ，其中 inventory[i] 表示第 i 种颜色球一开始的数目。
 * 同时给你整数 orders ，表示顾客总共想买的球数目。你可以按照 任意顺序 卖球。
 * 请你返回卖了 orders 个球以后 最大 总价值之和。
 * 由于答案可能会很大，请你返回答案对 109 + 7 取余数 的结果。
 *
 * 示例 1：
 * 输入：inventory = [2,5], orders = 4
 * 输出：14
 * 解释：卖 1 个第一种颜色的球（价值为 2 )，卖 3 个第二种颜色的球（价值为 5 + 4 + 3）。
 * 最大总和为 2 + 5 + 4 + 3 = 14 。
 *
 * 示例 2：
 * 输入：inventory = [3,5], orders = 6
 * 输出：19
 * 解释：卖 2 个第一种颜色的球（价值为 3 + 2），卖 4 个第二种颜色的球（价值为 5 + 4 + 3 + 2）。
 * 最大总和为 3 + 2 + 5 + 4 + 3 + 2 = 19 。
 *
 * 示例 3：
 * 输入：inventory = [2,8,4,10,6], orders = 20
 * 输出：110
 *
 * 示例 4：
 * 输入：inventory = [1000000000], orders = 1000000000
 * 输出：21
 * 解释：卖 1000000000 次第一种颜色的球，
 * 总价值为 500000000500000000 。
 * 500000000500000000 对 109 + 7 取余为 21 。
 *
 * 提示：
 * 1 <= inventory.length <= 105
 * 1 <= inventory[i] <= 109
 * 1 <= orders <= min(sum(inventory[i]), 109)
 */
public class SellDiminishingValuedColoredBalls {

    public static void main(String[] args) {
        System.out.println(new SellDiminishingValuedColoredBalls().maxProfit(
//                new int[]{2,5}, 4
//                new int[]{2,8,4,10,6}, 20
                new int[]{565259708,715164401,716563713,958255469,844600740,823949511,180479359,287829385,164248818,73361150,230686692,322986846,598720034,338241127,748922260,181241085,833659853,509571179,250093451,690995620,703292727,595636202},
                650114768
        ));
    }

    public int maxProfit(int[] inventory, int orders) {
        int mod = 1000000007;
        if(inventory==null||inventory.length==0) {
            return 0;
        }
        int n = inventory.length;
        Arrays.sort(inventory);
        // 最大值（数量最多的球的位置）
        int maxIdx = n-1;
        while(maxIdx>0&&inventory[maxIdx-1]==inventory[maxIdx]) {
            maxIdx--;
        }
        long ans = 0;
        while(orders>0) {
            // 当前最大值
            int max = inventory[maxIdx];
            // 最大值的个数
            int cnt = n-maxIdx;
            // 次最大值
            int secondMax = maxIdx==0?0:inventory[maxIdx-1];
            // 当前最大值到下一个最大值可以拿掉的球的个数
            long canTake = ((long)max-secondMax)*cnt;
            if(canTake>orders) {
                // 如果能拿掉的球的个数大于等于目标个数
                // 每个最大值要减掉的个数
                int commonTake = orders/cnt;
                // 剩下的最大值要减掉的个数
                int leftTake = orders%cnt;
                // 用等差数列公式计算可以得到公共部分的分数
                ans = (ans+sum(max,max-commonTake+1)*cnt)%mod;
                max -= commonTake;
                ans = (ans+(long)leftTake*max)%mod;
                orders = 0;
            }else {
                // 如果可以拿掉的球的总个数小于需要拿掉的球
                // 求可拿掉的球的总价值
                ans = (ans+sum(max,secondMax+1)*cnt)%mod;
                // 在下一个最大值位置
                maxIdx--;
                while(maxIdx>0&&inventory[maxIdx-1]==inventory[maxIdx]) {
                    maxIdx--;
                }
                // 计算剩余需要拿掉的球
                orders -= canTake;
            }
        }
        return (int)ans;
    }

    public long sum(int max, int min) {
        return ((long)max+min)*(max-min+1)/2;
    }

}

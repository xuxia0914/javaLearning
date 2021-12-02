package cn.xux.algorithm.leetcode.general.hard;

/**
 * 458. 可怜的小猪
 * 有 buckets 桶液体，其中 正好 有一桶含有毒药，其余装的都是水。
 * 它们从外观看起来都一样。为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，
 * 通过观察猪是否会死进行判断。不幸的是，
 * 你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。
 *
 * 喂猪的规则如下：
 * 选择若干活猪进行喂养
 * 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
 * 小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。
 * 在这段时间里，你只能观察，而不允许继续喂猪。
 * 过了 minutesToDie 分钟后，
 * 所有喝到毒药的猪都会死去，其他所有猪都会活下来。
 * 重复这一过程，直到时间用完。
 * 给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，
 * 返回在规定时间内判断哪个桶有毒所需的 最小 猪数。
 *
 * 示例 1：
 * 输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60
 * 输出：5
 *
 * 示例 2：
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 15
 * 输出：2
 *
 * 示例 3：
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 30
 * 输出：2
 *
 * 提示：
 * 1 <= buckets <= 1000
 * 1 <= minutesToDie <= minutesToTest <= 100
 */
public class PoorPigs {

    // 经过所有实验，一只小猪能有多少种状态？第一轮就死、第二轮死、...、第n轮死，以及生还，所以一共有n + 1种状态
    // 假设有m只小猪，将所有小猪的所有状态看做一个（n+1）进制数，最终可以有m^(n+1)种状态
    // 最佳方案 是 每一种状态 决定了 某一个桶 装的是毒药
    // 所以要确定buckets个桶中哪一桶是毒药，至少需要 log(n+1)^buckets
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int states = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(states));
    }

}

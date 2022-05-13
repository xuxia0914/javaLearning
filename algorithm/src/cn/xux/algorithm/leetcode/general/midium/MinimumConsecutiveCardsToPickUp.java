package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 2260. 必须拿起的最小连续卡牌数
 * 给你一个整数数组 cards ，
 * 其中 cards[i] 表示第 i 张卡牌的 值 。
 * 如果两张卡牌的值相同，则认为这一对卡牌 匹配 。
 * <p>
 * 返回你必须拿起的最小连续卡牌数，
 * 以使在拿起的卡牌中有一对匹配的卡牌。
 * 如果无法得到一对匹配的卡牌，返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：cards = [3,4,2,3,4,7]
 * 输出：4
 * 解释：拿起卡牌 [3,4,2,3] 将会包含一对值为 3 的匹配卡牌。注意，拿起 [4,2,3,4] 也是最优方案。
 * 示例 2：
 * <p>
 * 输入：cards = [1,0,5,3]
 * 输出：-1
 * 解释：无法找出含一对匹配卡牌的一组连续卡牌。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= cards.length <= 105
 * 0 <= cards[i] <= 106
 */
public class MinimumConsecutiveCardsToPickUp {

    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cards.length; i++) {
            int pre = map.getOrDefault(cards[i], -1);
            if (pre != -1 && i - pre + 1 < ans) {
                ans = i - pre + 1;
            }
            map.put(cards[i], i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}

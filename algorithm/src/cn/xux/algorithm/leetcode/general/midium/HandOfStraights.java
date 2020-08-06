package cn.xux.algorithm.leetcode.general.midium;

import java.util.TreeMap;

/**
 * 846. 一手顺子
 * 爱丽丝有一手（hand）由整数数组给定的牌。
 * 现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。
 * 如果她可以完成分组就返回 true，否则返回 false。
 *
 * 示例 1：
 * 输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
 * 输出：true
 * 解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 *
 * 示例 2：
 * 输入：hand = [1,2,3,4,5], W = 4
 * 输出：false
 * 解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。
 *
 * 提示：
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 */
public class HandOfStraights {

    public static void main(String[] args) {
        System.out.println(new HandOfStraights().isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3));
    }

    public boolean isNStraightHand(int[] hand, int W) {
        if(hand==null||hand.length%W!=0) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num : hand) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        while(map.size()>0) {
            int start = map.firstKey();
            for(int i=0;i<W;i++) {
                int target = start+i;
                if(map.containsKey(target)) {
                    if(map.get(start+i)==1) {
                        map.remove(target);
                    }else {
                        map.put(target, map.get(target)-1);
                    }
                }else {
                    return false;
                }
            }
        }
        return true;
    }

}

package cn.xux.algorithm.leetcode.vip.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 294. 翻转游戏 II（记忆化递归）
 * 你和朋友玩一个叫做「翻转游戏」的游戏，
 * 游戏规则：给定一个只有 + 和 - 的字符串。
 * 你和朋友轮流将 连续 的两个 "++" 反转成 "--"。
 * 当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。
 * 请你写出一个函数来判定起始玩家是否存在必胜的方案。
 *
 * 示例：
 * 输入: s = "++++"
 * 输出: true
 * 解析: 起始玩家可将中间的 "++" 翻转变为 "+--+" 从而得胜。
 *
 * 延伸：请推导你算法的时间复杂度。
 */
public class FlipGameII {

    Map<String, Boolean> mem = new HashMap<>();

    public boolean canWin(String s) {
        if(s==null||s.length()<2) {
            return false;
        }
        if(mem.containsKey(s)) {
            return mem.get(s);
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        for(int i=0;i<n-1;i++) {
            if(chars[i]=='+'&&chars[i+1]=='+') {
                chars[i] = '-';
                chars[i+1] = '-';
                if(!canWin(new String(chars))) {
                    mem.put(s, true);
                    return true;
                }
                chars[i] = '+';
                chars[i+1] = '+';
            }
        }
        mem.put(s, false);
        return false;
    }

}

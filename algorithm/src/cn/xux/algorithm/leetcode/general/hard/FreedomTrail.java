package cn.xux.algorithm.leetcode.general.hard;

import java.util.*;

/**
 * 514. 自由之路
 * 视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
 * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。
 * 您需要算出能够拼写关键词中所有字符的最少步数。
 * 最初，ring 的第一个字符与12:00方向对齐。
 * 您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * 您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。
 * 旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。
 * 按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 *
 * 示例：
 * 输入: ring = "godding", key = "gd"
 * 输出: 4
 * 解释:
 *  对于 key 的第一个字符 'g'，已经在要1步正确的位置, 我们只需来拼写这个字符。
 *  对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 *  当然, 我们还需要1步进行拼写。
 *  因此最终的输出是 4。
 *
 * 提示：
 * ring 和 key 的字符串长度取值范围均为 1 至 100；
 * 两个字符串中都只有小写字符，并且均可能存在重复字符；
 * 字符串 key 一定可以由字符串 ring 旋转拼出。
 */
public class FreedomTrail {

    //dp
    public int findRotateSteps(String ring, String key) {
        List<Integer>[] charIdxs = new List[26];
        for(int i=0;i<ring.length();i++) {
            if(charIdxs[ring.charAt(i)-'a']==null) {
                charIdxs[ring.charAt(i)-'a'] = new ArrayList<>();
            }
            charIdxs[ring.charAt(i)-'a'].add(i);
        }
        int lenRing = ring.length();
        int lenKey = key.length();
        int[] dp = new int[lenRing];
        for(int idx : charIdxs[key.charAt(0)-'a']) {
            dp[idx] = Math.min(idx, lenRing-idx)+1;
        }
        for(int i=1;i<lenKey;i++) {
            int[] newDp = new int[lenRing];
            for(int j=0;j<lenRing;j++) {
                if(dp[j]!=0) {
                    for(int idx : charIdxs[key.charAt(i)-'a']) {
                        int dis = Math.abs(j-idx);
                        int currStep = Math.min(dis, lenRing-dis)+1+dp[j];
                        newDp[idx] = newDp[idx]==0?currStep:Math.min(newDp[idx], currStep);
                    }
                }
            }
            dp = newDp;
        }
        int ans = Integer.MAX_VALUE;
        for(int step : dp) {
            if(step!=0) {
                ans = Math.min(ans, step);
            }
        }
        return ans;
    }

    //记忆化递归
    public int findRotateSteps1(String ring, String key) {
        List<Integer>[] charIdxs = new List[26];
        for(int i=0;i<ring.length();i++) {
            if(charIdxs[ring.charAt(i)-'a']==null) {
                charIdxs[ring.charAt(i)-'a'] = new ArrayList<>();
            }
            charIdxs[ring.charAt(i)-'a'].add(i);
        }
        return dfs(key, 0, 0, charIdxs, ring.length());
    }

    Map<String, Integer> mem = new HashMap<>();

    public int dfs(String key, int idx, int preIdx, List<Integer>[] charIdxs, int lenRing) {
        if(idx==key.length()) {
            return 0;
        }
        String k = idx+"#"+preIdx;
        if(mem.containsKey(k)) {
            return mem.get(k);
        }
        int ans = Integer.MAX_VALUE;
        for(int nextIdx : charIdxs[key.charAt(idx)-'a']) {
            int postAns = dfs(key, idx+1, nextIdx, charIdxs, lenRing);
            int dis = Math.abs(preIdx-nextIdx);
            ans = Math.min(ans, postAns+Math.min(dis, lenRing-dis)+1);
        }
        mem.put(k, ans);
        return ans;
    }

}

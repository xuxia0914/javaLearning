package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 948. 令牌放置
 * 你的初始能量为 P，初始分数为 0，只有一包令牌。
 * 令牌的值为 token[i]，每个令牌最多只能使用一次，可能的两种使用方法如下：
 * 如果你至少有 token[i] 点能量，可以将令牌置为正面朝上，失去 token[i] 点能量，并得到 1 分。
 * 如果我们至少有 1 分，可以将令牌置为反面朝上，获得 token[i] 点能量，并失去 1 分。
 * 在使用任意数量的令牌后，返回我们可以得到的最大分数。
 *
 * 示例 1：
 * 输入：tokens = [100], P = 50
 * 输出：0
 *
 * 示例 2：
 * 输入：tokens = [100,200], P = 150
 * 输出：1
 *
 * 示例 3：
 * 输入：tokens = [100,200,300,400], P = 200
 * 输出：2
 *
 * 提示：
 * tokens.length <= 1000
 * 0 <= tokens[i] < 10000
 * 0 <= P < 10000
 */
public class BagOfTokens {

    public int bagOfTokensScore(int[] tokens, int P) {
        if(tokens==null||tokens.length==0) {
            return 0;
        }
        Arrays.sort(tokens);
        if(P<tokens[0]) {
            return 0;
        }
        int left = 0;
        int right = tokens.length-1;
        int score = 0;
        int result = 0;
        while(left<=right&&P>=tokens[left]) {
            while(left<=right&&P>=tokens[left]) {
                P -= tokens[left++];
                score++;
            }
            result = Math.max(result, score);
            if(left<=right&&score>0) {
                P += tokens[right--];
                score--;
            }
        }
        return result;
    }

}

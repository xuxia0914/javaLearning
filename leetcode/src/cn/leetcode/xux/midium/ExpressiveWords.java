package cn.leetcode.xux.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 809. 情感丰富的文字
 * 有时候人们会用重复写一些字母来表示额外的感受，比如 "hello" -> "heeellooo", "hi" -> "hiii"。
 * 我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。
 * 对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，
 * 我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母 c ），
 * 然后往其中添加相同的字母 c 使其长度达到 3 或以上。
 *
 * 例如，以 "hello" 为例，我们可以对字母组 "o" 扩张得到 "hellooo"，
 * 但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 长度小于 3。
 * 此外，我们可以进行另一种扩张 "ll" -> "lllll" 以获得 "helllllooo"。如果 S = "helllllooo"，
 * 那么查询词 "hello" 是可扩张的，因为可以对它执行这两种扩张操作使得 query = "hello" -> "hellooo" -> "helllllooo" = S。
 *
 * 输入一组查询单词，输出其中可扩张的单词数量。
 *
 * 示例：
 * 输入：
 * S = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * 输出：1
 * 解释：
 * 我们能通过扩张 "hello" 的 "e" 和 "o" 来得到 "heeellooo"。
 * 我们不能通过扩张 "helo" 来得到 "heeellooo" 因为 "ll" 的长度小于 3 。
 *
 * 说明：
 * 0 <= len(S) <= 100。
 * 0 <= len(words) <= 100。
 * 0 <= len(words[i]) <= 100。
 * S 和所有在 words 中的单词都只由小写字母组成。
 */
public class ExpressiveWords {

    public int expressiveWords(String S, String[] words) {
        if(S==null||S.length()==0) {
            return 0;
        }
        Queue<int[]> queue = new LinkedList<>();
        int n = words.length;
        int len = S.length();
        for(int i=0;i<n;i++) {
            String word = words[i];
            if(word!=null&&word.length()>0&&word.length()<=len&&word.charAt(0)==S.charAt(0)) {
                queue.offer(new int[]{i, 0});
            }
        }
        int idxS = 0;
        while(idxS<len&&!queue.isEmpty()) {
            char c = S.charAt(idxS);
            int cnt = 1;
            while(idxS<len-1&&S.charAt(idxS+1)==c) {
                idxS++;
                cnt++;
            }
            idxS++;
            int size = queue.size();
            while(size-->0) {
                int[] curr = queue.poll();
                String word = words[curr[0]];
                if(curr[1]>=word.length()||word.charAt(curr[1])!=c) {
                    continue;
                }
                int cnt1 = 1;
                while(curr[1]+1<word.length()&&word.charAt(curr[1]+1)==c) {
                    cnt1++;
                    curr[1]++;
                }
                if(cnt1==cnt||(cnt1<cnt&&cnt>=3)) {
                    curr[1]++;
                    queue.offer(curr);
                }
            }
        }
        int result = 0;
        int size = queue.size();
        while(size-->0) {
            int[] curr = queue.poll();
            if(curr[1]==words[curr[0]].length()) {
                result++;
            }
        }
        return result;
    }

}
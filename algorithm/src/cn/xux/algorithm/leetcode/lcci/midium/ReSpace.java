package cn.xux.algorithm.leetcode.lcci.midium;

/**
 * 面试题 17.13. 恢复空格
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
 * 像句子"I reset the computer. It still didn’t boot!"
 * 已经变成了"iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。
 * 当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 *
 * 示例：
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 *
 * 提示：
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 */
public class ReSpace {

    public static void main(String[] args) {
        System.out.println(new ReSpace().respace(
                new String[]{"looked","just","like","her","brother"},
                "jesslookedjustliketimherbrother"));
    }

    public int respace(String[] dictionary, String sentence) {
        Trie root = new Trie();
        for(String word : dictionary) {
            insert(root, word);
        }
        int len = sentence.length();
        int[] dp = new int[len+1];
        for(int i=len-1;i>=0;i--) {
            Trie curr = root;
            dp[i] = len-i;
            for(int j=i;j<len;j++) {
                char c = sentence.charAt(j);
                if(curr.children[c-'a']!=null) {
                    curr = curr.children[c-'a'];
                    if(curr.isWord) {
                        dp[i] = Math.min(dp[i], dp[j+1]);
                    }else {
                        dp[i] = Math.min(dp[i], dp[j+1]+j-i+1);
                    }
                }else {
                    dp[i] = Math.min(dp[i], dp[j+1]+j-i+1);
                    break;
                }
            }
        }
        return dp[0];
    }

    private void insert(Trie root, String word) {
        Trie curr = root;
        for(char c : word.toCharArray()) {
            if(curr.children[c-'a']==null) {
                curr.children[c-'a'] = new Trie();
            }
            curr = curr.children[c-'a'];
        }
        curr.isWord = true;
    }

}

class Trie {

    Trie[] children = new Trie[26];
    boolean isWord = false;

}

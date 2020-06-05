package cn.leetcode.xux.general.midium;

/**
 * 820. 单词的压缩编码
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 *
 * 示例：
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 *
 * 提示：
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母 。
 */
public class ShortEncodingOfWords {

    int result = 0;

    public int minimumLengthEncoding(String[] words) {
        if(words==null||words.length==0) {
            return 0;
        }
        TreeNodeI root = new TreeNodeI();
        for(String word : words) {
            root.insert(word);
        }
        dfs(root, 1);
        return result;
    }

    public void dfs(TreeNodeI root, int len) {
        boolean flag = false;
        for(TreeNodeI child : root.children) {
            if(child!=null) {
                flag = true;
                dfs(child, len+1);
            }
        }
        if(!flag) {
            result += len;
        }
    }

}

class TreeNodeI {

    TreeNodeI[] children = new TreeNodeI[26];

    public void insert(String word) {
        if(word==null||word.length()==0) {
            return;
        }
        TreeNodeI curr = this;
        for(int i=word.length()-1;i>=0;i--) {
            char c = word.charAt(i);
            if(curr.children[c-'a']==null) {
                curr.children[c-'a'] = new TreeNodeI();
            }
            curr = curr.children[c-'a'];
        }
    }

}

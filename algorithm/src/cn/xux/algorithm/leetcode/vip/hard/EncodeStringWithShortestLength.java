package cn.xux.algorithm.leetcode.vip.hard;

/**
 * 471. 编码最短长度的字符串
 * 给定一个 非空 字符串，将其编码为具有最短长度的字符串。
 * 编码规则是：k[encoded_string]，其中在方括号 encoded_string 中的内容重复 k 次。
 *
 * 注：
 * k 为正整数且编码后的字符串不能为空或有额外的空格。
 * 你可以假定输入的字符串只包含小写的英文字母。字符串长度不超过 160。
 * 如果编码的过程不能使字符串缩短，则不要对其进行编码。如果有多种编码方式，返回任意一种即可。
 *
 * 示例 1：
 * 输入： "aaa"
 * 输出： "aaa"
 * 解释： 无法将其编码为更短的字符串，因此不进行编码。
 *
 * 示例 2：
 * 输入： "aaaaa"
 * 输出： "5[a]"
 * 解释： "5[a]" 比 "aaaaa" 短 1 个字符。
 *
 * 示例 3：
 * 输入： "aaaaaaaaaa"
 * 输出： "10[a]"
 * 解释： "a9[a]" 或 "9[a]a" 都是合法的编码，和 "10[a]" 一样长度都为 5。
 *
 * 示例 4：
 * 输入： "aabcaabcd"
 * 输出： "2[aabc]d"
 * 解释： "aabc" 出现两次，因此一种答案可以是 "2[aabc]d"。
 *
 * 示例 5：
 * 输入： "abbbabbbcabbbabbbc"
 * 输出： "2[2[abbb]c]"
 * 解释： "abbbabbbc" 出现两次，但是 "abbbabbbc" 可以编码为 "2[abbb]c"，因此一种答案可以是 "2[2[abbb]c]"。
 */
public class EncodeStringWithShortestLength {

    public String encode(String s) {
        if(s==null||s.length()<5) {
            return s;
        }
        int len = s.length();
        String[][] dp = new String[len][len];
        for(int l=1;l<=4;l++) {
            for(int start=0;start+l-1<len;start++) {
                dp[start][start+l-1] = s.substring(start, start+l);
            }
        }
        for(int l=5;l<=len;l++) {
            for(int start=0;start+l-1<len;start++) {
                int end = start+l-1;
                dp[start][end] = s;
                if(same(s, start, end)) {
                    dp[start][end] = l+"["+s.charAt(start)+"]";
                }else {
                    for(int mid=start;mid<end;mid++) {
                        String pre = dp[start][mid];
                        String post = dp[mid][end];
                        if(pre.indexOf('[')!=-1&&pre.charAt(pre.length()-1)==']'
                                &&post.indexOf('[')!=-1&&post.charAt(post.length()-1)==']'
                                &&pre.substring(pre.indexOf('[')+1,pre.length()-1)
                                        .equals(post.substring(post.indexOf('[')+1,post.length()-1))) {
                            int cnt = Integer.parseInt(pre.substring(0, pre.indexOf('[')))
                                    +Integer.parseInt(post.substring(0, post.indexOf('[')));
                            String tmp = cnt+pre.substring(pre.indexOf('['));
                            if(tmp.length()<dp[start][end].length()) {
                                dp[start][end] = tmp;
                            }
                        }
                        if(l%2==0&&dp[start][mid].equals(dp[mid+1][end])
                                &&dp[start][mid].length()+3<dp[start][end].length()) {
                            dp[start][end] = 2+'['+dp[start][mid]+']';
                        }
                    }
                }
            }
        }
        return dp[0][len-1];
    }

    private boolean same(String s, int start, int end) {
        for(int i=start;i<end-1;i++) {
            if(s.charAt(i+1)!=s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}

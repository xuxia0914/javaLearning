package cn.leetcode.xux.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 467. 环绕字符串中唯一的子字符串
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，
 * 所以 s 看起来是这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * 现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，尤其是当你的输入是字符串 p ，
 * 你需要输出字符串 s 中 p 的不同的非空子串的数目。
 * 注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。
 *
 * 示例 1:
 * 输入: "a"
 * 输出: 1
 * 解释: 字符串 S 中只有一个"a"子字符。
 *
 * 示例 2:
 * 输入: "cac"
 * 输出: 2
 * 解释: 字符串 S 中的字符串“cac”只有两个子串“a”、“c”。.
 *
 * 示例 3:
 * 输入: "zab"
 * 输出: 6
 * 解释: 在字符串 S 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。.
 */
public class UniqueSubstringsInWraparoundString {

    public static void main(String[] args) {
        System.out.println(new UniqueSubstringsInWraparoundString().findSubstringInWraproundString("zab"));
    }

    public int findSubstringInWraproundString(String p) {
        if(p==null||p.length()==0) {
            return 0;
        }
        //表示以当前字母为结尾的符合条件的子串的个数
        int[] cnts = new int[26];
        int idx = 0;
        int len = p.length();
        int currLen = 0;
        while(idx<len) {
            char c = p.charAt(idx);
            if(idx==0||c%26==(p.charAt(idx-1)+1)%26) {
                currLen++;
            }else {
                currLen = 1;
            }
            cnts[c-'a'] = Math.max(cnts[c-'a'], currLen);
            idx++;
        }
        int result = 0;
        for(int cnt : cnts) {
            result += cnt;
        }
        return result;
    }

    //TLE
    public int findSubstringInWraproundString1(String p) {
        if(p==null||p.length()==0) {
            return 0;
        }
        int idx = 0;
        int len = p.length();
        Set<String> result = new HashSet<>();
        int currLen = 1;
        while(idx<len) {
            if(idx==len-1||(p.charAt(idx)+1)%26!=p.charAt(idx+1)%26) {
                String s = p.substring(idx-currLen+1, idx+1);
                for(int i=1;i<=s.length();i++) {
                    for(int j=0;j<=s.length()-i;j++) {
                        result.add(s.substring(j, j+i));
                    }
                }
                currLen = 1;
            }else {
                currLen++;
            }
            idx++;
        }
        return result.size();
    }

}

package cn.xux.algorithm.leetcode.general.midium;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 * Note:
 *
 * S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString {

    public static String reorganizeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int[] array = new int[26];
        int n = s.length();
        for(int i=0;i<n;i++) {  //存储每个字符的数量
            array[s.charAt(i)-'a']++;
        }
        for(int i : array) {    //每个字符的个数不能大于(n+1)/2，否则不能重组
            if(i>(n+1)/2) {
                return "";
            }
        }
        StringBuilder sb = new StringBuilder();
        int num;    //本次要append的字符的剩余个数,与上一次append的字符不相同且剩余个数最多
        int pre = -1;   //上一次append的字符 - 'a'
        int curr = -1;  //本次要append的字符 - 'a'
        while(true) {
            num = 0;
            for(int i=0;i<26;i++) { //每次取与上一次append不相同且个数最多的字符
                if(array[i]>num&&i!=pre) {
                    num = array[i];
                    curr = i;
                }
            }
            if(num==0) {
                break;
            }else {
                sb.append((char)(curr+'a'));
                array[curr]--;
                pre = curr;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reorganizeString("vvvlo"));
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));
    }

}

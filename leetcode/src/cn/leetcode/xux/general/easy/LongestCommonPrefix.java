package cn.leetcode.xux.general.easy;

/**
 *Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * Example 1:
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 * All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix {

    public static String solution(String[] ss) {
        StringBuffer sb = new StringBuffer();
        int minLength = ss[0].length();
        for(int i=1;i<ss.length;i++) {
            minLength = Math.min(minLength, ss[i].length());
        }
        if(minLength<1) {
            return "";
        }
        for(int i=0;i<minLength;i++) {
            boolean flag = true;
            for(int j=0;j<ss.length-1;j++) {
                if(ss[j].charAt(i)!=ss[j+1].charAt(i)) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                sb.append(ss[0].charAt(i));
            }else {
                break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(new String []{"flower","flow","flight"}));
        System.out.println(solution(new String []{"dog","racecar","car"}));
    }

}

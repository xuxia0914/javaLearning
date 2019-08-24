package cn.leetcode.xux.midium;

/**
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same,
 * where in each step you can delete one character in either string.
 * Example 1:
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * Note:
 * The length of given words won't exceed 500.
 * Characters in given words can only be lower-case letters.
 */
public class DeleteOperationForTwoStrings {

    public int minDistance1(String word1, String word2) {
        if(word1==null||word2==null) {
            return 0;
        }
        if(word1.length()==0) {
            return word2.length();
        }
        if(word2.length()==0) {
            return word1.length();
        }
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<dp[0].length;i++) {
            dp[0][i] = i;
        }
        for(int i=0;i<dp.length;i++) {
            dp[i][0] = i;
        }
        for(int i=1;i<dp.length;i++) {
            for(int j=1;j<dp[0].length;j++) {
                if(word1.charAt(i-1)==word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    /**TLE*/
    public int minDistance2(String word1, String word2) {
        if(word1==null||word2==null) {
            return 0;
        }
        return helper(0, 0, word1, word2);
    }

    public int helper(int i, int j, String word1, String word2) {
        if(word1==null||word2==null) {
            return 0;
        }
        if(word1.length()==i) {
            return word2.length()-j;
        }
        if(word2.length()==j) {
            return word1.length()-i;
        }
        if(word1.charAt(i)==word2.charAt(j)) {
            return helper(i+1, j+1, word1, word2);
        }else {
            return 1+Math.min(helper(i, j+1, word1, word2), helper(i+1, j, word1, word2));
        }
    }

}

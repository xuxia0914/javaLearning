package cn.leetcode.xux.midium;

/**
 * 1079. 活字印刷
 * 你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。
 * 返回你可以印出的非空字母序列的数目。
 * 注意：本题中，每个活字字模只能使用一次。
 *
 * 示例 1：
 * 输入："AAB"
 * 输出：8
 * 解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
 *
 * 示例 2：
 * 输入："AAABBC"
 * 输出：188
 *
 * 提示：
 * 1 <= tiles.length <= 7
 * tiles 由大写英文字母组成
 */
public class LetterTilePossibilities {

    public static void main(String[] args) {
        //8
        System.out.println(new LetterTilePossibilities().numTilePossibilities("AAB"));
    }

    int result = 0;

    public int numTilePossibilities(String tiles) {
        int[] cnts = new int[26];
        for(char c : tiles.toCharArray()) {
            cnts[c-'A']++;
        }
        dfs(cnts, "");
        return result-1;
    }

    public void dfs(int[] cnts, String curr) {
        result++;
        for(int i=0;i<26;i++) {
            if(cnts[i]>0) {
                cnts[i]--;
                dfs(cnts, curr+(char)(i+'A'));
                cnts[i]++;
            }
        }
    }

}

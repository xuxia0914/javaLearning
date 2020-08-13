package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 533. 孤独的像素 II
 * 找到满足以下条件的黑色像素点的个数（假设某个黑色像素点位于第R行和第C列）：
 * 第R行和第C列都恰好有N个黑色像素点。
 * 对于所有在第C列为黑色像素点的行，这些行需要和第R行完全一样。
 * 这个图形表示为包含'B'和'W'的二维字符型数组，其中'B' 表示黑色像素点，'W'表示白色像素点。
 *
 * 样例
 * 样例1
 *
 * 输入：
 * ["WBWBBW","WBWBBW", "WBWBBW","WWBWBW"]
 * 3
 * 输出：6
 * 解释：
 * 所有的红色的像素点'B'都是我们要找的黑色像素点（所有的'B'都在列1和列3)
 * [['W', 'B', 'W', 'B', 'B', 'W'],
 * ['W', 'B', 'W', 'B', 'B', 'W'],
 * ['W', 'B', 'W', 'B', 'B', 'W'],
 * ['W', 'W', 'B', 'W', 'B', 'W']]
 * ['W', 'W', 'B', 'W', 'B', 'W']]
 *
 * 以行R = 0和列C = 1的黑色色素点作为例子：
 * 规则1，行R = 0和列C = 1都恰好含有N = 3个黑色像素点。
 * 规则2，在列C = 1是黑色像素点的行有第0， 1， 2行，他们和行R = 0完全一样。
 *
 * 样例2
 *
 * 输入：
 * ["WWW","WWW","WWB"]
 * 1
 * 输出：
 * 1
 * 注意事项
 * 输入二维数组的长和宽的范围都是[1,200]。
 */
public class LonelyPixelII {

    public int findBlackPixel(char[][] picture, int N) {
        int m = picture.length;
        int n = picture[0].length;
        int[] cols = new int[n];
        Map<String, Integer> map = new HashMap<>();
        int res = 0;

        // iterate picture
        for(int i=0; i<m; i++) {
            int count = 0;
            StringBuilder sb = new StringBuilder();

            for(int j=0; j<n; j++) {
                if(picture[i][j] == 'B') {
                    cols[j]++;
                    count++;
                }
                sb.append(picture[i][j]);
            }

            if(count == N) {// only store rowString into map when this row has N B
                String curRow = sb.toString();
                map.put(curRow, map.getOrDefault(curRow, 0) + 1); // count how many rows are same
            }

        }

        // iterate keySet
        for(String row : map.keySet()) {
            if(map.get(row) != N) // if value is not N, meaning rule 1 (columns need to have N Bs) is not satisfied.
                continue;
            // if value is N, meaning rule 2 is also satisfied because all these rows are same.
            for(int j=0; j<n; j++) {    // count Bs in this column
                if(row.charAt(j) == 'B' && cols[j] == N)
                    res += N;
            }
        }
        return res;
    }

}

package cn.xux.algorithm.leetcode.lcci.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 16.14. 最佳直线
 * 给定一个二维平面及平面上的 N 个点列表Points，
 * 其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。
 * 设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，
 * 你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，
 * 则选择S[0]值较小的直线返回，S[0]相同则选择S[1]值较小的直线返回。
 *
 * 示例：
 * 输入： [[0,0],[1,1],[1,0],[2,0]]
 * 输出： [0,2]
 * 解释： 所求直线穿过的3个点的编号为[0,2,3]
 *
 * 提示：
 * 2 <= len(Points) <= 300
 * len(Points[i]) = 2
 */
public class BestLine {

    public int[] bestLine(int[][] points) {
        int len = points.length;
        int[] res = null;
        int resLen = 0;
        Set<String> mem = new HashSet<>();
        for(int i=0;i<=len-2;i++) {
            for(int j=i+1;j<=len-1;j++) {
                if(mem.contains(i+"#"+j)) {
                    continue;
                }
                int[] curr = new int[]{i, j};
                int currLen = 2;
                for(int k=j+1;k<=len-1;k++) {
                    if((points[k][1]-points[i][1])*(points[j][0]-points[i][0])
                            ==(points[k][0]-points[i][0])*(points[j][1]-points[i][1])) {
                        mem.add(j+"#"+k);
                        currLen++;
                    }
                }
                if(currLen>resLen) {
                    resLen = currLen;
                    res = curr;
                }
            }
        }
        return res;
    }

}

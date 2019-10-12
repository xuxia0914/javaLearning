package cn.leetcode.xux.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    /**
     * O(n3)
     * @param ia
     * @return
     */
    public static List<int[]> solution1(int[] ia) {
        List<int[]> result = new ArrayList<int[]>();
        if(ia==null||ia.length<3) {
            return result;
        }
        Arrays.sort(ia);
        int[] tmp;
        for(int i=0;i<ia.length-2;i++) {
            if(i>0&&ia[i]==ia[i-1]) {
                continue;
            }
            for(int j=i+1;j<ia.length-1;j++) {
                if(j>i+1&&ia[j]==ia[j-1]) {
                    continue;
                }
                for(int k=j+1;k<ia.length;k++) {
                    if(k>j+1&&ia[k]==ia[k-1]) {
                        continue;
                    }
                    if(ia[i]+ia[j]+ia[k]==0) {
                        tmp = new int[]{ia[i], ia[j], ia[k]};
                        result.add(tmp);
                    }
                }
            }
        }
        return result;
    }

    /**
     * O(n2)
     * @param ia
     * @return
     */
    public static List<int[]> solution2(int[] ia) {
        List<int[]> result = new ArrayList<int[]>();
        if(ia==null||ia.length<3) {
            return result;
        }
        Arrays.sort(ia);
        for(int i=0;i<ia.length-2;i++) {
            int left=i+1,right=ia.length-1;
            if(i>0&&ia[i]==ia[i-1]) {
                continue;
            }
            while(left<right) {
                if(ia[i]+ia[left]+ia[right]==0) {
                    result.add(new int[]{ia[i], ia[left], ia[right]});
                    left++;
                    while(left<=ia.length-1&&left>0&&ia[left]==ia[left-1]) {
                        left++;
                    }
                    right--;
                    while(right>=0&&right<ia.length-1&&ia[right]==ia[right+1]) {
                        right--;
                    }
                }else if(ia[i]+ia[left]+ia[right]>0) {
                    right--;
                    while(right>=0&&right<ia.length-1&&ia[right]==ia[right+1]) {
                        right--;
                    }
                }else {
                    left++;
                    while(left<=ia.length-1&&left>0&&ia[left]==ia[left-1]) {
                        left++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<int[]> ial = solution2(new int[]{-1, 0, 1, 2, -1, -1, -1, 2, 2, 2});
        for(int[] ia : ial) {
            System.out.println(Arrays.toString(ia));
        }
    }

}

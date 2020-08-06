package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 面试题 17.08. 马戏团人塔
 * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。
 * 已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
 *
 * 示例：
 * 输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
 * 输出：6
 * 解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
 *
 * 提示：
 * height.length == weight.length <= 10000
 */
public class CircusTowerLcci {

    public int bestSeqAtIndex(int[] height, int[] weight) {
        if(height==null||height.length==0) {
            return 0;
        }
        int len = height.length;
        quickSort(height, weight, 0, len-1);
        /*int[] dp = new int[len];
        int res = 1;
        for(int i=0;i<len;i++) {
            dp[i] = 1;
            for(int j=0;j<i;j++) {
                if(weight[i]>weight[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;*/
        int res = 1;
        for(int i=1;i<len;i++) {
            int curr = weight[i];
            if(curr>weight[res-1]) {
                weight[res++] = curr;
            }else {
                int index = Arrays.binarySearch(weight, 0, res-1, curr);
                if(index<0) {
                    index = -index-1;
                    weight[index] = curr;
                }
            }
        }
        return res;
    }

    public void quickSort(int[] height, int[] weight, int start, int end) {
        if(start>=end) {
            return;
        }
        int left = start;
        int right = end;
        int key_height = height[left];
        int key_weight = weight[left];
        while(left<right) {
            while(left<right&&(height[right]>key_height||(height[right]==key_height&&weight[right]<=key_weight))) {
                right--;
            }
            while(left<right&&(height[left]<key_height||(height[left]==key_height&&weight[left]>=key_weight))) {
                left++;
            }
            if(left<right) {
                int tmp_height = height[left];
                height[left] = height[right];
                height[right] = tmp_height;
                int tmp_weight = weight[left];
                weight[left] = weight[right];
                weight[right] = tmp_weight;
            }
        }
        height[start] = height[left];
        height[left] = key_height;
        weight[start] = weight[left];
        weight[left] = key_weight;
        quickSort(height, weight, start, right-1);
        quickSort(height, weight, right+1, end);
    }

    public static void main(String[] args) {
        /**
         * [2868,5485,1356,1306,6017,8941,7535,4941,6331,6181]
         * [5042,3995,7985,1651,5991,7036,9391,428,7561,8594]
         */
        CircusTowerLcci ctl = new CircusTowerLcci();
        int[] height = new int[]{2868,5485,1356,1306,6017,8941,7535,4941,6331,6181};
        int[] weight = new int[]{5042,3995,7985,1651,5991,7036,9391,428,7561,8594};
        System.out.println(height.length);
        System.out.println(weight.length);
        ctl.quickSort(height, weight, 0, height.length-1);
        for(int num : height) {
            System.out.print(num+",");
        }
        System.out.println();
        for(int num : weight) {
            System.out.print(num+",");
        }
    }

}

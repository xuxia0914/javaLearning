package cn.leetcode.xux.midium;

/**
 * 1093. 大样本统计
 * 我们对 0 到 255 之间的整数进行采样，并将结果存储在数组 count 中：count[k] 就是整数 k 的采样个数。
 * 我们以 浮点数 数组的形式，分别返回样本的最小值、最大值、平均值、中位数和众数。其中，众数是保证唯一的。
 * 我们先来回顾一下中位数的知识：
 * 如果样本中的元素有序，并且元素数量为奇数时，中位数为最中间的那个元素；
 * 如果样本中的元素有序，并且元素数量为偶数时，中位数为中间的两个元素的平均值。
 *
 * 示例 1：
 * 输入：count = [0,1,3,4,0,0,0,0,0,0,0,...]
 * 输出：[1.00000,3.00000,2.37500,2.50000,3.00000]
 *
 * 示例 2：
 * 输入：count = [0,4,3,2,2,0,0,0,0,0,0,...]
 * 输出：[1.00000,4.00000,2.18182,2.00000,1.00000]
 *
 * 提示：
 * count.length == 256
 * 1 <= sum(count) <= 10^9
 * 计数表示的众数是唯一的
 * 答案与真实值误差在 10^-5 以内就会被视为正确答案
 */
public class StatisticsFromALargeSample {

    public static void main(String[] args) {
        int[] count = new int[256];
        count[1] = 4;
        count[2] = 3;
        count[3] = 2;
        count[3] = 2;
        new StatisticsFromALargeSample().sampleStats(count);
    }

    public double[] sampleStats(int[] count) {
        int min = -1;
        for(int i=0;i<256;i++) {
            if(count[i]>0) {
                min = i;
                break;
            }
        }
        int max = 256;
        for(int i=255;i>=0;i--) {
            if(count[i]>0) {
                max = i;
                break;
            }
        }
        int total = 0;
        long sum = 0L;
        for(int i=min;i<=max;i++) {
            total += count[i];
            sum += i*count[i];
        }
        double avg = (double)sum/total;
        double midNum = 0;
        int target = total/2;
        int currTotal = 0;
        for(int i=min;i<=max;i++) {
            currTotal += count[i];
            if(currTotal==target) {
                int next = i+1;
                while(count[next]==0) {
                    next++;
                }
                if(total%2==1) {
                    midNum = next;
                }else {
                    midNum = (double)(i+next)/2;
                }
                break;
            }else if(currTotal>target) {
                midNum = i;
                break;
            }
        }
        int manyNum = min;
        for(int i=min+1;i<=max;i++) {
            if(count[i]>=count[manyNum]) {
                manyNum = i;
            }
        }
        return new double[]{min, max, avg, midNum, manyNum};
    }

}

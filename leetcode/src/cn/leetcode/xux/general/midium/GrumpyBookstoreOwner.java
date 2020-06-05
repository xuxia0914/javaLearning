package cn.leetcode.xux.general.midium;

/**
 * 1052. 爱生气的书店老板
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。
 * 每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，
 * 那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *
 * 示例：
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 * 提示：
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 */
public class GrumpyBookstoreOwner {

    public static void main(String[] args) {
        System.out.println(new GrumpyBookstoreOwner().maxSatisfied(
                new int[]{1,0,1,2,1,1,7,5},
                new int[]{0,1,0,1,0,1,0,1},
                3
        ));
    }

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int sumSat = 0;
        int left = 0;
        int right = 0;
        int canAddCurr = 0;
        int canAddMax = 0;
        while(right<customers.length) {
            sumSat += grumpy[right]==0?customers[right]:0;
            canAddCurr += grumpy[right]==1?customers[right]:0;
            if(right-left+1>X) {
                canAddCurr -= grumpy[left]==1?customers[left]:0;
                left++;
            }
            canAddMax = Math.max(canAddMax, canAddCurr);
            right++;
        }
        return sumSat+canAddMax;
    }

    public int maxSatisfied1(int[] customers, int[] grumpy, int X) {
        int len = customers.length;
        int[] sumsSat = new int[len];
        sumsSat[0] = grumpy[0]==0?customers[0]:0;
        int[] sums = new int[len];
        sums[0] = customers[0];
        for(int i=1;i<len;i++) {
            sumsSat[i] = sumsSat[i-1]+(grumpy[i]==0?customers[i]:0);
            sums[i] = sums[i-1]+customers[i];
        }
        if(X>=len) {
            return sums[len-1];
        }
        int result = Math.max(sums[X-1]+sumsSat[len-1]-sumsSat[X-1],
                sums[len-1]-sums[len-X-1]+sumsSat[len-X-1]);
        for(int i=1;i+X<len;i++) {
            result = Math.max(result,
                    sumsSat[i-1]+sums[i+X-1]-sums[i-1]+sumsSat[len-1]-sumsSat[i+X-1]);
        }
        return result;
    }

}

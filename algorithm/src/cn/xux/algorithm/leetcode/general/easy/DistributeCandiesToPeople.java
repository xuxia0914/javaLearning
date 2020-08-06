package cn.xux.algorithm.leetcode.general.easy;

/**
 * 1103. 分糖果 II
 * 排排坐，分糖果。
 * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
 * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。
 * 注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
 * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
 *
 * 示例 1：
 * 输入：candies = 7, num_people = 4
 * 输出：[1,2,3,1]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
 * 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
 *
 * 示例 2：
 * 输入：candies = 10, num_people = 3
 * 输出：[5,2,3]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3]。
 * 第四次，ans[0] += 4，最终数组变为 [5,2,3]。
 *
 * 提示：
 * 1 <= candies <= 10^9
 * 1 <= num_people <= 1000
 */
public class DistributeCandiesToPeople {

    //枚举 O(max(sqrt(candies), num_people))
    public int[] distributeCandies(int candies, int num_people) {
        int index = 0;
        int leftCandies = candies;
        int currNum = 1;
        int[] result = new int[num_people];
        while(leftCandies>0) {
            if(leftCandies>currNum) {
                result[index] += currNum;
                leftCandies -= currNum;
                index = (index+1)%num_people;
                currNum++;
            }else {
                result[index] += leftCandies;
                leftCandies = 0;
            }
        }
        return result;
    }

    //等差数列 O(num_people);
    public int[] distributeCandies1(int candies, int num_people) {
        int n = num_people;
        int p = (int) (Math.sqrt(2*candies-0.25)-0.5);
        int remain = candies - p*(p+1)/2;
        int rows = p/n;
        int cols = p%n;
        int[] result = new int[n];
        for(int i=0;i<n;i++) {
            result[i] = rows*(i+1)+rows*n*(rows-1)/2;
            if(i<cols) {
                result[i] += i+1+rows*n;
            }else if(i==cols) {
                result[i] += remain;
            }
        }
        return result;
    }

}

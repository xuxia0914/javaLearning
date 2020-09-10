package cn.xux.algorithm.leetcode.vip.easy;

import java.util.Arrays;

/**
 * 1196：最多可以买到的苹果数量
 * 楼下水果店正在促销，你打算买些苹果，arr[i] 表示第 i 个苹果的单位重量。
 * 你有一个购物袋，最多可以装 5000 单位重量的东西，算一算，最多可以往购物袋里装入多少苹果。
 *
 * 示例 1：
 * 输入：arr = [100,200,150,1000]
 * 输出：4
 * 解释：所有 4 个苹果都可以装进去，因为它们的重量之和为 1450。
 *
 * 示例 2：
 * 输入：arr = [900,950,800,1000,700,800]
 * 输出：5
 * 解释：6 个苹果的总重量超过了 5000，所以我们只能从中任选 5 个。
 *
 * 提示：
 * 1 <= arr.length <= 10^3
 * 1 <= arr[i] <= 10^3
 */
public class HowManyApplesCanYouPutIntoTheBasket {

    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int weight = 0;
        int ans = 0;
        for(int w : arr) {
            if(weight+w<=5000) {
                weight += w;
                ans++;
            }else {
                break;
            }
        }
        return ans;
    }

}

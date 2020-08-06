package cn.xux.algorithm.leetcode.general.midium;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class BeforhandArrayI {

    public static int solution(int[] array, int target) {
        return helper(array, 0, array.length-1, target);
    }

    /**
     * @param array
     * @param start
     * @param end
     * @param target
     * @return
     */
    public static int helper(int[] array, int start, int end, int target) {
        if(start>end) {
            return -1;
        }
        if(start==end) {
            return array[start]==target?start:-1;
        }
        int i = (end+start)/2;
        int left = array[i];
        int right = array[i+1];
        if(left==target) {
            return i;
        }else if(right==target) {
            return i+1;
        }
        if(left<right) {
            if (target > left && target < right) {
                return -1;
            } else {
                int reuslt_left = helper(array, start, i - 1, target);
                if (reuslt_left == -1) {
                    return helper(array, i + 2, end, target);
                } else {
                    return reuslt_left;
                }
            }
        }else {
            if (target > right && target < left) {
                int reuslt_left = helper(array, start, i - 1, target);
                if (reuslt_left == -1) {
                    return helper(array, i + 2, end, target);
                } else {
                    return reuslt_left;
                }
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{4,5,7,0,1,2}, 0));
        System.out.println(solution(new int[]{4,5,7,0,1,2}, 4));
        System.out.println(solution(new int[]{4,5,7,0,1,2}, 7));
        System.out.println(solution(new int[]{4,5,7,0,1,2}, 2));
        System.out.println(solution(new int[]{4,5,7,0,1,2}, 3));
        System.out.println(solution(new int[]{4,5,7,0,1,2}, 6));
    }

}

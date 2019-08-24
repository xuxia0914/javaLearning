package cn.leetcode.xux.midium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 378. 有序矩阵中第K小的元素
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 *
 * 示例:
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 * 返回 13。
 *
 * 说明:
 * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 */
public class KthSmallestElementInASortedMatrix {

    /**
     * 执行用时 :110 ms, 在所有 Java 提交中击败了6.81%的用户
     * 内存消耗 :49.9 MB, 在所有 Java 提交中击败了37.13%的用户
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        if(k==1) {
            return matrix[0][0];
        }
        int n = matrix.length;
        //默认小顶堆，这里重写为小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1);
        /*PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });*/
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                queue.offer(matrix[i][j]);
                if(queue.size()>k) {
                    queue.poll();
                }
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        System.out.println(new KthSmallestElementInASortedMatrix().kthSmallest(new int[][]{{1,5,9},{10,11,13},{12,13,15}}, 8)); //13
        System.out.println(new KthSmallestElementInASortedMatrix().kthSmallest(new int[][]{{1,2},{3,3}}, 4));   //3
        System.out.println(new KthSmallestElementInASortedMatrix().kthSmallest(new int[][]{{1,3,5},{6,7,12},{11,14,14}}, 9));   //14
        System.out.println(new KthSmallestElementInASortedMatrix().kthSmallest(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 5));   //5
    }

}

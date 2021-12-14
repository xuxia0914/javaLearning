package cn.xux.algorithm.leetcode.general.hard;

import java.util.*;

/**
 * 699. 掉落的方块
 * 在无限长的数轴（即 x 轴）上，
 * 我们根据给定的顺序放置对应的正方形方块。
 * <p>
 * 第 i 个掉落的方块（positions[i] = (left, side_length)）是正方形，
 * 其中 left 表示该方块最左边的点位置(positions[i][0])，
 * side_length 表示该方块的边长(positions[i][1])。
 * <p>
 * 每个方块的底部边缘平行于数轴（即 x 轴），
 * 并且从一个比目前所有的落地方块更高的高度掉落而下。
 * 在上一个方块结束掉落，并保持静止后，才开始掉落新方块。
 * <p>
 * 方块的底边具有非常大的粘性，
 * 并将保持固定在它们所接触的任何长度表面上（无论是数轴还是其他方块）。
 * 邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。
 * <p>
 * <p>
 * <p>
 * 返回一个堆叠高度列表 ans 。每一个堆叠高度 ans[i] 表示在通过 positions[0],
 * positions[1], ..., positions[i] 表示的方块掉落结束后，
 * 目前所有已经落稳的方块堆叠的最高高度。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1, 2], [2, 3], [6, 1]]
 * 输出: [2, 5, 5]
 * 解释:
 * <p>
 * 第一个方块 positions[0] = [1, 2] 掉落：
 * _aa
 * _aa
 * -------
 * 方块最大高度为 2 。
 * <p>
 * 第二个方块 positions[1] = [2, 3] 掉落：
 * __aaa
 * __aaa
 * __aaa
 * _aa__
 * _aa__
 * --------------
 * 方块最大高度为5。
 * 大的方块保持在较小的方块的顶部，不论它的重心在哪里，因为方块的底部边缘有非常大的粘性。
 * <p>
 * 第三个方块 positions[1] = [6, 1] 掉落：
 * __aaa
 * __aaa
 * __aaa
 * _aa
 * _aa___a
 * --------------
 * 方块最大高度为5。
 * <p>
 * 因此，我们返回结果[2, 5, 5]。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [[100, 100], [200, 100]]
 * 输出: [100, 100]
 * 解释: 相邻的方块不会过早地卡住，只有它们的底部边缘才能粘在表面上。
 * <p>
 * <p>
 * 注意:
 * <p>
 * 1 <= positions.length <= 1000.
 * 1 <= positions[i][0] <= 10^8.
 * 1 <= positions[i][1] <= 10^6.
 */
public class FallingSquares {

    public static void main(String[] args) {
        System.out.println(new FallingSquares().fallingSquares(
                new int[][]{{1, 2}, {2, 3}, {6, 1}}
        ));
    }

    // 先对边界压缩映射，在使用线段树记录区间最大值
    public List<Integer> fallingSquares(int[][] positions) {
        // 先对所需线段树的长度做压缩，不然会MLE
        // 具体方法：
        //  1、记录positions所有方块的边界(最多有2*positions.length个)
        Set<Integer> set = new HashSet<>();
        for (int[] p : positions) {
            set.add(p[0]);
            set.add(p[0] + p[1] - 1);
        }
        //  2、对所有边界严格递增排序
        int[] arr = new int[set.size()];
        int i = 0;
        for (int a : set) {
            arr[i++] = a;
        }
        int n = arr.length;
        Arrays.sort(arr);
        // 3、记录实际边界值和当前边界的位置的映射
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < n; j++) {
            map.put(arr[j], j);
        }
        // 用线段树记录区间最大值
        BIT bit = new BIT(n);
        List<Integer> ans = new ArrayList<>();
        for (int[] p : positions) {
            int left = map.get(p[0]);
            int right = map.get(p[0] + p[1] - 1);
            int h = bit.query(left, right);
            int newH = h + p[1];
            for (int j = left; j <= right; j++) {
                bit.update(j, newH);
            }
            ans.add(bit.tree[1]);
        }
        return ans;
    }

    // 线段树，保存区间最大值
    class BIT {

        int[] tree;
        int n;

        public BIT(int n) {
            this.n = n;
            tree = new int[n << 1];
        }

        public void update(int curr, int val) {
            curr += n;
            if (val <= tree[curr]) {
                return;
            }
            tree[curr] = val;
            curr >>= 1;
            while (curr > 0) {
                if (Math.max(tree[curr << 1], tree[(curr << 1) + 1]) > tree[curr]) {
                    tree[curr] = Math.max(tree[curr << 1], tree[(curr << 1) + 1]);
                    curr >>= 1;
                } else {
                    return;
                }
            }
        }

        public int query(int x, int y) {
            int left = x + n;
            int right = y + n;
            int ans = 0;
            while (left <= right) {
                if ((left & 1) == 1) {
                    ans = Math.max(ans, tree[left++]);
                }
                if ((right & 1) == 0) {
                    ans = Math.max(ans, tree[right--]);
                }
                left >>= 1;
                right >>= 1;
            }
            return ans;
        }

    }

}

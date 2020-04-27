package cn.leetcode.xux.midium;

/**
 * 904. 水果成篮
 * 在一排树中，第 i 棵树产生 tree[i] 型的水果。
 * 你可以从你选择的任何树开始，然后重复执行以下步骤：
 * 把这棵树上的水果放进你的篮子里。如果你做不到，就停下来。
 * 移动到当前树右侧的下一棵树。如果右边没有树，就停下来。
 * 请注意，在选择一颗树后，你没有任何选择：你必须执行步骤 1，
 * 然后执行步骤 2，然后返回步骤 1，然后执行步骤 2，依此类推，直至停止。
 * 你有两个篮子，每个篮子可以携带任何数量的水果，但你希望每个篮子只携带一种类型的水果。
 * 用这个程序你能收集的水果总量是多少？
 *
 * 示例 1：
 * 输入：[1,2,1]
 * 输出：3
 * 解释：我们可以收集 [1,2,1]。
 *
 * 示例 2：
 * 输入：[0,1,2,2]
 * 输出：3
 * 解释：我们可以收集 [1,2,2].
 * 如果我们从第一棵树开始，我们将只能收集到 [0, 1]。
 *
 * 示例 3：
 * 输入：[1,2,3,2,2]
 * 输出：4
 * 解释：我们可以收集 [2,3,2,2].
 * 如果我们从第一棵树开始，我们将只能收集到 [1, 2]。
 *
 * 示例 4：
 * 输入：[3,3,3,1,2,1,1,2,3,3,4]
 * 输出：5
 * 解释：我们可以收集 [1,2,1,1,2].
 * 如果我们从第一棵树或第八棵树开始，我们将只能收集到 4 个水果。
 *
 * 提示：
 * 1 <= tree.length <= 40000
 * 0 <= tree[i] < tree.length
 */
public class FruitIntoBaskets {

    public static void main(String[] args) {
        System.out.println(new FruitIntoBaskets().totalFruit(new int[]{0,0,1,1}));
    }

    public int totalFruit(int[] tree) {
        if(tree==null||tree.length==0) {
            return 0;
        }
        int[] fruits = new int[2];
        int[] cnts = new int[2];
        int left = 0;
        int right = 0;
        int result = 0;
        while(right<tree.length) {
            if(cnts[0]==0||fruits[0]==tree[right]) {
                fruits[0] = tree[right];
                cnts[0]++;
            }else if(cnts[1]==0||fruits[1]==tree[right]) {
                fruits[1] = tree[right];
                cnts[1]++;
            }else {
                result = Math.max(result, cnts[0]+cnts[1]);
                while(left<right) {
                    if(tree[left]==fruits[0]) {
                        if(--cnts[0]==0) {
                            cnts[0] = 1;
                            fruits[0] = tree[right];
                            left++;
                            break;
                        }
                    }else {
                        if(--cnts[1]==0) {
                            cnts[1] = 1;
                            fruits[1] = tree[right];
                            left++;
                            break;
                        }
                    }
                    left++;
                }
            }
            right++;
        }
        return Math.max(result, cnts[0]+cnts[1]);
    }

}

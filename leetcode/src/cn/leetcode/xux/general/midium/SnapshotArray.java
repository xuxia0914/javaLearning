package cn.leetcode.xux.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1146. 快照数组
 * 实现支持下列接口的「快照数组」- SnapshotArray：
 * SnapshotArray(int length) - 初始化一个与指定长度相等的 类数组 的数据结构。初始时，每个元素都等于 0。
 * void set(index, val) - 会将指定索引 index 处的元素设置为 val。
 * int snap() - 获取该数组的快照，并返回快照的编号 snap_id（快照号是调用 snap() 的总次数减去 1）。
 * int get(index, snap_id) - 根据指定的 snap_id 选择快照，并返回该快照指定索引 index 的值。
 *
 * 示例：
 * 输入：["SnapshotArray","set","snap","set","get"]
 *      [[3],[0,5],[],[0,6],[0,0]]
 * 输出：[null,null,0,null,5]
 * 解释：
 * SnapshotArray snapshotArr = new SnapshotArray(3); // 初始化一个长度为 3 的快照数组
 * snapshotArr.set(0,5);  // 令 array[0] = 5
 * snapshotArr.snap();  // 获取快照，返回 snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5
 *
 * 提示：
 * 1 <= length <= 50000
 * 题目最多进行50000 次set，snap，和 get的调用 。
 * 0 <= index < length
 * 0 <= snap_id < 我们调用 snap() 的总次数
 * 0 <= val <= 10^9
 */
public class SnapshotArray {

    private Map<Integer, Map<Integer, Integer>> snaps = null;
    private int snapId = 0;

    public SnapshotArray(int length) {
        snaps = new HashMap<>(length);
        while (length-- > 0) {
            snaps.put(length, new HashMap<Integer, Integer>());
        }
    }

    public void set(int index, int val) {
        snaps.get(index).put(snapId, val);
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snap_id) {
        while(snap_id>-1){//关键在这，因为在snap()生成新快照，没有为每个新生成的快照添加数据
            if (snaps.get(index).get(snap_id) != null) {
                return snaps.get(index).get(snap_id);
            }
            --snap_id;
        }
        return 0;
    }

    public static void main(String[] args) {
        SnapshotArray s = new SnapshotArray(1);
        s.set(0, 15);
        s.snap();
        s.snap();
        s.snap();
        System.out.println(s.get(0, 2));
    }

}

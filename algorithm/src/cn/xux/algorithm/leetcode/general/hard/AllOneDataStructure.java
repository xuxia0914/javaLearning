package cn.xux.algorithm.leetcode.general.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 432. 全 O(1) 的数据结构
 * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 * <p>
 * 实现 AllOne 类：
 * <p>
 * AllOne() 初始化数据结构的对象。
 * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
 * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * 输出
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 * <p>
 * 解释
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "leet"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= key.length <= 10
 * key 由小写英文字母组成
 * 测试用例保证：在每次调用 dec 时，数据结构中总存在 key
 * 最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 104 次
 */
public class AllOneDataStructure {

    class AllOne {

        Node root;

        Map<String, Node> nodes;

        public AllOne() {
            root = new Node();
            root.pre = root;
            // 初始化链表哨兵，下面判断节点的 next 若为 root，则表示 next 为空（prev 同理）
            root.next = root;
            nodes = new HashMap<String, Node>();
        }

        public void inc(String key) {
            if (nodes.containsKey(key)) {
                Node cur = nodes.get(key);
                Node nxt = cur.next;
                if (nxt == root || nxt.count > cur.count + 1) {
                    nodes.put(key, cur.insert(new Node(key, cur.count + 1)));
                } else {
                    nxt.keys.add(key);
                    nodes.put(key, nxt);
                }
                cur.keys.remove(key);
                if (cur.keys.isEmpty()) {
                    cur.remove();
                }
            } else {
                // key 不在链表中
                if (root.next == root || root.next.count > 1) {
                    nodes.put(key, root.insert(new Node(key, 1)));
                } else {
                    root.next.keys.add(key);
                    nodes.put(key, root.next);
                }
            }
        }

        public void dec(String key) {
            Node cur = nodes.get(key);
            // key 仅出现一次，将其移出 nodes
            if (cur.count == 1) {
                nodes.remove(key);
            } else {
                Node pre = cur.pre;
                if (pre == root || pre.count < cur.count - 1) {
                    nodes.put(key, cur.pre.insert(new Node(key, cur.count - 1)));
                } else {
                    pre.keys.add(key);
                    nodes.put(key, pre);
                }
            }
            cur.keys.remove(key);
            if (cur.keys.isEmpty()) {
                cur.remove();
            }
        }

        public String getMaxKey() {
            return root.pre != null ? root.pre.keys.iterator().next() : "";
        }

        public String getMinKey() {
            return root.next != null ? root.next.keys.iterator().next() : "";
        }

    }

    class Node {

        Node pre;

        Node next;

        Set<String> keys;

        int count;

        public Node() {
            this("", 0);
        }

        public Node(String key, int count) {
            this.count = count;
            keys = new HashSet<>();
            keys.add(key);
        }

        // 在this后插入node
        public Node insert(Node node) {
            node.pre = this;
            node.next = this.next;
            node.pre.next = node;
            node.next.pre = node;
            return node;
        }

        public void remove() {
            this.pre.next = this.next;
            this.next.pre = this.pre;
        }

    }

}


/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */

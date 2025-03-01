package Thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();

        List<List<Integer>> res = new ArrayList<>();

        if (root != null) {
            queue.add(root);
        }

        while ( !queue.isEmpty()) {

            LinkedList<Integer> temp = new LinkedList<>();

            boolean  direction = res.size()%2 ==0; // 上一个队列是偶数咧。这个是奇数咧。
            // 偶数
            int size = queue.size();
            for (int i = 0 ; i < size; i++) {

                TreeNode node = queue.poll();
                if (direction) {
                    // 当前列为偶数，则证明下一行 需要顺着。
                    temp.add(node.val);
                } else {
                    temp.addFirst(node.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(temp);
        }
        return res;
    }


}

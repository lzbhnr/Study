package Thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Res {


    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        reverseKGroup(node1,2);

    }


    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();


        for (int first = 0; first < nums.length -2; first++ ) {


            if (first>0 && nums[first] == nums[first-1]) {

                continue;
            }

            int third = nums.length-1;
            int target = -nums[first];

            for ( int second = first + 1; second<nums.length ; second++){

                if (second > first+1 && nums[second] == nums[second-1]) {

                    continue;
                }

                while( second < third &&  nums[second] + nums[first] >target) {
                    third--;
                }

                if (second == third) {
                    break;
                }

                if (nums[second] + nums[third] ==target) {
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[second]);
                    list.add(nums[first]);
                    list.add(nums[third]);
                    ans.add(list);
                }

            }

        }

        return ans;
    }
    public  static ListNode reverseKGroup(ListNode head, int k) {
        //
        //
        ListNode preHead = new ListNode(0, head);
        ListNode preTail = preHead;
        ListNode cur = head;
        ListNode nextStart = null;
        for (; cur != null; ) {
            // 说明当前组还有元素
            // 如果恰好成立，则说明不是null
            for (int i = 0; i < k-1; i++) {
                if (cur == null) {
                    preTail.next = nextStart;
                    // 返回head
                    return preHead.next;
                } else {
                    if (i != k-1) {
                        cur = cur.next;
                    }
                }

            }

            // 找到下一个开始，并断开连接
            nextStart = cur.next;
            cur.next = null;

            // 翻转当前链表
            preTail.next = reverse(head);

            //更新下一组节点的开始值
            preTail = head;
            head = nextStart;
            cur = nextStart;
        }
        return preHead.next;

    }


    // 返回新的头节点

    public static ListNode reverse(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
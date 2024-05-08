package LeetCode;

public class LC25 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;

        ListNode l11 = new ListNode(1);
        ListNode l21 = new ListNode(3);
        ListNode l31 = new ListNode(4);
        l11.next = l21;
        l21.next = l31;


        ListNode node = mergeTwoLists(l1,l11);
        System.out.println(node.val);

        executeVal(node);

    }

    private static void executeVal (ListNode listNode){
        if(listNode != null){
            System.out.println(listNode.val);
            executeVal(listNode.next);
        }
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode temp = pre;

        while(l1 != null || l2 != null){

            if(l1 == null){
                pre.next = l2;
                break;
            }

            if(l2 == null){
                pre.next = l1;
                break;
            }

            if(l1.val < l2.val){
                pre.next = l1;
                pre = pre.next;
                l1 = l1.next;
            }else{
                pre.next = l2;
                pre = pre.next;
                l2 = l2.next;


            }

        }
        return temp.next;
    }

}

package OdTest;

import java.util.Scanner;

/**
 * @ClassName Test24
 * @Description
 * @Author SDY
 * @Date 2024/6/29 10:33
 **/
public class Test24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.next().split(",");
        ListNode listNode = new ListNode(-1);
        ListNode head = listNode;
        for (int i = 0; i < split.length; i++) {
            listNode.next = new ListNode(Integer.parseInt(split[i]));
            listNode = listNode.next;
        }
        System.out.println(head.next);
        new Test24().swapPairs(head.next);
    }

    public ListNode swapPairs(ListNode head) {
        if(head == null){
            return null;
        }
        int flag = 0;
        ListNode currentListNode = head;
        while (currentListNode.next != null){
            if(flag % 2 == 0){
                ListNode next = currentListNode.next;
                int tmp = currentListNode.val;
                currentListNode.val = next.val;
                next.val = tmp;
            }
            flag++;
            currentListNode = currentListNode.next;
        }
        System.out.println(head);
        return head;
    }
}

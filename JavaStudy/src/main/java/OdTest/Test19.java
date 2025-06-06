package OdTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test19
 * @Description
 * @Author SDY
 * @Date 2024/6/22 15:49
 **/
public class Test19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] splits = sc.next().split(",");
        List<ListNode> listNodes = new ArrayList<>();
        for (int i = 0; i < splits.length; i++) {
            ListNode listNode = new ListNode(Integer.parseInt(splits[i]));
            listNodes.add(listNode);
        }
        for (int i = 0; i < listNodes.size()-1; i++) {
            listNodes.get(i).next = listNodes.get(i+1);
        }
        removeNthFromEnd(listNodes.get(0),sc.nextInt());


    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next == null){
            return null;
        }
        // 获取链表长度
        int len = 0;
        ListNode tmp = head;
        List<ListNode> tmpListNodes = new ArrayList<>();
        while (head.next != null){
            ListNode listNode = new ListNode(head.val);
            tmpListNodes.add(listNode);
            head = head.next;
        }
        tmpListNodes.add(new ListNode(head.val));
        tmpListNodes.remove(tmpListNodes.size() - n);
        ListNode result = tmpListNodes.get(0);
        ListNode listNode = tmpListNodes.get(0);
        for (int i = 1; i < tmpListNodes.size(); i++) {
            listNode.next = tmpListNodes.get(i);
            listNode = tmpListNodes.get(i);
        }
        System.out.println(tmpListNodes);
        System.out.println(result);
        return result;
    }


}

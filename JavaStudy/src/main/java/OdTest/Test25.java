package OdTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Test25
 * @Description
 * @Author SDY
 * @Date 2024/7/6 16:46
 **/
public class Test25 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String data = sc.next();
        ListNode listNode = new ListNode(-1);
        ListNode head = listNode;
        String[] split = data.split(",");
        for (String s : split) {
            listNode.next = new ListNode(Integer.parseInt(s));
            listNode = listNode.next;
        }
        reverseKGroup(head.next,sc.nextInt());
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode resultNode = new ListNode(-1);
        ListNode tmpHead = resultNode;
        // 计算链表的长度
        ListNode tmp = head;
        int len = 0;
        List<Integer> dataList = new ArrayList<>();
        while (tmp != null){
            len++;
            dataList.add(tmp.val);
            tmp = tmp.next;
        }
        tmp = head;
        while (len >= k){
            List<Integer> tmpList = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                tmpList.add(dataList.get(0));
                dataList.remove(0);
            }
            for (int i = tmpList.size() -1; i >= 0; i--) {
                resultNode.next = new ListNode(tmpList.get(i));
                resultNode = resultNode.next;
            }
            len -= k;
        }
        if(dataList.size() != 0){
            for (Integer integer : dataList) {
                resultNode.next = new ListNode(integer);
                resultNode = resultNode.next;
            }
        }

        System.out.println(tmpHead);

        return tmpHead.next;
    }


}

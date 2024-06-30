package OdTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author 18438
 * @date 2024/6/23 11:24
 * @description
 */
public class Test21 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListNode listNode1 = new ListNode(0);
        ListNode listNode2 = new ListNode(0);
        ListNode tmp1 = listNode1;
        ListNode tmp2 = listNode2;
        String[] data1 = sc.next().split(",");
        String[] data2 = sc.next().split(",");
        for (int i = 0; i < data1.length; i++) {
            tmp1.next = new ListNode(Integer.parseInt(data1[i]));
            tmp1 = tmp1.next;
        }
        for (int i = 0; i < data2.length; i++) {
            tmp2.next = new ListNode(Integer.parseInt(data2[i]));
            tmp2 = tmp2.next;
        }

        mergeTwoLists(listNode1.next,listNode2.next);

    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        List<Integer> dataList = new ArrayList<>();
        if(list1 != null){
            while (list1.next != null){
                dataList.add(list1.val);
                list1 = list1.next;
            }
            // 添加最后元素
            dataList.add(list1.val);
        }
        if(list2 != null){
            while (list2.next != null){
                dataList.add(list2.val);
                list2 = list2.next;
            }
            // 添加最后元素
            dataList.add(list2.val);
        }

        Collections.sort(dataList);
        ListNode pre = new ListNode(0);
        ListNode tmp = pre;
        for (int i = 0; i < dataList.size(); i++) {
            tmp.next = new ListNode(dataList.get(i));
            tmp = tmp.next;

        }

        System.out.println(pre);

        return pre.next;

    }
}

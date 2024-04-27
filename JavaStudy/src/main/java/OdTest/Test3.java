package OdTest;

import org.apache.kafka.common.protocol.types.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test3
 * @Description
 * @Author SDY
 * @Date 2024/4/25 21:14
 **/
public class Test3 {
    public static void main(String[] args) {
        String[] s1 = "9,9,9,9,9,9,9".split(",");
        String[] s2 = "9,9,9,9".split(",");
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();
        ListNode tmp1 = new ListNode();
        ListNode tmp2 = new ListNode();
        for (int i = 0; i < s1.length; i++) {
            tmp1 = new ListNode(Integer.parseInt(s1[i]));
            l1.next = tmp1;
        }
        for (int i = 0; i < s2.length; i++) {
            tmp2 = new ListNode(Integer.parseInt(s1[i]));
            l1.next = tmp2;
        }
        new Test3().test1(l1,l2);
    }

    public ListNode test1(ListNode l1, ListNode l2) {

        int tmp = 0;
        ArrayList<ListNode> listNodes = new ArrayList<>();
        while (l1.next != null && l2.next != null){
            int d = l1.val + l2.val + tmp;
            tmp = d / 10;
            listNodes.add(new ListNode(d % 10));
            l1 = l1.next;
            l2 = l2.next;
        }
        int d = l1.val + l2.val + tmp;
        tmp = d / 10;
        listNodes.add(new ListNode(d % 10));
        l1 = l1.next;
        l2 = l2.next;

        int count = 1;
        if( l1 != null && l1.next != null){
            while (l1.next != null){
                int dd = l1.val + tmp;
                tmp = dd / 10;
                listNodes.add(new ListNode(dd%10));
                l1 = l1.next;
            }
            listNodes.add(new ListNode(l1.val));
        }else if( l2 !=null && l2.next != null){
            while (l2.next != null){
                int dd = l2.val + tmp;
                tmp = dd / 10;
                listNodes.add(new ListNode(dd%10));
                l2 = l2.next;
            }
            listNodes.add(new ListNode(l2.val));
        }
        for (int i = 0; i < listNodes.size()-1; i++) {
            listNodes.get(i).next = listNodes.get(i+1);
        }
        System.out.println(listNodes.get(0));
        return listNodes.get(0);
    }

}

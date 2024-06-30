package OdTest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @ClassName Test23
 * @Description
 * @Author SDY
 * @Date 2024/6/29 9:41
 **/
public class Test23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] datas = sc.next().split("]");
        ListNode[] lists = new ListNode[datas.length];
        for (int i = 0; i< datas.length; i++) {
            if(StringUtils.isEmpty(datas[i])){
                continue;
            }
            String[] numArray = datas[i].replace(",[", "").replace("[","").split(",");
            ListNode preListNode = new ListNode(0);
            ListNode currentListNode = preListNode;
            for (String s : numArray) {
                if(StringUtils.isEmpty(s)){
                    continue;
                }
                ListNode listNode = new ListNode(Integer.parseInt(s));
                currentListNode.next = listNode;
                currentListNode = listNode;
            }
            lists[i] = preListNode.next;
        }
        System.out.println(lists);
        new Test23().mergeKLists2(lists);

    }

    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> dataList = new ArrayList<>();
        ListNode preListNode = new ListNode(0);
        ListNode currentListNode = preListNode;
        for (int i = 0; i < lists.length; i++) {
            ListNode list = lists[i];
            if(list == null){
                continue;
            }
            while (list.next != null){
                dataList.add(list.val);
                list = list.next;
            }
            // 添加最后一个元素
            dataList.add(list.val);
        }
        if(dataList.size() == 0){
            return preListNode.next;
        }
        Collections.sort(dataList);

        for (Integer integer : dataList) {
            ListNode listNode = new ListNode(integer);
            currentListNode.next = listNode;
            currentListNode = listNode;
        }
//        System.out.println(preListNode.next);
        return preListNode.next;
    }

    /**
     * 用堆做
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists){
        if(lists == null || lists.length == 0){
            return null;
        }
        // 创建堆
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        // 遍历链表数组，所有数组入队列
        for (int i = 0; i < lists.length; i++) {
            while (lists[i] != null){
                queue.add(lists[i]);
                lists[i] = lists[i].next;
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        //从堆中取元素
        while (!queue.isEmpty()){
            dummy.next = queue.poll();
            dummy = dummy.next;
        }
        dummy.next = null;
        System.out.println(head.next);
        return head.next;
    }
}

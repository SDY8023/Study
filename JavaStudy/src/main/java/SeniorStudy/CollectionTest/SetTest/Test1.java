package SeniorStudy.CollectionTest.SetTest;

import SeniorStudy.CollectionTest.bean.Person;
import java.util.HashSet;


/**
 * @ClassName Test1
 * @Description
 * @Author SDY
 * @Date 2023/8/28 20:47
 **/
public class Test1 {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.testHashSet();
    }

    public void testHashSet(){
        HashSet hashSet = new HashSet();
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");

        hashSet.add(p1);
        hashSet.add(p2);
        // 打印元素
        System.out.println(hashSet);
        System.out.println("=========================");
        p1.setName("CC");
        // 修改了元素的值，但是元素的位置不会改变,删除元素时根据hashCode值找到的元素对应不上所以删除不掉
        hashSet.remove(p1);
        System.out.println(hashSet);
        // 集合中有一个元素虽然改了值，但位置没变，新加一个与修改后一样的元素，但是位置不一样，所以可以加入
        hashSet.add(new Person(1001,"CC"));
        System.out.println(hashSet);
        // 没有修改过的元素位置都一样，加不进去
        hashSet.add(p2);
        System.out.println(hashSet);
        hashSet.add(new Person(1002,"BB"));
        System.out.println(hashSet);
        // 该元素第一次加入的时候位置上有元素了，所以加入不进去
        hashSet.add(p1);
        System.out.println(hashSet);

    }
}

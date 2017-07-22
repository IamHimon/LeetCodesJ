package com.company.BasedClass;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.Objects;

/**
 * Created by รอ on 2017/7/22.
 */
public class ListNode {
    public Object value;
    protected ListNode next;

    public ListNode() {
    }

    public ListNode(Object value) {
        this.value = value;
    }
    public static ListNode buildListNode(Object[] obj){
        ListNode head = new ListNode();
//        ListNode pNode = head;
//        for (int i=1;i<obj.length;i++){
//            pNode.next = new ListNode(obj[i]);
//            pNode.next = pNode;
//        }
        for (Object o:obj) {
            addToTail(head, o);
        }

        return head;
    }

    public static ListNode addToTail(ListNode pHead, Object value) {
        ListNode pNew = new ListNode(value);
        if (pHead == null){
            pHead = pNew;
        }else {
            ListNode pNode = pHead;
            while (pNode.next != null)
                pNode = pNode.next;
            pNode.next = pNew;
        }
        return pHead;
    }

    public static void printListNode(ListNode head){
        for (ListNode h = head;h!=null;h = h.next)
            System.out.println(h.value);
    }


    public static void main(String[] args) {
        Object[] obj = {1,2,3,4};
//        ListNode head = new ListNode(1);
//        head = addToTail(head, 2);
//        head = addToTail(head, 3);
//        printListNode(head);

        ListNode h2 = buildListNode(obj);
        printListNode(h2);


    }
}

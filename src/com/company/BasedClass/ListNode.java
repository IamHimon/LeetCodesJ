package com.company.BasedClass;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.Objects;

/**
 * Created by �� on 2017/7/22.
 */
public class ListNode {
    public Object value=null;
    protected ListNode next=null;

    public ListNode() {
    }

    public ListNode(Object value) {
        this.value = value;
    }


    public static ListNode buildListNode(Object[] obj) throws Exception {
        if (obj.length < 1)
            throw new Exception("obj is null!");
        ListNode head = new ListNode(obj[0]);
        for (int i=1;i<obj.length;i++) {
            addToTail(head, obj[i]);
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
//        for (ListNode h = head;h!=null;h = h.next)
//            System.out.println(h.value);
        ListNode pNode = head;
        while (pNode.next!=null){
            System.out.println(pNode.value);
            pNode = pNode.next;
        }
    }

    /*删除某个节点*/
    public static void DeleteNode(ListNode pListHead, ListNode pToBeDeleted) throws Throwable {
        if (pListHead==null||pToBeDeleted==null)
            return;
        //要删除的节点不在尾节点
        if (pListHead.next != null){
            ListNode pNext = pToBeDeleted.next;
            pToBeDeleted.value = pNext.value;
            pToBeDeleted.next = pNext.next;
            pNext.finalize();
        }else if (pListHead == pToBeDeleted){ //链表中只有一个节点，删除头节点（尾节点）
            pToBeDeleted.finalize();
        }else {  //要删除的节点在尾戒点，遍历得到他的前序节点
            ListNode pNode = pListHead;
            while (pNode.next != pToBeDeleted){
                pNode = pNode.next;
            }
            pNode.next = null;
            pToBeDeleted = null;
            pToBeDeleted.finalize();
        }
    }

    /*在一个排序链表中，删除重复的节点*/
    private static ListNode deleteDuplication(ListNode pHead) throws Throwable {
        if (pHead==null)
            return null;

        ListNode pNode = pHead;
        ListNode pPreNode = pHead;

        while (pNode.next!=null){
            ListNode pToBeDel = pNode;
            if (pToBeDel.next.value == pNode.value){
                pNode = pToBeDel.next;
                continue;
            }
            pNode = pNode.next;
            pPreNode.next = pNode;
            pPreNode = pNode;
        }
        //重复在结尾
        if (pPreNode != pNode){
            pPreNode.next = pNode;
        }
        return pHead;
    }

    public static void main(String[] args) throws Throwable {
//        Object[] obj = {1,1,2,3,3,5,6,7,7,8,8};
//        Object[] obj = {1,2,3,5,6,7,8};
        Object[] obj = {1, 1,1,1,1};


        ListNode h2 = buildListNode(obj);
//        DeleteNode(h2, h2.next.next);
        deleteDuplication(h2);
        printListNode(h2);

    }
}

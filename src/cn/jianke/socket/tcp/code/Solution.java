package cn.jianke.socket.tcp.code;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;


// Definition for singly-linked list.


/**
 * 链表反转
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode newNode = null;

        ListNode localNode = head;

        while (localNode != null) {
            ListNode listNode = localNode.next;

            //翻转
            localNode.next = newNode;

            newNode = localNode;

            localNode = listNode;
        }


        return newNode;
    }

    //递归
    public static ListNode reverseList2(ListNode head) {

        //找到最后一个节点
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseList2(head.next);

        head.next.next = head;

        head.next = null;

        return p;
    }

    public static ListNode reverseList3(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseList3(head);

        head.next.next = head;

        head.next = null;

        return p;

    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        listNode.next = listNode2;
        listNode2.next = listNode3;
        ListNode listNode1 = reverseList2(listNode);

        System.out.println("head.next.next = head" + listNode1.toString());
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
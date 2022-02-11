package cn.jianke.socket.tcp.code;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 最近最少使用
 */
class LRUCache {

    //双向链表
    class DListNode {

        int key;

        int value;

        DListNode prev;
        DListNode next;

        public DListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public DListNode() {

        }
    }

    //长度
    private int size;

    //阈值
    private int capacity;

    //双向链表的前一个
    private DListNode head;

    //双向链表的后一个
    private DListNode tail;

    private Map<Integer, DListNode> map = new Hashtable<>();


    public LRUCache(int capacity) {
        size = 0;
        this.capacity = capacity;

        head = new DListNode();
        tail = new DListNode();
        head.next = tail;
        tail.prev = head;
    }

    //增加
    public void put(int key, int value) {

        DListNode node = map.get(key);

        if (node == null) {
            //数据为空

            //初始化数据
            node = new DListNode(key, value);

            //计数器+1
            size++;

            //缓存添加
            map.put(key, node);
            //新数据移动到头部
            addToHead(node);

            //判断阈值
            if (size > capacity) {
                //大于阈值了，准备删除最后一个
                DListNode node1 = removeTail();
                map.remove(node1.value);
                size--;
            }
        } else {
            //缓存不为空
            node.value = value;
        }
    }

    //删除指定节点
    public void remove(DListNode node) {
        //先拿到这个节点的上一个和下一个

        DListNode prev = node.prev;

        DListNode next = node.next;

        prev.next = next;

        next.prev = prev;
    }

    //移除最后一个节点
    public DListNode removeTail() {

        DListNode tailPrev = tail.prev;
        remove(tailPrev);
        return tailPrev;
    }

    //获取
    public int get(int key) {

        DListNode node = map.get(key);

        if (node == null) {
            return -1;
        } else {

        }
        removeToHead(node);
        return node.value;
    }

    public void removeToHead(DListNode node) {
        remove(node);
        addToHead(node);
    }

    //将指定节点，添加到head
    public void addToHead(DListNode node) {
        //先拿到head和下一个

        DListNode headNext = head.next;

        node.prev = head;

        node.next = headNext;

        headNext.prev = node;

        head.next = node;
    }


    public static void main(String[] args) {
        //测试
//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.put(1, 1); // 缓存是 {1=1}
//        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//        lRUCache.get(1);    // 返回 1
//        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//        lRUCache.get(2);    // 返回 -1 (未找到)
//        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//        lRUCache.get(1);    // 返回 -1 (未找到)
//        lRUCache.get(3);    // 返回 3
//        lRUCache.get(4);    // 返回 4

        int i = 1;

        System.out.println(++i);
    }
}

package com.aha.tom.link.singleList;

import java.util.LinkedList;

public class LruLinkedList<T> extends SingleLinkedList<T> {

    int maxSize;
    private static final int DEFAULT_SIZE = 6;

    public LruLinkedList() {
        this(DEFAULT_SIZE);
    }
    public LruLinkedList(int maxSize) {
        this.maxSize = maxSize;
    }


    /**
     * 新增数据，头部添加，淘汰尾部数据
     * @param data
     */
    public void lurPut(T data){

        if(size >= maxSize){
            remove();
            put(data);
        }else{
            put(data);
        }
    }

    public T lruGet(int index){
        checkPositionIndex(index);
        //获取数据节点
        Node pre = list;
        Node cur = list;
        for(int i = 0; i < index; i++){
            pre = cur;
            cur = cur.next;
        }
        pre.next = cur.next;
        //获取头节点
        Node head = list;
        //将当前获取数据的节点的下节点指向头节点
        cur.next = head;
        //将cur置为头节点
        list = cur;
        return cur.data;
    }

    public void lruRemove(){
       remove();
    }



    public static void main(String[] args) {
        LruLinkedList<Integer> lruLinkedList = new LruLinkedList<>(5);
        for(int i = 0; i <4; i++) {
            lruLinkedList.lurPut(i);
        }
//        lruLinkedList.toString();
        System.out.println(lruLinkedList.lruGet(3));
        lruLinkedList.toString();
        lruLinkedList.lurPut(20);
        lruLinkedList.toString();
//
        lruLinkedList.lurPut(18);
        lruLinkedList.toString();

    }
}
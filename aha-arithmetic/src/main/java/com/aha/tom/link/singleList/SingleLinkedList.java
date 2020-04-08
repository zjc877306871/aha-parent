package com.aha.tom.link.singleList;

public class SingleLinkedList<T> {

    int size;
    Node list;
    class Node{
         T data;
         Node next;

        Node(T data, Node node) {
            this.data = data;
            this.next = node;
        }
    }

    /**
     * 默认新增到头节点
     */
    public void put(T data){
        Node head = list;
        Node curNode = new Node(data, head);
        //将头节点只想占位符
        list = curNode;
        size++;
    }

    /**
     * 插入指定位置
     * @param index
     * @param data
     */
    public void put(int index, T data){
        //校验位置的合法性
        checkPositionIndex(index);
        Node pre = list;
        Node curNode = list;
        for(int i = 0; i < index; i++){
            pre = curNode;
           curNode = curNode.next;
        }
        Node node = new Node(data, curNode);
        pre.next = node;
        size++;

    }

    public void checkPositionIndex(int index) {
        if(!(index >= 0 && index < size)){
            throw new IndexOutOfBoundsException("index" + index + "out of size" + size);
        }
    }

    /**
     * 更新指定位置数据
     * @param index
     * @param data
     */
    public void set(int index, T data){

        //校验位置的合法性
        checkPositionIndex(index);
        Node pre = list;
        for(int i = 0; i < index; i++){
            pre = pre.next;
        }
        pre.data = data;
    }

    /**
     * 获取头节点数据
     * @return
     */
    public T get(){
        Node node = list;
        if(node != null){
            return node.data;
        }else {
            return null;
        }

    }

    public T get(int index){
        //校验位置的合法性
        checkPositionIndex(index);
        Node node = list;
        for(int i = 0; i < index; i++){
            node = node.next;
        }
        if(node != null){
            return node.data;
        }else {
            return null;
        }

    }

    /**
     * 删除最后的节点
     */
    public void remove(){
        if(list != null){
            Node pre = list;
            Node cur = list;
            for(int i = 0; i < size; i++){
                pre = cur;
                cur = cur.next;
            }
            pre.next = null;
            size--;
        }

    }

    /**
     * 删除指定位置数据
     * @param index
     */
    public void remove(int index){

        checkPositionIndex(index);
        if(list != null){
            Node pre = list;
            Node cur = list;
            for(int i = 0; i < index; i++){
                pre = cur;
                cur = cur.next;
            }
            pre.next = cur.next;
            cur.next = null;  //消除cur对下一节点的数据持有，便于对cur节点的GC
            size--;
        }
    }

    @Override
    public String toString() {
        Node node = list;
        for (int i = 0; i < size; i++) {
            System.out.print(node.data + " ");
//			System.out.print(" ");
            node = node.next;
        }
        System.out.println();
        return super.toString();
    }
}
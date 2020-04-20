package com.aha.tom.queue;

public class MyCircularDeque<T> {

    private Node tail;
    private Node head;
    int size;
    int capacity;
    static final int DEFAULT_CAPACITY = 7;

    public MyCircularDeque(int capacity) {
        this.capacity = capacity;
        tail = new Node(-1);
        head = new Node(-1);
        this.size = 0;
        tail.prev = head;
        head.next = tail;
    }

    public MyCircularDeque() {
        this(DEFAULT_CAPACITY);
    }


    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {

        if(size < capacity){
            Node next = head.next;
            Node node = new Node(value);
            size++;
            node.prev = head;
            node.next = next;
            head.next = node;
            next.prev = node;
            return true;

        }

        return false;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(size < capacity){
            Node node = new Node(value);
            size++;
            Node prev = tail.prev;
            node.next= tail;
            node.prev = prev;
            tail.prev = node;
            prev.next = node;
            return true;
        }

        return false;

    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {

        if(size > 0){
            Node node = head.next;
            Node next = node.next;
            next.prev = head;
            head.next = next;
            size--;
            node.prev = null;
            node.next = null;
            return true;
        }
        return false;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {

        if(size > 0){
            Node node = tail.prev;
            node.prev.next = tail;
            tail.prev = node.prev;
            node.prev = null;
            node.next = null;
            size--;
            return true;

        }
        return false;

    }

    /** Get the front item from the deque. */
    public int getFront() {

        Node node = head.next;
        return (int) node.value;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        Node node = tail.prev;
        return (int) node.value;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        if(size > 0){
            return false;
        }
        return true;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {

        if(size < capacity){
            return false;
        }
        return true;
    }

    class Node<T>{
        T value;
        Node prev;
        Node next;

        Node(T data){
            this.value = data;
        }

    }




    public static void main(String[] args) {
        MyCircularDeque<Integer> deque = new MyCircularDeque(6);
        deque.insertFront(4);
        deque.insertFront(5);
        boolean empty = deque.isEmpty();
        int front = deque.getFront();
        System.out.println("front is " + front + "isEmpty " + empty);
        ThreadLocal local = new ThreadLocal();
        local.set("22");

    }

}
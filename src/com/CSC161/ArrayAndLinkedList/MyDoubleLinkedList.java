package com.CSC161.ArrayAndLinkedList;

import java.util.*;

public class MyDoubleLinkedList<E> implements List<E> {

    private class Node {
        public E data;
        public Node next;
        public Node prev;

        public Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public MyDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(E element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        Node newNode = new Node(element);
        if (index == 0) {
            if (head != null) head.prev = newNode;
            newNode.next = head;
            head = newNode;
            if (tail == null) tail = newNode;
        } else if (index == size) {
            add(element);
            return;
        } else {
            Node node = getNode(index);
            Node prevNode = node.prev;
            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = node;
            node.prev = newNode;
        }
        size++;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node node = head;
        for (int i = 0; i < index; i++) node = node.next;
        return node;
    }

    @Override
    public E get(int index) {
        return getNode(index).data;
    }

    @Override
    public boolean remove(Object obj) {
        Node node = head;
        while (node != null) {
            if (equals(obj, node.data)) {
                if (node == head) head = node.next;
                if (node == tail) tail = node.prev;
                if (node.prev != null) node.prev.next = node.next;
                if (node.next != null) node.next.prev = node.prev;
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        Node node = getNode(index);
        if (node == head) head = node.next;
        if (node == tail) tail = node.prev;
        if (node.prev != null) node.prev.next = node.next;
        if (node.next != null) node.next.prev = node.prev;
        size--;
        return node.data;
    }

    public void reverse() {
        Node current = head, temp = null;
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        if (temp != null) head = temp.prev;
    }

    public int count() {
        return size;
    }

    public ArrayList<E> toArrayList() {
        ArrayList<E> list = new ArrayList<>();
        Node node = head;
        while (node != null) {
            list.add(node.data);
            node = node.next;
        }
        return list;
    }

    public void fromArrayList(ArrayList<E> arr) {
        clear();
        for (E e : arr) add(e);
    }

    @Override public int size() { return size; }
    @Override public void clear() { head = tail = null; size = 0; }
    @Override public boolean isEmpty() { return size == 0; }
    @Override public boolean contains(Object o) { return indexOf(o) != -1; }
    @Override public int indexOf(Object target) {
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (equals(target, node.data)) return i;
            node = node.next;
        }
        return -1;
    }
    private boolean equals(Object target, Object element) {
        if (target == null) return element == null;
        return target.equals(element);
    }

    @Override public Iterator<E> iterator() { return toArrayList().iterator(); }
    @Override public Object[] toArray() { return toArrayList().toArray(); }
    @Override public <T> T[] toArray(T[] a) { return toArrayList().toArray(a); }
    @Override public boolean addAll(Collection<? extends E> c) { throw new UnsupportedOperationException(); }
    @Override public boolean addAll(int index, Collection<? extends E> c) { throw new UnsupportedOperationException(); }
    @Override public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public E set(int index, E element) { throw new UnsupportedOperationException(); }
    @Override public int lastIndexOf(Object o) { throw new UnsupportedOperationException(); }
    @Override public ListIterator<E> listIterator() { throw new UnsupportedOperationException(); }
    @Override public ListIterator<E> listIterator(int index) { throw new UnsupportedOperationException(); }
    @Override public List<E> subList(int fromIndex, int toIndex) { throw new UnsupportedOperationException(); }
}

package uz.pdp.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class SinglyLinkedList<E> implements Iterable<E>{

    private Node<E> head;
    private int size;

    public boolean add(E element) {
        var newNode = new Node<>(element);
        if (this.head == null) {
            this.head = newNode;
        } else {
            var current = this.head;
            while(current.next != null)
                current = current.next;
            current.next = newNode;
        }
        this.size++;
        return true;
    }

    public boolean set(int index, E element) {
        Objects.checkIndex(index, size);
        Node<E> current = head;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        current.element = element;
        return true;

    }

    public E removeFromBeginning() {
        if (head == null)
            throw new NoSuchElementException("Linked list is empty");
        E element = head.element;
        head = head.next;
        size--;
        return element;
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) return removeFromBeginning();
        var current = head;
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        var deletingNode = current.next;
        current.next = deletingNode.next;
        size--;
        return deletingNode.element;
    }

    public boolean remove(Object o) {
        if (head == null) return false;
        Node<E> prev = null;
        Node<E> current = head;
        while (current != null) {
            if (Objects.equals(o, current.element)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean addAtBeginning(E element) {
        var newNode = new Node<>(element);
        if (head != null)
            newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        if (index == 0)
            return head.element;
        var current = head;
        for (int i = 1; i <= index; i++)
            current = current.next;
        return current.element;
    }

    private static class Node<E> {

        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    @Override
    public String toString(){
        var sj = new StringJoiner(",", "[", "]");
        var current = this.head;
        while (current != null){
            sj.add(String.valueOf(current.element));
            current = current.next;
        }

        return sj.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = current.element;
                current = current.next;
                return data;
            }
        };
    }

    public static void main(String[] args) {
        var sll = new SinglyLinkedList<String>();
        sll.add("java");
        sll.add("python");
        System.out.println(sll.size());
    }
}

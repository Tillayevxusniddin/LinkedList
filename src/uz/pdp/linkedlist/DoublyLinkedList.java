package uz.pdp.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class DoublyLinkedList<E> implements Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    // methods

    public boolean add(E element) {
        Node<E> oldTail = tail;
        Node<E> newNode = new Node<>(element);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
            newNode.prev = oldTail;
        }
        size++;
        return true;
    }

    public boolean set(int index, E element) {
        Objects.checkIndex(index, size);
        Node<E> current = getNode(index);
        current.element = element;
        return true;
    }

    public E removeFromBeginning() {
        if (head == null) {
            throw new NoSuchElementException("Linkes list is empty");
        }
        E element = head.element;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return element;
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            return removeFromBeginning();
        }
        if (index == size - 1) {
            return removeFromEnd();
        }
        Node<E> nodeToDelete = getNode(index);
        Node<E> previousNode = nodeToDelete.prev;
        Node<E> nextNode = nodeToDelete.next;
        previousNode.next = nextNode;
        nextNode.prev = previousNode;
        size--;
        return nodeToDelete.element;
    }

    public E removeFromEnd() {
        if (tail == null) {
            throw new NoSuchElementException("Linked list is empty");
        }
        E element = tail.element;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return element;

    }

    public boolean remove(Object object) {
        Node<E> current = head;
        while (current != null) {
            if (Objects.equals(object, current.element)) {
                if (current == head) {
                    removeFromBeginning();
                    return true;
                }
                if (current == tail) {
                    removeFromEnd();
                    return true;
                }
                Node<E> previousNode = current.prev;
                Node<E> nextNode = current.next;
                previousNode.next = nextNode;
                nextNode.prev = previousNode;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean addAtBeginning(E element) {
        Node<E> oldHead = head;
        Node<E> newNode = new Node<>(element);
        head = newNode;
        if (oldHead == null) {
            tail = newNode;
        } else {
            newNode.next = oldHead;
            oldHead.prev = newNode;
        }
        size++;
        return true;
    }




    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = getNode(index);
        return node.element;
    }

    private Node<E> getNode(int index) {
        Objects.checkIndex(index, size);
        if (index < size / 2) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }
        Node <E> current = tail;
        for(int i = size - 1; i > index; i--) {
            current = current.prev;
        }
        return current;
    }

    // Node Class
    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
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
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    @Override
    public String toString() {
        var sj = new StringJoiner(",", "[", "]");
        this.forEach(lang -> sj.add(String.valueOf(lang)));
        return sj.toString();
    }

    public static void main(String[] args) {

        var dll = new DoublyLinkedList<String>();

        dll.add("Java");
        dll.add("Python");
        dll.add("C++");
        System.out.println(dll);
        System.out.println("Size: " + dll.size());
        System.out.println("Index 1: " + dll.get(1));
        dll.set(1, "JavaScript");
        System.out.println(dll);
        dll.addAtBeginning("C");
        System.out.println(dll);
        dll.removeFromBeginning();
        System.out.println(dll);
        dll.removeFromEnd();
        System.out.println(dll);
        dll.remove(1);
        System.out.println(dll);
        dll.remove("Java");
        System.out.println(dll);
    }

}

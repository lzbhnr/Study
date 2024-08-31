package rigth;

import java.util.HashMap;

public class LRU<T> {
    private int size;
    private HashMap<Node, T> hashMap = new HashMap<>();

    private Node head = new Node();

    private void set(String key, T value) {
        Node node = new Node();
        node.setKey(key);

        T t = hashMap.get(node);
        if (t == null && hashMap.keySet().size() < size) {
            // 没有达到最大值
            hashMap.put(node, value);
            insertHead(head, node);
            size++;
        } else {
            Node pre = node.getPre();
            Node next = node.getNext();
            pre.setNext(next);
            next.setPre(pre);
            insertHead(head, node);
            hashMap.put(node, value);
        }
    }

    public T get(String key) {
        Node node = new Node();
        node.setKey(key);
        T t = hashMap.get(key);
        if (t == null) {
            return null;
        } else {
            insertHead(head, node);
            return t;
        }
    }

    private void insertHead(Node head, Node node) {
        Node realHead = head.getNext();
        realHead.setPre(node);
        node.setNext(realHead);
        node.setPre(head);
        head.setNext(node);
    }
}

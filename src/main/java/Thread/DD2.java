package Thread;

import java.util.concurrent.TimeUnit;

public class DD2 {

    // 带超时时间的 hashMap

    // get 参数

    // set key value int ttl

    volatile Node[] table = null;

    // 负载因子
    private float loadFactor = 0.75f;

    public String get(String key) {
        if (table == null) {
            return null;
        }
        int hash = key.hashCode();
        int index = hash & (table.length - 1);
        for (Node node = table[index]; node != null; node = node.next) {
            if (node.key == key || (node.key.equals(key) && node.hashValue == hash)) {
                if (System.currentTimeMillis() <= node.getOutDateTime()) {
                    return node.getValue();
                }
            }
        }
        return null;
    }
    public void set(String key, String value, int time) {
        if (table == null) {
            table = new Node[16];
        }
        int hash = key.hashCode();
        int index = hash & (table.length - 1);
        Node newNode = new Node(key, value, System.currentTimeMillis() + time * TimeUnit.SECONDS.toMillis(1), hash);
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node node = table[index];
            Node preNode = null;
            for (; node != null; ) {

                if (node.key == key || (node.key.equals(key) && node.hashValue == hash)) {
                    node.value = value;
                    node.outDateTime = getOutTime(time);
                    return;
                }
                preNode = node;
                node = node.next;
            }
            preNode.next = newNode;
        }

    }

    public long getOutTime(int time) {
        return System.currentTimeMillis() + time * TimeUnit.SECONDS.toMillis(1);
    }


    class Node {
        private String key;
        private volatile String value;
        private volatile long outDateTime;
        private volatile int hashValue;
        private volatile Node next;
        public Node(String key, String value, long outDateTime, int hashValue) {
            this.key = key;
            this.value = value;
            this.outDateTime = outDateTime;
            this.hashValue = hashValue;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public long getOutDateTime() {
            return outDateTime;
        }

        public void setOutDateTime(long outDateTime) {
            this.outDateTime = outDateTime;
        }

        public int getHashValue() {
            return hashValue;
        }

        public void setHashValue(int hashValue) {
            this.hashValue = hashValue;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}

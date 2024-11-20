package ferdig;

public class HashMap<K,V> {
    public static void main(String[] args) {}

    private static class Node<K,V>{
        K key; V value;
        Node<K,V> next;
        public Node(K key, V value, Node<K,V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    Node<K,V>[] table;

    @SuppressWarnings("unchecked")
    public HashMap(int size){
        table=new Node[size];
    }
    public boolean insert(K key, V value){
        Node<K,V> n= getNode(key);
        if(n==null){
            int hash=key.hashCode();
            hash=fixHash(hash);
            table[hash]=new Node<K,V>(key,value,table[hash]);
            return true;
        }
        else{
            n.value=value;
            return false;}
    }

    private Node<K,V> getNode(K key){
        int hash = key.hashCode();
        hash=fixHash(hash);
        Node<K,V> node=table[hash];
        while(node!=null){
            if(node.key.equals(key)){
                break;
            }
            node=node.next;
        }
        return node;
    }

    public V get (K key){
        Node<K,V> n=getNode(key);
        return n == null ? null : n.value;
    }

    public int fixHash(int old){
        int newHash = old % table.length;
        if (newHash<0) newHash = table.length+newHash;
        return newHash;
    }

    public V pop(K key){
        int hash = key.hashCode();
        hash = fixHash(hash);

        Node<K, V> current = table[hash];
        Node<K, V> prev = null;

        // Traverse the linked list at the hash index
        while (current != null) {
            if (current.key.equals(key)) {
                // Found the node to remove
                if (prev == null) {
                    // The node to remove is the head of the list
                    table[hash] = current.next;
                } else {
                    // The node to remove is in the middle or end
                    prev.next = current.next;
                }
                return current.value; // Return the value of the removed node
            }
            prev = current;
            current = current.next;
        }

        return null; // Key not found
    }


    }




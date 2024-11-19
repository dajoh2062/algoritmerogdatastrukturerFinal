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

    }

    public int fixHash(int old){
        int newHash = old % table.length;
        if (newHash<0) newHash = table.length+newHash;
        return newHash;
    }

    public V pop(K key){}



}

public class Node <T> {
    //non-contiguous nodes with memory addresses
    T data;
    Node next;

    public Node(T data){
        this.data = data;
        this.next = null;
    }
    public Node(T data, Node next){
        this.data = data;
        this.next = next;
    }
}
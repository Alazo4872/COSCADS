public class LinkedList<T> implements LinkedIterator <T>{
    Node head;
    int size;
    Node current;

    LinkedList() {
        this.head = null;
        this.size = 0;
    }

    LinkedList(Node head) {
        this.head = head;
        size++;
        current = head;

    }

    public int size() {
        return size;
    }

    public void prepend(Node newNode) {
        newNode.next = head;
        head = newNode;
        current = head;

        size++;
    }
    public void append(Node newNode){
        if(head == null){
            head = newNode;
            current = head;
        }else {
            newNode.next = null;
            Node ptr = head;
            while (ptr.next != null) {
                ptr = ptr.next;
            }
            ptr.next = newNode;
        }
        size++;
    }
    public void insertAt(Node newNode, Node prevNode){
        if (prevNode == null){
            System.out.println("wrong");
            return;
        }
        newNode.next = prevNode.next;
        prevNode.next = newNode;
        size++;

    }
    public void resetIterator() {
        current = head;
    }

    public void deleteAt(Node prevNode){
        Node temp = prevNode.next;
        prevNode.next = prevNode.next.next;
        temp.next= null;
        size--;
    }
    public void deleteAtTail() {
        if(head ==null){
            return;
        }else if(head.next == null){
            head = null;
            size--;
            return;
        }
        Node ptr = head;
        while (ptr.next.next != null) {
            ptr = ptr.next;
        }
        ptr.next=null;
        size--;
    }
    public void clear(){
        head = null;
        size=0;
    }
    public void printList(){
        if(head == null) {
            System.out.println("List is empty");
            return;
        }
        Node ptr = head;
        while (ptr.next != null){
            System.out.println(ptr.data);
            ptr = ptr.next;
        }
    }

    @Override
    public boolean hasNext() {
        return current!=null;
    }

    @Override
    public Object getNext() {
        if(current == null){
            current = head;
        }
        if(!hasNext()){
            current = head;
            return null;

        }
        Object data = current.data;
        current = current.next;
        return data;
    }
}
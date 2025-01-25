public class MainPart2 {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        Node node = new Node<>("Apple");
        Node node1 = new Node<>("Orange");
        Node node2 = new Node<>("Grape");
        Node node3 = new Node<>("Banana");
        Node node4 = new Node<>("Peach");
        Node node5 = new Node<>("Tomato");

        linkedList.head = node;
        linkedList.append(node1);
        linkedList.append(node2);
        linkedList.append(node3);
        linkedList.append(node4);
        linkedList.append(node5);

        //assigns "current" to head node.
        linkedList.resetIterator();

        while(linkedList.hasNext()){
            System.out.println(linkedList.getNext());
        }



    }
}

public class SequenceList<E> {

    private Node<E> head = null;
    int size = 0;

    public void add(E data){
        Node<E> newNode = new Node <>(data);
        newNode.next = this.head;
        this.head = newNode;
        size++;
    }

    static class Node<E>{
        Node<E> next;
        E data;
        Node(E data){
            this.data = data;
            this.next = null;
        }
    }

    public int size(){
        return this.size;
    }

    public Node<E> get(){
        return this.head;
    }

    public E get(int index){
        if(index >= size){
            return null;
        }
        E sequence = null;
        Node<E> tempHead = null;
        for(int i = 0; i < index; i++){
            if(tempHead == null){
                tempHead = this.head;
            }
            else{
                tempHead = tempHead.next;
            }
            if(i == index - 1){
                sequence = tempHead.data;
            }
        }
        return sequence;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        Node<E> current = this.head;
        while(current.next != null){
            result.append(current.data);
            current = current.next;
            if(current.next != null){
                result.append("\n");
            }
            else{
                result.append("\n");
                result.append(current.data);
            }
        }
        return result.toString();
    }
}

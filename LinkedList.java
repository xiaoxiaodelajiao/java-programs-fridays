class Node{
    int value;
    Node next;
    
    Node(int value, Node next){
        this.value = value;
        this.next = next;
        this.length = next.length + 1;
    }
    
    static void print(Node node){
        if (node == null){
            System.out.println("Null");
        }
        else if(node.next == null){
            System.out.println(node.value);
        }
        else{
            System.out.print(node.value + " -> ");
            Node.print(node.next);
        }
    }
    
    static int sum(Node node){
        if (node == null){
            return 0;
        }
        return node.value + sum(node.next);
    }
    
    //O(n)
    
    static void append(Node node, int value){
        if(node.next == null){
            node.next = new Node(value, null);
        }
        else{
            append(node.next, value);
        }
    }
    
    static int length(Node node){
        return node.length;
    }
    
    //O(n)
    /*
    static int length(Node node){
        if(node == null) return 0;
        return  1 + length(node.next);
    }
    */
    
}

public class LinkedList{
    int length;
    Node head;
    Node tail;
    
    LinkedList(){
        this.length = 0;
        this.head = null;
        this.tail = null;
    }
    
    int getLength(){
        return length;
    }
    
    void append(int value){
        if(length == 0){
            head = new Node(value, null);
            tail = head
            length++;
        }
        else{
            tail.next = new Node(value, null);
            tail = tail.next;
            length++;
        }
    }
    
    void appendToFront(int value){
        if(length == 0){
            head = new Node(value, null);
            tail = head
            length++;
        }
        else{
            head = new Node(value, head);
            length++;
        }
    }
    
    int pop(){
        if(length == 0){
            return 0;
        }
        int n = head.value;
        head = head.next;
        length --;
        return n;
    }
    
    int getValue(int index){
        if(length == 0){
            return 0;
        }
        return getValue(index, head);
    }
    
    int getValueHelper(int index, Node node){
        if(index == 0){
            return node.value;
        }
        return getValueHelper(index-1, node.next);
    }

    public static void main(String []args){
    }    
}
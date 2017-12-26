/**
 * Created by Amer on 23-Dec-17.
 */
public class Main {

    static public void main(String args[]){
        new Main();
    }

    private Main(){
        Node a = new Node(15);
        Node b = new Node(12);

        Node r = mergeLists(a, b);

        while(r != null){
            System.out.println(r.data);
            r = r.next;
        }
    }

    Node mergeLists(Node headA, Node headB) {

        Node A = headA;
        Node B = headB;
        Node startNode = null;

        if(A == null){
            return B;
        } else if(B == null) {
            return A;
        } else if(A.data <= B.data){
            startNode = A;
            A = A.next;
        } else {
            startNode = B;
            B = B.next;
        }
        Node currNode = startNode;

        while(A != null || B != null){
            if(A == null){
                currNode.next = B;
                break;
            } else if(B == null) {
                currNode.next = A;
                break;
            } else if(A.data <= B.data){
                currNode.next = A;
                currNode = A;
                A = A.next;
            } else {
                currNode.next = B;
                currNode = B;
                B = B.next;
            }
        }
        return startNode;

    }
}

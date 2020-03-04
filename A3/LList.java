package LinkedList;

/*  Name(s):Siyu Liu && Xinyun Guo
 * Netid(s):sl3282 && xg284
 * What I thought about this assignment:
 *  This assignment's document is very clear and easy to follow.
 *  This assignment is not hard and very helpful in understanding Linked Lists.
 *
 */

/** An instance is a doubly linked list. */
public class LList<E> {
    /** Replace "-1" by the time you spent on A3 in hours.<br>
     * Example: for 3 hours 15 minutes, use 3.25<br>
     * Example: for 4 hours 30 minutes, use 4.50<br>
     * Example: for 5 hours, use 5 or 5.0 */
    public static double timeSpent= 2.5;

    private Node head; // first node of linked list (null if size is 0)
    private Node tail; // last node of linked list (null if size is 0)
    private int size;  // Number of values in the linked list.

    /** Constructor: an empty linked list. No need to change this. */
    public LList() {}

    /** = the number of values in this list. <br>
     * This function takes constant time. */
    public int size() {
        return size;
    }

    /** = the first node of the list (null if the list is empty). */
    public Node head() {
        return head;
    }

    /** = the last node of the list (null if the list is empty). */
    public Node tail() {
        return tail;
    }

    /** Return the value of node n of this list. <br>
     * Precondition: n is a node of this list; it may not be null. */
    public E value(Node n) {
        assert n != null;
        return n.val;
    }

    /** Return a representation of this list: its values, <br>
     * with adjacent ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * Takes time proportional to the length of this list.<br>
     * E.g. for the list containing 4 7 8 in that order, the result it "[4, 7, 8]". <br>
     * E.g. for the list containing two empty strings, the result is "[, ]" */
    @Override
    public String toString() {
        String res= "[";
        Node n= head;
        // inv: res contains values of nodes before node n (all of them if n = null),
        // with ", " after each (except for the last value)
        while (n != null) {
            res= res + n.val;
            n= n.succ;
            if (n != null) {
                res= res + ", ";
            }
        }

        return res + "]";
    }

    /** Return a representation of this list: its values in reverse, <br>
     * with adjacent ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * Note that toStringR is the reverse of toString. <br>
     * Takes time proportional to the length of this list. <br>
     * E.g. for the list containing 4 7 8 in that order, the result is "[8, 7, 4]". <br>
     * E.g. for the list containing two empty strings, the result is "[, ]". */
    public String toStringR() { // Note:
        // TODO 1. Look at toString to see how that was written.
        // Use the same scheme. Extreme case to watch out for:
        // E is String and values are the empty string.
        // You can't test this fully until #2, append, is written.
        String res= "[";
        Node n= tail;
        while (n != null) {
            res= res + n.val;
            n= n.pred;
            if (n != null) {
                res= res + ", ";
            }
        }
        return res + "]";
    }

    /** Add v to the end of this list. <br>
     * This operation takes constant time.<br>
     * E.g. if the list is [8, 7, 4], append(2) changes this list to [8, 7, 4, 2]. */
    public void append(E v) {
        // TODO 2. After writing this method, test this method and
        // method toStringR thoroughly before starting on the next
        // method. These two must be correct in order to be
        // able to write and test all the others.
        if (head == null) {
            Node n= new Node(null, v, null);
            head= n;
            tail= n;
            size++ ;
        } else {
            Node n= new Node(tail, v, null);
            tail.succ= n;
            tail= n;
            size++ ;
        }
    }

    /** Insert v at the beginning of the list. <br>
     * This operation takes constant time.<br>
     * E.g. if the list is [8, 7, 4], prepend(2) changes this list to [2, 8, 7, 4]. */
    public void prepend(E v) {
        // TODO 3. This is the third method to write and test.
        // Test it thoroughly before writing any others.
        if (head == null) {
            Node n= new Node(null, v, null);
            head= n;
            tail= n;
            size++ ;
        } else {
            Node n= new Node(null, v, head);
            head.pred= n;
            head= n;
            size++ ;
        }

    }

    /** Return node number h. <br>
     * Precondition: 0 <= h < size of the list. <br>
     * Note: If h is 0, return first node; if h = 1, return second node, ... */
    public Node getNode(int h) {
        // TODO 4. This method should take time proportional to min(h, size-h).
        // For example, if h < size/2, search from the beginning of the
        // list, otherwise search from the end of the list. If h = size/2,
        // search from either end; it doesn't matter.
        assert h >= 0 && h < size;
        Node temp;
        int dir= size / 2;
        if (h <= dir) {
            temp= head;
            for (int i= 0; i < h; i++ ) {
                temp= temp.succ;
            }
        } else {
            temp= tail;
            for (int i= 0; i < size - 1 - h; i++ ) {
                temp= temp.pred;
            }
        }
        return temp;
    }

    /** Remove node n from this list. <br>
     * This operation must take constant time. <br>
     * Precondition: n must be a node of this list; it may not be null. */
    public void remove(Node n) {
        // TODO 5. Make sure this method takes constant time.
        assert n != null;
        if (head == n && tail == n) {
            head= null;
            tail= null;
            size-- ;
        } else if (head == n) {
            head= n.succ;
            n.succ.pred= null;
            size-- ;
        } else if (tail == n) {
            tail= n.pred;
            n.pred.succ= null;
            size-- ;
        } else {
            n.succ.pred= n.pred;
            n.pred.succ= n.succ;
            size-- ;
        }
    }

    /** Insert value v in before node after node n. <br>
     * This operation takes constant time. <br>
     * Precondition: n must be a node of this list; it may not be null.<br>
     * E.g. if the list is [3, 8, 2] and n points to the node with 8 in it, <br>
     * and v is 1, the list is changed to [3, 1, 8, 2] */
    public void insertBefore(E v, Node n) {
        // TODO 6. Make sure this method takes constant time.
        assert n != null;
        Node newnode= new Node(null, v, n);
        if (head == n) {
            n.pred= newnode;
            head= newnode;
        } else {
            newnode.pred= n.pred;
            n.pred.succ= newnode;
            n.pred= newnode;
        }
        size++ ;
    }

    /*********************/

    /** An instance is a node of this list. */
    public class Node {
        private Node pred; // Previous node (predecessor) on list (null if this is first node).
        private E val;     // The value of this node
        private Node succ; // Next node (successor) on list (null if this is last node).

        /** Constructor: an instance with predecessor p (can be null), value v, and <br>
         * successor s (can be null). */
        Node(Node p, E v, Node s) {
            pred= p;
            val= v;
            succ= s;
        }

        /** = the predecessor of this node (null if this is the first node of the list). */
        public Node pred() {
            return pred;
        }

        /** = the value of this node. */
        public E value() {
            return val;
        }

        /** = the next node in this list (null if this is the last node of this list). */
        public Node succ() {
            return succ;
        }
    }

}

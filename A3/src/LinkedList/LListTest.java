package LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LListTest {

    @Test
    public void testConstructor() {
        LList<Integer> b= new LList<>();
        assertEquals("[]", b.toString());
        assertEquals("[]", b.toStringR());
        assertEquals(0, b.size());
    }

    @Test
    public void testappend() {
        LList<String> ll= new LList<>();
        ll.append("Bracy");
        assertEquals("[Bracy]", ll.toString());
        assertEquals("[Bracy]", ll.toStringR());
        assertEquals(1, ll.size());
        ll.append("Joe");
        assertEquals("[Bracy, Joe]", ll.toString());
        assertEquals("[Joe, Bracy]", ll.toStringR());
        assertEquals(2, ll.size());
    }

    @Test
    public void testPrepend() {
        LList<String> ll= new LList<>();
        ll.prepend("cat");
        ll.prepend("dog");
        assertEquals("[dog, cat]", ll.toString());
        assertEquals("[cat, dog]", ll.toStringR());
        assertEquals(2, ll.size());
    }

    @Test
    public void testgetnode() {
        LList<Integer> ll= new LList<>();
        ll.append(4);
        ll.append(9);
        ll.append(7);
        ll.append(22);
        assertEquals(4, ll.getNode(0).value());
        assertEquals(9, ll.getNode(1).value());
        assertEquals(7, ll.getNode(2).value());
    }

    @Test
    public void testremovenode() {
        LList<Integer> ll= new LList<>();
        ll.append(4);
        ll.append(9);
        ll.append(7);
        ll.append(13);
        LList<Integer>.Node n0= ll.getNode(0);
        LList<Integer>.Node n1= ll.getNode(2);
        LList<Integer>.Node n2= ll.getNode(3);
        ll.remove(n0);
        assertEquals("[9, 7, 13]", ll.toString());
        assertEquals(3, ll.size());
        ll.remove(n1);
        assertEquals("[9, 13]", ll.toString());
        assertEquals(2, ll.size());
        ll.remove(n2);
        assertEquals("[9]", ll.toString());
        assertEquals(1, ll.size());
    }

    @Test
    public void testinsertbefore() {
        LList<Integer> ll= new LList<>();
        ll.append(3);
        ll.append(5);
        ll.append(7);
        LList<Integer>.Node n0= ll.getNode(0);
        LList<Integer>.Node n1= ll.getNode(1);
        LList<Integer>.Node n2= ll.getNode(2);
        ll.insertBefore(2, n0);
        assertEquals("[2, 3, 5, 7]", ll.toString());
        assertEquals(4, ll.size());
        ll.insertBefore(4, n1);
        assertEquals("[2, 3, 4, 5, 7]", ll.toString());
        assertEquals(5, ll.size());
        ll.insertBefore(6, n2);
        assertEquals("[2, 3, 4, 5, 6, 7]", ll.toString());
        assertEquals(6, ll.size());
    }
}

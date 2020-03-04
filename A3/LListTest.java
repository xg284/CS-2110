package LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void testAppend() {
        LList<String> ll= new LList<>();
        ll.append("Bracy");
        assertEquals("[Bracy]", ll.toString());
        assertEquals("[Bracy]", ll.toStringR());
        assertEquals(1, ll.size());

        ll.append("");
        assertEquals("[Bracy, ]", ll.toString());
        assertEquals("[, Bracy]", ll.toStringR());
        assertEquals(2, ll.size());

        LList<Integer> ll1= new LList<>();
        ll1.append(0);
        assertEquals("[0]", ll1.toString());
        assertEquals("[0]", ll1.toStringR());
        assertEquals(1, ll1.size());

        ll1.append(1);
        assertEquals("[0, 1]", ll1.toString());
        assertEquals("[1, 0]", ll1.toStringR());
        assertEquals(2, ll1.size());
    }

    @Test
    public void testPrepend() {
        LList<String> ll= new LList<>();
        ll.prepend("Bracy");
        assertEquals("[Bracy]", ll.toString());
        assertEquals("[Bracy]", ll.toStringR());
        assertEquals(1, ll.size());

        ll.prepend("");
        assertEquals("[, Bracy]", ll.toString());
        assertEquals("[Bracy, ]", ll.toStringR());
        assertEquals(2, ll.size());

        LList<Integer> ll1= new LList<>();
        ll1.prepend(0);
        assertEquals("[0]", ll1.toString());
        assertEquals("[0]", ll1.toStringR());
        assertEquals(1, ll1.size());

        ll1.prepend(1);
        assertEquals("[1, 0]", ll1.toString());
        assertEquals("[0, 1]", ll1.toStringR());
        assertEquals(2, ll1.size());
    }

    @Test
    public void testgetNode() {
        LList<String> ll= new LList<>();
        assertThrows(AssertionError.class,
            () -> { ll.getNode(-1); });
        assertThrows(AssertionError.class,
            () -> { ll.getNode(0); });

        ll.append("Hello");
        ll.append("World");
        ll.append("!");
        assertEquals("Hello", ll.getNode(0).value());
        assertEquals("World", ll.getNode(1).value());
        assertEquals("!", ll.getNode(2).value());

        LList<Integer> ll1= new LList<>();
        assertThrows(AssertionError.class,
            () -> { ll1.getNode(-1); });
        assertThrows(AssertionError.class,
            () -> { ll1.getNode(0); });

        ll1.append(0);
        ll1.append(1);
        ll1.append(2);
        assertEquals(0, ll1.getNode(0).value());
        assertEquals(1, ll1.getNode(1).value());
        assertEquals(2, ll1.getNode(2).value());
    }

    @Test
    public void testremove() {
        LList<String> ll= new LList<>();
        assertThrows(AssertionError.class,
            () -> { ll.remove(null); });
        assertThrows(AssertionError.class,
            () -> { ll.remove(ll.head()); });
        assertThrows(AssertionError.class,
            () -> { ll.remove(ll.tail()); });

        ll.append("Hello");
        ll.append("World");
        ll.append("!");
        assertEquals("[Hello, World, !]", ll.toString());
        assertEquals("[!, World, Hello]", ll.toStringR());
        assertEquals(3, ll.size());

        ll.remove(ll.head());
        assertEquals("[World, !]", ll.toString());
        assertEquals("[!, World]", ll.toStringR());
        assertEquals(2, ll.size());

        ll.remove(ll.getNode(0));
        assertEquals("[!]", ll.toString());
        assertEquals("[!]", ll.toStringR());
        assertEquals(1, ll.size());

        ll.remove(ll.tail());
        assertEquals("[]", ll.toString());
        assertEquals("[]", ll.toStringR());
        assertEquals(0, ll.size());

        assertThrows(AssertionError.class,
            () -> { ll.remove(ll.head()); });
        assertThrows(AssertionError.class,
            () -> { ll.remove(ll.tail()); });
    }

    @Test
    public void testinsertBefore() {
        LList<String> ll= new LList<>();
        assertThrows(AssertionError.class,
            () -> { ll.insertBefore("Hi", null); });
        assertThrows(AssertionError.class,
            () -> { ll.insertBefore("Hi", ll.head()); });

        ll.append("Hello");
        ll.append("World");
        assertEquals("[Hello, World]", ll.toString());
        assertEquals("[World, Hello]", ll.toStringR());
        assertEquals(2, ll.size());

        ll.insertBefore("", ll.getNode(0));

        assertEquals("[, Hello, World]", ll.toString());
        assertEquals("[World, Hello, ]", ll.toStringR());
        assertEquals(3, ll.size());

        ll.insertBefore("Beautiful", ll.getNode(2));
        assertEquals("[, Hello, Beautiful, World]", ll.toString());
        assertEquals("[World, Beautiful, Hello, ]", ll.toStringR());
        assertEquals(4, ll.size());
    }

}

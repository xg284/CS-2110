import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class BugTreeTest {

    private static Network n;
    private static Person[] people;
    private static Person personA;
    private static Person personB;
    private static Person personC;
    private static Person personD;
    private static Person personE;
    private static Person personF;
    private static Person personG;
    private static Person personH;
    private static Person personI;
    private static Person personJ;
    private static Person personK;
    private static Person personL;

    @BeforeClass
    public static void setup() {
        n= new Network();
        people= new Person[] { new Person("A", 0, n),
                new Person("B", 0, n), new Person("C", 0, n),
                new Person("D", 0, n), new Person("E", 0, n), new Person("F", 0, n),
                new Person("G", 0, n), new Person("H", 0, n), new Person("I", 0, n),
                new Person("J", 0, n), new Person("K", 0, n), new Person("L", 0, n)
        };
        personA= people[0];
        personB= people[1];
        personC= people[2];
        personD= people[3];
        personE= people[4];
        personF= people[5];
        personG= people[6];
        personH= people[7];
        personI= people[8];
        personJ= people[9];
        personK= people[10];
        personL= people[11];
    }

    @Test
    public void testBuiltInGetters() {
        BugTree st= new BugTree(personB);
        assertEquals("B", toStringBrief(st));
    }

    // A.sh(B, C) = A
    // A.sh(D, F) = B
    // A.sh(D, I) = B
    // A.sh(H, I) = H
    // A.sh(D, C) = A
    // B.sh(B, C) = null
    // B.sh(I, E) = B

    /** Create a BugTree with structure A[B[D E F[G[H[I]]]] C] <br>
     * This is the tree
     *
     * <pre>
     *            A
     *          /   \
     *         B     C
     *       / | \
     *      D  E  F
     *            |
     *            G
     *            |
     *            H
     *            |
     *            I
     * </pre>
     */
    private BugTree makeTree1() {
        BugTree dt= new BugTree(personA); // A
        dt.add(personB, personA); // A, B
        dt.add(personC, personA); // A, C
        dt.add(personD, personB); // B, D
        dt.add(personE, personB); // B, E
        dt.add(personF, personB); // B, F
        dt.add(personG, personF); // F, G
        dt.add(personH, personG); // G, H
        dt.add(personI, personH); // H, I
        return new BugTree(dt);
    }

    @Test
    /** test a call on makeTree1(). */
    public void testMakeTree1() {
        BugTree dt= makeTree1();
        assertEquals("A[B[D E F[G[H[I]]]] C]", toStringBrief(dt));
    }

    @Test
    public void test1Add() {
        BugTree st= new BugTree(personB);

        // Test add to root
        BugTree dt2= st.add(personC, personB);
        assertEquals("B[C]", toStringBrief(st)); // test tree
        assertEquals(personC, dt2.getRoot());  // test return value
        //
        st.add(personD, personB);
        assertEquals("B[C D]", toStringBrief(st)); // test tree
        st.add(personE, personC);
        assertEquals("B[C[E] D]", toStringBrief(st)); // test tree

    }

    @Test
    public void test2size() {
        BugTree st= new BugTree(personC);
        assertEquals(1, st.size());
        //
        st.add(personB, personC);
        assertEquals(2, st.size());
        st.add(personD, personC);
        assertEquals(3, st.size());
        st.add(personE, personB);
        assertEquals(4, st.size());

    }

    @Test
    public void test3isInTree() {
        BugTree st= new BugTree(personC);
        assertEquals(true, st.isInTree(personC));
        //
        st.add(personB, personC);
        st.add(personD, personC);
        st.add(personE, personB);
        st.add(personF, personB);
        assertEquals(true, st.isInTree(personB));
        assertEquals(true, st.isInTree(personD));
        assertEquals(true, st.isInTree(personE));
        assertEquals(true, st.isInTree(personF));
        assertEquals(false, st.isInTree(personH));
    }

    @Test
    public void test4depth() {
        BugTree st= new BugTree(personB);
        BugTree dt2= st.add(personC, personB);
        st.add(personD, personC);
        assertEquals(0, st.depth(personB));
        //
        assertEquals(-1, st.depth(personH));
        assertEquals(0, dt2.depth(personC));
        assertEquals(1, st.depth(personC));
        assertEquals(2, st.depth(personD));
        st.add(personE, personC);
        assertEquals(2, st.depth(personE));
        st.add(personF, personD);
        assertEquals(3, st.depth(personF));
        BugTree dt= makeTree1();
        assertEquals(1, dt.depth(personB));
        assertEquals(2, dt.depth(personD));
        assertEquals(2, dt.depth(personF));
        assertEquals(3, dt.depth(personG));
        assertEquals(5, dt.depth(personI));
    }

    @Test
    public void test5WidthAtDepth() {
        BugTree st= new BugTree(personB);
        assertEquals(1, st.widthAtDepth(0));

        assertThrows(IllegalArgumentException.class, () -> st.widthAtDepth(-1));
        //
        st.add(personC, personB);
        st.add(personD, personB);
        st.add(personH, personB);
        st.add(personE, personC);
        st.add(personF, personC);
        st.add(personG, personD);
        assertEquals(1, st.widthAtDepth(0));
        assertEquals(3, st.widthAtDepth(1));
        assertEquals(3, st.widthAtDepth(2));
        BugTree dt= makeTree1();
        assertEquals(1, dt.widthAtDepth(0));
        assertEquals(2, dt.widthAtDepth(1));
        assertEquals(3, dt.widthAtDepth(2));
        assertEquals(1, dt.widthAtDepth(3));
        assertEquals(1, dt.widthAtDepth(5));
    }

    @Test
    public void test6bugRouteTo() {
        BugTree st= new BugTree(personB);
        BugTree dt2= st.add(personC, personB);
        List route= st.bugRouteTo(personB);
        assertEquals("[B]", getNames(route));
        BugTree dt= makeTree1();
        List route2= dt.bugRouteTo(personJ);
        assertEquals(null, route2);
        List route3= dt.bugRouteTo(personD);
        assertEquals("[A, B, D]", getNames(route3));
        List route4= dt.bugRouteTo(personG);
        assertEquals("[A, B, F, G]", getNames(route4));
    }

    /** Return the names of Persons in sp, separated by ", " and delimited by [ ]. Precondition: No
     * name is the empty string. */
    private String getNames(List<Person> sp) {
        String res= "[";
        for (Person p : sp) {
            if (res.length() > 1) res= res + ", ";
            res= res + p.getName();
        }
        return res + "]";
    }

    @Test
    public void test7sharedAncestor() {
        BugTree st= new BugTree(personB);
        BugTree dt2= st.add(personC, personB);
        Person p= st.sharedAncestor(personC, personC);
        assertEquals(personC, p);
        //
        st.add(personD, personC);
        st.add(personH, personC);
        st.add(personF, personB);
        st.add(personG, personF);
        assertEquals(personB, st.sharedAncestor(personB, personC));
        assertEquals(personB, st.sharedAncestor(personD, personF));
        assertEquals(personF, st.sharedAncestor(personF, personG));
        assertEquals(personB, st.sharedAncestor(personD, personG));
        assertEquals(personC, st.sharedAncestor(personD, personH));
        assertEquals(null, st.sharedAncestor(personD, personI));
        assertEquals(null, dt2.sharedAncestor(personD, personF));
    }

    @Test
    public void test8equals() {
        BugTree treeB1= new BugTree(personB);
        BugTree treeB2= new BugTree(personB);
        assertEquals(true, treeB1.equals(treeB2));
        //
        assertEquals(false, treeB1.equals(personB));
        BugTree treeB3= new BugTree(personC);
        assertEquals(false, treeB1.equals(treeB3));
        BugTree treeB4= new BugTree(personB);
        treeB1.add(personC, personB);
        treeB4.add(personC, personB);
        treeB4.add(personE, personB);
        assertEquals(false, treeB1.equals(treeB4));
        treeB2.add(personD, personB);
        assertEquals(false, treeB1.equals(treeB2));
        treeB1.add(personD, personB);
        treeB2.add(personC, personB);
        assertEquals(true, treeB1.equals(treeB2));
        treeB1.add(personE, personC);
        treeB2.add(personF, personD);
        assertEquals(false, treeB1.equals(treeB2));
        treeB1.add(personF, personD);
        treeB2.add(personE, personC);
        assertEquals(true, treeB1.equals(treeB2));
        treeB1.add(personG, personB);
        assertEquals(false, treeB1.equals(treeB2));
        treeB1.add(personH, personG);
        assertEquals(false, treeB1.equals(treeB2));
        treeB2.add(personG, personB);
        assertEquals(false, treeB1.equals(treeB2));
        treeB2.add(personH, personG);
        assertEquals(true, treeB1.equals(treeB2));
        treeB1.add(personI, personG);
        treeB2.add(personI, personH);
        assertEquals(false, treeB1.equals(treeB2));

    }

    // ===================================
    // ==================================

    /** Return a representation of this tree. This representation is: <br>
     * (1) the name of the Person at the root, followed by <br>
     * (2) the representations of the children <br>
     * . (in alphabetical order of the children's names). <br>
     * . There are two cases concerning the children.
     *
     * . No children? Their representation is the empty string. <br>
     * . Children? Their representation is the representation of each child, <br>
     * . with a blank between adjacent ones and delimited by "[" and "]". <br>
     * <br>
     * Examples: One-node tree: "A" <br>
     * root A with children B, C, D: "A[B C D]" <br>
     * root A with children B, C, D and B has a child F: "A[B[F] C D]" */
    public static String toStringBrief(BugTree t) {
        String res= t.getRoot().getName();

        Object[] childs= t.copyOfChildren().toArray();
        if (childs.length == 0) return res;
        res= res + "[";
        selectionSort1(childs);

        for (int k= 0; k < childs.length; k= k + 1) {
            if (k > 0) res= res + " ";
            res= res + toStringBrief((BugTree) childs[k]);
        }
        return res + "]";
    }

    /** Sort b --put its elements in ascending order. <br>
     * Sort on the name of the Person at the root of each BugTree.<br>
     * Throw a cast-class exception if b's elements are not BugTree */
    public static void selectionSort1(Object[] b) {
        int j= 0;
        // {inv P: b[0..j-1] is sorted and b[0..j-1] <= b[j..]}
        // 0---------------j--------------- b.length
        // inv : b | sorted, <= | >= |
        // --------------------------------
        while (j != b.length) {
            // Put into p the index of smallest element in b[j..]
            int p= j;
            for (int i= j + 1; i != b.length; i++ ) {
                String bi= ((BugTree) b[i]).getRoot().getName();
                String bp= ((BugTree) b[p]).getRoot().getName();
                if (bi.compareTo(bp) < 0) {
                    p= i;

                }
            }
            // Swap b[j] and b[p]
            Object t= b[j];
            b[j]= b[p];
            b[p]= t;
            j= j + 1;
        }
    }

}

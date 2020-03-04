import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class RhinoTest {

    @Test
    void testGroupA() {
        Rhino testConstructor1= new Rhino("testConstructor1", 1980, 7, 'F');
        assertEquals("testConstructor1", testConstructor1.getName());
        assertEquals(true, testConstructor1.isFemale());
        assertEquals(7, testConstructor1.getMOB());
        assertEquals(1980, testConstructor1.getYOB());
        assertEquals(null, testConstructor1.getMom());
        assertEquals(null, testConstructor1.getPop());
        assertEquals(0, testConstructor1.numChildren());
    }

    @Test
    void testGroupB() {
        Rhino testMother= new Rhino("testMother", 1981, 8, 'F');
        Rhino testFather= new Rhino("testFather", 1981, 9, 'M');
        Rhino testSetter= new Rhino("testSetter", 1995, 10, 'M');
        testSetter.setMom(testMother);
        testSetter.setPop(testFather);
        assertEquals(testMother, testSetter.getMom());
        assertEquals(testFather, testSetter.getPop());
        assertEquals(1, testMother.numChildren());
        assertEquals(1, testFather.numChildren());
    }

    @Test
    void testGroupC() {
        Rhino testMother1= new Rhino("testMother", 1981, 8, 'F');
        Rhino testFather1= new Rhino("testFather", 1981, 9, 'M');
        Rhino testConstructor2= new Rhino("testConstructor2", 1996, 11, 'M',
            testMother1, testFather1);
        assertEquals("testConstructor2", testConstructor2.getName());
        assertEquals(false, testConstructor2.isFemale());
        assertEquals(11, testConstructor2.getMOB());
        assertEquals(1996, testConstructor2.getYOB());
        assertEquals(testMother1, testConstructor2.getMom());
        assertEquals(testFather1, testConstructor2.getPop());
        assertEquals(1, testMother1.numChildren());
        assertEquals(1, testFather1.numChildren());
    }

    @Test
    void testGroupD() {
        // test isOrder
        Rhino feb77= new Rhino("feb77", 1977, 2, 'F');
        assertEquals(false, feb77.isOlder(feb77));
        Rhino mar77= new Rhino("mar77", 1977, 3, 'F');
        assertEquals(true, feb77.isOlder(mar77));
        Rhino jan77= new Rhino("jan77", 1977, 1, 'F');
        assertEquals(false, feb77.isOlder(jan77));
        Rhino feb78= new Rhino("feb78", 1978, 2, 'F');
        assertEquals(true, feb77.isOlder(feb78));
        Rhino mar78= new Rhino("mar78", 1978, 3, 'F');
        assertEquals(true, feb77.isOlder(mar78));
        Rhino jan78= new Rhino("jan78", 1978, 1, 'F');
        assertEquals(true, feb77.isOlder(jan78));
        Rhino feb76= new Rhino("feb77", 1976, 2, 'F');
        assertEquals(false, feb77.isOlder(feb76));
        Rhino mar76= new Rhino("mar76", 1976, 3, 'F');
        assertEquals(false, feb77.isOlder(mar76));
        Rhino jan76= new Rhino("jan76", 1976, 1, 'F');
        assertEquals(false, feb77.isOlder(jan76));

        // test areSiblings
        Rhino testMom= new Rhino("testMom", 1977, 6, 'F');
        Rhino testPop= new Rhino("testPop", 1975, 12, 'M');
        assertEquals(false, testMom.areSiblings(testPop));
        Rhino testChild1= new Rhino("testChild1", 1998, 5, 'M', testMom,
            testPop);
        assertEquals(false, testChild1.areSiblings(testChild1));
        Rhino testChild2= new Rhino("testChild2", 1999, 10, 'F');
        testChild2.setMom(testMom);
        assertEquals(true, testChild1.areSiblings(testChild2));
        Rhino testChild3= new Rhino("testChild3", 2000, 4, 'F');
        testChild3.setPop(testPop);
        assertEquals(true, testChild1.areSiblings(testChild3));
        Rhino testChild4= new Rhino("testChild4", 2001, 5, 'M');
        testChild4.setMom(testChild2);
        assertEquals(false, testChild1.areSiblings(testChild4));
        Rhino testChild5= new Rhino("testChild5", 2002, 6, 'F');
        testChild5.setPop(testChild4);
        assertEquals(false, testChild1.areSiblings(testChild5));

    }

    @Test
    void testAssertStatement() {
        assertThrows(AssertionError.class,
            () -> { new Rhino(null, 1990, 8, 'F'); });
        assertThrows(AssertionError.class,
            () -> { new Rhino("", 1990, 8, 'F'); });
        assertThrows(AssertionError.class,
            () -> { new Rhino("test", 1990, 0, 'F'); });
        assertThrows(AssertionError.class,
            () -> { new Rhino("test", 1990, 13, 'F'); });
        assertThrows(AssertionError.class,
            () -> { new Rhino("test", 1990, 8, 'A'); });
        Rhino testMother= new Rhino("testMother", 1970, 8, 'F');
        Rhino testFather= new Rhino("testFather", 1970, 8, 'M');
        Rhino testSetter1= new Rhino("testSetter1", 1990, 9, 'M');
        Rhino testSetter2= new Rhino("testSetter2", 1990, 12, 'F', testMother,
            testFather);
        assertThrows(AssertionError.class,
            () -> { testSetter1.setMom(null); });
        assertThrows(AssertionError.class,
            () -> { testSetter1.setPop(null); });
        assertThrows(AssertionError.class,
            () -> { testSetter1.setMom(testFather); });
        assertThrows(AssertionError.class,
            () -> { testSetter1.setPop(testMother); });
        assertThrows(AssertionError.class,
            () -> { testSetter2.setMom(testMother); });
        assertThrows(AssertionError.class,
            () -> { testSetter2.setPop(testFather); });
        assertThrows(AssertionError.class,
            () -> { new Rhino("test", 1990, 8, 'F', null, testFather); });
        assertThrows(AssertionError.class,
            () -> { new Rhino("test", 1990, 8, 'F', testMother, null); });
        assertThrows(AssertionError.class,
            () -> { new Rhino("test", 1990, 8, 'F', testFather, testFather); });
        assertThrows(AssertionError.class,
            () -> { new Rhino("test", 1990, 8, 'F', testMother, testMother); });
        assertThrows(AssertionError.class,
            () -> { testMother.isOlder(null); });

    }

}

/** NetId: sl3282, xg284. Time spent: 04 hours, 30 minutes. * What I thought about this assignment: The instruction is very helpful but the homework really needs time to do it completely.
*
*/
/** An instance maintains info about the rhino. */
public class Rhino {
    /** Name of this rhino. Must contain at least 1 character. */
    private String name;
    /** Gender of this rhino. 'F' for female, 'M' for male. */
    private char gender;
    /** Year of birth. Can be any integer. */
    private int year;
    /** Month of birth. 1 for Jan, 2 for Feb, ..., 12 for Dec. */
    private int month;
    /** Mother of this rhino —null if unknown. */
    private Rhino mom;
    /** Father of this rhino —null if unknown. */
    private Rhino pop;
    /** Number of known children of this rhino. */
    private int children;

    /** Group A */
    /** Constructor: a new Rhino with name n, birth year y, birth month m, and gender g.<br>
     * Its father and mother are unknown, and it has no children.<br>
     * Precondition: n has at least one character in it, m is 1 for Jan, 2 for Feb, etc.,<br>
     * and g is 'F' or 'M' for female or male */
    Rhino(String n, int y, int m, char g) {
        assert n != null && n.length() >= 1;
        assert g == 'F' || g == 'M';
        assert m == 1 || m == 2 || m == 3 || m == 4 || m == 5 || m == 6 ||
            m == 7 | m == 8 || m == 9 || m == 10 || m == 11 || m == 12;
        name= n;
        gender= g;
        year= y;
        month= m;
    }

    /** = the name of this rhino. */
    public String getName() {
        return name;
    }

    /** = this Rhino is female */
    public boolean isFemale() {
        if (gender == 'F')
            return true;
        else
            return false;
    }

    /** = the month this rhino was born in the range 1..12. */
    public int getMOB() {
        return month;
    }

    /** = the year this rhino was born. */
    public int getYOB() {
        return year;
    }

    /** = (pointer to) the object for mother of this rhino. */
    public Rhino getMom() {
        return mom;
    }

    /** = (pointer to) the object for father of this rhino. */
    public Rhino getPop() {
        return pop;
    }

    /** = the number of known children of this rhino. */
    public int numChildren() {
        return children;
    }

    /** Group B */
    /** Set the rhino's mom to mother.<br>
     * Precondition: this rhino's mom is null and mother is not null and<br>
     * mother is female. */
    public void setMom(Rhino mother) {
        assert mom == null && mother != null && mother.gender == 'F';
        mom= mother;
        mother.children++ ;
    }

    /** Set this rhino's dad to father.<br>
     * Precondition: this rhino's dad is null and father is not null and<br>
     * father is male. */
    public void setPop(Rhino father) {
        assert pop == null && father != null && father.gender == 'M';
        pop= father;
        father.children++ ;
    }

    /** Group C */
    /** Constructor: a new Rhino with name n, birth year y, birth month m, gender g,<br>
     * mother mother, father father, and no children.<br>
     * Precondition: n has at least one character in it, m is 1 for Jan, 2 for Feb, etc., <br>
     * g is 'F' or 'M' for female or male, and <br>
     * mother is non-null and female, and <br>
     * father is non-null and male). */
    Rhino(String n, int y, int m, char g, Rhino mother, Rhino father) {
        assert n != null && n.length() >= 1;
        assert g == 'F' || g == 'M';
        assert m == 1 || m == 2 || m == 3 || m == 4 || m == 5 || m == 6 ||
            m == 7 | m == 8 || m == 9 || m == 10 || m == 11 || m == 12;
        assert mother != null && mother.gender == 'F';
        assert father != null && father.gender == 'M';
        name= n;
        year= y;
        month= m;
        gender= g;
        mom= mother;
        pop= father;
        mother.children++ ;
        father.children++ ;
    }

    /** Group D */
    /** = "This rhino was born before r." <br>
     * Precondition: r is not null. */
    public boolean isOlder(Rhino r) {
        assert r != null;
        return year < r.year || year == r.year && month < r.month;
    }

    /** = "r is not null and this rhino and r are siblings. " */
    public boolean areSiblings(Rhino r) {
        return r != null && this != r && (mom == r.mom || pop == r.pop) &&
            (mom != null ||
                pop != null) &&
            (r.mom != null || r.pop != null);
    }

}

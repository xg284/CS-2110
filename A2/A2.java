import java.util.Arrays;

/* NetIds: xg284, sl3282.
 * What did you think of this assignment:
 * Some comments are not clear enough and is a little hard to understand.
 */

/** A collection of static functions. <br>
 * All methods assume that String parameters are non-null.
 *
 * If any method is called with arguments that do not satisfy the Preconditions,<br>
 * the behavior is undefined (the method can do anything). You do not have to use assert<br>
 * statements to test preconditions. We will not test with test cases that do <br>
 * not satisfy Preconditions. */
public class A2 {
    /* Wherever possible, prefer library functions to writing your own loops.
     *
     * Each function you write has a //ToDo comment. Look on the right; click a blue rectangle to
     * get to the corresponding //TODO comment. DO NOT DELETE THESE COMMENTS.
     *
     * The more complicated your loops become, the more important it is to
     * explain the logic in comments.
     *
     * See the JavaHyperText entries for if-statement, while-loop, and for-loop.
     * Use of the break-statement and continue-statement is discouraged but not
     * forbidden. They make loops and programs harder to understand. Usually,
     * they can be eliminated by restructuring/reorganizing code. */

    /** Replace "-1" by the time you spent on A2 in hours.<br>
     * Example: for 3 hours 15 minutes, use 3.25<br>
     * Example: for 4 hours 30 minutes, use 4.50<br>
     * Example: for 5 hours, use 5 or 5.0 */
    public static double timeSpent= 3.25;

    /** Return either s1 + s2 or s1 - s2, depending on b. <br>
     * If b is true, return the sum, otherwise return the difference. */
    public static int sumDif(boolean b, int s1, int s2) {
        // This method is already implemented; it is here to
        // show you different ways of writing simple code.
        if (b) {
            int s;
            s= s1 + s2;
            return s;

            /* equivalently: int s = s1 + s2; return s;
             *
             * or simply: return s1 + s2;
             */
        }

        // b is false;
        return s1 - s2;
    }

    /** Return true iff (i.e. if and only if) s has an odd number of characters and<br>
     * the three middle ones (if they exist) are all different. <br>
     * Examples: <br>
     * For s = "" return false <br>
     * For s = "$" return true <br>
     * For s = "23" return false <br>
     * For s = "2A2" return false <br>
     * For s = "22A" return false <br>
     * For s = "A22" return false <br>
     * For s = "AAA" return false <br>
     * For s = "ABC" return true <br>
     * For s = "ABCD" return false <br>
     * For s = "abcdefg2A%abcdefk" return true <br>
     * For s = "abcdefg2A2abcdefk" return false <br>
     * For s = "abcdefg22Aabcdefk" return false <br>
     * For s = "abcdefgA22abcdefk" return false <br>
     * For s = "abcdefgAAAabcdefk" return false <br>
     * For s = "abcdefg2A%abcdefk" return true <br>
     * For s = "abcdefg2A%abcdefgh" return false */
    public static boolean isMidDiff(String s) {
        // TODO 1. There is no need for a loop. Do not use a loop.
        // This can be done cleanly in 8 statements or less.
        // Hint: Follow these Principles:
        // Principle: Avoid unnecessary case analysis
        // Principle: Avoid the same expression in several places.
        // Principle: Keep the structure of the method as simple as possible.
        if (s.length() == 1)
            return true;
        else if (s.length() % 2 == 1) {
            int mid= (s.length() - 1) / 2;
            return s.charAt(mid - 1) != s.charAt(mid) &&
                s.charAt(mid) != s.charAt(mid + 1) &&
                s.charAt(mid - 1) != s.charAt(mid + 1);
        }
        return false;
    }

    /** Return s but, for each character that is a lower-case letter in a..z, <br>
     * insert the corresponding upper-case letter before it. <br>
     * Examples: <br>
     * For s = "", return "". <br>
     * For s = "b", return "Bb". <br>
     * For s = "B", return "B". <br>
     * For s = "å", return "å". <br>
     * For s = "$", return "$" <br>
     * For s = "1ABCDEFx", return "1ABCDEFXx".<br>
     * For s = "1Z$Bby", return "1Z$BByYy"<br>
     * For s = "abcdefghijklmnopqrstuvwxyz", <br>
     * ......... return "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz" */
    public static String addCapsToSmalls(String s) {
        // TODO 2.
        String s1= "abcdefghijklmnopqrstuvwxyz";
        String s2= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result= new String();
        for (int count= 0; count < s.length(); count++ ) {
            int pos= s1.indexOf(s.charAt(count));
            if (pos != -1) {
                result= result + s2.charAt(pos);
            }
            result+= s.charAt(count);

        }

        return result;
    }

    /** Return s but with each occurrence of a letter in 'b'..'z' replaced by<br>
     * the previous letter and 'a' replaced by 'z'.<br>
     * Examples: <br>
     * prevLowerCase("") = "" <br>
     * prevLowerCase("åç") = "åç" <br>
     * prevLowerCase("abcdefghijklmnopqrstuvwxyz") = "zabcdefghijklmnopqrstuvwxy" <br>
     * prevLowerCase("ABCDEFGHIJKLMNOPQRSTUVWXYZ") = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" <br>
     * prevLowerCase("1z$aàēĤƀ") = "1y$zàēĤƀ" */
    public static String prevLowerCase(String s) {
        // TODO 3. Hint: Follow these Principles:
        // Principle: Avoid unnecessary case analysis like the plague.
        // Principle: Avoid "magic numbers" ---the use of int constants for characters.
        // Principle: Use short names where long mnemonic names are unnecessary.
        String s1= "zabcdefghijklmnopqrstuvwxy";
        String result= new String();
        for (int count= 0; count < s.length(); count++ ) {
            int pos= s1.indexOf(s.charAt(count));
            if (pos != -1) {
                result= result + s1.charAt((pos + 25) % 26);
            } else {
                result= result + s.charAt(count);
            }
        }
        return result;
    }

    /** Precondition: s and s1 are not null. <br>
     * Return true iff s contains exactly one occurrence of s1. <br>
     * Examples: For s = "" and s1 = "", return true <br>
     * For s = "a" and s1 = "", return false. This is weird! <br>
     * .... The empty string occurs before and after each character! <br>
     * For s = "abc" and s1 = "", return false <br>
     * For s = "abbb" and s1 = "c", return false. <br>
     * For s = "acbb" and s1 = "c", return true. <br>
     * For s = "abbb" and s1 = "ab", return true. <br>
     * For s = "" and s1 = "a", return false. <br>
     * For s = "aaa" and s1 = "aa", return false. <br>
     * For s = "abbbabc" and s1 = "ab", return false. */
    public static boolean exactly1(String s, String s1) {
        // TODO 4 Do not use a loop or recursion. Instead, look through the
        // methods of class String and see how you can tell that the first
        // and last occurrences of s1 in s are the same occurrence. Be sure you handle
        // correctly the case that s1 does not occur in s.
        //
        // Hint: Follow this Principle:
        // Principle: Be aware of efficiency considerations.
        // Note that a call like s.indexOf(s1) may take time proportional to the
        // length of string s. If s contains 1,000 characters and s1 contains 5, then
        // about 9996 tests may have to be made in the worst case. So you don't want
        // to have the same method call executed several times. Even the call of
        // contains in the code below is wasteful.
        //
        // if (s.contains(s1)) {
        // int k= s.indexOf(s1);
        // ...
        // }
        int pos1= s.indexOf(s1);
        if (pos1 != -1) {
            int pos2= s.lastIndexOf(s1);
            return pos1 == pos2;
        }
        return false;
    }

    /** Return true iff s and u are anagrams.<br>
     * Note: 2 strings are anagrams of each other if swapping the characters<br>
     * around in one changes it into the other.<br>
     * Note: 'a' and 'A' are different chars, and the space ' ' is a character.
     *
     * Examples: For s = "noon", u = "noon", return true. <br>
     * For s = "mary", u = "army", return true. <br>
     * For s = "tom marvolo riddle", u = "i am lordvoldemort", return true. <br>
     * For s = "tommarvoloriddle", u = "i am lordvoldemort", return false. <br>
     * For s = "hello", u = "world", return false. */
    public static boolean areAnagrams(String s, String u) {
        // TODO 5
        /* Do not use a loop or recursion! This can be done in
         * five lines using methods of classes String and Arrays
         * (https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Arrays.html).
         * Hint: how can a sequence of characters be uniquely ordered? You might
         * need to first convert the string to an array of characters and then
         * use methods in class Arrays. */
        char[] array1= s.toCharArray();
        char[] array2= u.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);
        return Arrays.equals(array1, array2);
    }

    /** Return true iff s consists of y catenated with itself a number (> 0) of times.<br>
     * Examples: <br>
     * isCat("", "") is true<br>
     * isCat("x", "") is false<br>
     * isCat("x", "x") is true <br>
     * isCat("xxx", "") is false<br>
     * isCat("", "x") is false <br>
     * isCat("xx", "x") is true <br>
     * isCat("ccbbbb", "bb") is false <br>
     * isCat("bbbbcc", "bb") is false <br>
     * isCat("bbbbbb", "bb") is true <br>
     * isCat("bbbbbb", "bbb") is true <br>
     * isCat("bbbbbb", "bbbb") is false <br>
     * isCat("bbbbbb", "bbbbb") is false <br>
     * isCat("bbbbbb", "bbbbbb") is true <br>
     * isCat("bbbbbb", "bbbbbbb") is false <br>
     * isCat("xyzxyz", "xyz") is true <br>
     * isCat("xyzxyz", "xyzxyz") is true <br>
     * For any string s, isCat(s, s) is true */
    public static boolean isCat(String s, String y) {
        // TODO 6. Hint: Follow this Principle:
        // Make the structure of a loop reflect the structure of the data it processes.
        // Use function equals, not ==, to test equality of strings.
        int yl= y.length();

        // if(s==null && y == null) return true;
        if (yl != 0 && s.length() != 0) {
            if (s.length() % yl == 0) {
                for (int count= 0; count < s.length(); count+= yl) {
                    String s1= s.substring(count, count + yl);
                    if (!s1.equals(y)) { return false; }
                }
                return true;
            }
        } else if (s.length() == 0 && yl == 0) { return true; }
        return false;

    }

    /** Return the shortest substring x of s such that s = x + x + ... + x. <br>
     * Examples: For s = "" return ""<br>
     * For s = "xxxxxxxxx" return "x" <br>
     * For s = "xyxyxyxy" return "xy" <br>
     * For s = "012012012012" return "012" <br>
     * For s = "hellohellohello" return "hello" <br>
     * For s = "hellohelloworld" return "hellohelloworld" <br>
     * For s = "hellohel" return "hellohel" */
    public static String findShort(String s) {
        // TODO 7.
        // To implement this one, start checking for the shortest
        // substring to have length 1, then 2, then 3, and stop when
        // the answer is found. To make each of those checks,
        // use the previous method IsCat.
        // Note that IsCat(s, s) is true, for any s.
        for (int i= 0; i < s.length(); i++ ) {
            if (isCat(s, s.substring(0, i + 1))) {
                return s.substring(0, i + 1);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println("weird thing: " + "h".indexOf("", 9));
    }
}

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String n= sc.nextLine();
        String st= sc.nextLine();
        for (int i= 0; i < n.length(); i++ ) {
            while (n.charAt(i) == ' ') {
                n= n.substring(0, i) + n.substring(i + 1);
            }
        }
        int max= 0;
        if (n.length() < st.length())
            System.out.println(max);
        else {
            while (n.contains(st)) {
                n= n.substring(n.indexOf(st) + 1);
                max++ ;
            }
            System.out.println(max);
        }
    }
}
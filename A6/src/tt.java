import java.util.Scanner;

public class tt {
    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        String n= in.nextLine();
        int[] t= { 0, 0, 0, 0, 0 };
        int k= 0;
        while (n.length() > 0) {
            for (int i= 0; i < n.length(); i++ ) {
                if (n.charAt(i) == '|') {
                    t[k]= Integer.parseInt(n.substring(0, i));
                    n= n.substring(i + 1);
                    k++ ;
                    break;
                }
            }
            if (k == 4) {
                t[k]= Integer.parseInt(n);
                break;
            }
        }
        int talk_time= t[1] % 60 == 0 ? t[1] / 60 : t[1] / 60 + 1;
        int data= t[3] / 1024;
        int talk_payment= 0;
        if (talk_time < 5) {
            talk_payment+= t[2] * 1.5 * 4;
        } else if (talk_time < 10) {
            talk_payment+= t[2] * 1.5 * 4 + t[2] * 5;
        } else if (talk_time < 20) {
            talk_payment+= t[2] * 1.5 * 4 + t[2] * 5 + t[2] * 0.5 * 10;
        } else {
            talk_payment+= t[2] * 1.5 * 4 + t[2] * 5 + t[2] * 0.5 * 10 +
                t[2] * 0.2 * (talk_time - 20);
        }

        int data_payment= 0;
        if (data < 100) {
            data_payment= t[4] * data;
        }
        if (data >= 100) {
            data-= 25;
            if (data >= 200) {
                data-= 100;
                if (data >= 500) {
                    data= data - 500 == 0 ? 0 : (data - 500) / 100;
                    data_payment+= t[4] * 500 * 1024 + t[4] * data * 1024 * 100;
                } else {
                    data_payment+= t[4] * data;
                }
            } else {
                data_payment+= t[4] * data;
            }
        }
        System.out.println(talk_payment / 10 + data_payment / 100);
    }

}
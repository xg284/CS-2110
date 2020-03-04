
/** import java.util.Scanner;
 *
 * public class oa { public static int GetlongestSymStr(String str) { int n= str.length();
 * boolean[][] dp= new boolean[n][n]; int maxLen= 0; for (int i= 0; i < n; i++ ) { for (int j= i; j
 * >= 0; j-- ) { if (str.charAt(i) == str.charAt(j) && (i - j < 2 || dp[j + 1][i - 1] == true)) {
 * dp[j][i]= true; maxLen= Math.max(maxLen, i - j + 1); } } } return maxLen; }
 *
 * public static void main(String[] args) { Scanner sc= new Scanner(System.in); String s=
 * sc.nextLine(); System.out.println(GetlongestSymStr(s)); } } **/

/** import java.util.Scanner;
 *
 * public class oa {
 *
 * public static String SequenceOfDownload(int numOfLL, int[] needLL, int[] reword) {
 *
 * return ZeroOnePack(numOfLL, needLL.length, needLL, reword); }
 *
 * public static String ZeroOnePack(int V, int N, int[] weight, int[] value) {
 *
 * int[][] dp= new int[N + 1][V + 1]; System.out.print(N + " "); System.out.println(V); for (int i=
 * 1; i < N + 1; i++ ) { for (int j= 1; j < V + 1; j++ ) { if (weight[i - 1] > j) { dp[i][j]= dp[i -
 * 1][j]; } else { dp[i][j]= Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]); }
 * } } int maxValue= dp[N][V]; int j= V; String numStr= ""; for (int i= N; i > 0; i-- ) { if
 * (dp[i][j] > dp[i - 1][j]) { numStr= i + " " + numStr; j= j - weight[i - 1]; } if (j == 0) break;
 * } return numStr; }
 *
 * public static void main(String[] args) { Scanner sc= new Scanner(System.in); int numOfLL=
 * Integer.parseInt(sc.nextLine()); String str2= sc.nextLine(); String str3= sc.nextLine(); String[]
 * needLL; String[] reword; needLL= str2.split(" "); reword= str3.split(" ");
 *
 * int[] Reword= new int[needLL.length]; int[] NeedLL= new int[reword.length]; for (int i= 0; i <
 * needLL.length; i++ ) { NeedLL[i]= Integer.parseInt(needLL[i]); Reword[i]=
 * Integer.parseInt(reword[i]); } String result= SequenceOfDownload(numOfLL, NeedLL, Reword);
 * result= result.substring(0, result.length() - 1); System.out.println(result); sc.close(); } } */

/** created on Wed Mip1r 13 19:43:05 2019 huawei1 - 100%
 *
 * @author: youxinlin """
 *
 *          ip1,ip2,maskip = input().split()
 *
 *          ip1 = ip1.split('.') ip2 = ip2.split('.') maskip = maskip.split('.')
 *
 *          new_ip1 = [] new_ip2 = []
 *
 *          for i in range(len(ip1)): new_ip1.append(str(int(ip1[i])&int(maskip[i])))
 *          new_ip2.append(str(int(ip2[i])&int(maskip[i])))
 *
 *          res = 1 for i in range(len(ip1)): if new_ip1[i]!=new_ip2[i]: res = 0 break
 *          print(res,'.'.join(new_ip1)) ———————————————— */

import java.util.Scanner;

public class oa {
    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        String string= in.nextLine();
        char[] ch= string.toCharArray();
        for (int i= 0; i < string.length(); i++ ) {
            if (ch[i] >= 'a' && ch[i] <= 'z') {
                // System.out.println('A' - 'a');
                ch[i]= (char) (ch[i] + 'A' - 'a');
            }
            if (ch[i] >= 'A' && ch[i] <= 'Z') {
                ch[i]= (char) (ch[i] - 'A' + 'a');
            }
        }
        System.out.println(new String(ch));
    }
}

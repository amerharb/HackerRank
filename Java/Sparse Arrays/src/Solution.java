
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        String nArr[] = new String[n];
        in.nextLine(); //jump to first line 
        for (int i = 0; i < n; i++) {
            nArr[i] = in.nextLine();
        }

        int q = in.nextInt();
        String qArr[] = new String[q];
        in.nextLine(); //jump to first line 
        for (int i = 0; i < q; i++) {
            qArr[i] = in.nextLine();
        }

        for (String qs : qArr) {
            int c = 0;
            for (String ns : nArr) {
                if (ns.equals(qs)) {
                    c++;
                }
            }
            System.out.println(c);
        }
    }
}


import java.util.Scanner;

public class AbsoluteElementSums {

    public static void main(String[] args) {
        int n;
        int[] a;
        int q;
        int[] x;

        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        q = in.nextInt();
        x = new int[q];
        for (int i = 0; i < q; i++) {
            x[i] = in.nextInt();
        }

        final int[] r = new int[4001];
        final int[] rs = new int[4001]; //Repeat Sum
        final int[] t = new int[4001];
        final int[] ts = new int[4001]; //totalSum

        for (int i : a) {
            r[i + 2000]++;
        }

        rs[0] = r[0];
        t[0] = -2000 * r[0];
        ts[0] = t[0];
        for (int i = 1; i < 4001; i++) {
            rs[i] = r[i] + rs[i - 1];
            t[i] = (i - 2000) * r[i];
            ts[i] = t[i] + ts[i - 1];
        }

        a = null; //empty memory

        int totalX = 0;

        for (int i = 0; i < q; i++) {
            totalX += x[i];
            long total = ts[4000]
                    + (long) totalX
                    * (long) (rs[4000] - 2 * getNegativeCount(totalX, rs))
                    - 2 * getTotalSum(totalX, ts);
            System.out.println(total);
        }
    }

    static int getTotalSum(int offSet, int[] ts) {
        if (offSet > 1999) {
            return 0;
        } else if (offSet < -2001) {
            return ts[4000];
        } else {
            return ts[1999 - offSet];
        }
    }

    static private int getNegativeCount(int offSet, int[] rs) {
        if (offSet > 1999) {
            return 0;
        } else if (offSet < -2001) {
            return rs[4000];
        } else {
            return rs[1999 - offSet];
        }
    }
}

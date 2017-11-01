
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

            int NC, NT; //negative count, negative total
            if (totalX > 1999) {
                NC = 0;
                NT = 0;
            } else if (totalX < -2001) {
                NC = rs[4000];
                NT = ts[4000];
            } else {
                NC = rs[1999 - totalX];
                NT = ts[1999 - totalX];
            }

            long total = ts[4000] - 2 * NT
                    + (long) totalX * (long) (rs[4000] - 2 * NC);
            System.out.println(total);
        }
    }
}

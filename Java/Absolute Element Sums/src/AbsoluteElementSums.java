import java.util.Arrays;
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

        Arrays.sort(a);
        aValues[] aa = aggregateRepeating(a);
        a = null; //empty memory

        int fp = getFirst(aa, new aValues(0, 0)); //get first positive value in the array ;
        long totalP = 0, totalN = 0; //total X, P, N
        for (int i = 0; i < fp; i++) {
            totalN += -aa[i].total();
        }
        for (int i = fp; i < aa.length; i++) {
            totalP += aa[i].total();
        }

        int totalX = 0;
        for (int i = 0; i < q; i++) {
            totalX += x[i];
            int newFP = getFirst(aa, new aValues(-totalX, 0));
            long total = 0;
            int NC; //Negative count
            if (fp > 0) {
                NC = aa[fp - 1].rs;
            } else {
                NC = 0;
            }

            int nNC;//new Negative count
            if (newFP > 0) {
                nNC = aa[newFP - 1].rs;
            } else {
                nNC = 0;
            }

            total += totalP + totalN + ((long) totalX * (long) (aa[aa.length - 1].rs - NC - nNC));

            for (int j = newFP; j < fp; j++) {
                total -= Math.abs(aa[j].total());
                total += Math.abs(aa[j].total() + (totalX * aa[j].r));
            }
            for (int j = fp; j < newFP; j++) {
                total -= Math.abs(aa[j].total());
                total += Math.abs(aa[j].total() + (totalX * aa[j].r));
            }

            System.out.println(total);
        }
    }

    static int getFirst(aValues[] arr, aValues v) {
        int k = Arrays.binarySearch(arr, v);
        if (k < 0) {
            return -k - 1;
        } else {
            return k;
        }
    }

    static aValues[] aggregateRepeating(int[] arr) {

        aValues[] t = new aValues[Math.min(arr.length, 4001)];

        int j = 0;
        t[j] = new aValues(arr[0], 0);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                t[j].r++;
                t[j].rs++;
            } else {
                t[++j] = new aValues(arr[i], t[j - 1].rs);
            }
        }
        aValues[] r = new aValues[j + 1];
        System.arraycopy(t, 0, r, 0, j + 1);
        return r;
    }

    private static class aValues implements Comparable<aValues> {

        private final int a;
        int r = 1;
        int rs;

        public int total() {
            return a * r;
        }

        public aValues(int a, int prevRS) {
            this.a = a;
            this.rs = prevRS + 1;
        }

        @Override
        public int compareTo(aValues o) {
            return this.a - o.a;
        }
    }
}

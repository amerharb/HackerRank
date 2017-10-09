
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

        int fp = getFirst(aa, 0); //get first positive value in the array ;
        long totalP = 0, totalN = 0; //total X, P, N
        for (int i = 0; i < fp; i++) {
            totalN += -aa[i].total;
        }
        for (int i = fp; i < aa.length; i++) {
            totalP += aa[i].total;
        }

        int NC; //Negative count
        if (fp > 0) {
            NC = aa[fp - 1].rs;
        } else {
            NC = 0;
        }

        int totalX = 0;
        for (int i = 0; i < q; i++) {
            totalX += x[i];
            int newFP = getFirst(aa, -totalX);
            long total = 0;

            int nNC;//new Negative count
            if (newFP > 0) {
                nNC = aa[newFP - 1].rs;
            } else {
                nNC = 0;
            }

            total += totalP + totalN + ((long) totalX * (long) (aa[aa.length - 1].rs - NC - nNC));

            for (int j = newFP; j < fp; j++) {
                long tXa = (long)totalX * (long)aa[j].r;
                if (aa[j].total < tXa) {
                    total += aa[j].total + tXa - aa[j].absTotal;
                } else {
                    total += -aa[j].total - tXa - aa[j].absTotal;
                }
            }
            for (int j = fp; j < newFP; j++) {
                long tXa = (long)totalX * (long)aa[j].r;
                if (aa[j].total < tXa) {
                    total += aa[j].total + tXa - aa[j].absTotal;
                } else {
                    total += -aa[j].total - tXa - aa[j].absTotal;
                }
            }

            System.out.println(total);
        }
    }

    static int getFirst(aValues[] arr, int v) {
        int k = Arrays.binarySearch(arr, new aValues(v, 0));
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
                t[j].inc();
            } else {
                t[++j] = new aValues(arr[i], t[j - 1].rs);
            }
        }
        aValues[] r = new aValues[j + 1];
        System.arraycopy(t, 0, r, 0, j + 1);
        for (aValues v : r) {
            v.updateTotal();
        }
        return r;
    }

    private static class aValues implements Comparable<aValues> {

        private final int a;
        private int r = 1;
        private int rs;
        int total;
        int absTotal;

        public void inc() {
            r++;
            rs++;
        }

        public void updateTotal() {
            total = a * r;
            absTotal = Math.abs(total);
        }

        public aValues(int a, int prevRS) {
            this.a = a;
            this.rs = prevRS + 1;
            this.total = a;
            this.absTotal = Math.abs(a);
        }

        @Override
        public int compareTo(aValues o) {
            return this.a - o.a;
        }
    }
}

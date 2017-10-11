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

        final int NC; //Negative count
        if (fp > 0) {
            NC = aa[fp - 1].rSum;
        } else {
            NC = 0;
        }
        final int PC = aa[aa.length - 1].rSum - NC; //positive count
        final long totalPN = totalP + totalN;
        int totalX = 0;

        for (int i = 0; i < q; i++) {
            totalX += x[i];
            int newFP = getFirst(aa, -totalX);

            int nNC;//new Negative count
            if (newFP > 0) {
                nNC = aa[newFP - 1].rSum;
            } else {
                nNC = 0;
            }

            long total = totalPN + ((long) totalX * (long) (PC - nNC));
            if (newFP < fp) {
                total += (aa[fp - 1].totalSum - aa[newFP].prevTotal) * 2 + (long) totalX * (long) (aa[fp - 1].rSum - aa[newFP].prevR);
            } else if (newFP > fp) {
                total -= (aa[newFP - 1].totalSum - aa[fp].prevTotal) * 2 + (long) totalX * (long) (aa[newFP - 1].rSum - aa[fp].prevR);
            }

            System.out.println(total);
        }
    }

    static int getFirst(aValues[] arr, int v) {
        int k = Arrays.binarySearch(arr, new aValues(v, 0, 0));
        if (k < 0) {
            return -k - 1;
        } else {
            return k;
        }
    }

    static aValues[] aggregateRepeating(int[] arr) {

        aValues[] t = new aValues[(arr.length <= 4001) ? arr.length : 4001];

        int j = 0;
        t[j] = new aValues(arr[0], 0, 0);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                t[j].inc();
            } else {
                t[++j] = new aValues(arr[i], t[j - 1].getRSum(), t[j - 1].getTotalSum());
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
        private int r;
        final int prevR;
        int rSum;
        private int total;
        final int prevTotal;
        int totalSum;

        public void inc() {
            r++;
        }

        public aValues(int a, int prevR, int prevTotal) {
            this.a = a;
            this.r = 1;
            this.prevR = prevR;
            this.prevTotal = prevTotal;
        }

        public void updateTotal() {
            this.total = a * r;
            this.rSum = prevR + r;
            this.totalSum = total + prevTotal;
        }

        public int getRSum() {
            this.rSum = prevR + r;
            return this.rSum;
        }

        public int getTotalSum() {
            this.total = a * r;
            this.totalSum = prevTotal + total;
            return this.totalSum;
        }

        @Override
        public int compareTo(aValues o) {
            return this.a - o.a;
        }
    }
}

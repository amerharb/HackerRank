import java.util.Scanner;

public class AbsoluteElementSums
{
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

        FixedArray FA = new FixedArray(a);
        a = null; //empty memory

        final long totalPN = FA.getTotalPositive() - FA.getTotalNegative();
        int totalX = 0;

        for (int i = 0; i < q; i++) {
            totalX += x[i];
            long total = totalPN + ((long) totalX * (long) (FA.getPositiveCount() - FA.getNegativeCount(totalX))) + (FA.getTotalNegative() - FA.getTotalSum(-totalX - 1)) * 2 + (long) totalX * (long) (FA.getNegativeCount() - FA.getNegativeCount(totalX));
            System.out.println(total);
        }
    }

    private static class FixedArray
    {
       final int[] r = new int[4001];
        final int[] rs = new int[4001]; //Repeat Sum
        final int[] t = new int[4001];
        final int[] ts = new int[4001]; //totalSum

        private FixedArray(int[] arr) {
            for (int a : arr) {
                r[a + 2000]++;
            }

            //update values
            rs[0] = r[0];
            t[0] = -2000 * r[0];
            ts[0] = t[0];
            for (int i = 1; i < 4001; i++) {
                rs[i] = r[i] + rs[i - 1];
                t[i] = (i - 2000) * r[i];
                ts[i] = t[i] + ts[i - 1];
            }
        }

        int getTotal(int num) {
            return t[num - 2000];
        }

        int getTotalSum(int num) {
            if (num < -2000) {
                return 0;
            } else if (num > 2000) {
                return ts[4000];
            } else {
                return ts[num + 2000];
            }
        }

        int getRepeateSum(int num) {
            if (num < -2000) {
                return 0;
            } else if (num > 2000) {
                return rs[4000];
            } else {
                return rs[num + 2000];
            }
        }

        int getTotalNegative() {
            return ts[1999];
        }

        int getTotalPositive() {
            return ts[4000] - ts[1999];
        }

        private int getNegativeCount() {
            return rs[1999];
        }

        private int getPositiveCount() {
            return rs[4000] - rs[1999];
        }

        private int getNegativeCount(int offSet) {
            if (offSet > 1999) {
                return 0;
            } else if (offSet < -2001) {
                return rs[4000];
            } else {
                return rs[1999 - offSet];
            }
        }
    }
}

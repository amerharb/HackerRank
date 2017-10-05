import java.util.Arrays;
import java.util.Scanner;

public class AbsoluteElementSums {

    public static void main(String[] args){
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
        int fp = getFirst(a, 0); //get first positive value in the array ;
        long totalP = 0, totalN = 0; //total X, P, N
        for (int i = 0; i < fp; i++) {
            totalN += -a[i];
        }
        for (int i = fp; i < a.length; i++) {
            totalP += a[i];
        }

        int totalX = 0;
        for (int i = 0; i < q; i++) {
            totalX += x[i];
            int newFP = getFirst(a, -totalX);
            long total = 0;

            total += totalP + (totalX * (a.length - fp));
            total += totalN - (totalX * fp);

            for (int j = newFP; j < fp; j++) {
                total -= Math.abs(a[j]);
                total += totalX;
                total += Math.abs(a[j] + totalX);
            }
            for (int j = fp; j < newFP; j++) {
                total -= Math.abs(a[j]);
                total -= totalX;
                total += Math.abs(a[j] + totalX);
            }

            System.out.println(total);

        }
    }

    static int getFirst(int[] arr, int v) {
        int k = Arrays.binarySearch(arr, v);
        if (k < 0) {
            return -k - 1;
        } else {
            while (k > 0) {
                if (arr[k - 1] == v) {
                    k--;
                } else {
                    return k;
                }
            }
            return k;
        }
    }

}

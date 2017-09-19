import java.util.Scanner;

public class Solution {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        long arr[] = new long[n + 1];

        int a[] = new int[m];
        int b[] = new int[m];
        long k[] = new long[m];

        for (int i = 0; i < m; i++) {
            a[i] = in.nextInt() - 1;
            b[i] = in.nextInt() - 1;
            k[i] = in.nextLong();
        }
        in.close();

        for (int i = 0; i < m; i++) {
            arr[a[i]] += k[i];
            arr[b[i] + 1] -= k[i];
        }

        long max = 0;
        long total = 0;
        for (long l : arr) {
            total += l;
            if (max < total) {
                max = total;
            }
        }
        System.out.println(max);

    }
}

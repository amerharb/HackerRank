
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        long arr[] = new long[n];

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            long k = in.nextLong();
            for (int j = a - 1; j < b; j++) {
                arr[j] += k;
            }
        }

        long max = Long.MIN_VALUE;
        for (long l : arr) {
            if (max < l) {
                max = l;
            }
        }
        
        System.out.println(max);
    }
}


import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }

        int t1 = 0, t2 = 0;
        for (int i = 0; i < n; i++) {
            t1 += a[i][i];
            t2 += a[n - i - 1][i];
        }
        System.out.println(Math.abs(t1 - t2));
        
    }
}

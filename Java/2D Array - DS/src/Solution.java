
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arr[][] = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        int MaxSum = (-9 * 7); //smallest value
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                int hgt = getHourGlassSum(arr, i, j);
                if (MaxSum < hgt)
                    MaxSum = hgt;
            }
        }
        
        System.out.println(MaxSum);
    }

    private static int getHourGlassSum(int arr[][], int i, int j) {
        int t = 0;

        t += arr[i + 0][j + 0];
        t += arr[i + 0][j + 1];
        t += arr[i + 0][j + 2];
        t += arr[i + 1][j + 1];
        t += arr[i + 2][j + 0];
        t += arr[i + 2][j + 1];
        t += arr[i + 2][j + 2];
        
        return t;
    }
}

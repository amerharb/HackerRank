
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in = new Scanner(System.in);

        int c = in.nextInt();
        int v[] = new int[c];
        long m[] = new long[c];

        for (int i = 0; i < c; i++) {
            v[i] = in.nextInt();
        }

        for (int i = 0; i < c / 2; i++) {
            int l = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (v[j] > v[i]) {
                    l = j + 1;
                    break;
                }
            }
            if (l == 0) {
                m[i] = 0;
            } else {
                //find right
                int r = 0;
                for (int k = i + 1; k < c; k++) {
                    if (v[k] > v[i]) {
                        r = k + 1;
                        break;
                    }
                }
                m[i] = l * r;
            }
        }

        for (int i = c / 2 ; i < c; i++) {
            //find right first
            int r = 0;
            for (int k = i + 1; k < c; k++) {
                if (v[k] > v[i]) {
                    r = k + 1;
                    break;
                }
            }
            if (r == 0) {
                m[i] = 0;
            } else {
                //find left
                int l = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (v[j] > v[i]) {
                        l = j + 1;
                        break;
                    }
                }
                m[i] = l * r;
            }

        }

        //find max
        long max = m[0];
        for (long i : m) {
            if (max < i) {
                max = i;
            }
        }

        System.out.print(max);

    }
}


import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in = new Scanner(System.in);

        int c = in.nextInt();
        int v[] = new int[c];
        long m[] = new long[c];
        int leftV[] = new int[c];
        int rightV[] = new int[c];

        int lastLeftValue = Integer.MIN_VALUE;
        int lastLeftIndex = -1;

        if (c > 0) {
            v[0] = in.nextInt();
            leftV[0] = -1;
            lastLeftValue = v[0];
        }

        for (int i = 1; i < c; i++) {
            v[i] = in.nextInt();
            if (v[i] < v[i - 1]) {
                lastLeftValue = v[i - 1];
                lastLeftIndex = i - 1;
                leftV[i] = i - 1;
            } else if (v[i] == v[i - 1]) {
                leftV[i] = lastLeftIndex;
            } else if (lastLeftIndex == -1 || v[i] < lastLeftValue) {
                leftV[i] = lastLeftIndex;
            } else if (v[i] == lastLeftValue) {
                leftV[i] = leftV[lastLeftIndex];
            } else if (v[i] > lastLeftValue) {
                leftV[i] = -1;//unless next loop find better value
                lastLeftValue = v[i];
                int j = leftV[lastLeftIndex];
                while (j >= 0) {
                    if (v[j] > v[i]) {
                        leftV[i] = j;
                        break;
                    }
                    j = leftV[j];
                }
            }
        }

        int lastRightValue = Integer.MIN_VALUE;
        int lastRightIndex = -1;

        if (c > 0) {
            rightV[c - 1] = -1;
            lastRightValue = v[c - 1];
        }

        for (int i = c - 2; i >= 0; i--) {
            if (v[i] < v[i + 1]) {
                lastRightValue = v[i + 1];
                lastRightIndex = i + 1;
                rightV[i] = i + 1;
            } else if (v[i] == v[i + 1]) {
                rightV[i] = lastRightIndex;
            } else if (lastRightIndex == -1 || v[i] < lastRightValue) {
                rightV[i] = lastRightIndex;
            } else if (v[i] == lastRightValue) {
                rightV[i] = rightV[lastRightIndex];
            } else if (v[i] > lastRightValue) {
                rightV[i] = -1; //unless next loop find better value
                lastRightValue = v[i];
                int j = rightV[lastRightIndex];
                while (j >= 0) {
                    if (v[j] > v[i]) {
                        rightV[i] = j;
                        break;
                    }
                    j = rightV[j];
                }
            }
        }

        for (int i = 0; i < c; i++) {

            m[i] = (long) (leftV[i] + 1) * (long) (rightV[i] + 1);

        }

        //find max
        long max = Long.MIN_VALUE;
        for (long i : m) {
            if (max < i) {
                max = i;
            }
        }

        System.out.print(max);

    }
}

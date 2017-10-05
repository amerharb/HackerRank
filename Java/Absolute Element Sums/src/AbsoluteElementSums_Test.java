import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

public class AbsoluteElementSums_Test {
    public static void main(String[] args) throws IOException {
        final byte read = 1; //0 consle , 1- file

        int n;
        int[] a;
        int q;
        int[] x;
        switch (read) {
            case 0: { //read from concol
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
                break;
            }
            case 1: { //read from file
                long w = System.currentTimeMillis();
                String line;
                String[] l;
                try (InputStream fis = new FileInputStream("input.txt");
                        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                        BufferedReader br = new BufferedReader(isr);) {
                    
                    line = br.readLine();
                    n = Integer.parseInt(line);

                    line = br.readLine();
                    l = line.split(" ");
                    a = new int[n];
                    for (int i = 0; i < n; i++) {
                        a[i] = Integer.parseInt(l[i]);
                    }
                    
                    line = br.readLine();
                    q = Integer.parseInt(line);

                    line = br.readLine();
                    l = line.split(" ");
                    x = new int[q];
                    for (int i = 0; i < q; i++) {
                        x[i] = Integer.parseInt(l[i]);
                    }
                }
                System.out.println("time to read from file : "+(System.currentTimeMillis() - w) + "mili sec");
                break;
            }
            default: {
                //stuiped code to satisfiy java
                a = new int[0];
                x = new int[0];
                n = 0;
                q = 0;
                System.exit(0);
            }
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

            //System.out.println(total);

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

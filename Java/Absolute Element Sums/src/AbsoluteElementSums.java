
import java.util.Scanner;

public class AbsoluteElementSums {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int q = in.nextInt();
        int x[] = new int[q];
        for (int i = 0; i < q; i++) {
            x[i] = in.nextInt();
        }

        for (int i = 0; i < q; i++) {
            for (int j = 0; j < n; j++) {
                a[j] += x[i];
            }
            int t = 0; //total 
            for (int j = 0; j < n; j++) {
                t += Math.abs(a[j]);
            }
            System.out.println(t);
            
        }
    }

}

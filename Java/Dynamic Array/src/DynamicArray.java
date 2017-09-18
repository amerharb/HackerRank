
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author amerharb
 */
public class DynamicArray {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int Q = in.nextInt();

        ArrayList<Integer> S[] = new ArrayList[N];
        
        for (int i = 0; i < N; i++) {
            S[i] = new ArrayList<Integer>();
        }
 

        int LastAnswer = 0;
        for (int i = 0; i < Q; i++) {
            int qu = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            
            ArrayList<Integer> A = S[(x ^ LastAnswer) % N];
            if (qu == 1) {
                A.add(y);
            } else if (qu == 2) {
                LastAnswer = A.get(y % A.size());
                System.out.println(LastAnswer);
            }
        }

    }

}


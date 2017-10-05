
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

public class AbsoluteElementSums_Test {

    static int count_foundA = 0, count_foundB = 0, count_foundC = 0;
    static int count_L1 = 0, count_L2 = 0;

    public static void main(String[] args) throws IOException, Exception {
        final byte read = 1; //0-Scanner , 1- file (input.txt), 2- buffer
        final byte write = 1; //0 consle , 1- file (my_output.txt)
        final boolean check = true; //to compair my_output.txt with output.txt
        final String inputFileName = "input_testcase10.txt";
        final String outputFileName = "output_testcase10.txt";

        StopWatch sw = new StopWatch();
        sw.start();

        int n;
        long[] a;
        int q;
        long[] x;
        switch (read) {
            case 2: { //read from concol
                BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
                String line;
                line = bi.readLine();
                n = Integer.parseInt(line);
                sw.start();
                line = bi.readLine();
                int i = 0;
                a = new long[n];
                for (String numStr : line.split("\\s")) {
                    a[i] = Integer.parseInt(numStr);
                    i++;
                }
                line = bi.readLine();
                q = Integer.parseInt(line);
                line = bi.readLine();
                i = 0;
                x = new long[ q];
                for (String numStr : line.split("\\s")) {
                    x[i] = Integer.parseInt(numStr);
                    i++;
                }
                System.out.println("Reading using buffer: " + sw.stopAndStart());
                break;
            }
            case 0: {
                Scanner in = new Scanner(System.in);
                n = in.nextInt();
                sw.start();
                a = new long[n];
                for (int i = 0; i < n; i++) {
                    a[i] = in.nextInt();
                }

                q = in.nextInt();
                x = new long[q];
                for (int i = 0; i < q; i++) {
                    x[i] = in.nextInt();
                }
                System.out.println("Reading using scanenr: " + sw.stopAndStart());
                break;
            }
            case 1: { //read from file
                String line;
                String[] l;
                try (InputStream fis = new FileInputStream(inputFileName);
                        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                        BufferedReader br = new BufferedReader(isr);) {

                    line = br.readLine();
                    n = Integer.parseInt(line);

                    line = br.readLine();
                    l = line.split(" ");
                    a = new long[n];
                    for (int i = 0; i < n; i++) {
                        a[i] = Integer.parseInt(l[i]);
                    }

                    line = br.readLine();
                    q = Integer.parseInt(line);

                    line = br.readLine();
                    l = line.split(" ");
                    x = new long[q];
                    for (int i = 0; i < q; i++) {
                        x[i] = Integer.parseInt(l[i]);
                    }
                }
                System.out.println("Reading from file: " + sw.stopAndStart());
                break;
            }
            default: {
                //stuiped code to satisfiy java
                a = new long[0];
                x = new long[0];
                n = 0;
                q = 0;
                System.exit(0);
            }
        }

        Arrays.sort(a);
        StopWatch fsw = new StopWatch();
        fsw.resume();
        int fp = getFirst(a, 0); //get first positive value in the array ;
        fsw.pause();
        long totalP = 0, totalN = 0; //total X, P, N
        for (int i = 0; i < fp; i++) {
            totalN += -a[i];
        }
        for (int i = fp; i < a.length; i++) {
            totalP += a[i];
        }
        System.out.println("find total of a array: " + sw.stopAndStart());

        long totalX = 0;
        StringBuilder sb = new StringBuilder(q * 2); //at least 2 char for each line
        StopWatch L1 = new StopWatch();
        StopWatch L2 = new StopWatch();
        
        for (int i = 0; i < q; i++) {
            totalX += x[i];
            fsw.resume();
            int newFP = getFirst(a, -totalX);
            fsw.pause();
            //check result 
            if (newFP > 0 && newFP < a.length) {
                if (a[newFP - 1] >= a[newFP] || a[newFP + 1] < a[newFP]) {
                    System.out.println("ERROR in get First");
                }
            }

            long total = 0;

//            total += totalP + (totalX * (a.length - fp));
//            total += totalN - (totalX * fp);
//            total += totalX * (fp - newFP);
            total += totalP + totalN + totalX * (a.length - fp - newFP);

            L1.resume();
            for (int j = newFP; j < fp; j++) {
                count_L1++;
                total -= Math.abs(a[j]);
//                total += totalX;
                total += Math.abs(a[j] + totalX);
            }
            L1.pause();
            L2.resume();
            for (int j = fp; j < newFP; j++) {
                count_L2++;
                total -= Math.abs(a[j]);
//                total -= totalX;
                total += Math.abs(a[j] + totalX);
            }
            L2.pause();

            sb.append(total);
            sb.append("\n");

        }
        sb.deleteCharAt(sb.length() - 1); // delete last enter
        System.out.println("do the calc: " + sw.stopAndStart());
        System.out.println("L1: " + L1.getPeriod() + "count L1: " + count_L1);
        System.out.println("L2: " + L2.getPeriod() + "count L1: " + count_L2);

        if (write == 0) {
            System.out.print(sb);
            System.out.println("time to write to console: " + sw.stopAndStart());

        } else if (write == 1) {
            File file = new File("my_output.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.append(sb);
                System.out.println("file is written at: " + file.getAbsolutePath());
            }
            System.out.println("time to write to file : " + sw.stopAndStart());

            if (check) {
                if (compareFile(outputFileName, "my_output.txt")) {
                    System.out.println("result match");
                } else {
                    System.out.println("**** result NOT Match******");
                }
                System.out.println("time to compair files: " + sw.stopAndStart());
            }
        }
        
        System.out.println("time of find first:" + FIND_FIRST_SW.getPeriod());
        System.out.println("time of find first:" + fsw.getPeriod());
        System.out.println("found A :" + count_foundA);
        System.out.println("found B :" + count_foundB);
        System.out.println("found C :" + count_foundC);

    }

    static final StopWatch FIND_FIRST_SW = new StopWatch();

    static {
        FIND_FIRST_SW.start();
    }

    static int getFirst(long[] arr, long v) throws InterruptedException {
        FIND_FIRST_SW.resume();
        int k = Arrays.binarySearch(arr, v);
        if (k < 0) {
            count_foundA++;
            FIND_FIRST_SW.pause();
            return -k - 1;
        } else {
            int b = Arrays.binarySearch(arr, 0, k, v - 1);
            if (b < 0) {
                count_foundB++;
                FIND_FIRST_SW.pause();
                return -b - 1;

            } else {
                count_foundC++;
                while (k > 0) {
                    if (arr[k - 1] == v) {
                        k--;
                    } else {
                        FIND_FIRST_SW.pause();
                        return k;
                    }
                }
                FIND_FIRST_SW.pause();
                return k;
            }
        }
    }

    static boolean compareFile(String file1, String file2) throws Exception {

        File f1 = new File(file1); //OUTFILE
        File f2 = new File(file2); //INPUT

        FileReader fR1 = new FileReader(f1);
        FileReader fR2 = new FileReader(f2);

        BufferedReader reader1 = new BufferedReader(fR1);
        BufferedReader reader2 = new BufferedReader(fR2);

        String line1 = null;
        String line2 = null;
        boolean flag = true;
        while (flag && ((line1 = reader1.readLine()) != null) && ((line2 = reader2.readLine()) != null)) {
            if (!line1.equalsIgnoreCase(line2)) {
                flag = false;
                System.out.println("not macth at: " + line1 + " " + line2);
            }
        }
        reader1.close();
        reader2.close();
        return flag;

    }

}

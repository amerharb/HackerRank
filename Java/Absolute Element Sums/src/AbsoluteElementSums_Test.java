
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
        final String inputFileName = "input10.txt";
        final String outputFileName = "output10.txt";

        StopWatch sw = new StopWatch();
        sw.start();

        int n;
        int[] a;
        int q;
        int[] x;
        switch (read) {
            case 2: { //read from console
                BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
                String line;
                line = bi.readLine();
                n = Integer.parseInt(line);
                sw.start();
                line = bi.readLine();
                int i = 0;
                a = new int[n];
                for (String numStr : line.split("\\s")) {
                    a[i] = Integer.parseInt(numStr);
                    i++;
                }
                line = bi.readLine();
                q = Integer.parseInt(line);
                line = bi.readLine();
                i = 0;
                x = new int[q];
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
                a = new int[n];
                for (int i = 0; i < n; i++) {
                    a[i] = in.nextInt();
                }

                q = in.nextInt();
                x = new int[q];
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
                System.out.println("Reading from file: " + sw.stopAndStart());
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
        aValues[] aa = aggregateRepeating(a);
        a = null; //empty memory

        StopWatch fsw = new StopWatch();
        fsw.resume();
        //int fp = getFirst(a, 0); //get first positive value in the array ;
        int fp = getFirst(aa, new aValues(0, 0)); //get first positive value in the array ;
        fsw.pause();
        long totalP = 0, totalN = 0; //total X, P, N
        for (int i = 0; i < fp; i++) {
            if (aa[i].total() >= 0) {
                System.out.println("ERROR");
                System.exit(0);
            }
            totalN += -aa[i].total();
        }
        for (int i = fp; i < aa.length; i++) {
            if (aa[i].total() < 0) {
                System.out.println("ERROR");
                System.exit(0);
            }
            totalP += aa[i].total();
        }
        System.out.println("find total of a array: " + sw.stopAndStart());

        int totalX = 0;
        StringBuilder sb = new StringBuilder(q * 2); //at least 2 char for each line
        StopWatch L1 = new StopWatch();
        StopWatch L2 = new StopWatch();

        for (int i = 0; i < q; i++) {
            totalX += x[i];
            fsw.resume();
            // int newFP = getFirst(a, -totalX);
            int newFP = getFirst(aa, new aValues(-totalX, 0));
            fsw.pause();

            long total = 0;

//            total += totalP + (totalX * (aa.length - fp));
//            total += totalN - (totalX * fp);
//            total += totalX * (fp - newFP);
//            total += totalP + ((long) totalX * (aa[aa.length - 1].rs - aa[fp].rs));
//            total += totalN - ((long) totalX * aa[fp].rs);
//            total += (long) totalX * (aa[fp].rs - aa[newFP].rs);
//            total += totalP + totalN + ((long) totalX * (long)(aa.length - fp - newFP));
            int NC; //Negative count
            if (fp > 0) {
                NC = aa[fp - 1].rs;
            } else {
                NC = 0;
            }

            int nNC;//new Negative count
            if (newFP > 0) {
                nNC = aa[newFP - 1].rs;
            } else {
                nNC = 0;
            }

            total += totalP + totalN + ((long) totalX * (long) (aa[aa.length - 1].rs - NC - nNC));
            L1.resume();
            for (int j = newFP; j < fp; j++) {
                count_L1++;
                total -= Math.abs(aa[j].total());
//                total += totalX;
                total += Math.abs(aa[j].total() + (totalX * aa[j].r));
            }
            L1.pause();
            L2.resume();
            for (int j = fp; j < newFP; j++) {
                count_L2++;
                total -= Math.abs(aa[j].total());
//                total -= totalX;
                total += Math.abs(aa[j].total() + (totalX * aa[j].r));
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

        System.out.println("time of find first:" + fsw.getPeriod());
        System.out.println("found A :" + count_foundA);
        System.out.println("found B :" + count_foundB);
        System.out.println("found C :" + count_foundC);

    }

    static int getFirst(aValues[] arr, aValues v) throws InterruptedException {
        int k = Arrays.binarySearch(arr, v);
        if (k < 0) {
            return -k - 1;
        } else {
            return k;
        }
    }

    /*    
    static int getFirst(int[] arr, int v) {
        int k = Arrays.binarySearch( arr, v);
        if (k < 0) {
            return -k - 1;
        } else {
            int b = Arrays.binarySearch(arr, 0, k, v - 1);
            if (b < 0) {
                return -b - 1;
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
     */
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

    static aValues[] aggregateRepeating(int[] arr) {

        aValues[] t = new aValues[Math.min(arr.length, 4001)];

        int j = 0;
        t[j] = new aValues(arr[0], 0);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                t[j].r++;
                t[j].rs++;
            } else {
                t[++j] = new aValues(arr[i], t[j - 1].rs);
            }
        }
        aValues[] r = new aValues[j + 1];
        System.arraycopy(t, 0, r, 0, j + 1);
        return r;
    }

    private static class aValues implements Comparable<aValues> {

        private final int a;
        int r = 1;
        int rs;

        public int total() {
            return a * r;
        }

        /*
        public aValues(int a) {
            this.a = a;
        }
         */
        public aValues(int a, int prevRS) {
            this.a = a;
            this.rs = prevRS + 1;
        }

        @Override
        public int compareTo(aValues o) {
            return this.a - o.a;
        }
    }

}

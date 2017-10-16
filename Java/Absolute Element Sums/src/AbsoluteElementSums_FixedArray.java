
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

public class AbsoluteElementSums_FixedArray {

    public static void main(String[] args) throws IOException, Exception {
        final byte read = 0; //0-Scanner , 1- file (inputFileName), 2- buffer
        final byte write = 0; //0 consle , 1- file (outputFileName)
        final boolean check = false; //to compair my_output.txt with output.txt
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

//        Arrays.sort(a);
        FixedArray FA = new FixedArray(a);

//        StopWatch ar = new StopWatch();
//        ar.start();
////        aValue[] aa = aggregateRepeating(a);
//        System.out.println("aggregate Repeating " + ar.stop());
        a = null; //empty memory

        StopWatch fsw = new StopWatch();
        fsw.resume();
        //int fp = getFirst(a, 0); //get first positive value in the array ;
        //int fp = getFirst(aa, 0); //get first positive value in the array ;
        fsw.pause();

//        long totalP = 0, totalN = 0; //total P, N
//        for (int i = 0; i < fp; i++) {
//            totalN += -aa[i].total;
//        }
//        for (int i = fp; i < aa.length; i++) {
//            totalP += aa[i].total;
//        }
        final int totalP, totalN; //total P, N
        totalN = -FA.getTotalNegative();
        totalP = FA.getTotalPositive();

        System.out.println("find total of a array: " + sw.stopAndStart());

//        final int NC; //Negative count
//        if (fp > 0) {
//            NC = aa[fp - 1].rSum;
//        } else {
//            NC = 0;
//        }
        final int NC = FA.getNegativeCount();
        final int PC = FA.getPositiveCount();

        final long totalPN = totalP + totalN;
        int totalX = 0;
        StringBuilder sb = new StringBuilder(q * 2); //at least 2 char for each line

        StopWatch M = new StopWatch();
        StopWatch A = new StopWatch();

        for (int i = 0; i < q; i++) {
            totalX += x[i];
            fsw.resume();
            // int newFP = getFirst(a, -totalX);
            //int newFP = getFirst(aa, -totalX);
            fsw.pause();
            A.resume();
            M.resume();
//            int nNC;//new Negative count
//            if (newFP > 0) {
//                nNC = aa[newFP - 1].rSum;
//            } else {
//                nNC = 0;
//            }
            int nNC = FA.getNegativeCount(totalX);//new Negative count

            long total = totalPN + ((long) totalX * (long) (PC - nNC));
            M.pause();

//            if (newFP < fp) {
//                total += (aa[fp - 1].totalSum - aa[newFP].prevTotal) * 2 + (long) totalX * (long) (aa[fp - 1].rSum - aa[newFP].prevR);
//            } else if (newFP > fp) {
//                total -= (aa[newFP - 1].totalSum - aa[fp].prevTotal) * 2 + (long) totalX * (long) (aa[newFP - 1].rSum - aa[fp].prevR);
//            }
            if (totalX >= 0) {
                total += (FA.getTotalNegative() - FA.getTotalSum(-totalX - 1)) * 2 + (long) totalX * (long) (FA.getNegativeCount() - FA.getNegativeCount(totalX));
            } else if (totalX < 0) {
                total -= (FA.getTotalNegative() - FA.getTotalSum(-totalX - 1)) * 2 + (long) totalX * (long) (FA.getRepeateSum(-totalX - 1) - FA.getNegativeCount());
            }

            A.pause();

            sb.append(total);
            sb.append("\n");

        }
        sb.deleteCharAt(sb.length() - 1); // delete last enter
        System.out.println("do the calc: " + sw.stopAndStart());
        System.out.println("M = " + M.getPeriod());
        System.out.println("A = " + A.getPeriod());

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
                    System.out.println("result match OK OK OK OK ");
                } else {
                    System.out.println("************** result NOT Match *************");
                }
                System.out.println("time to compair files: " + sw.stopAndStart());
            }
        }

        System.out.println("time of find first:" + fsw.getPeriod());

    }

    private static class FixedArray {

        int[] r = new int[4001];
        int[] rs = new int[4001]; //Repeat Sum
        int[] t = new int[4001];
        int[] ts = new int[4001]; //totalSum

        long tp, tn; //total Positive, total Negative

        private FixedArray(int[] arr) {
            for (int a : arr) {
                r[a + 2000]++;
            }

            //update values
            rs[0] = r[0];
            t[0] = -2000 * r[0];
            ts[0] = t[0];
            for (int i = 1; i < 4000; i++) {
                rs[i] = r[i] + r[i - 1];
                int a = (i - 2000);
                t[i] = a * r[i];
                ts[i] = t[i] + ts[i - 1];
            }
        }

        int getTotal(int num) {
            return t[num - 2000];
        }

        int getTotalSum(int num) {
            if (num < -2000) {
                return 0;
            } else if (num > 2000) {
                return ts[4000];
            } else {
                return ts[num + 2000];
            }
        }

        int getRepeateSum(int num) {
            if (num < -2000) {
                return 0;
            } else if (num > 2000) {
                return rs[4000];
            } else {
                return rs[num + 2000];
            }
        }

        int getTotalNegative() {
            return ts[1999];
        }

        int getTotalPositive() {
            return ts[4000] - ts[1999];
        }

        private int getNegativeCount() {
            return rs[1999];
        }

        private int getPositiveCount() {
            return rs[4000] - rs[1999];
        }

        private int getNegativeCount(int offSet) {
            if (offSet > 1999) {
                return 0;
            } else if (offSet < -2001) {
                return rs[4000];
            } else {
                return rs[1999 - offSet];
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

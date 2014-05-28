package R22;
import static java.util.Arrays.deepToString;
import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
 
public class B_max {
//    private static final String INPUT_PATH = "C:\\atcoder\\regular_004_004.txt";
    private static final String INPUT_PATH = null;

    void debug(Object ... o) {
        pw.write(deepToString(o));
    }
    PrintWriter pw = new PrintWriter(System.out);

    public void solve() { 
        try {
            br = new BufferedReader(new InputStreamReader(
                    INPUT_PATH == null ? System.in : new FileInputStream(new File(INPUT_PATH))));

            int n = readInt();
            int[] nA = readIntArray();
            int[] dp = new int[100001];

            int max = 0;
            int p = 0;
            for(int i = 0;i < n;i++){
                dp[nA[i]]++;
                boolean isU = false;
                while(dp[nA[i]] > 1 && p < n) {
                    dp[nA[p++]]--;
                    isU = true;
                    System.out.print(i + " " +  p + " " + nA[i] + " " + nA[p] + " " + dp[nA[i]] + ",");
                }
                if (isU) System.out.println();
                
                System.out.println(i + " " + (i - p + 1));
                max = Math.max(max, i-p+1);
            } 
            
            pw.println(max);
 
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            pw.flush();
            try {
                br.close();
            } catch (Exception igunore) {}
        }
    }
 
    BufferedReader br = null;

    private int readInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private long readLong() throws IOException {
        return Long.parseLong(br.readLine());
    }

    private int[] readIntArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        int[] out = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            out[i] = Integer.parseInt(s[i]);
        }
        return out;
    }

    private Integer[] convIntArray(int[] arg) {
        int len = arg.length;
        Integer[] res = new Integer[len];
        for (int i = 0; i < len; i++) {
            res[i] = arg[i];
        }
        return res;
    }
    
    private long[] readLongArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        long[] out = new long[cnt];
        for (int i = 0; i < cnt; i++) {
            out[i] = Long.parseLong(s[i]);
        }
        return out;
    }

    private String[] readStrArray() throws IOException {
        String[] s = br.readLine().split(" ");
        return s;
    }

    void generate(int[] p, int L, int R) {
        if (L == R) { // all numbers are set
          // do something with permutation in array p[]
            System.out.println(deepToString(convIntArray(p)));
        } else { // numbers at positions [0, L-1] are set, try to set L-th position
          for (int i = L; i <= R; i++) {
            int tmp = p[L]; p[L] = p[i]; p[i] = tmp;
            generate(p, L+1, R);
            tmp = p[L]; p[L] = p[i]; p[i] = tmp;
          }
        }
     }

    /***
     * Å‘åŒö–ñ”‚ð‹‚ß‚éŠÖ”
     * @param n1
     * @param n2
     * @return
     */
    private static long gcd(long n1, long n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }
    private static int gcd(int n1, int n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }
   public static void main(String[] args) {
        B_max app = new B_max();
        app.solve();
    }

   class Edge {
       int x, y;
       
       Edge(int x, int y) {
           this.x = x;
           this.y = y;
       }
       
   }
}
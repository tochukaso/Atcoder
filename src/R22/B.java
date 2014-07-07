package R22;
import static java.util.Arrays.deepToString;
import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
 
public class B {
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

            int length = 0;
            int max = 0;
            for (int i = 0; i < n; i++) {
                int x = nA[i];
                if (dp[x] > length) {
                    max = max(max, i - length);
                    length = dp[x];
//                    debug(length + " " + max + " " + x);
                }
                dp[x] = i + 1;
            }
            max = max(max, n - length);
            
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
     * 最大公約数を求める関数
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
        B app = new B();
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
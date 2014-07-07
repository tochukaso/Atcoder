package B8;
import static java.util.Arrays.deepToString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.Bidi;
import java.util.HashMap;
import java.util.Map;
 
public class B3 {
    private static final String INPUT_PATH = "C:\\atcoder\\b_008_003.txt";
//    private static final String INPUT_PATH = null;

    public void solve() { 
        try {
            br = new BufferedReader(new InputStreamReader(
                    INPUT_PATH == null ? System.in : new FileInputStream(new File(INPUT_PATH))));
            int n = readInt();
            
            int[] arr = new int[n];
            
            for (int i = 0; i < n; i++) {
                arr[i] = readInt();
            }
            
            int[] dp = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
//                    if (i == j) continue;
                    if (arr[i] % arr[j] == 0) dp[i] ++;
                }
            }

            double k = 0;
            for (int i=0; i<n; i++) {
                k += (double)((dp[i]/2) + dp[i]%2) / dp[i];
            }
            if (false) {
                pw.println(k);
                return;
            }
            
            BigDecimal sum = new BigDecimal(0);
/**            
            BigDecimal kumiawase = new BigDecimal(n);
            for (int i = n - 1; i > 0; i --) {
                kumiawase = kumiawase.multiply(new BigDecimal(i));
            }
**/            
            
            for (int i = 0; i < n; i++) {
                BigDecimal add = null;
                if (dp[i] == 1) {
                    add = new BigDecimal(1);
                } else {
                    BigDecimal base = new BigDecimal(dp[i]);

                    if (dp[i] % 2 == 0) {
                        add = new BigDecimal("0.5");
                    } else {
                    //    add = new BigDecimal("0.6666666666666666666666");
                        add = base.add(new BigDecimal(1)).divide(new BigDecimal(2), 20, BigDecimal.ROUND_HALF_UP).divide(base, 20, BigDecimal.ROUND_HALF_UP);
/**
                        BigDecimal not = new BigDecimal(n - 1);
                        for (int j = n - 2; j > 0; j --) {
                            not = not.multiply(new BigDecimal(j));
                        }
                        
                        add = not.divide(kumiawase, 20, BigDecimal.ROUND_HALF_UP);
**/                        
                    }

                    /**
                    
                    int base = n - dp[i];
                    int tmp = dp[i];
                    
                    for (int j = tmp; j >= 2; j -=2) {
                        base++;
                    }
                    
                    BigDecimal okKumiawase = new BigDecimal(n - 1);
                    for (int j = n - 2; base > 1; j --) {
                        okKumiawase = okKumiawase.multiply(new BigDecimal(j));
                        base --;
                    }
                    
                    add = okKumiawase.divide(kumiawase, 20, BigDecimal.ROUND_FLOOR);
                    **/
                }
                sum = sum.add(add);
            }
            
            pw.println(sum.toString());
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception igunore) {}
        }
    }
 

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
        long from = System.currentTimeMillis();
        B3 app = new B3();
        app.solve();
        pw.flush();

        pw.println(System.currentTimeMillis() - from);
    }

   void debug(Object ... o) {
       pw.write(deepToString(o));
   }
   BufferedReader br = null;
   static PrintWriter pw = new PrintWriter(System.out);

   class Type {
       int x, y;
       
       Type (int x, int y) {
           this.x = x;
           this.y = y;
       }
       
   }
}
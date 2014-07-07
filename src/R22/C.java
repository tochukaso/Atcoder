package R22;
import static java.util.Arrays.deepToString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
 
public class C {
//    private static final String INPUT_PATH = "C:\\atcoder\\regular_004_004.txt";
    private static final String INPUT_PATH = null;

    void debug(Object ... o) {
        pw.write(deepToString(o));
    }
    PrintWriter pw = new PrintWriter(System.out);

    public void solve() { 
        try (PrintWriter pw = new PrintWriter(System.out)){
            br = new BufferedReader(new InputStreamReader(
                    INPUT_PATH == null ? System.in : new FileInputStream(new File(INPUT_PATH))));

            int n = readInt();
            
            List<Integer>[] map = new ArrayList[n + 1];
            
            for (int i = 0; i < n + 1; i++) {
                map[i] = new ArrayList<Integer>();
            }
            
            for (int i = 0; i < n - 1; i++) {
                int[] nA = readIntArray();
                map[nA[0]].add(Integer.valueOf(nA[1]));
                map[nA[1]].add(Integer.valueOf(nA[0]));
            }

            int m = 1;

            int p1 = 0;
            int p2 = 0;
            Deque<Integer> que = new ArrayDeque<Integer>();
            int[] dp = new int[n + 1];
            {
                List<Integer> list = map[m];
                Arrays.fill(dp, 1000000);
                dp[m] = 0;

                for (Integer x : list) {
                    dp[x] = 1;
                    que.push(x);
                }

                while(!que.isEmpty()) {
                    Integer e = que.poll();
                    List<Integer> eList = map[e];
                    for (Integer x : eList) {
                        if (dp[x] > dp[e] + 1) {
                            dp[x] = dp[e] + 1;
                            que.push(x);
                        }
                    }
                }

                int nMax = 0;
                for (int i = 1; i <= n; i++) {
                    if(dp[i] == 1000000) continue;
                    if (dp[i] > nMax) {
                        nMax = dp[i];
                        p1 = i;
                    }
                }
                
            }

            {
                m = p1;
                List<Integer> list = map[m];
                Arrays.fill(dp, 1000000);
                dp[m] = 0;
                for (Integer x : list) {
                    dp[x] = 1;
                    que.push(x);
                }

                while(!que.isEmpty()) {
                    Integer e = que.poll();
                    List<Integer> eList = map[e];
                    for (Integer x : eList) {
                        if (dp[x] > dp[e] + 1) {
                            dp[x] = dp[e] + 1;
                            que.push(x);
                        }
                    }
                }
                
                int nMax = 0;
                for (int i = 1; i <= n; i++) {
                    if(dp[i] == 1000000) continue;
                    if (dp[i] > nMax) {
                        nMax = dp[i];
                        p2 = i;
                    }
                }
            }

            pw.println(p1 + " " + p2);
            pw.flush();
 
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
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
        C app = new C();
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
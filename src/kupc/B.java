package kupc;


import static java.util.Arrays.deepToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
 
public class B {
    private static final boolean isDebug = false;
    
    void solve() throws Throwable {
        startTime = System.currentTimeMillis();
       
        Set<Integer> set = new HashSet<Integer>();
        
        for (int i = 2; i <= 1000; i++) {
            set.add(i);
        }
        
        for (int i = 2; i <= 1000; i++) {
            if (set.contains(i)) {
                System.out.println("? " + i);
                boolean isDiv = br.readLine().equals("Y");
                
                for (int j = 2; j <= 1000; j++) {
                    if (isDiv) {
                        if(j % i != 0) {
                            set.remove(j) ;
                        }
                    } else {
                        if(j % i == 0) {
                            set.remove(j);
                        }
                    }
                }
            }
        }
        
        if (set.size() == 1) {
           pw.println("! " + set.toArray()[0]) ;
        } else {
            pw.println("! 1");
        }
        
        for (int i : set) {
            pw.print(i + " ") ;
        }
    }    
    
    final void printMatrix(double[][] p) {
        for (double[] i : p) printArray(i);
    }
    
    final void printArray(double[] p) {
        for (double i : p) System.out.print(i + " ");
        System.out.println();
    }
    
    private static long gcd(long n1, long n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }

    private static int gcd(int n1, int n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }
    private int brPos = 0;
    private int[] brBuf = null;
    
    private final int readBufInt() throws IOException {
        if (brBuf == null || brBuf.length == brPos) {
            brBuf = readIntArray();
            brPos = 0;
            return readBufInt();
        }
        return brBuf[brPos++];
    }

    private final int readInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private final long readLong() throws IOException {
        return Long.parseLong(br.readLine());
    }

    private final int[] readIntArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        int[] out = new int[cnt];
        for (int i = 0; i < cnt; i++) out[i] = Integer.parseInt(s[i]);
        return out;
    }

    private final char[] readCharArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        char[] out = new char[cnt];
        for (int i = 0; i < cnt; i++) out[i] = s[i].charAt(0);
        return out;
    }

    private final long[] readLongArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        long[] out = new long[cnt];
        for (int i = 0; i < cnt; i++) out[i] = Long.parseLong(s[i]);
        return out;
    }

    private final String[] readStrArray() throws IOException {
//      String[] s = br.readLine().split(" ");
      List<String> res = new ArrayList<String>();
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      while (st.hasMoreTokens()) {
          res.add(st.nextToken());
      }
      
      return res.toArray(new String[0]);
  }
    static long startTime;
    public static void main(String[] args) {
        B app = new B();
        try {
            app.br = new BufferedReader(new InputStreamReader(System.in));
            app.solve();
        } catch(Throwable e) {
            e.printStackTrace();
        } finally {
            try { app.br.close();} catch (Exception igunore) {}
        }
        d(System.currentTimeMillis() - startTime + " ms");
        pw.flush();
        pw.close();
    }

   static final void d(Object ... o) {
       if (isDebug) pw.println(deepToString(o));
   }

   static final void d(int[][] oA) {
       for (int[] o : oA) {
           d(o);
       }
   }
   static final void d(boolean[][] oA) {
       for (boolean[] o : oA) {
           d(o);
       }
   }

   BufferedReader br = null;
   static PrintWriter pw = new PrintWriter(System.out);

   
    void permutationAll(int[] p) {
        permutation(p, 0, p.length - 1);
    }

    void permutationRange(int from, int to) {
        int cnt = to - from + 1;
        int[] elements = new int[cnt];
        for (int i = 0 ; i <  cnt; i++) elements[i] = from++;
        permutation(elements, 0, cnt - 1);
    }

    void permutation(int[] elements, int nowCnt, int totalCnt) {
        if (nowCnt == totalCnt) { 
            // TODO insertCode
        } else {

            for (int i = nowCnt; i <= totalCnt; i++) {
                int tmp = elements[nowCnt]; 
                elements[nowCnt] = elements[i]; 
                elements[i] = tmp;
                permutation(elements, nowCnt+1, totalCnt);
                tmp = elements[nowCnt]; 
                elements[nowCnt] = elements[i]; 
                elements[i] = tmp;
            }
        }
    }


}
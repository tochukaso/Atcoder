package B11;


import static java.util.Arrays.deepToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class D {
    private static final boolean isDebug = false;

    int N ;
    
    static class Pascale {
        
        
        static BigDecimal[][] probability = new BigDecimal[1010][1010];
        
        static {
           //probability[0] = new double[1]l;
            for (int i = 0; i < probability.length; i++) {
                Arrays.fill(probability[i], BigDecimal.ZERO);
            }
           probability[0][0] = BigDecimal.ONE;
           for (int i = 1; i < probability.length; i++) {
              //probability[i] = new double[i + 1];
              probability[i][0] = BigDecimal.ONE;
              probability[i][i] = BigDecimal.ONE;
              for (int j = 1; j < i ; j++) {
                  probability[i][j] = probability[i - 1][j - 1].add(probability[i - 1][j]);
              }
           }
            
        }
        
        static BigDecimal calc(int cnt, int pCnt) {
            BigDecimal allCnt = new BigDecimal(2).pow(cnt); 
            BigDecimal elementCnt = probability[cnt][pCnt]; 
            BigDecimal p = elementCnt.divide(allCnt, MathContext.DECIMAL128);
            return p;
        }
    }
    
    /**
     * @throws Throwable
     */
    void solve() throws Throwable {
        startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int D = sc.nextInt();
        
        int X = sc.nextInt();
        int Y = sc.nextInt();
        
        if (X % D != 0 || Y % D != 0) {
            pw.println(0);
            return;
        }
       
        X = Math.abs(X / D);
        Y = Math.abs(Y / D);
         
        BigDecimal res = BigDecimal.ZERO;
        for (int i = X; i <= N; i++) {
            
            // iは上下方向への総移動回数
            // moveXCntは目的方向への移動回数
            int moveXCnt = (i - X);
            if (moveXCnt %2 !=0) {
                // 目的方向への移動後に余った移動回数が偶数でない場合に目的地に戻れないのでコンテニューする
                continue;
            }
            moveXCnt = moveXCnt / 2 + X;

            int moveYCnt = (N - i - Y);
            if (moveYCnt < 0 || moveYCnt %2 !=0) {
                continue;
            }
            moveYCnt = moveYCnt / 2 + Y;
            res = res.add(Pascale.calc(N, i).multiply(Pascale.calc(i, moveXCnt).multiply(Pascale.calc(N - i, moveYCnt))));
        }
        
        pw.println(res);
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
        D app = new D();
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
   

}

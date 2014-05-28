package R23;

import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;
 
import java.math.*;
import java.io.*;
import java.text.*;
import java.util.*;
 
public class B {
//    private static final String INPUT_PATH = "C:\\atcoder\\b_008_004.txt";
    private static final String INPUT_PATH = null;
 
    public void solve() { 
        try {
            br = new BufferedReader(new InputStreamReader(
                    INPUT_PATH == null ? System.in : new FileInputStream(new File(INPUT_PATH))));
 
            int R = readBufInt();
            int C = readBufInt();
            int D = readBufInt();
 
            long[][] takos = new long[R][C];
            
            for (int i = 0; i < R; i++) {
                takos[i] = readLongArray();
            }
 
            long max = 0;
            
            boolean isG = D % 2 == 0;
 
            if (isG && D >= 2) {
                max = takos[0][0];
            }
 
            int def = isG ? 2 : 1;
            
            for (int i = def; i <= D ; i += 2) {
                for (int j = i ; j >= 0; j --) {
                    int h = i - j;
                    if (h >= R) {
                        break;
                    }
                    if (j >= C) {
                        continue;
                    }
                    max = max(max, takos[h][j]);
//                    System.out.println(takos[h][j] + " : " + h + " " + j);
                }
                
            }
            
            
            pw.println(max);
            
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception igunore) {}
        }
    }
 
    boolean deepEquals(int[] i1, int[] i2) {
        if (i1.length != i2.length) return false;
        for (int i = 0 ; i < i1.length; i++) if (i1[i] != i2[i]) return false;
        return true;
    }
    String getKey(int[] i) {
        StringBuilder sb = new StringBuilder();
        for (int s : i) sb.append(" " + s);
        return sb.toString();
    }
 
    private int brPos = 0;
    private int[] brBuf = null;
    
    private int readBufInt() throws IOException {
        if (brBuf == null || brBuf.length == brPos) {
            brBuf = readIntArray();
            brPos = 0;
        }
        return brBuf[brPos++];
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
        for (int i = 0; i < cnt; i++) out[i] = Integer.parseInt(s[i]);
        return out;
    }
 
    private Integer[] convIntArray(int[] arg) {
        int len = arg.length;
        Integer[] res = new Integer[len];
        for (int i = 0; i < len; i++) res[i] = arg[i];
        return res;
    }
    
    private long[] readLongArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        long[] out = new long[cnt];
        for (int i = 0; i < cnt; i++) out[i] = Long.parseLong(s[i]);
        return out;
    }
 
    private String[] readStrArray() throws IOException {
        String[] s = br.readLine().split(" ");
        return s;
    }
 
    void permutationAll(int[] p) {
        permutation(p, 0, p.length - 1);
     }
    
    void permutationRange(int from, int to) {
        int cnt = to - from + 1;
        int[] elements = new int[cnt];
        for (int i = 0 ; i <  cnt; i++) elements[i] = from++;
        permutation(elements, 0, cnt - 1);
    }
 
    void permutationString(String element) {
        char[] elements = element.toCharArray();
        permutationString(elements, 0, elements.length - 1);
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
 
    void permutationString(char[] elements, int nowCnt, int totalCnt) {
        if (nowCnt == totalCnt) { 
            
            // TODO insertCode
        } else {
            
          for (int i = nowCnt; i <= totalCnt; i++) {
            char tmp = elements[nowCnt]; 
            elements[nowCnt] = elements[i]; 
            elements[i] = tmp;
            permutationString(elements, nowCnt+1, totalCnt);
            tmp = elements[nowCnt]; 
            elements[nowCnt] = elements[i]; 
            elements[i] = tmp;
          }
        }
     }
 
    private static long gcd(long n1, long n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }
    private static int gcd(int n1, int n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }
 
    public static void main(String[] args) {
        long from = System.currentTimeMillis();
        B app = new B();
        app.solve();
        pw.flush();
        pw.println(System.currentTimeMillis() - from);
    }
 
    void printMatrix(int[][] p) {
        for (int[] i : p) printArray(i);
    }
    
    void printArray(int[] p) {
        for (int i : p) System.out.print(i + " ");
        System.out.println();
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
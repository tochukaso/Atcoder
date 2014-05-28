import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;

import java.math.*;
import java.io.*;
import java.text.*;
import java.util.*;
 
public class Main {
//    private static final String INPUT_PATH = "C:\\atcoder\\b_008_004.txt";
    private static final String INPUT_PATH = null;

    int D = 1000000007;
    
    int M = 0;
    int K = 0;
    
    String S = null;
    long[] aArray;
    long[] cArray;

    long[][] cMatrix;
    
    public void solve() { 
        try {
            br = new BufferedReader(new InputStreamReader(
                    INPUT_PATH == null ? System.in : new FileInputStream(new File(INPUT_PATH))));
            
            K = readBufInt();
            M = readBufInt();

            aArray = new long[K];
            cArray = new long[K];
            cMatrix = new long[K][K];
            
            long[] A = readLongArray();
            long[] C = readLongArray();
                
            for(int i = 0; i < K; i++) {
                aArray[i] = A[i];
                cArray[i] = C[i];
                if (i == 0) {
                    cMatrix[i] = cArray;
/**
                    for (int j = 0; j < K - 1; j++) {
                        cMatrix[i][j] = 1;
                    }
**/
                } else {

                    for (int j = 0; j < K; j++) {
                        cMatrix[i][j] = i - 1 == j ? 1 : 0;
                    }
                }
            }
            
            long[][] nextMatrix = cMatrix;
            long index = M;
            String binary = new StringBuffer( Long.toBinaryString(index) ).reverse().toString();
            
            long[][] workAarray = new long[K][1];
            for (int i = 0; i < K; i++) {
                workAarray[i][0] = aArray[i];
            }

            long[][] ansArray = workAarray;
            
            for (int i = 0; i < binary.length(); i++) {
                workAarray = maltipuleMatrix(nextMatrix, workAarray);

                if (binary.charAt(i) == '1') {
                    ansArray = maltipuleMatrix(ansArray, workAarray);
//                    cMatrix = maltipuleMatrix(cMatrix, nextMatrix);
//                    nextMatrix = cMatrix;
                } else {
//                    nextMatrix = maltipuleMatrix(nextMatrix, nextMatrix);
                }
            }
/**            
            while (index > 0) {
                if (index % 2 == 1) {
                    cMatrix = maltipuleMatrix(cMatrix, nextMatrix);
                } else {
                    nextMatrix = maltipuleMatrix(nextMatrix, nextMatrix);
                }
                index /= 2;
            }
**/            
            long bef = 0;
            for (int i = 0; i < K; i++) {
                bef ^= (cMatrix[K - 1][i] & aArray[K - i - 1]);
            }

/**            
            for (int i = K; i < M; i++) {
                aArray[i] = calculate(i);
            }
**/            
            pw.println(bef);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception igunore) {}
        }
    }

    int[][] maltipuleMatrix(int[][] lineMatrix, int[][] columnMatrix) {
        
        if (lineMatrix[0].length != columnMatrix.length) {
            throw new IllegalArgumentException(" line elements and column elements does'nt matching");
        }
        
        int[][] res = new int[lineMatrix.length][columnMatrix[0].length];

        for (int lineIndex = 0; lineIndex < lineMatrix.length; lineIndex ++) {
            for (int columnIndex = 0; columnIndex < columnMatrix[0].length; columnIndex ++) {
                int num = 0;
                for (int numIndex = 0; numIndex < lineMatrix.length; numIndex++) {
                    num += lineMatrix[lineIndex][numIndex] * columnMatrix[numIndex][columnIndex];
                }
                res[lineIndex][columnIndex] = num;
            }
        }
        
        return res;
    }
    

    long[][] maltipuleMatrix(long[][] lineMatrix, long[][] columnMatrix) {
        
        if (lineMatrix[0].length != columnMatrix.length) {
            throw new IllegalArgumentException(" line elements and column elements does'nt matching");
        }
        
        long[][] res = new long[lineMatrix.length][columnMatrix[0].length];

        for (int lineIndex = 0; lineIndex < lineMatrix.length; lineIndex ++) {
            for (int columnIndex = 0; columnIndex < columnMatrix[0].length; columnIndex ++) {
                long num = 0;
                for (int numIndex = 0; numIndex < lineMatrix.length; numIndex++) {
                    num ^= lineMatrix[lineIndex][numIndex] & columnMatrix[numIndex][columnIndex];
//                    num += lineMatrix[lineIndex][numIndex] * columnMatrix[numIndex][columnIndex];
                }
                res[lineIndex][columnIndex] = num;
            }
        }
        
        return res;
    }

    
    long calculate(int aIndex) {
            
        long bef = cArray[0] & aArray[aIndex - 1];
        for (int i = 1; i < K; i++) {
            bef ^= (cArray[i] & aArray[aIndex - i - 1]);
        }
        
        return bef;
    }
    
    public void newSolve() {
        
        Scanner in = new Scanner(System.in);
        K = in.nextInt();
        int M = in.nextInt();
        long[] A = new long[K];
        for (int k = 0; k < K; k++) {
            A[k] = in.nextLong();
        }
        long[] C = new long[K];
        for (int k = 0; k < K; k++) {
            C[k] = in.nextLong();
        }
        
        long[][] a = new long[K][K];
        a[0] = C;
        for (int k = 1; k < K; k++) {
            a[k][k - 1] = -1;
        }
        long[][] b = matpow(a, M - 1);
        long ans = 0;
        for (int k = 0; k < K; k++) {
            ans ^= b[K - 1][k] & A[K - k - 1];
        }
        
        System.out.println(ans);
    }
    long[][] matmul(long[][] a, long[][] b) {
        long[][] c = new long[K][K];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                for (int k = 0; k < K; k++) {
                    c[i][j] ^= a[i][k] & b[k][j];
                }
            }
        }
        return c;
    }
    
    long[][] matpow(long[][]a, int n) {
        long[][] b;
        if (n == 0) {
            b = new long[K][K];
            for (int k = 0; k < K; k++) {
                b[k][k] = -1;
            }
        } else {
            long[][] c = matpow(a, n / 2);
            b = matmul(c, c);
            if (n % 2 == 1) {
                b = matmul(a, b);
            }
        }
        return b;
    }
    
    
    void dp(int target) {
        
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
        Main app = new Main();
/**        
        int[][] a1 = {
                        {3, 4},  
                        {5, 6},
                      };

        int[][] a2 = {
                        {2, -1},  
                        {0, 1},
                      };

        int[][] res = app.maltipuleMatrix(a1, a2);
        
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] + ",");
            }
            System.out.println();
        }
**/        
//        app.newSolve();
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

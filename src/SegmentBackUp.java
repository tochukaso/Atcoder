

import static java.util.Arrays.deepToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
 
public class SegmentBackUp {
    private static final boolean isDebug = false;
    
    int N ;


    /**
     * セグメントツリーの実装
     * セグメントツリー構造はRMQ(Range Minimum Query)
     * 特定の区間での最小値を保持するために使用するデータ構造を保持する。
     *  
     * segment tree
     */
    public class SegmentTree {

        // データのサイズ数
        int elementCnt;

        // セグメント木を持つ配列。木構造のため、2*elementCnt-1のデータ数になる。
        int[] dat;

        // オーバーフローを加味して最大値は2の30乗にしておく。は2の30乗にしておく。
        static final int INF = 1 << 30;

        /**
         * デフォルトコンストラクタは持たない。
         * 必ず特定の要素数が指定されるべき。
         * @param size ノードの要素数
         */
        public SegmentTree(int size) {
            init(size);
        }
        
        /**
         * init
         */
        void init(int n_) {
            elementCnt = 1;

            // 簡便のため、要素数を2のべき乗にしておく
            while (elementCnt < n_) {
                elementCnt *=2;
            }
            
            dat = new int[2 * elementCnt - 1];
            // 全ての値をMAX値にしておく。
            Arrays.fill(dat, INF);
        }

        /**
         * 特定区間の最小値を更新する。
         * k番目の値(0 - indexed)をaに更新する 
         * @param k
         * @param a
         */
        void update (int k, int a) {
            // 葉の節点 木構造は要素数の2倍-1のデータを持つ。
            k += elementCnt - 1;
            dat[k] = a;
            // 末端からノードまで登りながら更新
            while (k > 0) {
                k = (k - 1) / 2;
                dat[k] = Math.min(dat[k * 2 + 1], dat[k * 2 + 2]);
            }
        }
       
        /**
        /**
         * 区間[a,b]の最小値を求める 
         * 
         * @param a 捜査対象の左端
         * @param b 捜査対象の右端
         * @return
         */
        public int query(int a, int b) {
            return query(a, b, 0, 0, elementCnt);
        }

        public int query(int a, int b, int r) {
            return query(a, b, 0, 0, r);
        }

        public int getReafValue(int reafIndex) {
            return dat[reafIndex + elementCnt - 1];
        }
        /**
         * 区間[a,b]の最小値を求める 
         * 
         * @param a 捜査対象の左端
         * @param b 捜査対象の右端
         * @param k 
         * @param l
         * @param r
         * @return
         */
        private int query (int a, int b, int k , int l, int r) {
            if (r <= a || b <= l) return INF;

            if ( a <= l && r <= b) {
                return dat[k];
            } else {
                int vl = query(a, b, k * 2 + 1, l, (l + r) / 2);
                int vr = query(a, b, k * 2 + 2, (l + r) / 2, r);
                return Math.min(vl, vr);
            }
        }
        
        void debug() {
            for (int i = 0; i < dat.length; i++) {
                System.out.println(dat[i]);
            }
        }
    }


    
    static class Model implements Comparable<Model>{
        int l;
        int r;
        int c;
        
        Model(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }
        

        @Override
        public int compareTo(Model o) {
            
            if (true) {
                return Integer.compare(o.l,this.l);
            }
            // TODO Auto-generated method stub
            if (l < o.l) {
                return - 1;
            } else if (l == o.l) {
                return 0;
            }
            
            return 1;
        }
    }
    /**
     * @throws Throwable
     */
    void solve() throws Throwable {
        startTime = System.currentTimeMillis();
        
        N = readBufInt();
        int L = readBufInt();
        
        Model[] elem = new Model[N];
        for (int i = 0; i < N; i++) {
           int l = readBufInt() ;
           int r = readBufInt() ;
           int c = readBufInt() ;
           
           elem[i] = new Model(l, r, c);
        }
        
//        Arrays.sort(elem);

        Arrays.sort(elem,new Comparator<Model>() {
            public int compare(Model o1, Model o2) {
                return Integer.compare(o1.l, o2.l);
            }
        });
        
        
        SegmentTree tree = new SegmentTree(L + 1);
        tree.update(0, 0); 
        
        for (int i = 0; i < N; i++) {
           int v = tree.query(elem[i].l, 1) + elem[i].c;
           
           if (v < tree.getReafValue(elem[i].r)) {
               tree.update(elem[i].r, v); 
           }
        }
        pw.println(tree.getReafValue(L));
        
        tree.debug();
//        int dp[]  = new int[L + 1];
//        Arrays.fill(dp, 10000000);
//        dp[0] = 0;
//        for (int i = 0; i <= L; i++) {
//
//            for (int j = 0; j < N; j++) {
//                Model m = elem[j];
//                if (m.l > i) break;
//                if (m.r < i) continue;
//                 
//                dp[i] = Math.min(dp[i], dp[m.l] + m.c); 
//            }
//            //System.out.println(dp[i]) ;
//        }
//        pw.println(dp[L]);
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
        SegmentBackUp app = new SegmentBackUp();
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
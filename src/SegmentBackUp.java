

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
     * �Z�O�����g�c���[�̎���
     * �Z�O�����g�c���[�\����RMQ(Range Minimum Query)
     * ����̋�Ԃł̍ŏ��l��ێ����邽�߂Ɏg�p����f�[�^�\����ێ�����B
     *  
     * segment tree
     */
    public class SegmentTree {

        // �f�[�^�̃T�C�Y��
        int elementCnt;

        // �Z�O�����g�؂����z��B�؍\���̂��߁A2*elementCnt-1�̃f�[�^���ɂȂ�B
        int[] dat;

        // �I�[�o�[�t���[���������čő�l��2��30��ɂ��Ă����B��2��30��ɂ��Ă����B
        static final int INF = 1 << 30;

        /**
         * �f�t�H���g�R���X�g���N�^�͎����Ȃ��B
         * �K������̗v�f�����w�肳���ׂ��B
         * @param size �m�[�h�̗v�f��
         */
        public SegmentTree(int size) {
            init(size);
        }
        
        /**
         * init
         */
        void init(int n_) {
            elementCnt = 1;

            // �ȕւ̂��߁A�v�f����2�ׂ̂���ɂ��Ă���
            while (elementCnt < n_) {
                elementCnt *=2;
            }
            
            dat = new int[2 * elementCnt - 1];
            // �S�Ă̒l��MAX�l�ɂ��Ă����B
            Arrays.fill(dat, INF);
        }

        /**
         * �����Ԃ̍ŏ��l���X�V����B
         * k�Ԗڂ̒l(0 - indexed)��a�ɍX�V���� 
         * @param k
         * @param a
         */
        void update (int k, int a) {
            // �t�̐ߓ_ �؍\���͗v�f����2�{-1�̃f�[�^�����B
            k += elementCnt - 1;
            dat[k] = a;
            // ���[����m�[�h�܂œo��Ȃ���X�V
            while (k > 0) {
                k = (k - 1) / 2;
                dat[k] = Math.min(dat[k * 2 + 1], dat[k * 2 + 2]);
            }
        }
       
        /**
        /**
         * ���[a,b]�̍ŏ��l�����߂� 
         * 
         * @param a �{���Ώۂ̍��[
         * @param b �{���Ώۂ̉E�[
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
         * ���[a,b]�̍ŏ��l�����߂� 
         * 
         * @param a �{���Ώۂ̍��[
         * @param b �{���Ώۂ̉E�[
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
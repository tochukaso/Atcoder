

import static java.util.Arrays.deepToString;

import java.io.BufferedReader;
import java.io.IOException; import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * modidy comment
 * add comment
 */
public class Main {
    private static final boolean isDebug = false;

    void solve() throws Throwable {
        startTime = System.currentTimeMillis();
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


    static long startTime;
    public static void main(String[] args) {
        Main app = new Main();
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

    static PrintWriter pw = new PrintWriter(System.out);

    BufferedReader br = null;
    private int brPos = 0;
    private int[] brBuf = null;

    private String delimiter = " ";
    public void close() {
        try {
            this.br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public final int readInt()  {
        if (brBuf == null || brBuf.length == brPos) {
            brBuf = readIntArray();
            brPos = 0;
            return readInt();
        }
        return brBuf[brPos++];
    }

    private String readLine() {
        try {
            return br.readLine();
        } catch(IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException(ioe);
        }
    }

    public final int readNextInt()  {
        return Integer.parseInt(readLine());
    }

    public final long readNextLong()  {
        return Long.parseLong(readLine());
    }

    public final int[] readIntArray()  {
        String[] s = readStrArray();
        int cnt = s.length;
        int[] out = new int[cnt];
        for (int i = 0; i < cnt; i++) out[i] = Integer.parseInt(s[i]);
        return out;
    }

    public final int[] readIntColumnArray(int N)  {
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            res[i] = readInt();
        } 
        return res;
    }

    public final int[][] readIntMatrix(int N) {
        int[][] res = new int[N][];
        for (int i = 0; i < N; i++) {
            res[i] = readIntArray();
        }

        return res;
    }

    public final char[][] readCharMatrix(int N) {
        char[][] res = new char[N][];
        for (int i = 0; i < N; i++) {
            res[i] = readCharArray();
        }

        return res;
    }

    public final char[] readCharArray()  {
        String[] s = readStrArray();
        int cnt = s.length;
        char[] out = new char[cnt];
        for (int i = 0; i < cnt; i++) out[i] = s[i].charAt(0);
        return out;
    }

    public final long[] readLongArray()  {
        String[] s = readStrArray();
        int cnt = s.length;
        long[] out = new long[cnt];
        for (int i = 0; i < cnt; i++) out[i] = Long.parseLong(s[i]);
        return out;
    }

    public final String readString() {
        return readLine();
    }

    public final String[] readStrArray()  {
        List<String> res = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(readLine(), delimiter);
        while (st.hasMoreTokens()) {
            res.add(st.nextToken());
        }
        return res.toArray(new String[0]);
    }

    public void setDelimiter(String delim) {
        this.delimiter = delim;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    /**
     * UnionFindのデータ構造を実現するクラス
     * UnionFindは各要素がどの集合に所属しているかを判別するために使用できる
     *
     */
    public static class UnionFind {
        // 各要素が保持している親のindex
        private int[] parentArray ;

        // 各要素が保持している木の深さ
        private int[] rankArray;

        // 要素数
        private int nodeCount;

        public UnionFind(int totalCount) {
            this.nodeCount = totalCount;
            parentArray = new int[totalCount];
            rankArray = new int[totalCount];

            for (int i = 0; i < totalCount; i++) {
                parentArray[i] = i;
                rankArray[i] = 0;
            }
        }

        /**
         * <pre>
         * 要素検索
         * インデックスが要素の順番と等しいときは引数の値をそのまま返却する。
         * インデックスの順番が等しくないときは検索したインデックスリストに
         * 格納されている番号を元に再度検索を行い、再帰的にインデックス配列の番号を入れ替える。
         * </pre>
         * @param nodeIndex
         * @return
         */
        public int findParent(int nodeIndex) {
            if (parentArray[nodeIndex] == nodeIndex) {
                return nodeIndex;
            } else {
                return parentArray[nodeIndex] = findParent(parentArray[nodeIndex]);
            }
        }

        public void union(int node1, int node2) {
            node1 = findParent(node1);
            node2 = findParent(node2);
            if (node1 == node2) {
                return;
            }

            if (rankArray[node1] < rankArray[node2]) {
                parentArray[node1] = node2;
            } else if (rankArray[node1] > rankArray[node2]) {
                parentArray[node2] = node1;
            } else {
                parentArray[node2] = node1;
                rankArray[node1]++;
            }

        }

        public boolean isSameParent(int node1, int node2) {
            return findParent(node1) == findParent(node2);
        }

        public int getNodeCount() {
            return this.nodeCount;
        }
        
        public int getNodeConut(int node) {
            return rankArray[findParent(node)];
        }
    }

}

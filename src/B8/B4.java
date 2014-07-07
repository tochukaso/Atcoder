package B8;
import static java.util.Arrays.deepToString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class B4 {
//    private static final String INPUT_PATH = "C:\\atcoder\\b_008_004.txt";
    private static final String INPUT_PATH = null;

    boolean[][] map = null;
    int[][] mPos = null;
//    Map<Integer, Long> dp = new HashMap<Integer, Long>();
    Map<String, Integer> dp = new HashMap<String, Integer>();
    
    int W = 0;
    int H = 0;
    int m = 0;

    int [][][][] dpM = null;


    int dfs(int sx, int sy, int ex, int ey) {

//        if (dpM[sx][sy][ex][ey] != 0) {
//            return dpM[sx][sy][ex][ey];
//        }
        
        String key = "" + sx + ":" + sy + ":" + ex + ":" + ey;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        
        int max = 0;
        for (int i = 0; i < m; i++) {
            int score = Math.max(0, (ey - sy) + (ex - sx) - 1);
            
            int dx = mPos[i][0];
            int dy = mPos[i][1];
            if (sx >= dx || dx > ex || sy >= dy || dy > ey) {
                continue;
            }
            score += dfs(sx, sy, dx - 1, dy - 1);
            score += dfs(dx, sy, ex, dy - 1);
            score += dfs(sx, dy, dx - 1, ey);
            score += dfs(dx, dy, ex, ey);
            max = Math.max(max, score);
        }
//        dpM[sx][sy][ex][ey] = max;
        dp.put(key, max);
        return max;
    }
    
    
    public void newSolve() { 
        try {
            br = new BufferedReader(new InputStreamReader(
                    INPUT_PATH == null ? System.in : new FileInputStream(new File(INPUT_PATH))));
            int[] n = readIntArray();
            
            W = n[0];
            H = n[1];
            
            m = readInt();
            
            mPos = new int[m][2];
            for (int i = 0; i < m; i++) {
                mPos[i] = readIntArray();
            }

    //        dpM = new int[W + 2][H + 2][W + 2][H + 2];

            
            pw.println(dfs(0, 0, W, H));
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception igunore) {}
        }
    }


    
    
    public void solve() { 
        try {
            br = new BufferedReader(new InputStreamReader(
                    INPUT_PATH == null ? System.in : new FileInputStream(new File(INPUT_PATH))));
            int[] n = readIntArray();
            
            W = n[0];
            H = n[1];
            
            m = readInt();
            
            map = new boolean[H + 1][W + 1];
            for (int i = 1; i <= H; i++) {
                Arrays.fill(map[i], true);
                map[i][0] = false;
            }
            
            mPos = new int[m][2];
            for (int i = 0; i < m; i++) {
                mPos[i] = readIntArray();
            }
            
            pw.println(search(0, 0));
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception igunore) {}
        }
    }

    long search(int searchSet, long score) {
        if (dp.containsKey(searchSet)) {
            if (score <= dp.get(searchSet)) {
//                return dp.get(searchSet);
                return dp.get(searchSet);
            } 
        }
        //dp.put(searchSet, score);
        
        if (searchSet == (1 << m) - 1) {
            return score;
        }
            
        long sum = 0;
        
        for (int i = 0; i < m; i++) {
            if ((searchSet & (1 << i)) == 0) {
                int y = mPos[i][1];
                int x = mPos[i][0];
                int s = 0;

                List<Type> list = new ArrayList<Type>();
//                boolean[][] changePos= new boolean[H + 1][W + 1];

                if (map[y][x]) {
                    map[y][x] = false;
                    list.add(new Type(y,x));
//                    changePos[y][x] = true;
                    s++;
                }
                
                for (int j = y - 1; j > 0; j --) {
                    if (map[j][x]) {
                        s++;
                        map[j][x] = false;
                        list.add(new Type(j,x));
//                        changePos[j][x] = true;
                    } else {
                        break;
                    }
                }

                for (int j = y + 1; j <= H; j ++) {
                    if (map[j][x]) {
                        s++;
                        map[j][x] = false;
//                        changePos[j][x] = true;
                        list.add(new Type(j,x));
                    } else {
                        break;
                    }
                }

                for (int j = x - 1; j > 0; j --) {
                    if (map[y][j]) {
                        s++;
                        map[y][j] = false;
//                        changePos[y][j] = true;
                        list.add(new Type(y,j));
                    } else {
                        break;
                    }
                }

                for (int j = x + 1; j <= W; j ++) {
                    if (map[y][j]) {
                        s++;
                        map[y][j] = false;
//                        changePos[y][j] = true;
                        list.add(new Type(y,j));
                    } else {
                        break;
                    }
                }
                
                int nextSet = searchSet | (1 << i);
                sum = Math.max(sum, search(nextSet, score + s));

                /**
                for (int j = y - 1; j > 0; j --) {
                    if (!map[j][x]) {
                        map[j][x] = true;
                    } else {
                        break;
                    }
                }

                for (int j = y + 1; j <= H; j ++) {
                    if (!map[j][x]) {
                        map[j][x] = true;
                    } else {
                        break;
                    }
                }

                for (int j = x - 1; j > 0; j --) {
                    if (!map[y][j]) {
                        map[y][j] = true;
                    } else {
                        break;
                    }
                }

                for (int j = x + 1; j <= W; j ++) {
                    if (!map[y][j]) {
                        map[y][j] = true;
                    } else {
                        break;
                    }
                }
                
                **/
/**                
                for (int j = 1; j <= H; j++) {
                    for (int k = 1; k <= W; k++) {
                        if (changePos[j][k]) map[j][k] = true;
                    }
                }
**/                
                for (Type t : list) {
                    map[t.x][t.y] = true;
                }
            }
        }
        
        return sum;
        
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
        B4 app = new B4();
//        app.solve();
        app.newSolve();

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
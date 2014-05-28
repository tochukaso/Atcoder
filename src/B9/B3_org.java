package B9;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;

import java.math.*;
import java.io.*;
import java.text.*;
import java.util.*;
 
public class B3_org {
//    private static final String INPUT_PATH = "C:\\atcoder\\b_008_004.txt";
    private static final String INPUT_PATH = null;

    int D = 1000000007;
    
    int N = 0;
    int K = 0;
    String S = null;

    static class SortIndex implements Comparable<SortIndex> {
        
        char c;
        int index;
        
        SortIndex(char c, int index) {
            this.c = c;
            this.index = index;
        }

        @Override
        public int compareTo(SortIndex o) {
            
            SortIndex compare = (SortIndex) o;
            if (this.c > compare.c) {
                return  1 ;
            } else if (this.c == compare.c) {
                return  this.index > compare.index ? -1 : 1 ;
            }

            return - 1;
        }
        
    }

    int[] addArray(int[] original, int addNum) {
        int[] res = new int[original.length + 1];
        System.arraycopy(original, 0, res, 0, original.length);
        res[original.length] = addNum;
        return res;
    }

    int[] addArray(int[] original, int[] addArray) {
        int[] res = new int[original.length + addArray.length];
        System.arraycopy(original, 0, res, 0, original.length);
        System.arraycopy(addArray, 0, res, original.length, addArray.length);
        return res;
    }
    
    int[] removeArray(int[] original, int removeIndex) {
        int[] res = new int[original.length - 1];
        int[] before = copyOfRange(original, 0, removeIndex);
        int[] after = copyOfRange(original, removeIndex + 1, original.length);
        System.arraycopy(before, 0, res, 0, before.length);
        System.arraycopy(after, 0, res, before.length, after.length);
        return res;
    }

    int[] swapArray(int[] original, int fromIndex, int toIndex) {
        int[] res = Arrays.copyOf(original, original.length);
        int tmp = res[fromIndex];
        res[fromIndex] = res[toIndex];
        res[toIndex] = tmp;
        return res;
    }

    int convArray(int[] compare1, int[] compare2, int matchedIndex) {
        int sum = 0;
        for (int i = 0; i < matchedIndex; i++) {
            if (cA[compare1[i]] != cA[compare2[i]]) sum++;
        }
        
        boolean[] used = new boolean[compare2.length];
        for (int i = matchedIndex; i < compare1.length; i++) {
            boolean isMatch = false;
            for (int j = matchedIndex; j < compare2.length; j++) {
                if (cA[compare1[i]] == cA[compare2[j]] && !used[j]) {
                    used[j] = true;
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch) sum++;
        }
        return sum;
    }

    int matchingConvArray(int[] compare1, int[] compare2) {
        int sum = 0;
        for (int i = 0; i < compare1.length; i++) {
            if (compare1[i] != compare2[i]) sum++;
        }
        return sum;
    }

    char[] cA = null;
    
    public void newSolve() {
     
        try {
            br = new BufferedReader(new InputStreamReader(
                    INPUT_PATH == null ? System.in : new FileInputStream(new File(INPUT_PATH))));
            
            N = readBufInt();
            K = readBufInt();
            
            S = br.readLine();

            cA = S.toCharArray();
            
            List<SortIndex> sortList = new ArrayList<SortIndex>();
            for (int i = 0; i < N; i++) {
                SortIndex index = new SortIndex(cA[i], i);
                sortList.add(index);
            }
            
            sort(sortList);
            
            int[] ansIndex = new int[N];
            for (int i = 0; i < N; i++) {
                ansIndex[i] = i;
            }

            int[] originIndex = copyOf(ansIndex, N);
            
            for (int charIndex = 0; charIndex < N; charIndex++) {
                for (int sortListIndex = 0; sortListIndex < sortList.size(); sortListIndex++) {
                    SortIndex roopIndex = sortList.get(sortListIndex);
                    
                    if (roopIndex.c == cA[charIndex] && roopIndex.index != charIndex) {
//                        continue;
                    }
                        
                    int[] nextCandidate = swapArray(ansIndex, charIndex, roopIndex.index);
                    
                    int unMatchCnt = convArray(originIndex, nextCandidate, charIndex + 1);
                    
                    if (unMatchCnt <= K) {
                        ansIndex = nextCandidate;
                        
                        for (int moveSearchIndex = 0; moveSearchIndex < sortList.size(); moveSearchIndex++) {
                            SortIndex moveSearch = sortList.get(moveSearchIndex);
                            if (moveSearch.index == charIndex) {
                                moveSearch.index = roopIndex.index;
                                sortList.set(moveSearchIndex, moveSearch);
                            }
                            
                        }
                        
                        sortList.remove(sortListIndex);
                        sort(sortList);
                        break;
                    }
                }
                
            }
            
            for (int index : ansIndex) {
                pw.print(cA[index]);
            }
            pw.println();
            
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
            
            N = readBufInt();
            K = readBufInt();
            
            S = br.readLine();

            char[] cA = S.toCharArray();
            
            char[] sA = copyOf(cA, N);
            sort(sA);
            
            char[] ans = new char[N];
            
            int unMatchCnt = 0;
            
            boolean[] used = new boolean[N];

            boolean[] notPos = new boolean[N];

            int[] movePos = new int[N];
            Arrays.fill(movePos, -1);
            
            for (int i = 0; i < N; i++) {
                for(int j = 0; j < sA.length; j++) {
                    char now = sA[j];
                    int index = 0;
                    for (int k = 0; k < N; k++) {
                        if (cA[k] == now && !used[k]) {
                            index = k;
                            break;
                        }
                    }

                    if(cA[i] == now && !used[i]) {
                        index = i;
                    }

                    int bef = index;
                    index = max(index,movePos[index]);
                    
                    int roopMatchCnt = unMatchCnt;
                                        
                    if (index != i) {
                        if (!notPos[bef] ) {
                            roopMatchCnt+=2;
                        } else if (notPos[bef]) {
                            roopMatchCnt++;
                        }
                        if (index != bef) {
                            roopMatchCnt --;
                        }
                    } 
                    if (roopMatchCnt > K) {
                        continue;
                    }
                    
                    unMatchCnt = roopMatchCnt;

                    char[] before = copyOfRange(sA,0,j);
                    char[] after = copyOfRange(sA,j + 1, sA.length);
                    char[] next = new char[sA.length - 1];
                    System.arraycopy(before, 0, next, 0, before.length);
                    System.arraycopy(after, 0, next, before.length, after.length);

                    ans[i] = now;
                    sA = next;
                    used[bef] = true;
                    if (index != i) {
                        notPos[i] = true;
                        movePos[i] = index;
                    }
                    break;

                    /**                    
                    int roopUnMatchCnt = unMatchCnt;
                    boolean isOk = true;
                    
                    for (int k = 0; k < sA.length - 1 ; k++) {
                        if (K < roopUnMatchCnt) {
                            isOk = false;
                            break;
                        }

                        if (next[k] != cA[i+k]) {
                            roopUnMatchCnt++;
                        }
                    }
                    
                    if (isOk) {
                        ans[i] = now;
                        sA = next;
                        used[index] = true;
                        if (index != i) {
                            notPos[i] = true;
                            unMatchCnt++;
                        }
                        break;
                    }
**/                
                    
                }
                
            }
            
            pw.println(ans);
            

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

    static String c1 = "gasgdajslkjdfalskjffasdlkjfgaefawefjlawjegijasivjijflajdaklgjakljfewoigjasfjarqweropjvzvnjlfjiewgioajslajsdfreqwjijasdijfaewhahgasdhourewoqjfdasjlbjlajewijgalqewjpdvjzcasfdjlewjozjclweasdfewljfldksajf";
    static String c2 = "aaaaaaaaaaaaaaaaaaaaaaaaabccdddewefjlfwjegijfsivjijflfjdfklgjfkljfewoigjgsfjgrqweropjvzvnjlfjiewgiogjsljjsdfreqwjijjsdijfjewhjhgksdhourewoqjfdksjlkjlljewijgllqewjpdvjzlssfdjlewjozjslwessdfewljfldkssjf";

    
    static void conv (String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        
        int sum = 0;
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) sum++;
        }
        System.out.println(sum);
    }
    
    public static void main(String[] args) {
        long from = System.currentTimeMillis();
        B3_org app = new B3_org();
//        conv(c1, c2);
        app.newSolve();
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

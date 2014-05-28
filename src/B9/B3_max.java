package B9;

import java.util.Scanner;

public class B3_max {
    static int N, K;
    static String S, T = "";
    static int[] restS, restT;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();
        S = in.next();
        
        restS = new int[26];
        for (int i = 0; i < N; i++) {
            char s = S.charAt(i);
            restS[s - 'a']++;
        }
        restT = restS.clone();
        dfs(0, K);
        
        System.out.println(T);
        in.close();
    }
    
    static boolean dfs(int i, int K) {
        if (i == N) {
            return true;
        }
        int k = N - i;
        for (char c = 'a'; c <= 'z'; c++) {
            k -= Math.min(restS[c - 'a'], restT[c - 'a']);
        }
        if (k > K) {
            return false;
        }
        
        for (char t = 'a'; t <= 'z'; t++) {
            if (restT[t - 'a'] == 0) {
                continue;
            }
            char s = S.charAt(i);
            restS[s - 'a']--;
            restT[t - 'a']--;
            if (dfs(i + 1, (s == t) ? K : K - 1)) {
                T = t + T;
                return true;
            }
            restS[s - 'a']++;
            restT[t - 'a']++;
        }
        
        return false;
    }
}
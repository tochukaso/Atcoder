package B1;
import static java.util.Arrays.deepToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
 
public class Main {
    private static final boolean isDebug = false;
    
    int N;
    
    static final String[] DIRECTION = {"N", "NNE", "NE", "ENE",
                                          "E", "ESE", "SE", "SSE",
                                          "S", "SSW", "SW", "WSW",
                                          "W", "WNW", "NW", "NNW",
    };
    
    static double[] WIND_POWER = {0.2, 1.5, 3.3, 5.4,
                                    7.9, 10.7, 13.8, 17.1,
                                    20.7, 24.4, 28.4, 32.6
    };
    
    
    boolean[][] isLain = new boolean[25][12];
    

    int[] imos = new int[24*12 + 1];
    public final void solve5() throws Throwable { 
        int N = readBufInt();
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("-");
            int fromH = Integer.parseInt(line[0].substring(0,2));
            int fromM = Integer.parseInt(line[0].substring(2,4)) / 5;

            int toH = Integer.parseInt(line[1].substring(0,2));
            int toM = Integer.parseInt(line[1].substring(2,4));
            int tmp = 0;
            if (toM % 5 > 0) {
                tmp++;
            }
            toM = toM / 5 + tmp;
            
            imos[fromH * 12 + fromM]++;
            imos[toH * 12 + toM]--;
        }        

        DecimalFormat format = new DecimalFormat("00");
        
        int s = 0;
        boolean isCall = false;
        int sum = 0;
        for (int i = 0; i < 24 * 12 + 1; i++) {
            sum+=imos[i];
            if (sum >= 1) {
                if (!isCall) {
                    isCall = true;
                    s = i;
                }
            } else {
                if (isCall) {
                    isCall = false;
                    int fromH = s / 12;
                    int fromM = (s % 12) * 5;
                    int toH = i / 12;
                    int toM = (i % 12) * 5;

                    pw.println(format.format(fromH) + 
                            format.format(fromM) + 
                            "-" +
                            format.format(toH) + 
                            format.format(toM));
                }
            }
        }
        
    }
    
    
    public final void solve4() throws Throwable { 
        
        int N = readBufInt();
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("-");
            int fromH = Integer.parseInt(line[0].substring(0,2));
            int fromM = Integer.parseInt(line[0].substring(2,4)) / 5;

            int toH = Integer.parseInt(line[1].substring(0,2));
            int toM = Integer.parseInt(line[1].substring(2,4));
            int tmp = 0;
            if (toM % 5 > 0) {
                tmp++;
            }
            toM = toM / 5 + tmp;
            
            d(fromH + " " + fromM);
            d(toH + " " + toM);
            
            
            boolean isFirstLine = true;
            for (; fromH <= toH; fromH++) {
                int loopFromM = 0;
                int loopToM = 12;
                
                if (isFirstLine) {
                    loopFromM = fromM;
                    isFirstLine = false;
                }
                if (fromH == toH) {
                    loopToM = toM;
                }
                
                for (;loopFromM < loopToM; loopFromM++) {
                    isLain[fromH][loopFromM] = true;
                }
                
            }
        }

        boolean isCall = false;
        int callH = 0;
        int callM = 0;
        DecimalFormat format = new DecimalFormat("00");
        for (int i = 0; i <= 24; i++) {
            for (int j = 0; j < 12; j++) {
                if (isCall) {
                    if (!isLain[i][j]) {
                        pw.println(format.format(callH) + 
                                   format.format(callM * 5) + 
                                   "-" +
                                   format.format(i) + 
                                   format.format(j * 5)
                                   );
                        isCall = false;
                    }
                } else {
                    if (isLain[i][j]) {
                        isCall = true;
                        callH = i;
                        callM = j;
                    }
                }
            }
        }
        
        
        
    }
    public final void solve2() throws Throwable { 
        startTime = System.currentTimeMillis();
        
        int m = readBufInt();
        
        int K = 1000;
 
        String V;
        
        if (m < 0.1 * K) {
            V = "00";
        } else if (m <= 5 * K) {
            int vInt = m * 10 / K ;
            if (vInt / 10 < 1) {
                V = "0" + vInt;
            } else {
                V = String.valueOf(vInt);
            }
        } else if (m <= 30 * K) {
            int vInt = m / K + 50;
            V = String.valueOf(vInt);
        } else if (m <= 70 * K) {
            int vInt = (m / K - 30) / 5 + 80;
            V = String.valueOf(vInt);
        } else {
            V = "89";
        }
        
        pw.println(V);
    }
    
    
    public final void solve3() throws Throwable { 
        startTime = System.currentTimeMillis();
        
        int dis = readBufInt();
        int deg = readBufInt();
        
        BigDecimal wPower = new BigDecimal(deg).divide(new BigDecimal(60), 1, BigDecimal.ROUND_HALF_UP);
        int wIndex = 12;
        for (int i = 0; i < 12; i++) {
            if (wPower.compareTo(new BigDecimal(WIND_POWER[i]).setScale(1, BigDecimal.ROUND_HALF_EVEN ))<= 0) {
                wIndex = i;
                break;
            }
        }

        String Dir = "C";;

        if (wIndex != 0) {
            int range = 36000 / 16;
            int start = range / 2;
            
            int dir = (dis * 10 + start) / range;
            if (dir > 15) dir = 0;
            Dir = DIRECTION[dir];
        }
        
        pw.println(Dir + " " + wIndex);
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
        String[] s = br.readLine().split(" ");
        return s;
    }

    static long startTime;
    public static void main(String[] args) {
        Main app = new Main();
        try {
            app.br = new BufferedReader(new InputStreamReader(System.in));
            app.solve5();
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

   BufferedReader br = null;
   static PrintWriter pw = new PrintWriter(System.out);
}

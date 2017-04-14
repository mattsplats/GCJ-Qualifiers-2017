import java.util.*;
import java.lang.*;
import java.io.*;

class BruteForce {
    public static int[] lastStall (final int n, int k) {
        if (k == n) {
            final int[] none = {0, 0};
            return none;
        }
        
        int[] lastMaxMin = new int[2];
        boolean[] stalls = new boolean[n + 2];
        stalls[0]     = true;
        stalls[n + 1] = true;

        for (; k > 0; k--) {
            int maxMinls = Integer.MIN_VALUE,
                maxMaxls = Integer.MIN_VALUE,
                pos      = 0;

            for (int i = 1; i <= n; i++) {
                if (!stalls[i]) {
                    final int[] thisMinMax = getMinMax(stalls, i);

                    if (thisMinMax[0] > maxMinls) {
                        maxMinls = thisMinMax[0];
                        maxMaxls = thisMinMax[1];
                        pos      = i;
                    
                    } else if (thisMinMax[0] == maxMinls) {
                        if (thisMinMax[1] > maxMaxls) {
                            maxMinls = thisMinMax[0];
                            maxMaxls = thisMinMax[1];
                            pos      = i;
                        }
                    }
                }
            }

            stalls[pos] = true;
            lastMaxMin[0] = maxMaxls;
            lastMaxMin[1] = maxMinls;
            
            // for (boolean b : stalls) System.out.print((b ? "1" : "0") + " ");
            // System.out.println();
        }

        return lastMaxMin;
    }

    public static int[] getMinMax (final boolean[] stalls, final int pos) {
        int ls = 0, rs = 0;
        
        int i = pos - 1;
        while (i >= 0 && !stalls[i]) {
            ls++;
            i--;
        }

        i = pos + 1;
        while (i < stalls.length && !stalls[i]) {
            rs++;
            i++;
        }

        int[] result = {Math.min(ls, rs), Math.max(ls, rs)};
        
        // for (int x : result) System.out.print(x + " ");
        // System.out.println();

        return result;
    }

    public static void main (String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            final int n = in.nextInt();
            final int k = in.nextInt();
            final int[] minMax = lastStall(n, k);
            System.out.println("Case #" + i + ": " + minMax[0] + " " + minMax[1]);
        }

        in.close();
    }
}
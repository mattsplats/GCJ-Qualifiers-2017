import java.util.*;
import java.lang.*;
import java.io.*;

class BruteForce {
    private static Map<String, Integer> states;
    private static int minMoves;
  
    public static String flips (final String input, final int k) {
        states = new HashMap<String, Integer>();
        minMoves = Integer.MAX_VALUE;

        final int len = input.length();
        BitSet b = new BitSet(len);
        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == '+') b.set(i);
        }

        flips_sub(b, 0, len, k);
        return minMoves == Integer.MAX_VALUE ? "IMPOSSIBLE" : ((Integer) minMoves).toString();
    }

    public static void flips_sub (BitSet b, int moveCount, final int len, final int k) {
        if (b.nextClearBit(0) >= len) {
            // System.out.println("Solution: " + moveCount);
            if (moveCount < minMoves) minMoves = moveCount;
            return;
        }
        if (states.containsKey(b.toString()) && states.get(b.toString()) <= moveCount) return;

        states.put(b.toString(), moveCount);
        
        BitSet bCopy = b.get(0, len);
        bCopy.flip(0, k);

        for (int i = k; i <= len; i++) {
            // System.out.print((moveCount + 1) + " (" + (i - k) + ", " + (i - 1) + "): ");
            // System.out.println(bCopy.toString());
            flips_sub(bCopy, moveCount + 1, len, k);
            bCopy.flip(i - k);
            if (i < len) bCopy.flip(i);
        }
    }

    public static void main (String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            String row = in.next();
            int k = in.nextInt();
            System.out.println("Case #" + (i+1) + ": " + flips(row, k));
        }

        in.close();
    }
}
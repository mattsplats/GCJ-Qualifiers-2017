import java.util.*;
import java.lang.*;
import java.io.*;

class Greedy {
    public static int flips (final String input, final int k) {
        final int len = input.length();
        BitSet b = new BitSet(len);
        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == '+') b.set(i);
        }

        int fromLeft  = doFlips((BitSet) b.clone(), len, k),
            fromRight = doFlips(reverseBitSet(b, len), len, k);
        
        if (fromLeft == -1) {
            if (fromRight == -1) return -1;
            return fromRight;
        
        } else if (fromRight == -1) {
            return fromLeft;

        } else return Math.min(fromLeft, fromRight);
    }

    public static int doFlips (BitSet b, final int len, final int k) {
        int pos = b.nextClearBit(0), flipCount = 0;
        while (pos + k <= len) {
             b.flip(pos, pos + k);
             flipCount++;
             pos = b.nextClearBit(pos);
             // System.out.println(b);
        }
        if (pos >= len) return flipCount;
        return -1;
    }

    public static BitSet reverseBitSet (BitSet b, final int len) {
        final int mid = len / 2;
        for (int i = 0; i < mid; i++) {
            if (b.get(i) != b.get(len - i - 1)) {
                 b.flip(i);
                 b.flip(len - i - 1);
             }
        }
        return b;
    }

    public static void main (String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            final String row = in.next();
            final int k = in.nextInt();

            final int flipCount = flips(row, k);
            System.out.println("Case #" + (i+1) + ": " + (flipCount == -1 ? "IMPOSSIBLE" : flipCount));
        }

        in.close();

        // System.out.println(flips("---", 3));
    }
}
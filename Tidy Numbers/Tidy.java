import java.util.*;
import java.lang.*;
import java.io.*;

class Tidy {
    public static String largestTidy (String numStr) {
        final int len = numStr.length();
        if (len < 2) return numStr;  // Numbers with one digit are already tidy

        // Iterate through adjacent pairs of characters until b < a
        char a = numStr.charAt(0),
             b = numStr.charAt(1);
        int index = 1;
        while (a <= b) {
            index++;
            if (index >= len) break;
            a = b;
            b = numStr.charAt(index);
        }

        if (index == len) return numStr;  // If b >= a for all (a,b), the number is already tidy
        
        // Constructing the new string
        StringBuilder newStr = new StringBuilder(numStr);
        int pos = index - 1;
        
        // While we haven't run off the end of the string (pos >= 0) AND
        //      The current digit is a 1 or 0 (if we subtract 1, we'd have to carry) OR
        //      The current digit is equal to the previous digit (if we subract one, it will be less than the previous digit and thus not tidy)
        while (pos >= 0 && (newStr.charAt(pos) < '2' || (pos > 0 && newStr.charAt(pos - 1) == newStr.charAt(pos)))) pos--;

        if (pos < 0) newStr.deleteCharAt(0);                               // We never found a stopping point (delete the zeroth digit)
        else         newStr.setCharAt(pos, minusOne(newStr.charAt(pos)));  // We found a stopping point!  Subtract one from this digit
        
        // Fill everything after the stopping point with 9's
        for (int i = pos + 1; i < newStr.length(); i++) {
            newStr.setCharAt(i, '9');
        }

        return newStr.toString();
    }

    public static char minusOne (char c) {
        return (char) (((int) c) - 1);
    }

    public static void main (String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            final String numStr = in.next();
            System.out.println("Case #" + (i+1) + ": " + largestTidy(numStr));
        }

        in.close();
    }
}
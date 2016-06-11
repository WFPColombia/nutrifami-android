package org.pma.nutrifami.util;

/**
 * Created by Peter Juras on 11.06.16.
 */

public class Range {
    public static int[] range(int min, int max) {
        final int[] range = new int[max - min];
        int currentNumber = min;
        for (int i = 0; i < range.length; i++) {
            range[i] = currentNumber++;
        }
        return range;
    }
}

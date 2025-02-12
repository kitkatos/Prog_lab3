package org.example.utils;

public class ArrayUtils {
    static public int getFirstEmptySlot(Object[] array) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                return i;
            }
        }

        return -1;
    }

    static public boolean isArraysEqual(Object[] array1, Object[] array2) {
        if(array1.length != array2.length) {
            return false;
        }

        for(int i = 0; i < array1.length; i++) {
            if(array1[i] == null && array2[i] == null) {
                continue;
            }

            if(array1[i] == null || array2[i] == null) {
                return false;
            }

            if(array1[i].equals(array2[i])) {
                return false;
            }
        }

        return true;
    }
}

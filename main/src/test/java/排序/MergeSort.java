package 排序;

import java.util.Arrays;

/**
 * @author: Bing Juan
 * @date: 2022/10/25 15 28
 * @desc:
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = {56, 23, 85, 41, 12, 9, 24, 11, 46};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
        sort(array, 0, array.length);
    }

    public static void sort(int[] array, int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort(array, begin, mid);
        sort(array, mid, end);
        merge(array, begin, mid, end);
    }

    public static void merge(int[] array, int begin, int mid, int end) {

        int li = 0;
        int le = mid - begin;

        int ri = mid;
        int re = end;

        int ai = begin;

        int[] cp = new int[mid-begin];
        System.arraycopy(array, begin, cp, 0, cp.length);


        while (li < le) {
            if (ri < re && array[ri] > cp[li]) {
                array[ai] = array[ri];
                ai++;
                ri++;
            } else {
                array[ai++] = cp[li++];
            }
        }
    }
}

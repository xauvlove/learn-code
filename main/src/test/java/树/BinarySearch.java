package 树;

/**
 * @author: Bing Juan
 * @date: 2022/10/25 16 48
 * @desc:
 */
public class BinarySearch {


    public static int search(int[] array, int num) {
        int begin = 0;
        int end = array.length;
        int mid = (begin + end) >> 1;

        int index = -1;
        while (true) {
            if (array[mid] == num) {
                index = mid;
                break;
            } else if (array[mid] > num) {
                end = mid;
                mid = (begin + end) >> 1;
            } else {
                begin = mid+1;
                mid = (begin + end) >> 1;
            }
            if (begin>=end) {
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,1,2,3,4,5,6,7,8,9};
        System.out.println(BinarySearch.search(arr, -1));
    }
}

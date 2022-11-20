package com.xauv.algorithm.刷题.数组;

/**
 * @author: Bing Juan
 * @date: 2022/11/18 14 37
 * @desc:
 *
 * 1.0 < 2.0
 *
 * 1.1 = 1.1.0
 *
 * 1.1 < 1.2
 *
 * 1.1.1 < 1.1.1.1
 */
public class 比较版本号 {

    public static int compare (String version1, String version2) {
        // write code here
        char[] vc1 = version1.toCharArray();
        char[] vc2 = version2.toCharArray();

        boolean inorder = true;

        char[] sc = vc1.length <= vc2.length ? vc1 : vc2;
        char[] lc = vc1.length > vc2.length ? vc1 : vc2;

        if (sc != vc1) {
            inorder = false;
        }

        int idx = 0;
        for (; idx < sc.length; idx++) {
            char c1 = sc[idx];
            char c2 = lc[idx];
            if (c1 > c2) {
                return inorder ? 1 : -1;
            } else if (c1 < c2) {
                return inorder ? -1 : 1;
            }
        }
        for (int i = idx; i < lc.length; i++) {
            if (lc[i] == '.') {
                continue;
            }
            if (lc[i] != '0') {
                return inorder ? -1 : 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String v1 = "1.01";
        String v2 = "1.001";
        System.out.println(compare(v1, v2));
    }
}

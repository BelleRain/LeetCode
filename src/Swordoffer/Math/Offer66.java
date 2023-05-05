package Swordoffer.Math;

/**
 * @author mxy
 * @create 2022-10-22 9:45
 */

import java.util.Arrays;

/**
 * 构建乘积数组  (同 Top238)
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
 * 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 * 示例:
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 */
public class Offer66 {

    public static void main(String[] args) {
        Offer66 offer = new Offer66();
        int[] a = {1,2,3,4,5};
        //System.out.println(offer.multi(a,0,2));
        System.out.println(Arrays.toString(offer.constructArr(a)));
    }

    /*
    注意点：递推思想
     */

    /**
     * 题解链接：https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/solution/mian-shi-ti-66-gou-jian-cheng-ji-shu-zu-biao-ge-fe/
     * 方法一： 正序 + 逆序 遍历数组（左右乘积列表，空间复杂度为 O(1) ）
     * 复杂度分析：
     * 时间复杂度 O(N) ： 其中 N 为数组长度，两轮遍历数组 a ，使用 O(N) 时间。
     * 空间复杂度 O(1) ： 变量 tmp 使用常数大小额外空间（数组 b 作为返回值，不计入复杂度考虑）。
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        int len = a.length;
        if (len == 0) return new int[0];
        int[] b = new int[len];
        b[0] = 1;
        int tmp = 1;
        //b[i] = a[0] * a[1] * ... * a[i-1] * a[i+1] * ...*a[len - 1]
        //正序遍历数组，计算由 a[0] 到 a[i-1]的乘积
        for (int i = 1; i < len; i++) {
            b[i] = b[i - 1] * a[i - 1];
            /*
            b[0] = 1;
            b[1] = b[0] * a[0];
            b[2] = b[1] * a[1] = a[0] * a[1]
            b[3] = b[2] * a[2] = a[0] * a[1] * a[2]
             */
        }
        //当 i = len - 1时，b[len - 1] = b[len-2] * a[len-2]
        //                            = a[0] * a[1] * ...*a[len - 2]
        //b[len - 1]已在正序遍历中计算完毕
        //逆序遍历 由 i = len - 2开始
        //计算 由 a[i+1] 到 a[len - 1] 的乘积 * 由 a[0] 到 a[i-1]的乘积
        for (int i = len - 2; i >= 0; i--) {
            tmp *= a[i+1];
            b[i] *= tmp;
            /*
            i = len - 2, tmp = a[len - 1],b[len - 2] = a[0] * ...a[len - 3]*a[len - 1]
            i = len - 3, tmp = a[len - 1]*a[len - 2],b[len - 3] = a[0] * ...a[len - 4]*a[len - 1]*a[len - 2]
            ......
             */
        }
        return b;
    }

    /**
     * 题解链接：https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/solution/gou-jian-cheng-ji-shu-zu-by-leetcode-sol-aqg2/
     */

    /**
     * 方法二：左右乘积列表
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 指的是数组 a 的大小。预处理 L 和 R 数组以及最后的遍历计算都是 O(N) 的时间复杂度。
     * 空间复杂度：O(N)，其中 N 指的是数组 a 的大小。使用了 L 和 R 数组去构造答案，L 和 R 数组的长度为数组 a 的大小。
     * @param a
     * @return
     */
    //public int[] constructArr(int[] a) {
    //    int length = a.length;
    //
    //    // L 和 R 分别表示左右两侧的乘积列表
    //    int[] L = new int[length];
    //    int[] R = new int[length];
    //
    //    int[] answer = new int[length];
    //    if(length == 0) return answer;
    //
    //    // L[i] 为索引 i 左侧所有元素的乘积
    //    // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
    //    L[0] = 1;
    //    for (int i = 1; i < length; i++) {
    //        L[i] = a[i - 1] * L[i - 1];
    //    }
    //
    //    // R[i] 为索引 i 右侧所有元素的乘积
    //    // 对于索引为 'length-1' 的元素，因为右侧没有元素，所以 R[length-1] = 1
    //    R[length - 1] = 1;
    //    for (int i = length - 2; i >= 0; i--) {
    //        R[i] = a[i + 1] * R[i + 1];
    //    }
    //
    //    // 对于索引 i，除 a[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
    //    for (int i = 0; i < length; i++) {
    //        answer[i] = L[i] * R[i];
    //    }
    //
    //    return answer;
    //}


    /*
    超时处理
     */
    //public int[] constructArr(int[] a) {
    //    int[] b = new int[a.length];
    //    for (int i = 0; i < a.length; i++) {
    //        b[i] = multi(a, 0, i - 1) * multi(a, i+1, a.length - 1);
    //    }
    //    return b;
    //}
    //
    ////数组连续元素乘积
    //public int multi(int[] a,int left,int right){
    //    int res = 1;
    //    while (left <= right){
    //        res = res * a[left];
    //        left++;
    //    }
    //    return res;
    //}

}































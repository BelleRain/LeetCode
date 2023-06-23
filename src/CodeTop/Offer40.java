package CodeTop;

/**
 * @author mxy
 * @create 2023-06-19 9:59
 */

import java.util.*;

import static CodeTop.Code912_1.quickSort1;

/**
 * 剑指 Offer 40. 最小的 k 个数    链接：https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 *
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 *
 * 限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 *
 */
public class Offer40 {

    public static void main(String[] args) {
        int[] arr = {3,2,1};
        System.out.println(Arrays.toString(getLeastNumbers(arr, 2)));
    }


    /**
     * 方法一： 快速排序
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers1(int[] arr, int k) {
        quickSort1(arr, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }

    public static void quickSort1(int[] arr, int l, int r){
        //子数组长度为 1 时终止递归
        if (l >= r) return;
        //哨兵划分操作（以 arr[l]为基准数）
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, l);
        quickSort1(arr, l, i - 1);
        quickSort1(arr, i + 1, r);
    }

    public static void swap(int[] arr, int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 方法二： 基于快速排序的数组划分
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers2(int[] arr, int k) {
        if (k >= arr.length) return arr;
        return quickSort(arr, k, 0, arr.length - 1);
    }

    private static int[] quickSort(int[] arr, int k, int l, int r) {
        int i = l, j = r;
        while (i < j){
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, l);
        //说明 k 在左子数组
        if (i > k) return quickSort(arr, k, l, i - 1);
        //说明 k 在右子数组
        if (i < k) return quickSort(arr, k, i + 1, r);
        return Arrays.copyOf(arr, k);
    }


    /**
     * 方法三：堆
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers3(int[] arr, int k) {
        int[] vec = new int[k];
        if (k == 0) {  //排除 0  的情况
            return vec;
        }
        //默认是小根堆，大根堆重写比较器
        PriorityQueue<Integer> queue = new PriorityQueue<>((x,y) -> y.compareTo(x));
        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]){
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            vec[i] = queue.poll();
        }
        return vec;
    }


    /**
     * 方法四： 二叉搜索树 O(NlogK)
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers4(int[] arr, int k) {
        if (k == 0 || arr.length == 0){
            return new int[0];
        }
        //TreeMap的key是数字，value是该数字的个数
        //cnt表示当前map总共存了多少个数字
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int cnt = 0;
        for (int num : arr) {
            //1.遍历数组，若当前map中的数字个数小于k，则map中当前数字对应个数 +1
            if (cnt < k){
                map.put(num, map.getOrDefault(num, 0) + 1);
                cnt++;
                continue;
            }

            //2. 否则，取出map中最大的Key（即最大的数字），判断当前数字与map中最大数字的大小关系
            //   若当前数字比map中最大的数字还大，就直接忽略
            //   若当前数字比map中最大的数字还小，则将当前数字加入map中，并将map中的最大数字的个数-1
            Map.Entry<Integer, Integer> entry = map.lastEntry();
            if (entry.getKey() > num) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                if (entry.getValue() == 1){
                    map.pollLastEntry();
                }else {
                    //保证原树中一直有 k 个元素
                    map.put(entry.getKey(), entry.getValue() - 1);
                }
            }
        }
        //最后返回map中的元素
        int[] res = new int[k];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            while (freq-- > 0) {
                res[idx++] = entry.getKey();
            }
        }
        return res;
    }


    /**
     * 方法五： 数据范围有限时，计数排序 O(N)
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0){
            return new int[0];
        }
        //统计每个数字出现的次数
        int[] counter = new int[10001];
        for (int num : arr) {
            counter[num]++; //将数字放至对应索引的位置
        }
        //根据counter数组从头找出k个数作为返回结果
        int[] res = new int[k];
        int idx = 0;
        for (int num = 0; num < counter.length; num++) {
            while (counter[num]-- > 0 && idx < k){
                res[idx++] = num;
            }
            if (idx == k){
                break;
            }
        }
        return res;
    }
}













































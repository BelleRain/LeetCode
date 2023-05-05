package CodeTop;

/**
 * @author mxy
 * @create 2023-03-20 15:33
 */

import java.util.*;

/**
 * 480. 滑动窗口中位数   链接：https://leetcode.cn/problems/sliding-window-median
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * 例如：
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。
 * 你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 *  
 * 示例：
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 *
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7      -1
 *  1  3 [-1  -3  5] 3  6  7      -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 *  因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 *
 * 提示：
 * 你可以假设 k 始终有效，即：k 始终小于等于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 */
public class Code480 {

    public static void main(String[] args) {
        //[-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648]
        //3
        Code480 code480 = new Code480();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        //int[] nums = {2147483647,2147483647}; //2
        System.out.println(Arrays.toString(code480.medianSlidingWindow(nums, 3)));
        //考虑溢出问题：整数 + 整数 = 整数，溢出部分被剪掉，结果出现负数
        //double i = Integer.MAX_VALUE;
        //double test = 2 * i;
        //System.out.println(test);
    }

    private int k,minHeapSize,maxHeapSize;
    private Map<Integer,Integer> map = new HashMap<>();
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    //对于 Integer.MAX_VALUE-（-Integer.MAX_VALUE） 会出现越界问题，所以不可用简单的 (a,b) -> b - a
    //PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b - a);
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());


    public double[] medianSlidingWindow(int[] nums, int k){
        this.k = k;
        for (int i = 0; i < k; i++) {
            insert(nums[i]);
        }
        double[] result = new double[nums.length - k + 1];
        result[0] = getMedian();

        for (int i = k; i < nums.length; i++) {
            insert(nums[i]);
            delete(nums[i - k]);
            result[i - k + 1] = getMedian();
        }
        return result;
    }

    private double getMedian() {
        return (k & 1) != 0 ? maxHeap.peek() : ((double) minHeap.peek() + maxHeap.peek()) / 2.0;
    }

    private void delete(int num) {
        map.put(num, map.getOrDefault(num,0) + 1);
        if (num <= maxHeap.peek()){
            maxHeapSize--;
            if (num == maxHeap.peek()) prune(maxHeap);
        }else {
            minHeapSize--;
            if (num == minHeap.peek()) prune(minHeap);
        }
        makeBalance();
    }

    private void insert(int num) {
       if (maxHeap.isEmpty() || num <= maxHeap.peek()){
           maxHeap.offer(num);
           maxHeapSize++;
       }else {
           minHeap.offer(num);
           minHeapSize++;
       }
       makeBalance();
    }

    private void makeBalance() {
        if (maxHeapSize > minHeapSize + 1){
            minHeap.offer(maxHeap.poll());
            maxHeapSize--;
            minHeapSize++;
            prune(maxHeap);
        }else if (maxHeapSize < minHeapSize){
            maxHeap.offer(minHeap.poll());
            minHeapSize--;
            maxHeapSize++;
            prune(minHeap);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()){
            int num = heap.peek();
            if (map.containsKey(num)){
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0){
                    map.remove(num);
                }
                heap.poll();
            }else {
                break;
            }
        }
    }

    //运行超时
    /*public static double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new double[0];
        double[] median = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int[] subNums = new int[k];
            for (int j = 0; j < k; j++) {
                subNums[j] = nums[i + j];
            }
            //System.out.println(Arrays.toString(subNums));
            median[i] = findMedian(subNums,k);
            //break;
        }
        return median;
    }

    private static double findMedian(int[] subNums,int k) {
        double ret = 0;
        PriorityQueue<Integer> l = new PriorityQueue<>((a,b) -> b - a); //前半部分，由大到小，大根堆
        PriorityQueue<Integer> r = new PriorityQueue<>((a,b) -> a - b); //后半部分，由小到大，小根堆
        for (int i = 0; i < k; i++) {
            if (l.size() == r.size()){
                if (r.isEmpty() || subNums[i] <= r.peek()){
                    l.add(subNums[i]);
                }else {
                    l.add(r.poll());
                    r.add(subNums[i]);
                }
            }else {
                if (subNums[i] >= l.peek()){
                    r.add(subNums[i]);
                }else {
                    r.add(l.poll());
                    l.add(subNums[i]);
                }
            }
        }
        //Iterator<Integer> iterator1 = l.iterator();
        //while (iterator1.hasNext()){
        //    System.out.print(iterator1.next() + " ");
        //}
        //System.out.println();
        //Iterator<Integer> iterator2 = r.iterator();
        //while (iterator2.hasNext()){
        //    System.out.print(iterator2.next() + " ");
        //}
        //System.out.println();
        if (l.size() == r.size()){
            ret = ((double)l.peek() + (double)r.peek()) / 2.0;
        }else {
            ret = l.peek();
        }
        //Integer.MAX_VALUE = 2,147,483,648 - 1 = 2147483647;
        //Double.MAX_VALUE
        return ret;
    }*/
}

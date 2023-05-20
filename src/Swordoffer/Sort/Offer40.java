package Swordoffer.Sort;

/**
 * @author mxy
 * @create 2022-10-02 8:38
 */

import java.util.*;

/**
 * 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 *
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 */
public class Offer40 {

    public static void main(String[] args) {
        //int[] arr = {0,1,2,1};
        int[] arr = {6,9,9,3,4,1,3};
        int[] nums = {10,9,8,7,6,5,4,3,2,1};
        Offer40 offer40 = new Offer40();
        //System.out.println(Arrays.toString(offer40.getLeastNumbers(arr, 3)));
        System.out.println(Arrays.toString(offer40.getLeastNumbers1(nums, 3)));
    }

    /**
     * 方法一：排序  详细图解见原文链接：https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/solution/jian-zhi-offer-40-zui-xiao-de-k-ge-shu-j-9yze/
     * 快速排序原理：
     * 快速排序算法有两个核心点，分别为 “哨兵划分” 和 “递归” 。
     *             哨兵划分操作： 以数组某个元素（一般选取首元素）为 基准数 ，将所有小于基准数的元素移动至其左边，大于基准数的元素移动至其右边。
     *             递归： 对 左子数组 和 右子数组 递归执行 哨兵划分，直至子数组长度为 1 时终止递归，即可完成对整个数组的排序。
     * 复杂度分析：
     * 时间复杂度 O(NlogN) ： 库函数、快排等排序算法的平均时间复杂度为 O(NlogN) 。
     * 空间复杂度 O(N) ： 快速排序的递归深度最好（平均）为 O(logN) ，最差情况（即输入数组完全倒序）为 O(N)。
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers1(int[] arr, int k) {
        quickSort1(arr, 0, arr.length-1);
        return Arrays.copyOf(arr, k);
    }

    public void quickSort1(int[] arr,int l,int r){
        //子数组长度为1时终止递归
        if(l>=r) return;
        //哨兵划分操作（以arr[l]为基准数）
        int i = l,j = r;
        while (i<j){
            //注意：先移动 j 指针，再移动 i 指针，令 i 与 j 汇合，再交换 i与l 的值，则 分为两部分，i 处为初始的哨兵值
            //否则，若先移动 i ，令 j 与 i汇合，则最终 i 的值为 比 哨兵值l 大的值，则交换 i 和 l，不能将数组分为两部分
            while (i<j && arr[j] >= arr[l]) j--;
            while (i<j && arr[i] <= arr[l]) i++;
            swap1(arr,i,j);
        }
        swap1(arr,i,l);
        System.out.println(Arrays.toString(arr));
        //递归（左）右数组执行哨兵划分
        quickSort1(arr, l, i-1);
        quickSort1(arr, i+1, r);
    }

    public void swap1(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 方法二：基于快速排序的数组划分  原文链接：https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/solution/jian-zhi-offer-40-zui-xiao-de-k-ge-shu-j-9yze/
     *    题目只要求返回最小的 k 个数，对这 k 个数的顺序并没有要求。因此，只需要将数组划分为 最小的 k 个数 和 其他数字 两部分即可，而快速排序的哨兵划分可完成此目标。
     *    根据快速排序原理，如果某次哨兵划分后 基准数正好是第 k+1 小的数字 ，那么此时基准数左边的所有数字便是题目所求的 最小的 k 个数 。
     *    根据此思路，考虑在每次哨兵划分后，判断基准数在数组中的索引是否等于 k ，若 true 则直接返回此时数组的前 k 个数字即可。
     *算法流程：
     * 1、getLeastNumbers() 函数：
     *     若 k 大于数组长度，则直接返回整个数组；
     *     执行并返回 quick_sort() 即可；
     * 2、quick_sort() 函数：
     *     注意，此时 quick_sort() 的功能不是排序整个数组，而是搜索并返回最小的 k 个数。
     *    1、哨兵划分：
     *          划分完毕后，基准数为 arr[i] ，左 / 右子数组区间分别为 [l, i - 1] , [i + 1, r]；
     *    2、递归或返回：
     *          若 k < i ，代表第 k + 1小的数字在 左子数组 中，则递归左子数组；
     *          若 k > i ，代表第 k + 1小的数字在 右子数组 中，则递归右子数组；
     *          若 k = i ，代表此时 arr[k] 即为第 k + 1 小的数字，则直接返回数组前 k 个数字即可；
     * 复杂度分析：
     *    本方法优化时间复杂度的本质是通过判断舍去了不必要的递归（哨兵划分）。
     * 时间复杂度 O(N) ： 其中 N 为数组元素数量；对于长度为 N 的数组执行哨兵划分操作的时间复杂度为 O(N) ；每轮哨兵划分后根据 k 和 i 的大小关系选择递归，由于 i 分布的随机性，
     *    则向下递归子数组的平均长度为 (N/2);因此平均情况下，哨兵划分操作一共有 N + (N/2) + (N/4) + ...+ N/N = (N-1/2)/(1-1/2) = 2N-1,（等比数列求和），即总体时间复杂度为 O(N)。
     * 空间复杂度 O(logN) ： 划分函数的平均递归深度为 O(logN) 。
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k){
        if (k >= arr.length) return arr;
        return quickSort(arr, k, 0, arr.length-1);
    }

    public int[] quickSort(int[] arr,int k,int l,int r){
        int i = l,j = r;
        while (i<j){
            while (i<j && arr[j] >= arr[l]) j--;
            while (i<j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, l);
        if (i > k) return quickSort(arr, k, l, i-1); //说明k在左子数组
        if (i < k) return quickSort(arr, k, i+1, r); //说明k在右子数组
        return Arrays.copyOf(arr, k);
    }

    public void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 方法三： 堆 原文链接：https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/solution/zui-xiao-de-kge-shu-by-leetcode-solution/
     * 思路和算法
     *     我们用一个大根堆实时维护数组的前 k 小值。首先将前 k 个数插入大根堆中，随后从第 k+1 个数开始遍历，如果当前遍历到的数比大根堆的堆顶的数要小，
     *     就把堆顶的数弹出，再插入当前遍历到的数。最后将大根堆里的数存入数组返回即可。在下面的代码中，由于 C++ 语言中的堆（即优先队列）为大根堆，我们可以这么做。
     *     而 Python 语言中的堆为小根堆，因此我们要对数组中所有的数取其相反数，才能使用小根堆维护前 k 小值。
     * 复杂度分析
     * 时间复杂度：O(nlogk)，其中 n 是数组 arr 的长度。由于大根堆实时维护前 k 小值，所以插入删除都是 O(logk) 的时间复杂度，最坏情况下数组里 n 个数都会插入，所以一共需要 O(nlogk) 的时间复杂度。
     * 空间复杂度：O(k)，因为大根堆里最多 k 个数。
     * @param arr
     * @param k
     * @return
     */
    //public int[] getLeastNumbers(int[] arr, int k){
    //    int[] vec = new int[k];
    //    if (k == 0) { // 排除 0 的情况
    //        return vec;
    //    }
    //    //默认是小根堆，大根堆重写比较器
    //    PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
    //        public int compare(Integer num1, Integer num2) {
    //            return num2 - num1; //降序
    //        }
    //    });
    //    for (int i = 0; i < k; ++i) {
    //        queue.offer(arr[i]);
    //    }
    //    for (int i = k; i < arr.length; ++i) {
    //        if (queue.peek() > arr[i]) {
    //            queue.poll();
    //            queue.offer(arr[i]);
    //        }
    //    }
    //    for (int i = 0; i < k; ++i) {
    //        vec[i] = queue.poll();
    //    }
    //    return vec;
    //}

    /*
    快速排序和堆排序两种方法的优劣性比较
        在面试中，另一个常常问的问题就是这两种方法有何优劣。看起来分治法的快速选择算法的时间、空间复杂度都优于使用堆的方法，但是要注意到快速选择算法的几点局限性：
        第一，算法需要修改原数组，如果原数组不能修改的话，还需要拷贝一份数组，空间复杂度就上去了。
        第二，算法需要保存所有的数据。如果把数据看成输入流的话，使用堆的方法是来一个处理一个，不需要保存数据，只需要保存 k 个元素的最大堆。
        而快速选择的方法需要先保存下来所有的数据，再运行算法。当数据量非常大的时候，甚至内存都放不下的时候，就麻烦了。所以当数据量大的时候还是用基于堆的方法比较好。
        链接：https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/solution/tu-jie-top-k-wen-ti-de-liang-chong-jie-fa-you-lie-/
     */

    /**
     * 方法四：二叉搜索树 O(NlogK)   原文链接：https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/solution/3chong-jie-fa-miao-sha-topkkuai-pai-dui-er-cha-sou/
     *   BST 相对于前两种方法没那么常见，但是也很简单，和大根堆的思路差不多～
     *   要提的是，与前两种方法相比，BST 有一个好处是求得的前K大的数字是有序的。
     *    因为有重复的数字，所以用的是 TreeMap 而不是 TreeSet（有的语言的标准库自带 TreeMultiset，也是可以的）。
     *  TreeMap的key 是数字，value 是该数字的个数。
     *  我们遍历数组中的数字，维护一个数字总个数为 K 的 TreeMap：
     *    1.若目前 map 中数字个数小于 K，则将 map 中当前数字对应的个数 +1；
     *    2.否则，判断当前数字与 map 中最大的数字的大小关系：若当前数字大于等于 map 中的最大数字，就直接跳过该数字；若当前数字小于 map 中的最大数字，则将 map 中当前数字对应的个数 +1，并将 map 中最大数字对应的个数减 1。
     * @param arr
     * @param k
     * @return
     */
    //public int[] getLeastNumbers(int[] arr, int k) {
    //    if (k == 0 || arr.length == 0) {
    //        return new int[0];
    //    }
    //    // TreeMap的key是数字, value是该数字的个数。
    //    // cnt表示当前map总共存了多少个数字。
    //    TreeMap<Integer, Integer> map = new TreeMap<>();
    //    int cnt = 0;
    //    for (int num: arr) {
    //        // 1. 遍历数组，若当前map中的数字个数小于k，则map中当前数字对应个数+1
    //        if (cnt < k) {
    //            map.put(num, map.getOrDefault(num, 0) + 1);
    //            cnt++;
    //            continue;
    //        }
    //        // 2. 否则，取出map中最大的Key（即最大的数字), 判断当前数字与map中最大数字的大小关系：
    //        //    若当前数字比map中最大的数字还大，就直接忽略；
    //        //    若当前数字比map中最大的数字小，则将当前数字加入map中，并将map中的最大数字的个数-1。
    //        Map.Entry<Integer, Integer> entry = map.lastEntry();
    //        if (entry.getKey() > num) {
    //            map.put(num, map.getOrDefault(num, 0) + 1);
    //            if (entry.getValue() == 1) {
    //                map.pollLastEntry();
    //            } else {
    //                map.put(entry.getKey(), entry.getValue() - 1); //保证原树中一直有k个元素
    //            }
    //        }
    //
    //    }
    //    // 最后返回map中的元素
    //    int[] res = new int[k];
    //    int idx = 0;
    //    for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
    //        int freq = entry.getValue();
    //        while (freq-- > 0) {
    //            res[idx++] = entry.getKey();
    //        }
    //    }
    //    return res;
    //}

    /**
     * 方法五：数据范围有限时，计数排序 O(N) 原文链接：https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/solution/3chong-jie-fa-miao-sha-topkkuai-pai-dui-er-cha-sou/
     *
     * @param arr
     * @param k
     * @return
     */
    //public int[] getLeastNumbers(int[] arr, int k) {
    //    if (k == 0 || arr.length == 0) {
    //        return new int[0];
    //    }
    //    // 统计每个数字出现的次数
    //    int[] counter = new int[10001];
    //    for (int num: arr) {
    //        counter[num]++; //将数字放至对应索引的位置
    //    }
    //    // 根据counter数组从头找出k个数作为返回结果
    //    int[] res = new int[k];
    //    int idx = 0;
    //    for (int num = 0; num < counter.length; num++) {  //逐个遍历counter数组，查找对应位置的数字，没有赋值的位置初始值为0
    //        while (counter[num]-- > 0 && idx < k) {
    //            res[idx++] = num;
    //        }
    //        if (idx == k) {
    //            break;
    //        }
    //    }
    //    return res;
    //}




}







































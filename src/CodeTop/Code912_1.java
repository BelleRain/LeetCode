package CodeTop;

/**
 * @author mxy
 * @create 2023-05-18 10:47
 */

import java.util.Arrays;
import java.util.Random;

/**
 * 912. 排序数组    链接：https://leetcode.cn/problems/sort-an-array
 * 给你一个整数数组 nums，请你将该数组升序排列。
 *
 * 示例 1：
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *
 * 提示：
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 *
 */
public class Code912_1 {

    public static void main(String[] args) {
        int[] nums = {-9,78,0,23,-567,70, -1,900, 4561};
        System.out.println(Arrays.toString(sortArray(nums)));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/sort-an-array/solution/fu-xi-ji-chu-pai-xu-suan-fa-java-by-liweiwei1419/
     */


    public static int[] sortArray(int[] nums) {
        //System.out.println("selectSort:" + selectSort(nums));

        //System.out.println("insertSort:" + insertSort(nums));

        //int[] temp = new int[nums.length];
        //mergeSort(nums, 0, nums.length - 1, temp);
        //System.out.println("mergeSort:" + Arrays.toString(nums));

        //quickSort1(nums, 0, nums.length - 1);
        //quickSort2(nums, 0, nums.length - 1);
        //quickSort3(nums, 0, nums.length - 1);
        //System.out.println("quickSort:" + Arrays.toString(nums));

        //将数组整理成堆
        //heapify(nums);
        //循环不变量：区间 [0,i] 堆有序
        //for (int i = nums.length - 1; i >= 1; i--) {
        //    //把堆顶元素（当前最大）交换到数组末尾
        //    swap(nums, 0, i);
        //    //逐步减少堆有序的部分
        //    i--;
        //    //下标 0 位置下沉操作，使得区间 (0,i] 堆有序
        //    sitDown(nums, 0, i);
        //}
        //System.out.println("heapSort:" + Arrays.toString(nums));

        //int h = 1;
        ////使用 Knuth 增量序列
        ////找增量的最大值
        //while (3 * h + 1 < nums.length){
        //    h = 3 * h + 1;
        //}
        //while (h >= 1){
        //    for (int i = h; i < nums.length; i++) {
        //        shellSort(nums, h, i);
        //    }
        //    h = h/3;
        //}
        //System.out.println("shellSort:" + Arrays.toString(nums));

        //bubbleSort(nums);

        //countSort(nums);

        basicSort(nums);
        return null;

    }

    /**
     * 排序一：
     * 选择排序：每一轮选择最小元素交换到未排定部分的开头
     * 总结：
     * 算法思想 1：贪心算法：每一次决策只看当前，当前最优，则全局最优。注意：这种思想不是任何时候都适用。
     * 算法思想 2：减治思想：外层循环每一次都能排定一个元素，问题的规模逐渐减少，直到全部解决，即「大而化小，小而化了」。运用「减治思想」很典型的算法就是大名鼎鼎的「二分查找」。
     * 优点：交换次数最少。
     * 「选择排序」看起来好像最没有用，但是如果在交换成本较高的排序任务中，就可以使用
     *
     * 复杂度分析：
     * 时间复杂度：O(N^2)，这里 N 是数组的长度；
     * 空间复杂度：O(1)，使用到常数个临时变量。
     * @param nums
     * @return
     */
    public static int[] selectSort(int[] nums){
        int len = nums.length;
        //循环不变量：[0,i)有序，且该区间里所有元素就是最终排定的样子。
        for (int i = 0; i < len - 1; i++) {
            //选择区间[i,len - 1]里最小的元素的索引，交换到下标i
            int minIndex = i;
            for (int j = i; j < len; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }

    /**
     * 排序二：
     * 插入排序：每次将一个数字插入一个有序的数组里，成为一个长度更长的有序数组，有限次操作以后，数组整体有序。
     * 优化：「将一个数字插入一个有序的数组」这一步，可以不使用逐步交换，使用先赋值给「临时变量」，然后「适当的元素」后移，空出一个位置，最后把「临时变量」赋值给这个空位的策略（就是上面那张图的意思）。编码的时候如果不小心，可能会把数组的值修改，建议多调试；
     * 特点：「插入排序」可以提前终止内层循环（体现在 nums[j - 1] > temp 不满足时），在数组「几乎有序」的前提下，「插入排序」的时间复杂度可以达到 O(N)；
     * 由于「插入排序」在「几乎有序」的数组上表现良好，特别地，在「短数组」上的表现也很好。因为「短数组」的特点是：每个元素离它最终排定的位置都不会太远。
     *      为此，在小区间内执行排序任务的时候，可以转向使用「插入排序」。
     * @return
     */
    public static int[] insertSort(int[] nums){
        //插入排序：稳定排序，在接近有序的情况下，表现优异
        int len = nums.length;
        //循环不变量：将nums[i]插入到区间[0,i)使之成为有序数组
        for (int i = 1; i < len; i++) {
            //先暂存这个元素，然后之前元素逐个后移，留出空位
            int temp = nums[i];
            int j = i;
            while (j > 0 && nums[j - 1] > temp){
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
        return nums;
    }

    /**
     * 排序三：
     * 归并排序：借助额外的空间，合并两个有序数组，得到更长的数组。
     * 优化一： 在【小区间】里转向使用【插入排序】，Java源码里面也有类似这种操作，【小区间】的长度是个超参数，需要测试决定，我这里参考了JDK源码；
     * 优化二： 在【两个数组】本身就是有序的情况下，无需合并；
     * 优化三：全程使用一份临时数组进行【合并两个有序数组】的操作，避免创建临时数组和销毁的消耗，避免计算下标偏移量。
     * 注意：实现归并排序的时候，要特别注意，不要把这个算法实现成非稳定排序，区别就在 <= 和 < ,已在代码中注明。
     * 【归并排序】 比 【快速排序】好的一点是，它借助了额外空间，可以实现【稳定排序】，java里对于【对象数组】的排序任务，就是使用归并排序（的升级版TimSort）。
     * 复杂度分析：
     *      时间复杂度：O(NlogN)，这里 N 是数组的长度；
     *      空间复杂度：O(N)，辅助数组与输入数组规模相当。
     * @return
     */
    //列表大小等于或小于该大小，将优先于 mergeSort 使用插入排序
    private static final int INSERTION_SORT_THRESHOLD = 7;
    public static void mergeSort(int[] nums,int left,int right,int[] temp){
        //小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD){
            insertionSort(nums,left,right);
            return;
        }
        int mid = left + (right - left)/2;
        //Java里有更优的写法，在left 和 right 都是大整数时，即使溢出，结论依然正确
        //int mid = (left + right) >>> 1;
        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);
        //如果数组的这个子区间本身有序，无需合并
        if (nums[mid] <= nums[mid + 1]){
            return;
        }
        mergeOfSortedArray(nums,left,mid,right,temp);
    }

    /**
     * 合并两个有序数组：先把值复制到临时数组，再合并回去
     * @param nums
     * @param left
     * @param mid    [left,mid]有序，[mid + 1,right]有序
     * @param right
     * @param temp  全局使用的临时数组
     */
    private static void mergeOfSortedArray(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right + 1 -left);
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1){
                //如果左半边遍历完毕
                nums[k] = temp[j];
                j++;
            }else if (j == right + 1){
                //如果右半边遍历完毕
                nums[k] = temp[i];
                i++;
            }else if (temp[i] <= temp[j]){
                //注意写成 < 就丢失了稳定性（相同元素原来靠前的排序以后依然靠前）
                nums[k] = temp[i];
                i++;
            }else {
                nums[k] = temp[j];
                j++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 对数组 arr 的子区间 [left,right] 使用插入排序
     * @param arr   给定数组
     * @param left  左边界，能取到
     * @param right 右边界，能取到
     */
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i;
            while (j > left && arr[j - 1] > temp){
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 排序四：
     * 基本思路：快速排序
     *    快速排序每一次都排定一个元素（这个元素呆在了它最终应该呆的位置），然后递归地去排它左边的部分和右边的部分，依次进行下去，直到数组有序；
     * 算法思想：
     *    分而治之（分治思想），与【归并排序】不同，【快速排序】在【分】这件事情上不像【归并排序】无脑地一分为二，而是采用了partition的方法，因此就没有【合】的过程。
     * 实现细节（注意事项）：
     *     （针对特殊测试用例：顺序数组或者逆序数组）一定要随机化选择切分元素（pivot），否则在输入数组是有序数组或者是逆序数组的时候，快速排序会变得非常慢（等同于冒泡排序或者【选择排序】）；
     * 以下是针对特殊测试用例（有很多重复元素的输入数组）有3种版本的***：
     *  版本1：基本*** ：把等于切分元素的所有元素分到了数组的同一侧，可能会造成递归树倾斜；
     *  版本2：双指针***：把等于切分元素的所有元素分到了数组的两侧，避免了递归树倾斜，递归树相对平衡；
     *  版本3：三指针***：把等于切分元素的所有元素挤到了数组的中间，在有很多元素和切分元素相等的情况下，递归区间大大减少。
     *   {
     *       这里有一个经验总结：之所以***有这些优化，起因都是来自【递归树】的高度。关于【树】的算法的优化，绝大部分都是在和树的【高度】较劲。类似的通过减少树高度、使得树更平衡的数据结构还有【二叉搜索树】优化成【AVL树】或者【红黑树】、【并查集】的【按秩合并】与【路径压缩】。
     *   }
     * 写对【快速排序】的技巧：保持【循环不变量】，即定义的变量在循环开始前，循环过程中、循环结束以后，都保持不变的性质，这个性质是人为根据问题特点定义的。
     * 【循环不变量】的内容在《算法导论》这本书里有介绍。
     * 【循环不变量是证明算法有效性的基础，更是写对代码的保证，遵守循环不变量，是不是该写等于号，先交换还是先++，就会特别清楚，绝对不会写错，我在编码的时候，会将遵守的 循环不变量 作为注释写在代码中。】
     * 快速排序丢失了稳定性，如果需要稳定的快速排序，需要具体定义比较函数，这个过程叫 【稳定化】。
     *
     * 下面提供三种 *** 的版本，供参考
     * 说明：
     *     lt 是 less than 的缩写，表示（严格）小于
     *     gt 是 greater than 的缩写，表示（严格）大于
     *     le 是 less than or equal 的缩写，表示小于等于 ；
     *     ge 是 greater than or equal 的缩写，表示大于等于。
     * 复杂度分析：  时间复杂度：O(NlogN)，这里 N 是数组的长度；
     * 空间复杂度：O(logN)，这里占用的空间主要来自递归函数的栈空间。
     * @param nums
     * @return
     */
    /**
     * INSERTION_SORT_THRESHOLD
     * //列表大小等于或小于该大小，将优先于 quickSort 使用插入排序
     */
    private static final Random RANDOM = new Random();
    public static void quickSort1(int[] nums,int left,int right){
        //快速排序 1 ：基本快速排序
        //小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD){
            insertionSort(nums,left,right);
            return;
        }
        int pIndex = partition(nums,left,right);
        quickSort1(nums, left, pIndex - 1);
        quickSort1(nums, pIndex + 1, right);
    }

    /**
     * 寻找 [left,right] 区间内的分割点
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] nums, int left, int right) {
        int randomIndex = RANDOM.nextInt(right - left + 1) + left;
        swap(nums, left, randomIndex);
        //基准值
        int privot = nums[left];
        int lt = left;
        //循环不变量；
        //all in [left + 1，lt] < privot
        //all in [lt + 1,i) >= privot
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < privot){
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, left, lt);
        return lt;
    }

    private static void swap(int[] nums,int index1,int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    /**
     * 快速排序 2 ：双指针（指针对撞）快速排序
     * @param nums
     * @param left
     * @param right
     */
    public static void quickSort2(int[] nums,int left,int right){
        //小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD){
            insertionSort(nums, left, right);
            return;
        }
        int pIndex = partition2(nums, left, right);
        quickSort2(nums, left, pIndex - 1);
        quickSort2(nums, pIndex + 1, right);
    }

    private static int partition2(int[] nums, int left, int right) {
        int randomIndex = left + RANDOM.nextInt(right - left + 1);
        swap(nums, randomIndex, left);
        int privot = nums[left];
        int lt = left + 1;
        int gt = right;
        //循环不变量
        //all in [left + 1,lt) <= privot
        //all in (gt,right] >= privot
        while (true){
            while (lt <= right && nums[lt] < privot){
                lt++;
            }
            while (gt > left && nums[gt] > privot){
                gt--;
            }
            if (lt >= gt){
                break;
            }
            //细节：相等的元素通过交换，等概率分到数组的两边
            swap(nums, lt, gt);
            lt++;
            gt--;
        }
        swap(nums, left, gt);
        return gt;
    }

    /**
     * 快速排序 3 ：三指针快速排序
     * @param nums
     * @param left
     * @param right
     */
    public static void quickSort3(int[] nums,int left,int right){
        //小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD){
            insertionSort(nums, left, right);
            return;
        }
        int randomIndex = left + RANDOM.nextInt(right - left + 1);
        swap(nums, randomIndex, left);

        //循环不变量:
        //all in [left + 1, lt] < privot
        //all in [lt + 1,i) = privot
        //all in [gt,right] > privot
        int privot = nums[left];
        int lt = left;
        int gt = right + 1;
        int i = left + 1;
        while (i < gt){
            if (nums[i] < privot){
                lt++;
                swap(nums, i, lt);
                i++;
            }else if (nums[i] == privot){
                i++;
            }else {
                gt--;
                swap(nums, i, gt);
            }
        }
        swap(nums, left, lt);
        //注意这里，大大减少了两侧分治的区间
        quickSort3(nums, left, lt - 1);
        quickSort3(nums, gt, right);
    }

    /**
     * 排序五：
     * 堆排序：
     * 将数组整理成堆 (堆有序)
     * 复杂度分析：
     * 时间复杂度：O(NlogN)，这里 N 是数组的长度；
     * 空间复杂度：O(1)。
     * @param nums
     */
    private static void heapify(int[] nums){
        int len = nums.length;
        //只需要从 i = (len - 1) / 2 这个位置开始逐层下移
        for (int i = (len - 1) / 2; i >= 0; i--) {
            sitDown(nums,i,len - 1);
        }
    }

    /**
     * @param nums
     * @param k     当前下沉元素的下标
     * @param end   [0,end] 是 nums 的有效部分
     */
    private static void sitDown(int[] nums, int k, int end) {
        while (2 * k + 1 <= end){
            int j = 2 * k + 1;
            if (j + 1 <= end && nums[j + 1] > nums[j]){
                j++;
            }
            if (nums[j] > nums[k]){
                swap(nums, j, k);
            }else {
                break;
            }
            k = j;
        }
    }

    /**
     * 排序六：希尔排序
     * 思想来源：插入排序的优化。在插入排序里，如果靠后的数字较小，它来到前面就得交换多次。「希尔排序」改进了这种做法。带间隔地使用插入排序，直到最后「间隔」为 1 的时候，就是标准的「插入排序」，此时数组里的元素已经「几乎有序」了；
     * 希尔排序的「间隔序列」其实是一个超参数，这方面有一些研究成果，有兴趣的朋友可以了解一下，但是如果这是面向笔试面试，就不用了解了。
     */
    /**
     * 将 nums[i] 插入到对应分组的正确位置上，其实就是将原来的 1 的部分改成 gap
     * @param nums
     * @param gap
     * @param i
     */
    public static void shellSort(int[] nums,int gap,int i){
        int temp = nums[i];
        int j = i;
        //注意：这里 j >= deta 的原因
        while (j >= gap && nums[j - gap] > temp){
            nums[j] = nums[j - gap];
            j = j - gap;
        }
        nums[j] = temp;
    }


    /**
     * 排序七 ：冒泡排序
     * @param nums
     */
    public static void bubbleSort(int[] nums){
        for (int i = nums.length - 1; i >= 0; i--) {
            //先默认数组是有序的，只要发生一次交换，就必须进行下一轮比较
            //如果在内层循环中，都没有执行一次交换操作，说明此时数组已经是升序数组
            boolean sorted = true;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]){
                    swap(nums, j, j + 1);
                    sorted = false;
                }
            }
            if (sorted){
                break;
            }
            System.out.println(Arrays.toString(nums));
        }
    }


    /**
     * 排序八：计数排序
     *      把每个出现的数值都做一个计数，然后根据计数从小到大输出得到有序数组。
     *      这种做法丢失了稳定性，如果是本题这种基本数据类型的话没有关系。如果是对象类型，就不能这么做了。
     *  保持稳定性的做法是：先对计数数组做前缀和，在第 2 步往回赋值的时候，根据原始输入数组的数据从后向前赋值，
     *      前缀和数组保存了每个元素存放的下标信息（这里没有说得太细，本来这一点就不重要，也不难理解）。
     */
    private static final int OFFSET = 50000;
    public static void countSort(int[] nums){
        int len = nums.length;
        // 由于 -50000 <= A[i] <= 50000
        //因此 “桶” 的大小为 50000 - （-50000） = 100000
        //并且设置偏移 OFFSET = 50000，目的是让每一个数都能大于等于0
        //这样就可以作为 count 数组的下标，查询这个数的计数
        int size = 100000;
        //计数数组
        int[] count = new int[size];
        //计算计数数组
        for (int num : nums) {
            count[num + OFFSET]++;
        }
        //把count数组变成前缀和数组
        for (int i = 1; i < size; i++) {
            count[i] += count[i - 1];
        }
        //先把原始数组赋值到一个临时数组里，然后回写数据
        int[] temp = new int[len];
        System.arraycopy(nums, 0, temp, 0, len);
        //为了保证稳定性，从后向前赋值
        for (int i = len - 1; i >= 0; i--) {
            int index = count[temp[i] + OFFSET] - 1;
            nums[index] = temp[i];
            count[temp[i] + OFFSET]--;
        }
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 排序九： 基数排序
     * 基本思路：也称为基于关键字的排序，例如针对数值排序，个位、十位、百位就是关键字。针对日期数据的排序：年、月、日、时、分、秒就是关键字。
     * 「基数排序」用到了「计数排序」。
     * @param nums
     */
    public static void basicSort(int[] nums){
        //基数排序：低位优先
        int len = nums.length;
        //预处理，让所有的数都大于等于0，这样才可以使用基数排序
        for (int i = 0; i < len; i++) {
            nums[i] += OFFSET;
        }

        //第一步：找出最大的数字
        int max = nums[0];
        for (int num : nums) {
            if (num > max){
                max = num;
            }
        }

        //第二步：计算出最大的数字有几位，这个数组决定了我们要将整个数组看几遍
        int maxLen = getMaxLen(max);

        //计数排序需要使用的计数数组和临时数组
        int[] count = new int[10];
        int[] temp = new int[len];

        //表征关键字的量：除数
        //1 表示按照个位关键字排序
        //10 表示按照十位关键字排序
        //100 表示按照百位关键字排序
        //1000 表示按照千位关键字排序
        int divisor = 1;
        //有几位数，外层循环就得执行几次
        for (int i = 0; i < maxLen; i++) {
            //每一步都使用计数排序，保证排序结果是稳定的
            //这一步需要额外空降保存结果集，因此把结果保存在 temp 中
            countingSort(nums,temp,divisor,len,count);
            //交换nums 和 temp 的引用，下一轮还是按照 nums 做计数排序
            int[] t = nums;
            nums = temp;
            temp = t;

            //divisor 自增，表示采用低位优先的基数排序
            divisor *= 10;
        }
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = nums[i] - OFFSET;
        }
        System.out.println(Arrays.toString(res));
    }

    private static void countingSort(int[] nums, int[] res, int divisor, int len, int[] count) {
        //1、计算计数数组
        for (int i = 0; i < len; i++) {
            //计算数位上的数是几，先取个位，再十位、百位
            int remainder = (nums[i] / divisor) % 10;
            count[remainder]++;
        }

        //2、变成前缀和数组
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        //3、从后向前赋值
        for (int i = len - 1; i >= 0; i--) {
            int remainder = (nums[i] / divisor) % 10;
            int index = count[remainder] - 1;
            res[index] = nums[i];
            count[remainder]--;
        }

        //4、count数组需要设置为0，以免干扰下一次排序使用
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }
    }

    /**
     * 获取一个整数的最大位数
     * @param num
     * @return
     */
    private static int getMaxLen(int num) {
        int maxLen = 0;
        while (num > 0){
            num = num / 10;
            maxLen++;
        }
        return maxLen;
    }

    /**
     * 排序十：桶排序
     * 基本思路：一个坑一个萝卜，也可以一个坑多个萝卜，对每个坑排序，再拿出来，整体就有序。
     * @param nums
     */
    public static void bucketSort(int[] nums){
        //桶排序
        //1 <= A.length <= 10000
        //-50000 <= A[i] <= 50000
        //10000
        int len = nums.length;

        //第一步: 将数据转换为 [0,10000] 区间里的数
        for (int i = 0; i < len; i++) {
            nums[i] += OFFSET;
        }

        //第二步: 观察数据，设置桶的个数
        //步长: 步长如果设置成 10 会超出内存限制
        int step = 1000;
        //桶的个数
        int bucketLen = 10000/step;

        int[][] temp = new int[bucketLen + 1][len];
        int[] next = new int[bucketLen + 1];

        //第三步：分桶
        for (int num : nums) {
            int bucketIndex = num/step;
            temp[bucketIndex][next[bucketIndex]] = num;
            next[bucketIndex]++;
        }

        //第四步：对于每个桶执行插入排序
        for (int i = 0; i < bucketLen + 1; i++) {
            insertionSort1(temp[i], next[i] - 1);
        }

        //第五步：从桶里依次取出来
        int[] res = new int[len];
        int index = 0;
        for (int i = 0; i < bucketLen + 1; i++) {
            int curLen = next[i];
            for (int j = 0; j < curLen; j++) {
                res[index] = temp[i][j] - OFFSET;
                index++;
            }
        }
        System.out.println(Arrays.toString(res));
    }

    private static void insertionSort1(int[] arr, int endIndex) {
        for (int i = 1; i <= endIndex; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > temp){
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

}







































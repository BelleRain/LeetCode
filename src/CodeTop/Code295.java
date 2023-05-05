package CodeTop;

/**
 * @author mxy
 * @create 2023-03-20 15:43
 */

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 295. 数据流的中位数  链接：https://leetcode.cn/problems/find-median-from-data-stream
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 *
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 *
 * MedianFinder() 初始化 MedianFinder 对象。
 *
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 *
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10^-5 以内的答案将被接受。
 *
 * 示例 1：
 *
 * 输入
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * 输出
 * [null, null, null, 1.5, null, 2.0]
 *
 * 解释
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 * 提示:
 * -10^5 <= num <= 10^5
 * 在调用 findMedian 之前，数据结构中至少有一个元素
 * 最多 5 * 10^4 次调用 addNum 和 findMedian
 *
 */
public class Code295 {

    public static void main(String[] args) {
        Code295 code295 = new Code295();
        code295.addNum(1);
        code295.addNum(3);
        System.out.println(code295.findMedian());
        code295.addNum(-1);
        System.out.println(code295.findMedian());
    }

    /**
     * 题解链接：https://leetcode.cn/problems/find-median-from-data-stream/solution/gong-shui-san-xie-jing-dian-shu-ju-jie-g-pqy8/
     */

    PriorityQueue<Integer> l;
    PriorityQueue<Integer> r;

    //当前数据流元素数量为偶数：l 和 r大小相同，此时动态中位数为两者堆顶元素的平均值
    //当前数据流元素数量为奇数：l 比 r多一，此时动态中位数为 l 的堆顶元素
    public Code295() {
        l = new PriorityQueue<>((a,b) -> b - a); //大根堆，从大到小，前半部分
        r = new PriorityQueue<>((a,b) -> a - b); //小根堆，从小到大，后半部分
    }

    public void addNum(int num) {
        int s1 = l.size(), s2 = r.size();
        //插入前两者大小相同，说明插入前数据流元素为偶数，插入后变为奇数。
        //期望操作完 l 比 r 多1（同时维持双堆有序），则
        if (s1 == s2){
            //如果 r 为空，则直接加入 l即可
            //若 r 不为空，num <= r.peek(),说明 num的插入位置不会在后半部分（不会在r中），直接加入l即可
            if (r.isEmpty() || num <= r.peek()){
                l.add(num);
            }else {
                //如果r不为空，且num > r.peek()，说明num的插入位置在后半部分，
                //此时将 r 的堆顶元素放到 l 中，再把 num 放到 r（相当于从 r 中置换一位出来放到 l 中）。
                l.add(r.poll());
                r.add(num);
            }
        }else { //插入前两者大小不同，说明数据流元素个数为奇数，插入后变为偶数。
            //我们期望操作完达到 l 和 r 数量相等，同时双堆维持有序，进一步分情况讨论 （此时 l 必然比 r 元素多一）：
            //如果 num >= l.peek,说明num的插入部分不会在前半部分（不会在l中），直接添加到r即可。
            if (l.peek() <= num){
                r.add(num);
            }else {
                //如果 num < l.peek,说明num的插入部分在前半部分，此时将l的堆顶元素放到r中，
                //再把num放入l中，（相当于从l中替换一位出来到r中）
                r.add(l.poll());
                l.add(num);
            }
        }
    }

    public double findMedian() {
        int s1 = l.size(),s2 = r.size();
        if (s1 == s2){
            return (l.peek() + r.peek()) / 2.0;
        }else {
            return l.peek();
        }
    }

    /*
    进阶
    如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
    可以使用建立长度为 101 的桶，每个桶分别统计每个数的出现次数，同时记录数据流中总的元素数量，
    每次查找中位数时，先计算出中位数是第几位，从前往后扫描所有的桶得到答案。

    这种做法相比于对顶堆做法，计算量上没有优势，更多的是空间上的优化。

    对顶堆解法两个操作中耗时操作复杂度为 O(logn)，log 操作常数不会超过 3，在极限数据 10^7
     情况下计算量仍然低于耗时操作复杂度为 O(C)（C 固定为 101）桶计数解法。

    如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
    和上一问解法类似，对于 1% 采用哨兵机制进行解决即可，在常规的最小桶和最大桶两侧分别维护一个有序序列，即建立一个代表负无穷和正无穷的桶。

    上述两个进阶问题的代码如下，但注意由于真实样例的数据分布不是进阶所描述的那样（不是绝大多数都在 [0,100] 范围内），会 TLE。

    作者：AC_OIer
    链接：https://leetcode.cn/problems/find-median-from-data-stream/solution/gong-shui-san-xie-jing-dian-shu-ju-jie-g-pqy8/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    /*class MedianFinder {
        TreeMap<Integer, Integer> head = new TreeMap<>(), tail = new TreeMap<>();
        int[] arr = new int[101];
        int a, b, c;
        public void addNum(int num) {
            if (num >= 0 && num <= 100) {
                arr[num]++;
                b++;
            } else if (num < 0) {
                head.put(num, head.getOrDefault(num, 0) + 1);
                a++;
            } else if (num > 100) {
                tail.put(num, tail.getOrDefault(num, 0) + 1);
                c++;
            }
        }
        public double findMedian() {
            int size = a + b + c;
            if (size % 2 == 0) return (find(size / 2) + find(size / 2 + 1)) / 2.0;
            return find(size / 2 + 1);
        }
        int find(int n) {
            if (n <= a) {
                for (int num : head.keySet()) {
                    n -= head.get(num);
                    if (n <= 0) return num;
                }
            } else if (n <= a + b) {
                n -= a;
                for (int i = 0; i <= 100; i++) {
                    n -= arr[i];
                    if (n <= 0) return i;
                }
            } else {
                n -= a + b;
                for (int num : tail.keySet()) {
                    n -= tail.get(num);
                    if (n <= 0) return num;
                }
            }
            return -1; // never
        }
    }*/

}

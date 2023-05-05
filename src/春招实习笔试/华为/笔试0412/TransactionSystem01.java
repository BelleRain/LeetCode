package 春招实习笔试.华为.笔试0412;

/**
 * @author mxy
 * @create 2023-04-13 8:22
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * https://mp.weixin.qq.com/s?__biz=MzU2Mzg0OTQxOQ==&mid=2247485291&idx=1&sn=af014f4a5bc4902ab31bdf7d1e765f51&chksm=fc52b6cfcb253fd92bb7d4e51aeb45668466e3bd5ce4f6e2954134db7fc2580cc7e3f6a33fae&cur_album_id=2828382526552391682&scene=190#rd
 * 有一个核心交易系统接口被N个上游系统调用，每个上游系统的调用量R=[R1,R2.....,RN].
 * 由于核心交易系统集群故障，需要暂时系统降级限制调用，核心交易系统能接受的最大调用量为cnt。
 * 设置降级规则如下;如果sum(R1.R2..RN)小于等于cnt，则全部可以正常调用，返回-1;
 * 如果sum(R1.R2....RN)大于cnt，设置一个阈值limit，如果某个上游系统发起的调用量超过limit，
 * 就将该上游系统的调用量限制为limit，其余未达到limit的系统可以正常发起调用。
 * 求出这个最大的limit (limit可以为0)此题目对效率有要求，请选择高效的方式。
 *
 * 输入描述
 * 第一行:每个上游系统的调用量(整型数组)
 * 第二行:核心交易系统的最大调用量 0<R.length<=10^5，0<R[i]<10^5，0<cnt <= 10^9
 *
 * 输出描述
 * 调用量的阈值limit
 *
 * 样例1输入:
 * 1 4 2 5 5 1 6  ([R1,R2.....,RN])
 * 13             最大调用量为cnt
 * 输出:
 *  2             调用量的阈值limit
 * 解释:
 * 因为 1+4+2+5+5+1+6 > 13; 将limit设置为2，则1+2+2+2+2+1+2=12<13。所以imit为2
 *
 * 样例2输入:
 * 1 7 8 8 1 0 2 4 9
 * 7
 * 输出:
 * 0
 * 解释:因为即使limil设置为1,1+1+1+1+1+1+1+1=8>7也不满足，所以limit只能为0
 *
 */
public class TransactionSystem01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int[] nums = new int[s.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.valueOf(s[i]);
            R.add(nums[i]);
        }
        cnt = scanner.nextInt();
        System.out.println(maxLimit(nums, cnt));
        System.out.println(max_Limit());
    }

    /**
     * 这样解 不全面，阈值的设定没有要求必须是 nums 数组中的元素值
     * 测试案例 ：1 4 3 5 5 1 6  13
     * 1、数组排序 nums
     * 2、dp[i]：[0,i] 的 子序列和
     * 3、diff ：cnt 与 dp[i] 的 差值 cnt - dp[i] = diff
     *    sub_sum : nums[i] * [i + 1,len - 1].length 若 nums[i] 为 limit ，计算[i + 1,len - 1]的和
     *      if(diff < sub_num) return limit;
     *      //(cnt - dp[i] < sub_num ===> cnt < dp[i] + sub_num)
     *      else {
     *          limit = max(limit,nums[i]);
     *      }
     * @param nums
     * @param cnt
     * @return
     */
    public static int maxLimit(int[] nums,int cnt){
        int limit = 0, sum = 0, diff = 0, sub_sum = 0,len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            sum = sum + nums[i];
            diff = cnt - sum;
            sub_sum = (len - i - 1) * nums[i];
            if (diff < sub_sum) return limit;
            limit = Math.max(nums[i], limit);
        }
        return limit;
    }


    /**
     * 题解
     * @param nums
     * @param cnt
     * @return
     */
    static int cnt;
    static List<Integer> R = new ArrayList<>();

    //如果 元素 r 超出 阈值 x ，则 和 res = res + x
    //否则 res = res + r
    public static boolean check(int x){
        int res = 0;
        for (int r : R) {
            if (r <= x) res += r;
            else res += x;
        }
        return res <= cnt;
    }

    //mid 为阈值
    public static int max_Limit(){
        int l = 0,r = (int) 1e5;
        while (l < r){
            //int mid = (l + r + 1) >> 1;
            int mid = (l + r) / 2;
            if (check(mid)){
                l = mid;
            }
            else r = mid - 1;
        }
        return r;
    }
}

















































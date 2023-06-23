package 春招实习笔试.华为.笔试0524;

/**
 * @author mxy
 * @create 2023-06-09 9:03
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 *  https://mp.weixin.qq.com/s?__biz=MzkyNTQ3NDAzNw==&mid=2247485617&idx=1&sn=7b85e096dc40e8b30d1001c80995e8e1&chksm=c1c7452bf6b0cc3dda7ed9738b7aa42537c1eb0e3cc936cfbbd216a4837b3951ce744f332c23&scene=178&cur_album_id=2887532096360136706#rd
 *  连续空闲内存合并管理
 *  动态内存管理根据用户的需求分配任意大小的内存，当用户释放内存时，被释放的内存回到池(堆)中供其他用户使用。
 *  现设计某实时操作系统计划的内存管理功能，请你实现被释放内存的回收合并模块，当经过一次内存释放操作后，
 *  请返回当前最大的连续内存块的起始位置，以及此连续内存的数量(块数)。
 *  若存在多个最大连续内存块，则返回编号最小的内存块信息。
 *  当前已经把连续内存，按块进行连续编号。
 *
 *  输入
 * 输入:1,3,2,5 表示释放四块内存，ID分别为1.3.2.5，每块内存的大小为1个单位[预制条件]
 * 函数执行前，所有内存均已被申请完毕，无空闲，不需考虑内存重复释放[取值范围]
 * 内存ID编号:0 < ID < 2^31-1，单次释放的内存个数 < 10000
 *
 * 输出
 * 输出：1,3 经过回收处理后，当前可用的最大连续内存大小3，以及此内存的起始编号1.
 * 说明:1,3,2,5四块内存，前面三块1,3,2为连续内存，合并后的连续内存数为3个单位起始编号为1，因此返回1,3
 *
 * 样例1
 * 输入:
 * 1,3,2,5
 * 输出:
 * 1,3
 * 解释:
 * 1,3,2,5四块内存，前面三块1,3,2为连续内存，合并后的连续内存数为3个单位。起始编号为1，因此返回1,3
 *
 * 样例2
 * 输入:
 * 2,4,3,7,6
 * 输出:
 * 2,3
 * 解释:
 * 2,4,3,7,6，表示释放了5块内存，内存块编号分别为2、4、3、7、6。
 * 经过回收合并后，2、3、4三块内存连续，可以合并为一块大内存，大小为3个单位
 * 6、7两块内存连续，合井后的连续内存大小为2。
 * 因此返回此时的最大连续内存的起始位置为2，内存大小为3
 *
 */
public class MemoryManager01 {

    /**
     * 思路：模拟即可
     * 返回：最大连续内存的起始位置 和 内存大小
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(",");
        int[] nums = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }
        Arrays.sort(nums);
        int maxLen = 0, st = 0;
        int index = 0;
        while (index < nums.length){
            int i = index;
            for (int j = index + 1; j < nums.length; j++) {
                    if (nums[j] - nums[i] != 1) break;
                    i += 1;
            }
            if (maxLen < i - index + 1){
                maxLen = i - index + 1;
                st = index;
            }
            index = i + 1;
        }
        System.out.println(nums[st] + "," + maxLen);
    }


}

















































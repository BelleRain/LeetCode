package Top100;

/**
 * @author mxy
 * @create 2022-11-22 8:28
 */

/**
 * 55. 跳跃游戏    链接：https://leetcode.cn/problems/jump-game
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *  
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 10^5
 */
public class Top55 {

    public static void main(String[] args) {
        Top55 top55 = new Top55();
        int[] nums = {2,3,1,1,4};
        //int[] nums = {1,1,0,1,1};
        System.out.println(top55.canJump(nums));
    }

    /**
     * 题解一：
     * 解题思路：
     *  1、如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点
     *  2、可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新
     *  3、如果可以一直跳到最后，就成功了
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 0) return false;
        /*定义一个cover，表示当前元素能跳的最大覆盖范围，每次我都只往右跳一格
        然后更新cover范围，将当前索引的覆盖范围和上一次的覆盖范围cover相比，
        两者中的最大值就是更新后的cover。当最大范围>=数组最后一个索引时，返回true*/
        int cover = 0;
        //i<=cover：当走到 cover 时，若cover + nums[cover] 超过，则没必要遍历后面的元素；若没更新cover且cover<nums.length-1，则证明元素不动，无法到达了
        for (int i = 0; i <= cover; i++) {
            //nums[i]+i 是因为当前的最大覆盖范围等于从当前元素的位置加上当前元素可以跳跃的最大长度，是从i这个位置开始起跳的。可以画个图更容易理解
            int temp = i + nums[i];
            cover = Math.max(temp, cover); //取能覆盖的最大范围
            if (cover >= nums.length - 1) return true;
        }
        return false;
    }


    /**
     * 题解二：从后向前遍历
     * @param nums
     * @return
     */
    /*public boolean canJump(int[] nums) {
        int n=1;
        //n表示到 下一个可以 达到最后下标的点 的距离
        //初始时最后一个点到最后一个点距离为0，n为0，因为讨论这一点并无意义，所以实际计算从倒数第二个点开始，即n=1
        for(int i=nums.length-2;i>=0;i--){
            //若nums[i]>=n,表示从i出发能达到下一个能达到最后下标的点，需要刷新现在n的值
            //同上，因为下一次讨论的是 i-1点 的位置，故直接将n赋值成1即可
            if(nums[i]>=n)
            {
                n=1;
            }
            //若nums[i]<n,表示从i出发 无法抵达 下一个可以抵达最后下标的点
            //对于i-1来说，i-1需要超过的距离比i大1，即n++
            else
            {
                n++;
            }
            //若n>1，即从0坐标无法抵达下一个能抵达最后下标点的位置，返回false
            if(i==0&&n>1)
            {
                return false;
            }
        }
        return true;
    }*/


}

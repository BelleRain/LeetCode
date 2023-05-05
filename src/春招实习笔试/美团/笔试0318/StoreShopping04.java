package 春招实习笔试.美团.笔试0318;

/**
 * @author mxy
 * @create 2023-03-23 14:52
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 链接：https://mp.weixin.qq.com/s/WLUqD-ov9M_bXXaipfec6g
 * 第四题：商店购物
 * 题目描述
 * 现在商店里有N个物品，每个物品有原价和折扣价。
 * 小美想要购买商品。小美拥有X元，一共Y张折扣券。
 * 小美需要最大化购买商品的数量，并在所购商品数量尽量多的前提下，尽量减少花费。
 * 你的任务是帮助小美求出最优情况下的商品购买数量和花费的钱数。
 * 输入描述
 * 第一行三个整数，以空格分开，分别表示N,X,Y。
 * 接下来N行，每行两个整数，以空格分开，表示一个的原价和折扣价。
 * 1≤N≤100, 1≤X≤5000, 1≤Y≤50，每个商品原价和折扣价均介于[1,50]之间。
 * 输出描述
 * 一行，两个整数，以空格分开。第一个数字表示最多买几个商品，第二个数字表示在满足商品尽量多的前提下所花费的最少的钱数。
 * 示例1
 * 输入
 * 3 5 1
 * 4 3
 * 3 1
 * 6 5
 * 输出
 * 2 5
 */
public class StoreShopping04 {

    /**
     * 思路和代码
     * 【此代码未进行大量数据的测试，仅供参考，有疑问欢迎讨论】
     * 定义状态dp[i,j,k]的含义为：考虑前i个物品，剩余金额为j，剩余优惠券为k，可以购买的最大物品数。
     * 每个物品可以考虑的选择有：不买、全价买、优惠券买。枚举更新最大值即可。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); //物品数量
        int X = scanner.nextInt(); //现有钱数
        int Y = scanner.nextInt(); //折扣券数
        List<List> goods = new ArrayList<>();

        while (scanner.hasNextInt()){
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                temp.add(scanner.nextInt());
            }
            goods.add(temp);
        }
        //System.out.println(goods);

        //dp[i][j][k] : 最大物品数
        // i：下标； j：剩余金额；k：剩余优惠券数量
        int[][][] dp = new int[N + 1][X + 1][Y + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < X + 1; j++) {
                for (int k = 0; k < Y + 1; k++) {
                    //可以买、不买、优惠券买
                    dp[i][j][k] = dp[i - 1][j][k];
                    //如果 当前剩余的钱 >= 当前物品的原价
                    if(j >= (Integer) goods.get(i - 1).get(0)){
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - (Integer)goods.get(i - 1).get(0)][k] + 1);
                    }
                    if (k >= 1 && j >= (Integer)goods.get(i - 1).get(1)){
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - (Integer) goods.get(i - 1).get(1)][k - 1] + 1);
                    }
                }
            }
        }

        int maxCnt = 0;
        int minFee = 100000000;

        for (int j = 0; j < X + 1; j++) {
            for (int k = 0; k < Y + 1; k++) {
                if (dp[N][j][k] > maxCnt){
                    maxCnt = dp[N][j][k];
                    minFee = j;
                }
                else if (dp[N][j][k] == maxCnt){
                    minFee = Math.min(minFee, j);
                }
            }
        }

        System.out.print(maxCnt + " " + minFee);
    }
}




















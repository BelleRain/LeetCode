package Swordoffer.DivideAndConquer;

/**
 * @author mxy
 * @create 2022-11-02 14:06
 */

/**
 * 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 * 说明：
 * 用返回一个整数列表来代替打印
 * n 为正整数
 */
public class Offer17 {

    public static void main(String[] args) {
        Offer17 offer = new Offer17();
        //System.out.println(Arrays.toString(offer.printNumbers(1)));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/solution/mian-shi-ti-17-da-yin-cong-1-dao-zui-da-de-n-wei-2/
     */

    /**
     * 方法一：
     * 复杂度分析：
     *    时间复杂度 O(10^n):生成长度为10^n的列表需使用O(10^n)时间。
     *    空间复杂度 O(1)： 建立列表需使用 O(1) 大小的额外空间（ 列表作为返回结果，不计入额外空间 ）。
     * @param n
     * @return
     */
    /*public int[] printNumbers(int n) {
        int max = (int)Math.pow(10, n) - 1;
        int[] res = new int[max];
        for(int i = 1; i <= max; i++) {
            res[i - 1] = i;
        }
        return res;
    }*/

    /**
     * 方法二：大数打印解法
     * 复杂度分析：
     *    时间复杂度O(10^n):递归的生成的排列的数量为10^n。
     *    空间复杂度O(10^n):结果列表res的长度为10^n-1，各数字字符串的长度区间为1,2,...,n,因此占用O(10^n)大小的额外空间。
     * @param n
     * @return
     */
    //为 正确表示大数 ，以下代码的返回值为数字字符串集拼接而成的长字符串。
    StringBuilder res;
    //start：数字字符串左边界定义; nine：数字各位中9的数量
    int nine = 0,count = 0,start,n;
    char[] num,loop = {'0','1','2','3','4','5','6','7','8','9'};
    public String printNumbers(int n){
        this.n = n;
        res = new StringBuilder(); //数字字符串
        num = new char[n]; //定义长度为n的字符串列表
        //字符串左边界初始值为 n-1，即指向数字字符串的最后一位
        //左边界随着进位减一，例：n=3时，“009”进位至“010”， "099" 进位至 "100"，start = 2 --》start = 1 --》start = 0
        //判断所有位都为9，即 n - start = nine
        //此处应用左边界的目的为 删除高位多余的0
        start = n - 1;
        dfs(0);
        res.deleteCharAt(res.length() - 1); //删除最后的','
        return res.toString(); //转化为字符串并返回
    }

    void dfs(int x){
        if (x == n){ //终止条件，已固定完所有位
            String s = String.valueOf(num).substring(start);//从左边界开始截取，删除高位多余的0
            //由于题目要求列表从1开始，所以，在添加数字字符串之前判断是否为"0",若为"0"则直接跳过。
            //去除"00..."
            if (!s.equals("0")) res.append(s + ",");
            //若所有位都为9，则意味着进1，start--
            if (n - start == nine) start--;
            return;
        }
        for (char i : loop) {
            //固定第 x 位时，当 i = 9 则执行 nine = nine + 1，并在回溯前恢复 nine = nine - 1。
            if (i == '9') nine++;
            num[x] = i;
            dfs(x+1);
        }
        //在回溯前恢复 nine = nine - 1
        nine--;
    }



    //本题要求输出 int 类型数组。为 运行通过 ，可在添加数字字符串 s 前，将其转化为 int 类型。
    /*int[] res;
    int nine = 0,count = 0,start,n;
    char[] num,loop = {'0','1','2','3','4','5','6','7','8','9'};
    public int[] printNumbers(int n) {
        this.n = n;
        res = new int[(int)Math.pow(10, n) - 1];
        num = new char[n];
        start = n - 1;
        dfs(0);
        return res;
    }

    void dfs(int x){
        if (x == n){
            String s = String.valueOf(num).substring(start);
            if (!s.equals("0")) res[count++] = Integer.parseInt(s);
            if (n - start == nine) start--;
            return;
        }
        for (char i : loop) {
            if (i == '9') nine++;
            num[x] = i;
            dfs(x+1);
        }
        nine--;
    }*/


    /*public int[] printNumbers(int n) {
        if (n == 0) return new int[0];
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            s.append(9);
        }
        int number = Integer.parseInt(s.toString());
        int[] res = new int[number];
        for (int i = 0; i < number; i++) {
            res[i] = i+1;
        }
        return res;
    }*/

}

package 春招实习笔试.美团.笔试0318;

/**
 * @author mxy
 * @create 2023-03-21 9:15
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 链接：https://mp.weixin.qq.com/s/WLUqD-ov9M_bXXaipfec6g
 * 第一题：捕获游戏
 * 小美在玩一项游戏。该游戏的目标是尽可能抓获敌人。敌人的位置将被一个二维坐标 (x, y) 所描述。
 * 小美有一个全屏技能，该技能能一次性将若干敌人一次性捕获。
 * 捕获的敌人之间的横坐标的最大差值不能大于A，纵坐标的最大差值不能大于B。
 * 现在给出所有敌人的坐标，你的任务是计算小美一次性最多能使用技能捕获多少敌人。
 * 输入描述
 * 第一行三个整数N,A,B，表示共有N个敌人，小美的全屏技能的参数A和参数B。
 * 接下来N行，每行两个数字x,y，描述一个敌人所在的坐标。
 * 1≤N≤500，1≤A,B≤1000，1≤x,y≤1000。
 * 输出描述
 * 一行，一个整数表示小美使用技能单次所可以捕获的最多数量。
 * 样例输入
 * 3 1 1
 * 1 1
 * 1 2
 * 1 3
 * 样例输出
 * 2
 */
public class CatchGame01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int count = 0;
        int[][] enemies = new int[n][2];
        for (int i = 0; i < n; i++) {
            enemies[i][0] = sc.nextInt();
            enemies[i][1] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(enemies[i][0] - enemies[j][0]) <= a &&
                        Math.abs(enemies[i][1] - enemies[j][1]) <= b) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    //题目相当于找到一个长为A，宽为B的子矩阵，使得其和最大，因此可以用前缀和进行模拟。
    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int A = scanner.nextInt();
        int B = scanner.nextInt();

        int[][] matrix = new int[1001][1001];
        List<List> points = new ArrayList<>();

        while (scanner.hasNextInt()){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            matrix[x][y] = 1;
            List<Integer> point = new ArrayList<>();
            point.add(x);
            point.add(y);
            points.add(point);
        }

        int[][] prefix_sum = new int[1001][1001];

        for (int i = 0; i < 1001; i++) {
            for (int j = 0; j < 1001; j++) {
                if (i == 0 && j == 0){
                    prefix_sum[i][j] = matrix[i][j];
                }else if (i == 0){
                    prefix_sum[i][j] = prefix_sum[i][j - 1] + matrix[i][j];
                }else if (j == 0){
                    prefix_sum[i][j] = prefix_sum[i - 1][j] + matrix[i][j];
                }else {
                    prefix_sum[i][j] = prefix_sum[i - 1][j] + prefix_sum[i][j - 1] - prefix_sum[i - 1][j - 1] + matrix[i][j];
                }
            }
        }

        int res = 1;
        for (List point : points) {
            int x1 = (Integer) point.get(0);
            int y1 = (Integer) point.get(1);
            int x2 = x1 + A <= N ? x1 + A : N;
            int y2 = y1 + B <= N ? y1 + B : N;
            res = Math.max(res, get_submatrix_sum(x1,y1,x2,y2,prefix_sum));
        }

        System.out.println(res);
    }

    private static int get_submatrix_sum(int x1, int y1, int x2, int y2,int[][] prefix_sum) {
        int sum = prefix_sum[x2][y2];
        if (x1 > 0){
            sum = sum - prefix_sum[x1 - 1][y2];
        }
        if (y1 > 0){
            sum = sum - prefix_sum[x2][y1 - 1];
        }
        if (x1 > 0 && y1 > 0){
            sum = sum + prefix_sum[x1 - 1][y1 - 1];
        }
        return sum;
    }*/

    /*
    题目相当于找到一个长为A，宽为B的子矩阵，使得其和最大，因此可以用前缀和进行模拟。
N,A,B = map(int, input().split(" "))
matrix = [[0 for _ in range(1001)] for _ in range(1001)]
points = []

for _ in range(N):
    x, y = map(int, input().split(" "))
    matrix[x][y] = 1
    points.append((x,y))

prefix_sum = [[0] * (1001) for _ in range(1001)]

for i in range(1001):
    for j in range(1001):
        if i == 0 and j == 0:
            prefix_sum[i][j] = matrix[i][j]
        elif i == 0:
            prefix_sum[i][j] = prefix_sum[i][j-1] + matrix[i][j]
        elif j == 0:
            prefix_sum[i][j] = prefix_sum[i-1][j] + matrix[i][j]
        else:
            prefix_sum[i][j] = prefix_sum[i-1][j] + prefix_sum[i][j-1] - prefix_sum[i-1][j-1] + matrix[i][j]

def get_submatrix_sum(x1, y1, x2, y2):
    sum = prefix_sum[x2][y2]
    if x1 > 0:
        sum -= prefix_sum[x1-1][y2]
    if y1 > 0:
        sum -= prefix_sum[x2][y1-1]
    if x1 > 0 and y1 > 0:
        sum += prefix_sum[x1-1][y1-1]
    return sum

res = 1
for point in points:
    x1,y1 = point[0], point[1]
    x2,y2 = x1 + A if x1 + A <= N else N, y1 + B if y1 + B <= N else N
    res = max(res, get_submatrix_sum(x1,y1,x2,y2))

print(res)
     */


}

package 春招实习笔试.拼多多.笔试0312;

/**
 * @author mxy
 * @create 2023-03-13 13:44
 */

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * https://mp.weixin.qq.com/s?__biz=MzU2Mzg0OTQxOQ==&mid=2247484989&idx=1&sn=3256158958b7b9dc4135805d23cda3f2&chksm=fc52b799cb253e8fff762fb2814acbddb6602e00d4b56a920f06a3b7cd4fc68ef582d6de7ad3&cur_album_id=2828382526552391682&scene=189#wechat_redirect
 * 飞机大战
 * 多多最近下载了一款飞机大战的游戏,多多可以通过游戏上的不同发射按键来控制飞机发射子弹：
 * 按下A键,飞机会发射出2枚子弹,每个子弹会对命中的敌人造成1点固定伤害，但不能作用于同个敌人。
 * 按下B键,飞机会发射出1枚子弹,子弹会对命中的敌人造成巨额伤害并瞬间将其秒杀。
 * 多多是个游戏高手,总是能操控子弹命中想要命中的敌人。
 * 这个游戏 —共有 T 价关卡,消灭当前关卡全部敌人后,发射出去多余的子弹会消失, 游戏会自动进入下一个关卡。
 * 假设每个关卡都会在屏幕中同时出现 N个敌人,这N个敌人所能承受的伤害也已经知道。
 * 多多想知道,每个关卡自己最少按几次发射按键就可以将敌人全部消灭?
 *
 * 输入描述：
 * 第一行输入一个固定数字T（1T=1000)表示关卡的总数量,N（1<=N<=200）表示每个关卡出现的敌人数量。
 * 接下来T行，每行有N个数字D1,D2, ..... Dw（1<= Di <= 200)分别表示这N个敌人所能承受的伤害。
 * 输出描述：
 * 结果共有 N 行，每行一个数字,分别表示对于这个关卡,最少按几次发射按键就可以将敌人全部消灭。
 *
 * 示例1
 * 输入
 * 3 3
 * 1 2 1
 * 2 3 2
 * 1 2 3
 * 输出
 * 2
 * 3
 * 3
 * 说明
 * 游戏共有3个关卡,每个关卡会出现3个敌人。
 * 第一个关卡,先按下A建控制子弹消灭第1个和第3个敌人后,再按下B键消灭第二个敌人。所以最少按2次。
 * 第二个关卡,按下3次B键分别消灭这3个敌人。
 * 第三个关卡,按下3次B键分别消灭这3个敌人。
 * 也可以按3次A建,敌人剩余承受伤害的变化为:[1,2,3] -> [1,1,2] -> [1,0,1] -> [0,0,0]
 */
public class AircraftWar {
    public static void main(String[] args) {
        /*int[][] matrix = {
                {1,2,1},
                {2,3,2},
                {1,2,3}
        };*/

        /*int[][] matrix = {
                {1,2,1,2},
                {2,2,2,2}
        };*/

        /*Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println("输出：");
        while (scanner.hasNextLine()){
            System.out.println(s);
        }*/
        //装箱：调用 Integer.valueOf(v1)
        int v1 = 1;
        Integer v2 = Integer.valueOf(v1);
        //拆箱：调用 intValue()
        int v3 = v2.intValue();

        Scanner scanner = new Scanner(System.in);
        //读取第一行为 T、N赋值
        String nextLine = scanner.nextLine();
        String[] s = nextLine.split(" ");
        int T = Integer.parseInt(s[0]);
        int N = Integer.parseInt(s[1]);
        int[][] matrix = new int[T][N];
        int i = 0;
        //读取其余N行数据
        nextLine = scanner.nextLine();
        while (nextLine != null && !nextLine.equals("")){
            String[] s1 = nextLine.split(" ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(s1[j]);
            }
            i++;
            nextLine = scanner.nextLine();
        }
        for(int[] ints : matrix){
            System.out.println(Arrays.toString(ints));
        }

        leastCountButton(matrix);
    }

    /* 贪心算法：如果2个敌人的血量为1，则是使用A建；否侧使用B键。以此类推
    T, N = map(int, input().split(" "))
    for _ in range(T):
    D = [int(c) for c in input().split(" ")]
    D.sort()
    cnt = 0
    i = 0
    while i + 1< N:
        if D[i] == D[i + 1] == 1:
            cnt += 1
            i += 2
        else:
            break
    cnt += N - i
    print(cnt)
     */

    public static void leastCountButton(int[][] matrix){
        if (matrix == null) return;
        int T = matrix.length;  //关卡的数量
        int N = matrix[0].length;  //敌人的数量
        for (int i = 0; i < T; i++) {
            //经过一轮排序，所有的1，将会被排在前面，非1，在其后
            Arrays.sort(matrix[i]);
            int cnt = 0;
            int j = 0;
            //思路：在一行（一关）中，如果有 2个1 ，则使用A键；否则使用B键
            //使用B键，一个元素按 1 次；所以非1 的总次数 即为 N - j（总敌人数 - 血量为1的 敌人数）
            while (j  < N - 1){
                if (matrix[i][j] == 1 && matrix[i][j+1] == 1){
                    cnt++;
                    j += 2;
                }else {
                    break;
                }
            }
            //使用B键，非1的敌人数，N - j
            cnt += N - j;
            System.out.println(cnt);
        }
    }

}

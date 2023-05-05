package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author mxy
 * @create 2022-09-17 11:12
 */
public class TestCode {
    private static int len;
    private static int x;
    private static int y;
    //private int len;

    public static void main(String[] args) {
        TestCode testCode = new TestCode();
        //traversal(3);
        //System.out.println("len= " + len);
        //len= 3
        //x= 3    x = ++len;
        //System.out.println("x= " + x);

        //len= 3
        //y= 2  //y = len++;
        //System.out.println("y= " + y);

        //solution();
        //solution1();

        testCode.fastPow(2, 7);
    }


    public  static void traversal(int node){
        if(node == 0){
            return;
        }
        //++len;
        x = ++len;
        //y = len++;
        traversal(node-1);
    }

    @Test
    public void sortTest(){
        List<Integer> list = new ArrayList<>();
        for (int i = 5; i > 0; i--) {
            list.add(i);
        }
        System.out.println(list);
        System.out.println(list.toArray());
        Arrays.sort(list.toArray());
        System.out.println(Arrays.toString(list.toArray()));
        list.sort((num1,num2) -> (num1.compareTo(num2)));
        System.out.println(list.toString());

        System.out.println(5/2.0);
    }

    @Test
    public void rTest(){
        int[] queue = new int[10];
        int[] queue1 = new int[11];
        int r = 0;
        int j = 0;
        for (int i = 0; i < 10; i++) {
            queue[r++] = i; //先将 r 的值取出，再加1，即queue[0] = 0
            queue1[++j] = i; //先将 j 加1，再将值取出 ，即 queue1[1] = 0
        }
        System.out.println(Arrays.toString(queue));
        System.out.println(Arrays.toString(queue1));
    }

    @Test
    public static void solution(){
        System.out.println("请输入：");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),x = sc.nextInt(),y = sc.nextInt();
        int nums[] = new int[n];
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        //if(2*x>n||2*y<n){
        if (x > n/2 || y < n/2 + n%2){
            System.out.println(-1);
            return;
        }
        Arrays.sort(nums);
        //右端缩小
        if(x+y>=n){
            System.out.println(nums[x-1]);
        }else{
            System.out.println(nums[n-y-1]);
        }

    }

    @Test
    public static void solution1(){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            double sum = 0;
            for(int i = 1;i < m ; i++){
                sum = sum + Math.sqrt(n);
            }
            System.out.printf("%.2f",sum);
        }

    }

    //求 x 的 n 次幂
    @Test
    public void fastPow(int x,int n){
        if (n == 0) System.out.println(1);
        int ans = 1;
        while (n > 0){
            if ((n & 1) == 1){
                ans = ans * x;
            }
            x = x * x;
            n >>= 1;
        }
        System.out.println(ans);
    }
}

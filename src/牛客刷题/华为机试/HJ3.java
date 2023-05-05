package 牛客刷题.华为机试;

import java.util.*;

/**
 * @author mxy
 * @create 2023-04-08 13:45
 */
public class HJ3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = scanner.nextInt();
        }
        removeRepeat(num);
        //System.out.println(Arrays.toString(num));
    }

    public static void removeRepeat(int[] num){
        int[] temp = new int[500];
        for (int i = 0; i < num.length; i++) {
            temp[num[i]]++;
        }
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != 0){
                System.out.println(i);
            }
        }
    }

}

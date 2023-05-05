package 春招实习笔试.华为.笔试0419;

import java.util.*;

/**
 * @author mxy
 * @create 2023-04-19 18:40
 */


public class Main01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] dp = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] s = scanner.nextLine().split(" ");
            for (int j = 0; j < 2; j++) {
                dp[i][j] = Integer.parseInt(s[j]);
            }
        }

        Arrays.sort(dp, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int sum = 0;

        for (int i = 0; i < dp.length; i++) {
            if (dp[i][0] > 1) sum = sum + 0;
            else if (dp[i][0] == 1 && dp[i][1] < dp[i + 1][1]) sum = sum + 3 * 1;
            if (dp[i][1] < dp[i + 1][1]){
                sum = sum + 3 * (dp[i][1] - dp[i][0] + 1);
            }
        }
        System.out.println(sum);
    }


}

























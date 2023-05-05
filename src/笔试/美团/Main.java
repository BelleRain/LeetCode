package 笔试.美团;

import java.util.*;

/**
 * @author mxy
 * @create 2023-03-18 9:36
 */


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int[][] nums = new int[N][2];
        while (scanner.hasNextInt()){
            for (int i = 0; i < N; i++) {
                nums[i][0] = scanner.nextInt();
                nums[i][1] = scanner.nextInt();
            }
        }
        solute(nums, A, B);
    }

    public static void solute(int[][] nums,int A,int B){
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            Set<List> res = new HashSet<>();
            List<Integer> temp1 = new ArrayList<>();
            temp1.add(nums[i][0]);
            temp1.add(nums[i][1]);
            res.add(temp1);
            for (int j = i + 1; j < nums.length; j++) {
                ArrayList<Object> list = new ArrayList<>();
                list.add(nums[j][0]);
                list.add(nums[j][1]);
                Iterator<List> iterator = res.iterator();
                while (iterator.hasNext()){
                    List re = iterator.next();
                    Integer o = (Integer)re.get(0);
                    Integer o1 = (Integer)re.get(1);
                    if (Math.abs(nums[j][0] - o.intValue()) <= A && Math.abs(nums[j][1] - o1.intValue()) <= B){

                    }
                }
            }
            count = Math.max(count, res.size());
        }
        System.out.println(count);
    }

//5 1 2
//1 1
//2 2
//3 3
//1 3
//1 4
//end
}

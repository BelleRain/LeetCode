import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author mxy
 * @create 2022-09-18 14:34
 */
public class Temp {

    public static void main(String args[]) {
        //String Str = new String("   hello world!   ");
        //System.out.print("原始值 :" );
        //System.out.println(Str);
        //
        //System.out.print("删除头尾空白 :" );
        //System.out.println(Str.trim());

        //test2();
    }

    @Test
    public void test1(){
        int[] nums = new int[]{100,2};
        //String str = nums.toString();
        //String str = Arrays.toString(nums);
        //char[] chars = str.toCharArray();
        //for (char aChar : chars) {
        //    System.out.print(aChar + " ");
        //}

        char[] cs = {'3','5','4','1','2',' '};
        Arrays.sort(cs);
        StringBuilder builder = new StringBuilder();
        //System.out.println(Arrays.toString(cs));
        for (char c : cs) {
            if ( c == ' ') continue;
            builder.append(c);
        }
        System.out.println(builder.toString());
    }

    public static void test2(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(n);
        //scanner.hasNext()
        //Arrays.sort();
    }

    @Test
    public void test3() {
        double r = 1e5; // 1e5 为 double 类型
        System.out.println(1e5);
    }
}

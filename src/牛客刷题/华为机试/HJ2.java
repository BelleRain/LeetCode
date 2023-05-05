package 牛客刷题.华为机试;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author mxy
 * @create 2023-04-08 12:54
 */
public class HJ2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = new String[2];
        for(int i = 0; i < 2;i++){
            s[i] = scanner.nextLine();
        }
        System.out.println(count(s));
    }

    public static int count(String[] s){
        if(s.length == 0) return 0;
        HashMap<Character,Integer> map = new HashMap<>();
        char c = s[1].toLowerCase().charAt(0);
        String s1 = s[0].toLowerCase();
        char[] cs = s1.toCharArray();
        for(int i = 0; i < cs.length; i++){
            //if (!map.containsKey(cs[i])){
            //    map.put(cs[i], 1);
            //}else{
            //    map.put(cs[i], map.get(cs[i]) + 1);
            //}
            map.put(cs[i], map.getOrDefault(cs[i], 0) + 1);
        }
        Integer integer = map.get(c);
        if (integer == null) return 0;
        return integer.intValue();
    }
}

package 春招实习笔试.shein.笔试0330;

import java.util.*;

/**
 * @author mxy
 * @create 2023-03-30 23:46
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solve("abcdabcde")));
        System.out.println(Arrays.toString(solve("intiintiinti")));
    }

    public static String[] solve(String input){
        if (input.length() == 0) return new String[0];
        List<String> list = new ArrayList<>();
        int length = input.length();
        int left = 0;
        int right = 0;
        int size = 0, pre = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (left <= right && right < length){
            while (right < length && !map.containsKey(input.charAt(right))){
                map.put(input.charAt(right), right);
                right++;
            }
            int temp1 = right - left;
            //本次size的结果
            size = Math.max(temp1, pre);
            //pre保存上一次size的结果
            if (pre < size){
                list.clear();
            }
            String s = input.substring(left, left + size);
            list.add(s);
            pre = size;
            map.remove(input.charAt(left));
            left++;
        }
        return list.toArray(new String[list.size()]);
    }
}

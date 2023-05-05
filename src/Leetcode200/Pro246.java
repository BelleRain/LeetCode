package Leetcode200;

/**
 * @author mxy
 * @create 2022-11-10 21:34
 */

import java.util.HashMap;

/**
 * 中心对称数
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 * 请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。
 *
 * 示例 1:
 * 输入: num = "69"
 * 输出: true
 *
 * 示例 2:
 * 输入: num = "88"
 * 输出: true
 *
 * 示例 3:
 * 输入: num = "962"
 * 输出: false
 *
 * 示例 4：
 * 输入：num = "1"
 * 输出：true
 */
public class Pro246 {

    public static void main(String[] args) {
        Pro246 pro246 = new Pro246();
        System.out.println(pro246.isStrobogrammatic("962"));
    }

    /**
     * [0-9]之间中心对称的数有 ：0,1,8，6和9
     * @param num
     * @return
     */
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        int left = 0, right = num.length() - 1;
        while (left <= right){
            if (map.get(num.charAt(left)) == null || map.get(num.charAt(right)) == null) return false;
            if (map.get(num.charAt(left)) != num.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    //占用空间少
    //public boolean isStrobogrammatic(String num) {
    //    int l = 0, r = num.length()-1;
    //    while(l<=r){
    //        char cl = num.charAt(l);
    //        char cr = num.charAt(r);
    //        if(l == r) return cl == '0' || cl =='8' || cl == '1';
    //        else {
    //            if(
    //                    !(((cl == '0' || cl =='8' || cl == '1') && cl == cr )||
    //                    (cl == '6' && cr == '9')||
    //                    (cl == '9' && cr == '6'))
    //            ){
    //                return false;
    //            }
    //        }
    //        l++;
    //        r--;
    //    }
    //    return true;
    //}
}

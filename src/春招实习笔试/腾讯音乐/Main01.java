package 春招实习笔试.腾讯音乐;

import java.util.HashSet;
import java.util.Set;

/**
 * @author mxy
 * @create 2023-03-23 18:41
 */
public class Main01 {

    public static void main(String[] args) {
        Main01 main01 = new Main01();
        //System.out.println(main01.getCnt("aABbbCcggtthh"));
        //System.out.println(main01.getCnt("aABbbCcggtthh"));
        System.out.println(main01.getCnt("aABbbC"));
        System.out.println(main01.change('A'));
        //char a = 'a';
        //char b = 'A';  //A : 65
        //char z = 'z';  //z : 122
        //char Z = 'Z';  //Z : 90
        //System.out.println(Z + 32);
    }

    //public TreeNode fun (TreeNode root) {
    //
    //}

    //aABbbC
    public int getCnt (String str) {
        if (str.length() == 0) return 0;
        char[] s = str.toCharArray();
        int count = 0;
        for (int i = 0; i < s.length - 1; i++) {
            s[i] = change(s[i]);
            s[i + 1] = change(s[i + 1]);
            if (s[i] == s[i+1]){
                count++;
            }else {
                continue;
            }
        }
        return count;
    }

    public char change(char c){
        if (c >= 'A' && c <= 'Z'){
            c = (char)(c + 32);
        }
        return c;
    }

    public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
      this.val = val; }
    }
}

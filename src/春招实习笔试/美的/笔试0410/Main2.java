package 春招实习笔试.美的.笔试0410;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author mxy
 * @create 2023-04-10 19:45
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            if (s.equals("#")) break;
            list.add(s);
        }
        //System.out.println(list);
        List<StringBuilder> res = new ArrayList<>();
        StringBuilder build = new StringBuilder();
        String s0 = list.get(0);
        res.add(build.append(s0));
        for (int i = 1; i < list.size(); i++) {
            StringBuilder builder = new StringBuilder();
            String s = list.get(i);
            if (s.charAt(0) != ' '){
                res.add(builder.append(s));
            }else {
                String s2 = list.get(i - 1);
                int bl = black(s);
                int pre = black(list.get(i - 1));

                if (bl == pre){ //和上一层在同一个层级
                    String s1 = s.trim();
                    String sub = s2.substring(0, bl);
                    builder.append(sub);
                    build.append("-");
                    build.append(s1);
                    res.add(builder);
                }else{
                    String s1 = s.trim();
                    int mh = s2.indexOf(':');
                    String sub = s2.substring(0, mh);
                    builder.append(sub);
                    build.append("-");
                    build.append(s1);
                    res.add(builder);
                }
            }
        }

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).toString());
        }
        System.out.println("#");
    }

    public static int black(String s){
        int bl = 0;
        while (s.charAt(bl) == ' '){
            bl++;
        }
        return bl;
    }
}


//-A1: v1
//        B1: v2
//        C1: v3
//        C2: v4
//        #
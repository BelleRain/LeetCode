package 春招实习笔试.携程.笔试0415;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author mxy
 * @create 2023-04-15 18:38
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        char[][] grid = new char[n][m];
        for (int i = 0; i < n + 1; i++) {
            String s = scanner.nextLine();
            char[] chars = s.toCharArray();
            if (i == 0) continue;
            for (int j = 0; j < chars.length; j++) {
                grid[i - 1][j] = chars[j];
            }
        }
        System.out.println(solu(grid));
    }

    private static int solu(char[][] grid) {
        Set<Character> set = new HashSet<Character>(){{
            add('y');
            add('o');
            add('u');
        }};
        if (grid.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Set<Character> temp = new HashSet<>();
                if (dfs(set,temp,grid,i,j)){
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean dfs(Set<Character> set, Set<Character> temp, char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length - 1 || c < 0 || c >= grid[0].length - 1) return false;
        if (verfiy(grid[r][c])) temp.add(grid[r][c]);
        if (verfiy(grid[r + 1][c])) temp.add(grid[r + 1][c]);
        if (verfiy(grid[r][c + 1])) temp.add(grid[r][c + 1]);
        if (verfiy(grid[r + 1][c + 1])) temp.add(grid[r+1][c + 1]);
        if (temp.containsAll(set)) return true;
        return false;
    }

    private static boolean verfiy(char c){
        if (c == 'y' || c == 'o' || c == 'u')
            return true;
        return false;
    }
}









































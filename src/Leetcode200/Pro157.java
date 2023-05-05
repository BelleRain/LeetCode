package Leetcode200;

/**
 * @author mxy
 * @create 2022-11-08 16:57
 */

/**
 * 用 Read4 读取 N 个字符
 *
 * 给你一个文件，并且该文件只能通过给定的 read4 方法来读取，请实现一个方法使其能够读取 n 个字符。
 * read4 方法：
 * API read4 可以从文件中读取 4 个连续的字符，并且将它们写入缓存数组 buf 中。
 * 返回值为实际读取的字符个数。
 * 注意 read4() 自身拥有文件指针，很类似于 C 语言中的 FILE *fp 。
 *
 * read4 的定义：
 * 参数类型: char[] buf4
 * 返回类型: int
 *
 * 注意: buf4[] 是目标缓存区不是源缓存区，read4 的返回结果将会复制到 buf4[] 当中。
 * 下列是一些使用 read4 的例子：
 * File file("abcde"); // 文件名为 "abcde"， 初始文件指针 (fp) 指向 'a'
 * char[] buf4 = new char[4]; // 创建一个缓存区使其能容纳足够的字符
 * read4(buf4); // read4 返回 4。现在 buf4 = "abcd"，fp 指向 'e'
 * read4(buf4); // read4 返回 1。现在 buf4 = "e"，fp 指向文件末尾
 * read4(buf4); // read4 返回 0。现在 buf = ""，fp 指向文件末尾
 *
 * read 方法：
 * 通过使用 read4 方法，实现 read 方法。该方法可以从文件中读取 n 个字符并将其存储到缓存数组 buf 中。您 不能 直接操作文件。
 * 返回值为实际读取的字符。
 *
 * read 的定义：
 * 参数类型:   char[] buf, int n
 * 返回类型:   int
 * 注意: buf[] 是目标缓存区不是源缓存区，你需要将结果写入 buf[] 中。
 *  
 *
 * 示例 1：
 * 输入： file = "abc", n = 4
 * 输出： 3
 * 解释： 当执行你的 read 方法后，buf 需要包含 "abc"。 文件一共 3 个字符，因此返回 3。 注意 "abc" 是文件的内容，不是 buf 的内容，buf 是你需要写入结果的目标缓存区。
 *
 * 示例 2：
 * 输入： file = "abcde", n = 5
 * 输出： 5
 * 解释： 当执行你的 read 方法后，buf 需要包含 "abcde"。文件共 5 个字符，因此返回 5。
 *
 * 示例 3:
 * 输入： file = "abcdABCD1234", n = 12
 * 输出： 12
 * 解释： 当执行你的 read 方法后，buf 需要包含 "abcdABCD1234"。文件一共 12 个字符，因此返回 12。
 *
 * 示例 4:
 * 输入： file = "leetcode", n = 5
 * 输出： 5
 * 解释： 当执行你的 read 方法后，buf 需要包含 "leetc"。文件中一共 5 个字符，因此返回 5。
 *  
 *
 * 提示：
 * 你 不能 直接操作该文件，文件只能通过 read4 获取而 不能 通过 read。
 * read  函数只在每个测试用例调用一次。
 * 你可以假定目标缓存数组 buf 保证有足够的空间存下 n 个字符。 
 *
 */
public class Pro157 {

    public static void main(String[] args) {

    }

    /**
     *   The read4 API is defined in the parent class Reader4.
     *        int read4(char[] buf4);  //原题意为：该方法已在父类 Reader4中写好，子类的方法read()直接调用即可。
     *   该题意为，read4每次只可以读取4个字符，并返回 实际读取的字符数；
     *   现通过read方法调用read4方法来处理文件，并将读取后的字符存储在目标缓冲区buf[]中
     * @param buf Destination buffer  目标缓冲区
     * @param n   Number of characters to read  要读取的字符数
     * @return    The number of actual characters read 实际的字符数
     */
    public int read(char[] buf, int n) {
        int idx = 0; //buf的索引
        char[] buf4 = new char[4]; // 创建一个缓存区使其能容纳足够的字符
        //每次只可以读取4个字符，故需要循环
        while (idx < n){
            //返回该次实际读取的字符数，ret最大为4
            int ret = read4(buf4);
            //如果为0，证明已到末尾，直接返回idx
            if (ret == 0){
                return idx;
            }
            //idx：已读取（已复制到buf中）的字符数
            // n-idx：距离目标字符数，未读取的字符数
            // 两者 之中的最小值，即为本次要向 buf 中添加的字符数
            //若ret较大，则说明 要读取的字符数 < 已读取的字符数；否则，说明要读取的字符数 >= 已读取的字符数，要进行一轮循环才能达到要求
            //最终都将buf4中的字符按预定字数写入buf中
            int len = Math.min(ret, n-idx);
            for (int i = 0; i < len; i++) {
                buf[idx] = buf4[i];
                idx++; //idx++
            }
        }
        return idx;
    }

   /*public int read(char[] buf, int n) {
        int idx = 0;
        char[] buf4 = new char[4];
        int size = read4(buf4);
        while (size > 0 && idx < n) {
            for (int i = 0; i < size && idx < n; i++) buf[idx++] = buf4[i];
            size = read4(buf4);
        }
        return idx;
    }*/

    //为程序不报错模拟read4方法
    private int read4(char[] buf4) {
        return 0;
    }
}

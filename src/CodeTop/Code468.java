package CodeTop;

/**
 * @author mxy
 * @create 2023-05-25 13:26
 */

/**
 * 468. 验证IP地址      链接：https://leetcode.cn/problems/validate-ip-address
 * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；
 * 如果不是上述类型的 IP 地址，返回 "Neither" 。
 *
 * 有效的IPv4地址 是 “”x1.x2.x3.x4 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。
 * 例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、
 * “192.168@1.1” 为无效IPv4地址。
 *
 * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 * 1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 * 在 xi 中允许前导零。
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，
 * 而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 *
 *
 * 示例 1：
 * 输入：queryIP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 *
 * 示例 2：
 * 输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 *
 * 示例 3：
 * 输入：queryIP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 *
 * 提示：
 * queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 *
 */
public class Code468 {

    public static void main(String[] args) {

    }

    public static String validIPAddress(String queryIP) {
        if (queryIP.indexOf('.') >= 0) {
            return isIPv4(queryIP) ? "IPv4" : "Neither";
        } else {
            return isIPv6(queryIP) ? "IPv6" : "Neither";
        }
    }

    // 0 <= xi <= 255 且 xi 不能包含 前导零。
    private static boolean isIPv4(String queryIP) {
        String[] str = queryIP.split("\\.", -1);
        if (str.length != 4) {
            return false;
        }
        for (String s : str) {
            //判断字符串长度是否条件
            if (s.length() > 3 || s.length() == 0) {
                return false;
            }
            //判断是否有前导0
            if (s.charAt(0) == '0' && s.length() != 1) {
                return false;
            }
            //判断中间是否有字符
            int ans = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!Character.isDigit(c)) {
                    return false;
                }
                ans = ans * 10 + (c - '0');
            }
            if (ans > 255) {
                return false;
            }
        }
        return true;
    }

    //1 <= xi.length <= 4
    //xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
    //在 xi 中允许前导零。
    private static boolean isIPv6(String queryIP) {
        String[] str = queryIP.split(":", -1);
        if (str.length != 8) {
            return false;
        }
        for (String s : str) {
            //判断长度是否合适
            if (s.length() > 4 || s.length() == 0) {
                return false;
            }
            //判断每个字符是否符合要求
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!Character.isDigit(c) && (Character.toLowerCase(c) < 'a' || Character.toLowerCase(c) > 'f')
                        && (Character.toUpperCase(c) < 'A' || Character.toUpperCase(c) > 'F')) {
                    return false;
                }
            }
        }
        return true;
    }

}



































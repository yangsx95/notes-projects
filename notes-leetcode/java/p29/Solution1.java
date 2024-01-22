package p29;

public class Solution1 {

    public int divide(int dividend, int divisor) {
        // 除数不能为0
        if (divisor == 0) {
            throw new IllegalArgumentException();
        }

        // 处理系统最小值问题
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 获取这两个数相除后的符号， TRUE代表正数，FALSE代表负数
        boolean positive = dividend >> 31 == divisor >> 31;

        // 统一转换为正数处理
        if (dividend < 0) {
            dividend = ~dividend + 1;
        }

        if (divisor < 0) {
            divisor = ~divisor + 1;
        }

        int quotient = 0; // 商数
        int remainder = dividend; // 余数
        int count = 0; // 左移次数

        // 退出条件 余数小于被除数
        while (remainder >= divisor) {
            if (divisor << count <= remainder && divisor << count <= 1073741823) { // 没有超过系统最小值的一半继续循环
                count++;
            } else if (divisor << count > 1073741823) { // 超过系统最小值的一半，停止循环
                quotient += (1 << --count);
                remainder -= (divisor << count);
                count = 0;
            } else {
                quotient += (1 << --count);
                remainder -= (divisor << count); // 上面count减过了 不用再减了
                count = 0;
            }
        }
        return positive ? quotient : (~quotient + 1);
    }

    public static void main(String[] args) {
        Solution1 s = new Solution1();
        int divide = s.divide(-2147483648, 1);
    }

}
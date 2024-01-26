package da;

import org.junit.Assert;
import org.junit.Test;

public class BitOperationTest {

    /**
     * 打印数字的二进制表现字符串
     */
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    /**
     * 统计二进制数中1的个数
     */
    public static int count(int num) {
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            count += (num & (1 << i)) == 0 ? 0 : 1;
        }
        return count;
    }

    /**
     * 设置二进制数的某一位
     *
     * @param num  二进制数
     * @param pos  位置
     * @param flag true => 1， false => 0
     */
    public static int set(int num, int pos, boolean flag) throws IllegalArgumentException {
        if (pos < 0 || pos > 31) {
            throw new IllegalArgumentException();
        }
        if (flag) {
            num |= (1 << pos);
            return num;
        }

        num &= ~(1 << pos);
        return num;
    }

    @Test
    public void testPrint() {
        print(1 << 31);
    }

    @Test
    public void testCount() {
        Assert.assertEquals(count(1), 1);
        Assert.assertEquals(count(3), 2);
    }

    @Test
    public void testSet() {
        print(18);
        print(set(18, 31, true));
        print(set(18, 1, false));
    }

    static class BitMap {
        // 1个long类型可以存储64位
        // 使用一个long类型数组存储bit位图
        private final long[] bits;

        // 根据max确定数组的长度
        // 如果 max 属于 0 ~ 63 则数组长度为1
        // 如果 max 属于 0 ~ 127 则数组长度为2
        public BitMap(int max) {
            if (max < 0) throw new IllegalArgumentException();
            bits = new long[(max + 64) >> 6]; // 等同于 (size + 64) / 64
        }

        // 将某个位置置为1，位置索引从0开始
        public void add(int num) {
            // 1. 确定num位置属于数组的第几个整数元素： 0/64=0, 64/64=1, 127/64=1, 128/64=2
            //    故元素位置= num / 64 也就是  num >> 6
            // 2. 确定num位于已确定的整型元素的第几位： 0%64=0, 1%64=1, 127%64=63, 128%64=0
            //    故num位置位于整型元素的第  num%64位。num%64 等同于 num&63：
            //    num%64等同于只保留二进制的最后6位，前面的位数全部去除，所以 num & 63 = num % 64
            // 3. 将指定位置1，使用 |，比如将第2位置1 
            //    0000 | 0100 = 0100
            //    0100 => 1 << (num & 63)
            // 故结果如下：
            bits[num >> 6] |= (1L << (num & 63));
        }

        public void delete(int num) {
            // 1. 确定num位置属于数组的第几个整数元素: num >> 6
            // 2. 确定num位于已确定的整型元素的第几位: num % 64 => num & 63
            // 3. 将指定位置0，使用&，比如将第二位置0
            //    1111 & 1011 = 1011
            //    1011 => ~0100
            // 故结果如下
            bits[num >> 6] &= ~(1L << (num & 63));
        }

        public boolean contains(int num) {
            // 1010 & 0100 => 0000 说明第二位是0，否则第二位是1
            return (bits[num >> 6] & (1L << (num & 63))) != 0;
        }
    }

    @Test
    public void testBitMap() {
        BitMap bitMap = new BitMap(200);
        bitMap.add(1);
        Assert.assertTrue(bitMap.contains(1));
        Assert.assertFalse(bitMap.contains(0));
        bitMap.delete(1);
        Assert.assertFalse(bitMap.contains(1));
    }

    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    // 减法就是 a + (-b)， 一个数的相反数可以用 ~b + 1来表示
    public static int minus(int a, int b) {
        return add(a, add(~b, 1));
    }

    // 乘法
    public static int multi(int a, int b) {
        int bi;
        int result = 0;
        int count = 0;
        while (count < 32) {
            bi = b & (1 << count);
            if (bi != 0) {
                result = add(result, a << count);
            }
            count++;
        }
        return result;
    }

    // 乘法更简单的实现
    // b每次向右侧移动一位，即可取到某位的状态
    public static int multi2(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    // 除法，累减法
    public static int division(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException();
        }
        int result = 0;
        while (a - b >= 0) {
            a -= b;
            result++;
        }
        return result;
    }

    // 除法，加权累减法
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
            if (count < 31 && divisor << count <= remainder) {
                count++;
            } else {
                quotient += (1 << --count);
                remainder -= (divisor << count); // 上面count减过了 不用再减了 
                count = 0;
            }
        }
        return positive ? quotient : (~quotient + 1);
    }
    

    @Test
    public void testAdd() {
        Assert.assertEquals(3, add(1, 2));
        Assert.assertEquals(3, add(2, 1));
        Assert.assertEquals(3, add(-1, 4));
        Assert.assertEquals(3, add(4, -1));
    }

    @Test
    public void testMinus() {
        Assert.assertEquals(-1, minus(1, 2));
        Assert.assertEquals(-5, minus(-1, 4));
    }

    @Test
    public void testMulti() {
        Assert.assertEquals(1, multi(1, 1));
        Assert.assertEquals(2, multi(1, 2));
        Assert.assertEquals(2, multi(2, 1));
        Assert.assertEquals(0, multi(0, 0));
        Assert.assertEquals(0, multi(1, 0));
        Assert.assertEquals(-1, multi(1, -1));
        Assert.assertEquals(8, multi(-2, -4));
    }

    @Test
    public void testDivision() {
        Assert.assertEquals(1, division(1, 1));
        Assert.assertEquals(0, division(1, 2));
        Assert.assertEquals(2, division(2, 1));
        Assert.assertEquals(1, division(2, 2));
        Assert.assertEquals(11, division(199, 18));
    }

}

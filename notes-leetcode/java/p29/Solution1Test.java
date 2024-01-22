package p29;

import java.util.Random;

public class Solution1Test {

    public static int divide(int a, int b) {
        return a / b;
    }

    public static int randomInt(boolean excludeZero) {
        Random random = new Random();
        int result;
        do {
            result = random.nextInt();
        } while (result == 0 && excludeZero);
        return result;
    }

    public static void main(String[] args) {
        int testTimes = 50_0000;
        int dividend;
        int divisor;
        Solution1 solution1 = new Solution1();
        for (int i = 0; i < testTimes; i++) {
            // 随机两个int类型的数字，除数不能为0
            // 分别使用测试目标与系统方法计算结果
            dividend = randomInt(false);
            divisor = randomInt(true);
            int targetRes = solution1.divide(dividend, divisor);
            int sysRes = divide(dividend, divisor);
            if (targetRes != sysRes) {
                System.err.println("测试失败，目标数字 " + dividend + " / " + divisor + "  期望结果：" + sysRes + "  实际结果：" + targetRes);
                break;
            }
        }
    }
}
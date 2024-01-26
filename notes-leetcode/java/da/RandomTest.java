package da;

import org.junit.Test;

public class RandomTest {

    @Test
    public void equalProbabilityRandom() {
        int testTimes = 10000000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            double random = Math.max(Math.random(), Math.random());
            if (random < 0.5) {
                count++;
            }
        }
        System.out.println("随机概率为：" + ((double) count / (double) testTimes));  // 0.2501026
    }

    @Test
    public void m1to5and1to7() {
        int testTimes = 1000000;
        int[] counts = new int[8];
        for (int i = 0; i < testTimes; i++) {
            int num = g();
            counts[num]++;
        }
        // 打印每个的次数
        for (int i = 0; i < counts.length; i++) {
            System.out.println("数字" + i + "出现的次数为" + counts[i]);
        }
    }

    // 返回 1 ~ 5等概率的随机整数
    public int f() {
        return (int) (Math.random() * 5 + 1);
    }

    // 根据 f() 构造一个等概率的 0 1 发生器
    public int f1() {
        // 1, 2, 3,   4, 5
        // 0, 0, 重来, 1, 1
        while (true) {
            int num = f();
            if (num < 3) {
                return 0;
            }
            if (num > 3) {
                return 1;
            }
        }
    }

    // 要想得到 1 ~ 7 范围的数，可以先构造 0 ~ 7 的等概率随机函数
    // 使用 0 1 发生器构建二进制数，随机生成 0 ~ 7 内的数字
    // 000  ~ 111
    public int f2() {
        return (f1() << 2) + (f1() << 1) + f1();
    }

    // 使用f2() 剔除 7，或者剔除 0，完成 1~7的随机
    public int g() {
        int num;
        do {
            num = f2();
        } while (num == 0);
        return num;
    }

    @Test
    public void notEqual0and1() {
        int testTimes = 1000000;
        int[] counts = new int[2];
        for (int i = 0; i < testTimes; i++) {
            counts[y()]++;
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.println("数字" + i + "出现的概率为" + counts[i]);
        }
    }
    
    public int x() {
        return Math.random() < 0.86 ? 0: 1;
    }
    
    // 将x()随机两次
    // 出现 01 的概率是与出现 10的概率相同的
    // 故代码如下：
    public int y() {
        int num;
        do {
            num = x();
        } while (num == x());
        return num;
    }
}

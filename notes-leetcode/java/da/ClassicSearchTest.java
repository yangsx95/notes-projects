package da;

public class ClassicSearchTest {

    /**
     * 在一个有序数组中，找出某个数是否存在
     */
    public boolean binarySearchExist(int[] sortedArray, int num) {
        if (sortedArray == null || sortedArray.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArray.length - 1;
        int mid = 0;
        while (L < R) {
            // mid = (L + R) / 2;
            // 防止 L + R 所得的结果过大，溢出int类型最大值
            // 改造
            // mid = L + (R - L) / 2; // R 不会溢出，L 不会溢出， (R - L) / 2 更不会造成溢出
            // 等同于
            mid = L + ((R - L) >> 1);
            if (sortedArray[mid] == num) {
                return true;
            } else if (sortedArray[mid] > num) {
                R = mid;
            } else {
                L = mid;
            }
        }
        return sortedArray[mid] == num;
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 5, 1, 3, 4, 1, 6, 6, 1, 6, 4,};
        printOddNum2(arr);
    }

    public static int bit1Count(int n) {
        int count = 0;
        int rightOne;
        while (true) {
            // 找到最右侧的1
            rightOne = n & (~n + 1);
            if (rightOne == 0) {
                return count;
            }
            count++;
            // 抹掉最右侧的1
            n ^= rightOne;
        }
    }

    public static int bit1Count2(int n) {
        int count = 0;
        int rightOne;
        while (n != 0) {
            // 找到最右侧的1
            rightOne = n & (~n + 1);
            count++;
            n ^= rightOne;
        }
        return count;
    }

    // 假设两个奇数为 a 和 b
    // eor = 将数组中所有元素相互异或
    // eor = a ^ b != 0
    // 因为eor != 0，故eor 在至少有一个位置上一定有1，所以 a 和 b 在这些位置上的 0 与 1 的状态一定不一致
    // 取eor出最右侧的1，与数组的所有元素相与，可以得出该位置为1的所有元素，其中一定含有 a 或者 b
    // 将这些元素再次相互异或，可以得到 a 或者 b， 根据 eor = a ^ b; a = eor ^ b; 故而得到 a 和 b
    // 代码如下：
    public static void printOddNum2(int[] arr) {
        int eor = 0;
        for (int j : arr) {
            eor ^= j;
        }
        int rightOne = eor & (~eor + 1);
        int aOrB = 0;
        for (int j : arr) {
            if ((j ^ rightOne) == 0) {
                aOrB ^= j;
            }
        }
        System.out.println(aOrB + "  " + (eor ^ aOrB));
    }
    
}

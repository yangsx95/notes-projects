package da;

import org.junit.Test;

import java.util.Arrays;

public class ClassicSortTest {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 找出 0 ~ n-1 中的最小值的索引
        // 找出 1 ~ n-1 中的最小值的索引
        // 找出 2 ~ n-1 中的最小值的索引
        // 找出 i ~ n-1 中的最小值的索引
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            // 交换值，将每次最小都放入前面
            swap(arr, i, minValueIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int iV = arr[i];
        arr[i] = arr[j];
        arr[j] = iV;
    }

    @Test
    public void testSelectionSort() {
        int[] arr = new int[]{1, 3, 5, 3, 2, 9, 2, 5};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ n-1
        // 0 ~ n-2
        // 0 ~ n-3
        // 0 ~ n-4

        // 也就是 0 ~ end
        for (int end = arr.length - 1; end >= 0; end--) {
            // 在 0 ~ end 上对数进行两两比较，将最大的数冒泡到最后
            // 需要循环 end-1 次，倒数第二次循环已经将最大值选出了
            for (int i = 0; i <= end - 1; i++) { // 从1开始是为了不溢出
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 0 ~ 0 
        // 0 ~ 1 范围有序
        // 0 ~ 2 范围有序
        // ...   
        // 0 ~ n 范围有序
        for (int end = 0; end < arr.length; end++) {
            // 排序新数
            int newNumIndex = end;
            while (newNumIndex - 1 >= 0 && arr[newNumIndex - 1] > arr[newNumIndex]) {
                swap(arr, newNumIndex - 1, newNumIndex);
                newNumIndex--;
            }
        }
    }

    @Test
    public void testBubbleSort() {
        int[] arr = new int[]{1, 3, 5, 3, 2, 9, 2, 5};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testInsertSort() {
        int[] arr = new int[]{1, 3, 5, 3, 2, 9, 2, 5};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 递归实现归并排序
     */
    public void mergeSort1(int[] arr, int L, int R) {
        if (arr == null || arr.length < 2
                || L < 0 || L >= arr.length
                || R < 0 || R >= arr.length || L >= R) {
            return;
        }
        process(arr, L, R);
    }

    public void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = (L + R) / 2;
        process(arr, L, M);
        process(arr, M + 1, R);
        merge(arr, L, M, R);
    }

    public void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1]; // 创建一个用于盛放合并后数组的数组
        int i = 0; // help的指针，每次填充后+1
        int p1 = L; // 遍历左部分的指针
        int p2 = M + 1;// 遍历有部分的指针

        // p1 p2 都在正确的范围内
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        // 如果p1在正确的范围内，说明p2循环完毕了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }

        //  如果p2在正确的范围内，说明p1循环完毕了
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        // 将help排序后的内容填充到arr中
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    @Test
    public void testMergeSort1() {
        int[] arr = new int[]{9, 7, 3, 1, 0, 1, 4, 5, 9, 8, 0, 2};
        mergeSort1(arr, 0, 5);
        System.out.println(Arrays.toString(arr));
    }

    public void mergeSort2(int[] arr) {
        int k = 1;
        int i = 0;
        while (k <= arr.length) {
            if (i >= arr.length) {
                i = 0;
                k <<= 1;
            }
            // 左索引为i，右索引为 i + 2 * k - 1，且不能超过数组长度-1，中间为i + k - 1，且不能超过数组长度
            int L = i;
            int R = Math.min(i + 2 * k - 1, arr.length - 1);
            int M = Math.min(i + k - 1, arr.length - 1);
            // 将小组合并为大组
            merge(arr, L, M, R);
            // 重置i索引
            i += 2 * k;
        }
    }

    @Test
    public void testMergeSort2() {
        int[] arr = new int[]{9, 7, 3, 1, 0, 1, 4, 5, 9, 8, 0, 2};
        mergeSort2(arr);
        System.out.println(Arrays.toString(arr));
    }
}

package Thread;

public class QS1 {

    public static void main(String[] args) {
        int[] nums = {110, 100, 0};

        int[] a = sortArray(nums);


        for (int i = 0 ; i < a.length ; i++){
            System.out.println(a[i]);
        }
    }

    public static int[] sortArray(int[] nums) {


        quickSort(nums,0,nums.length-1);
        return nums;
    }

    public static void quickSort(int[] arr, int start, int end) {

        if(start >= end) {
            return;
        }

        // 1.  设两个指针 low 和 high ，他们的初始值分别为数组开始的下标 start，数组结束的下标end
        int low = start;
        int high = end;

        // 2.  枢轴值为 pivotKey
        int pivotKey = arr[start];

        // 5. 重复前两步操作（3，4），直至 low == high
        while (low<high) {

            // 3. 首先从 high 所指位置向前搜索，搜索到第一个小于 pivotKey 的 值，然后将这个值和  pivotKey 互相交换
            while (low<high && arr[high]>=pivotKey) {
                --high;
            }
            int temp1 = arr[low];
            arr[low] = arr[high];
            arr[high] = temp1;


            // 4. 从 low 所指位置向后搜索，搜索到第一个大于 pivotKey 的 值，然后将这个值和  pivotKey 互相交换。
            while (low<high && arr[low]<=pivotKey) {
                ++low;
            }
            temp1 = arr[low];
            arr[low] = arr[high];
            arr[high] = temp1;
        }

        //
        // 对 小于枢轴值的那部分数组值 进行快排
        quickSort(arr, start, low-1);
        // 对 大于枢轴值的那部分数组值 进行快排
        quickSort(arr, low+1, end);
    }
}

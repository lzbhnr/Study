package Thread;

public class QS {

    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 2, 0, 0};


        int[] a = sortArray(nums);
    }

    public static int[] sortArray(int[] nums) {


        quickSort(nums, 0, nums.length - 1);
        return nums;

    }


    public static void quickSort(int[] nums, int start, int end) {

        // 排序两个数据

        if (start >= end) {
            return;
        }

        int standard = start;


        int left = start;

        int right = end;

        while (left < right) {

            System.out.println(left);
            while (left < right && nums[left] < nums[standard]) {

                left++;
            }

            while (left < right && nums[right] > nums[standard]) {

                right--;

            }


            System.out.println(left);
            System.out.println(right);
            System.out.println("----");

            int temp = nums[left];


            nums[left] = nums[right];

            nums[right] = temp;


        }

        nums[start] = nums[left];

        nums[left] = standard;


        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);


    }

}

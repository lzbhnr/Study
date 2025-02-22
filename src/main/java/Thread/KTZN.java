package Thread;

/**
 * 给定一个升序排列的整数数组和一个目标值，返回数组中第一个大于等于目标值的元素的索引。如果所有元素都小于目标值，返回数组的长度。
 * Eg: [1,3,5,7,9] 查找4 预期返回2， 查找8 返回 4， 查找10 返回5
 */
public class KTZN {


    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};

        int value = solution(nums, 5);

        System.out.println(value);

        value = solution(nums, 8);

        System.out.println(value);

        value = solution(nums, 10);

        System.out.println(value);
    }

    private static int solution(int[] nums, int aim) {

        if (nums == null || nums.length == 0) {
            return -2;
        }

        int left = 0;
        int right = nums.length - 1;


        if (nums[right] < aim) {
            return nums.length;
        }

        if (nums[left] > aim) {
            return -1;
        }


        for (; left < right; ) {

            int mid = left + (right - left) / 2;
            if (nums[mid] == aim) {
                // 第一个
                return mid;
            } else if (nums[mid] > aim) {
                right = mid;
            } else {
                //nums[mid] < aim
                left = mid + 1;
            }
        }

        return left;
    }
}

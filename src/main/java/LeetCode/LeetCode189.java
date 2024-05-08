package LeetCode;

public class LeetCode189 {


    /**
     * https://leetcode-cn.com/problems/rotate-array/
     * @param nums
     * @param start
     * @param end
     */

    private void reverse(int[] nums, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public static void main(String[] args) {
        int k = 100;
        int n = 16;
        k %= n;
        System.out.println(k);
    }
}

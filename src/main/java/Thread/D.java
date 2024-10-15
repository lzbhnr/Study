package Thread;

import java.util.Arrays;

public class D {


    public static void main(String[] args) {

        int[] nums = {1, 2, 4, 9};
        int x = 2533;
        int result = getMax(nums, x);
        System.out.println(result);

        System.out.println("-----");

        nums = new int[]{1, 2, 5, 4};
        x = 2543;
        result = getMax(nums, x);
        System.out.println(result);
        System.out.println("-----");

        nums = new int[]{1, 2, 5, 4};
        x = 2541;
        result = getMax(nums, x);
        System.out.println(result);
        System.out.println("-----");

        nums = new int[]{1, 2, 9, 4};
        x = 2111;
        result = getMax(nums, x);
        System.out.println(result);
        System.out.println("-----");

        nums = new int[]{5, 9};
        x = 5555;
        result = getMax(nums, x);
        System.out.println(result);
        System.out.println("-----");
    }

    //


    /**
     * 字节面试题，求大佬们看看，数组A中给定可以使用的1~9的数，返回由A数组中的元素组成的小于n的最大数。
     * 例如A={1, 2, 4, 9}，x=2533，返回2499
     * 兄弟们这个题怎么做呀，或者给一下有没有力扣类似的题的链接
     * <p>
     * 作者：Damon
     * 链接：https://leetcode.cn/circle/discuss/fbhhev/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * <p>
     * <p>
     * <p>
     * 回溯一下就好了啊，对每一位先尝试使用相同数字，直到最后一位或者没有相同的数字时，尝试是否有比当前数字更小的，有的话选更小的数字里最大的，剩下的用最大数字；都没有就向前回溯看前一个有没有更小的。如果一直回溯到第一个数字，就用位数更少的全都是最大数字的数。
     * <p>
     * 作者：灵剑2012
     * 链接：https://leetcode.cn/circle/discuss/fbhhev/view/ZNJTdm/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @param aim
     * @return
     */
    static boolean[] temp = null;


    public static int getMax(int[] nums, int aim) {

        String aimStr = String.valueOf(aim);
        Arrays.sort(nums);
        temp = new boolean[10];
        // i 代表的是 第 i个位置是否有值。
        for (int i = 0; i < nums.length; i++) {
            temp[nums[i]] = true;
        }
        String result = "";
        for (int i = 0; i < aimStr.length(); i++) {
            // i 这个位置是否有数
            // value 代表位置i的值
            int value = aimStr.charAt(i) - '0';

            if (temp[value] && i != aimStr.length() - 1) {
                //  if (temp[value] ) {
                result += value;
            } else {
                int res = findNexMin(value);
                if (res > -1 && i != aimStr.length() - 1) {
                    result += res;
                    int leftLength = aimStr.length() - result.length();
                    return Integer.valueOf(result + repeat(nums[nums.length - 1], leftLength));
                } else {
                    // 没有找到更小的值。往前回溯
                    for (int j = i; j >= 0; j--) {
                        // 代表的是 前一个字符。
                        int tempValue = aimStr.charAt(j) - '0';
                        // 比 第 j 个 数字小的索引。
                        int nextIndex = findNexMin(tempValue);
                        if (nextIndex > -1) {
                            // 往前回溯
                            // 往前找，找到j+1个数值
                            result = result.substring(0, j);
                            // 找到了
                            result += nextIndex;
                            int leftLength = aimStr.length() - result.length();
                            while (leftLength > 0) {
                                result += nums[nums.length - 1];
                                leftLength--;
                            }
                            return Integer.valueOf(result);
                        }
                    }
                    return Integer.valueOf(repeat(nums[nums.length - 1], aimStr.length() - 1));
                }
            }
        }

        // 能组装出这个数来


        return -2;
    }

    public static int findNexMin(int index) {
        for (int i = index - 1; i >= 0; i--) {
            if (temp[i]) {
                return i;
            }
        }
        return -1;
    }

    public static String repeat(int num, Integer time) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < time; i++) {
            result.append(num);
        }
        return result.toString();
    }


}

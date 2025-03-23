import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class matrix {


        public List<Integer> spiralOrder(int[][] matrix) {
            if (matrix.length == 0)
                return new ArrayList<Integer>();
            int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
            Integer[] res = new Integer[(r + 1) * (b + 1)];
            while (true) {
                for (int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right
                if (++t > b) break;
                for (int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom
                if (l > --r) break;
                for (int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left
                if (t > --b) break;
                for (int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top
                if (++l > r) break;
            }
            return Arrays.asList(res);
        }


    public static void main(String[] args) {
        int [] nums = {12,11,10,10,10,9,9,2};
        int result = findTheFirst(nums,1,3,nums[5]);
        System.out.println(result);
        System.out.println(nums[result]);


        //int resu = findLastGreater(nums,9);
        //System.out.println(resu);
    }



    public int findTheFirst22(int [] arr,int lf ,int rt ,int target){

        int left = lf;
        int right = rt;

        int pos = lf; // lf 的位置肯定是最后的结。

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                pos = mid;       // 记录可能的位置
                right = mid - 1; // 继续向左查找更小的索引
            } else {
                left = mid + 1;  // 向右查找更大的值
                pos = mid-1;
            }
        }

        // 根据pos判断结果
        if (pos > 0) {
            return pos - 1; // 最后一个大于target的元素
        } else {
            return -1; // 无解
        }
    }






    public static int findTheFirst(int [] nums,int lf ,int rt ,int target){
        int res = lf;
        while(lf<=rt){
            // 左边的边界，小于右边的边界

            int mid = lf+(rt-lf)/2;
            // 中间值


            if(nums[mid]==target){
                // 如果中间值 rt = mid-1;
                rt = mid-1;
            } else if (nums[mid] <target) {
                rt = mid+1;
            } else {
               lf = mid+1;
               if (lf == rt - 1) {
                    res = nums[rt] > nums[i - 1] ? rigth : left;
                    break;
               }
            }

        }
        return res;
    }

    public static int findTheFirst1(int [] nums,int lf ,int rt ,int target){


        while(lf<=rt){
            int mid = lf+(rt-lf)/2;
            if(nums[mid]>target){
                rt = mid-1;
            }
            else{
                lf = mid+1;
            }

        }
        return lf;
    }

    public static int findLastGreater(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int pos = 0; // 初始化位置为数组末尾之后，假设所有元素均大于target

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                pos = mid;       // 记录可能的位置
                right = mid - 1; // 继续向左查找更小的索引
            } else {
                left = mid + 1;  // 向右查找更大的值
                pos = mid-1;
            }
        }

        // 根据pos判断结果
        if (pos > 0) {
            return pos - 1; // 最后一个大于target的元素
        } else {
            return -1; // 无解
        }
    }

}

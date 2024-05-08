package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class leetcode56 {


    public static void main(String[] args) {
        int [] a1 = {1,2};
        int [] a2 = {2,3};
        int [] a3 = {3,4};

        int [] [] intervals = {a1,a2,a3};

        int [][] result =  merge(intervals);

        result.getClass();
        System.out.println(result);

        for (int i = 0 ; i < result.length;i++){
            System.out.println(result[i][0]);
            System.out.println(result[i][1]);
        }
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


}

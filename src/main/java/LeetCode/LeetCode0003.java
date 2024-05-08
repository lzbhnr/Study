package LeetCode;

import java.util.HashMap;

public class LeetCode0003 {

    public static void main(String[] args) {
        String s = "tmmzuxt";

        int a = lengthOfLongestSubstring(s);
        System.out.println(a);
    }
    public static int lengthOfLongestSubstring(String s) {

        if (s.length() == 1){
            return 1 ;
        }
        int length = s.length();
        HashMap<Character, Integer> map = new HashMap<>();

        int m = 0;
        int maxLength = 0 ;
        for (int i = 0; i < length; i++) {

            Integer index = map.put(s.charAt(i), i);
            if (index != null) {
                for (int x = m ;  x <index ; x++){
                    map.remove(s.charAt(x));
                }
                m = index+1;
            } else{
                maxLength = Math.max(i - m +1,maxLength);
            }
        }
        return maxLength;
    }
}

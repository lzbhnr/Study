package Thread;


import java.util.HashMap;
import java.util.LinkedList;

public class So {



    private HashMap<Character, Character> map = new HashMap<>();

    private LinkedList<Character> stack = new LinkedList();

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {

            return false;
        }

        for (int i = 0 ;  i< s.length() ; i++) {
            Character cer = s.charAt(i);

            if (map.containsKey(cer)) {
                stack.add(cer);
            }else {

                Character value =  map.get(cer);

                if (value != stack.removeLast()) {
                    return false;
                }

            }
        }

        return stack.size()==0;

    }








}

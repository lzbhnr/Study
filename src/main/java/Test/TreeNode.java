package Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;

    public static void main(String[] args) {

        TreeNode node = new TreeNode();

        boolean res = node.isValid("{}");

        System.out.println(res);
    }

    private LinkedList<Character> stack = new LinkedList();

    private HashMap<Character, Character> map = new HashMap<Character, Character>() {
        {
            put('{', '}');
            put('[', ']');
            put('(', ')');
            put('?', '?');
        }
    };

    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }

        if (s.length() >0 && map.containsKey(s.charAt(0))  ){

            System.out.println("----");

            return false;
        }

        stack.add('?');
        for (int i = 0 ; i < s.length(); i++ ) {
            Character ch = s.charAt(i);
            System.out.println("---" + ch);
            if (map.containsKey(ch)) {
                stack.addLast(ch);
            } else if ( map.get(stack.removeLast()) != ch)  {
                return false;
            }

        }

        System.out.println(stack.size());

        return stack.size() == 1;
    }
}



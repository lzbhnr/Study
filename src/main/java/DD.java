public class DD {
    /**
     * 二维数组、元素是可重复字母；给定单词，查询是不是在二维数组里面
     * 查询的路径是：可横向纵向相邻查询，单个坐标不可重复走
     * 查询到 true；查不到 false
     * a b c
     * a d e
     * abde true； aaa false。
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    private String aimStr;
    private char[][] args;

    public boolean find(char[][] args, String s) {
        this.aimStr = s;
        this.args = args;
        int rows = args.length;
        int cols = args[0].length;
        boolean[][] tempArgs = new boolean[args.length][args[0].length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean find = dfs(i, j, 0, tempArgs);
                if (find) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int i, int j, int index, boolean[][] tempArgs) {
        if (i < 0 || i >= args.length || j < 0 || j >= args[0].length || index < aimStr.length() - 1) {
            return false;
        }
        boolean equal = args[i][j] == aimStr.charAt(index);
        if (!equal) {
            return false;
        }
        if (index == aimStr.length() - 1) {
            return true;
        }
        if (equal) {
            tempArgs[i][j] = true;
            index++;

            boolean r = dfs(i - 1, j, index, tempArgs) ||
                    dfs(i + 1, j, index, tempArgs) ||
                    dfs(i, j - 1, index, tempArgs) ||
                    dfs(i, j + 1, index, tempArgs);
            tempArgs[i][j] = false;
            return r;

        }
        return false;
    }


}

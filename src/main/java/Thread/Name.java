package Thread;

public class Name {
    public static void main(String[] args) {
        int [] nums = {1,1,2};
        int value = removeDuplicates(nums);
       // System.out.println(value);


        TreeNode root = new TreeNode(1);

        TreeNode l = new TreeNode(-1);
        TreeNode r = new TreeNode(3);


        root.left = l;
        root.right = r;
        int a = maxPathSum(root);

        System.out.println(a);

    }

    public static int removeDuplicates(int[] nums) {
        int m = 0;
        int n = 0;
        int i = 0 ;
        for (;n < nums.length;n++) {
            if (nums[m] != nums[ n]) {
                nums[i] = nums[m];
                i++;
                m= n;
            }
        }
        return i+1;
    }

    static int result = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        dfs(root);
        return result;
    }

    // 函数功能：返回当前节点能为父亲提供的贡献，需要结合上面的图来看！
    private static int dfs(TreeNode root) {
        // 如果当前节点为叶子节点，那么对父亲贡献为 0
        if(root == null) return 0;
        // 如果不是叶子节点，计算当前节点的左右孩子对自身的贡献left和right
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 更新最大值，就是当前节点的val 加上左右节点的贡献。
        result = Math.max(result, root.val + left + right);
        // 计算当前节点能为父亲提供的最大贡献，必须是把 val 加上！
        int max = Math.max(root.val + left, root.val + right);
        // 如果贡献小于0的话，直接返回0即可！
        return max < 0 ? 0 : max;
    }
}

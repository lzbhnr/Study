import java.util.Scanner;

public class Solution {
    static class Command {
        private  String cmd;
        private  int row;
        private  int col;
        Command(String str) {
            String[] strings = str.split(" ");
            this.cmd = strings[0];
            this.row = Integer.parseInt(strings[1]);
            this.col = Integer.parseInt(strings[2]);
        }
    }
    private static int fun(int rows, int cols, int[][] map, Command[] commands) {
        int[][] matrix = new int[rows + 2][cols + 2];
        int len = map.length;
        for (int i = 0; i < len; i++) {
            int mRow = map[i][0];
            int mCol = map[i][1];
            matrix[mRow][mCol] = -1;
        }
        for (Command command : commands) {
            int mRow = command.row;
            int mCol = command.col;
            switch (command.cmd) {
                case "add":
                    if (matrix[mRow][mCol] == 0) {
                        matrix[mRow][mCol] = -1;
                    }
                    break;
                case "delete":
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            delete(mRow + i - 1, mCol + j - 1, matrix);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        int result = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i][j] == 0) {
                    int value = matrix[i - 1][j - 1] + matrix[i - 1][j + 1] + matrix[i - 1][j] +
                            matrix[i + 1][j - 1] + matrix[i + 1][j + 1] + matrix[i + 1][j]
                            + matrix[i][j - 1] + matrix[i][j + 1];
                    result += value;
                }
            }
        }
        return Math.abs(result);
    }

    private static void delete(int row, int col, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (row != 0 && row != rows - 1 && col != 0 && col != cols - 1) {
            if (matrix[row][col] == -1) {
                matrix[row][col] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        // System.out.println("rows"+rows);
        int cols = scanner.nextInt();
       // System.out.println("cols"+cols);
        scanner.nextLine();
        int baseStationCnt = scanner.nextInt();
        //System.out.println("base"+baseStationCnt);
        int[][] map = new int[baseStationCnt][2];
        for (int i = 0; i < baseStationCnt; i++) {
            String lineStr = scanner.nextLine();
            //  System.out.println("lineStr"+lineStr);
            String[] line = lineStr.split(" ");
            System.out.println(line.length);
            System.out.println(line[0]);
            map[i][0] = Integer.parseInt(line[0]);
            map[i][1] = Integer.parseInt(line[1]);
        }
        int cmdCnt = scanner.nextInt();
        Command[] commands = new Command[cmdCnt];
        for (int i = 0; i < cmdCnt; i++) {
            commands[i] = new Command(scanner.nextLine());
        }
        scanner.close();
        int result = fun(rows, cols, map, commands);
        System.out.println(result);
    }
}

public class SudokuFunction {
    private int[][] sudoku;
    private int blank;                                    /*blank表示初始状况下的数独中空白元素的个数*/

    /*构造函数初始化sudoku数组和blank*/
    private SudokuFunction() {
        sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = 0;
            }
            blank = 81;
        }
    }

    /*利用已有数组初始化数独*/
    private void init(int[][] data) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = data[i][j];
                if (sudoku[i][j] != 0) {
                    blank--;
                }
            }
        }
        System.out.println(blank);
    }

    /*输出数独*/
    private void printSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*判断数字num是否存在于第row行*/
    private boolean isInRow(int num, int row) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    /*判断数字num是否存在于第col列*/
    private boolean isInCol(int num, int col) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    /*判断数字num是否存在于第row行，第col列所在的九宫格*/
    private boolean isInCell(int num, int row, int col) {
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        int endRow = startRow + 3;
        int endCol = startCol + 3;
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                if (sudoku[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    /*求解函数*/
    private boolean solve() {
        int num;
        int data = 1;
        int[] stack = new int[81];                //栈保存一个三位数，第一位代表行标，第二位代表列标，第三位代表该位置的值
        int top = 0;
        int i, j, k;
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {            //若该处元素为空则可以尝试填入1~9
                    for (k = data; k <= 9; k++) {
                        if (!isInCell(k, i, j) && !isInCol(k, j) && !isInRow(k, i)) {
                            sudoku[i][j] = k;
                            stack[top++] = i * 100 + j * 10 + k;                        //填入元素后压栈，保存所填元素的基本信息
                            if (top == blank) {
                                return true;
                            }
                            data = 1;
                            break;
                        }
                    }
                    if (k == 10) {
                        num = stack[--top];                //弹出上一个填入数独的元素信息，并重新求解
                        i = num / 100;
                        j = num % 100 / 10 - 1;                //减一是因为在第二层for循环结束时i会自动加1
                        sudoku[i][j + 1] = 0;
                        data = num % 10 + 1;                //从下一个数字开始重新尝试
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] data = {
                {0, 0, 5, 3, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 2, 0},
                {0, 7, 0, 0, 1, 0, 5, 0, 0},
                {4, 0, 0, 0, 0, 5, 3, 0, 0},
                {0, 1, 0, 0, 7, 0, 0, 0, 6},
                {0, 0, 3, 2, 0, 0, 0, 8, 0},
                {0, 6, 0, 5, 0, 0, 0, 0, 9},
                {0, 0, 4, 0, 0, 0, 0, 3, 0},
                {0, 0, 0, 0, 0, 9, 7, 0, 0}
        };
        SudokuFunction sudoku = new SudokuFunction();
        sudoku.init(data);
        boolean isFinished = sudoku.solve();
        if (isFinished) {
            sudoku.printSudoku();
        } else {
            System.out.println("ERROR!");
        }
    }

}
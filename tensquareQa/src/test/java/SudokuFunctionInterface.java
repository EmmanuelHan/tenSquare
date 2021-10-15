public interface SudokuFunctionInterface {

    void sudoInit(int[][] arr);      // 初始化数独数组

    int verSquare(int y, int x);        // 在9个小格组成的方块中验证（y, x）的值

    int verRow(int y, int x);           // 在每行中验证（y, x）的值

    int verCol(int y, int x);           // 在每列中验证（y, x）的值

    int verify(int y, int x);           // 验证一个位置

    int verifyAll();                    // 验证整个数组

    void sudoPrt();                     // 输出

    void next(int y, int x);          // 指向下一个位置

    int sudoGo(int[][] arr);        // Go

}

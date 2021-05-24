package logic_pack;

public class GameBoard {

    public final int[][] board;
    public static final int numberCellsY = 4;
    public static final int numberCellsX = 4;

    public GameBoard() {
        board = new int[numberCellsX][numberCellsY];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public int getState(int x, int y) {
        return board[x][y];
    }

    public void setState(int x, int y, int state) {
        board[x][y] = state;
    }

    public int[] getColumn(int i) {
        return board[i];
    }

    public void setColumn(int i, int[] column) {
        board[i] = column;
    }

    public int[] getRow(int i) {
        int[] line = new int[numberCellsX];
        for (int j = 0; j < numberCellsX; j++) {
            line[j] = board[j][i];
        }
        return line;
    }

    public void setRow(int i, int[] line) {
        for (int j = 0; j < numberCellsX; j++) {
            board[j][i] = line[j];
        }
    }
}

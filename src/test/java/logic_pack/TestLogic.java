package logic_pack;

import org.junit.Assert;
import org.junit.Test;

public class TestLogic {

    @Test
    public void testCreateCells() {

        int state = 2;
        String path = "assets/" + state + ".png";
        String path1 = "assets/2.png";
        Assert.assertEquals(path1, path);

        Logic.initialState();
        Logic.createCells();
        Logic.createCells();
        Logic.createCells();
        Logic.createCells();
        int numberCells = 0;
        int[][] board = Logic.gameBoard.board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) numberCells++;
            }
        }
        Assert.assertEquals(8, numberCells);
    }

    @Test
    public void testChoiceCell() {
        Logic.initialState();
        Logic.choiceCell();
        Logic.choiceCell();
        Logic.choiceCell();
        Logic.choiceCell();
        int numberCells = 0;
        int[][] board = Logic.gameBoard.board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) numberCells++;
            }
        }
        Assert.assertEquals(4, numberCells);
    }

    @Test
    public void testCheckShift() {
        Logic.initialState();
        int[][] board = Logic.gameBoard.board;
        board[0][2] = 128;
        board[3][2] = 128;
        board[3][0] = 256;
        board[2][0] = 16;
        board[0][3] = 4;
        board[1][0] = 16;
        board[3][3] = 4;
        Logic.checkShift(Direction.RIGHT);
        Logic.checkShift(Direction.DOWN);
        Assert.assertEquals(512, board[3][0]);
        Assert.assertEquals(32, board[2][0]);
        Assert.assertEquals(8, board[3][1]);
    }

}

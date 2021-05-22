package logic_pack;

import java.io.*;
import org.junit.Assert;
import org.junit.Test;

public class TestLogic {

    @Test
    public void logicConstructor() {
        Logic.initialState();
        Assert.assertEquals(0, Logic.gameBoard.getState(1,3));
    }

    @Test
    public void testCreateCells() {
        Logic.initialState();
        Logic.createCells();
        int state = 2;
        String path = "assets/" + state + ".png";
        String path1 = "assets/2.png";
        Assert.assertEquals(path1, path);

        int numberCells = 2;
        int[][] board = Logic.gameBoard.board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 4) numberCells++;
            }
        }
        Assert.assertEquals(2, numberCells);
    }

    @Test
    public void testChoiceCell() {
        Logic.initialState();
        Logic.choiceCell();
        int numberCells = 0;
        int[][] board = Logic.gameBoard.board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) numberCells++;
            }
        }
        Assert.assertEquals(1, numberCells);
    }

    @Test
    public void testCheckShift() {
        Logic.initialState();
        int[][] board = Logic.gameBoard.board;
        board[0][2] = 128;
        board[3][2] = 128;
        board[3][0] = 256;
        Logic.checkShift(Direction.RIGHT);
        Logic.checkShift(Direction.DOWN);
        Assert.assertEquals(512, board[3][0]);
    }

}

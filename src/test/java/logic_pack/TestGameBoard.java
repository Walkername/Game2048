package logic_pack;

import org.junit.Assert;
import org.junit.Test;

public class TestGameBoard {

    @Test
    public void testGetState() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.board[1][0] = 2;
        gameBoard.board[0][1] = 1024;
        gameBoard.board[3][2] = 512;
        gameBoard.board[0][3] = 32;
        gameBoard.board[1][3] = 4;
        gameBoard.board[2][1] = 64;
        gameBoard.board[1][2] = 128;
        gameBoard.board[2][3] = 8;
        Assert.assertEquals(2, gameBoard.getState(1,0));
        Assert.assertEquals(1024, gameBoard.getState(0,1));
        Assert.assertEquals(512, gameBoard.getState(3,2));
        Assert.assertEquals(32, gameBoard.getState(0,3));
        Assert.assertEquals(4, gameBoard.getState(1,3));
        Assert.assertEquals(64, gameBoard.getState(2,1));
        Assert.assertEquals(128, gameBoard.getState(1,2));
        Assert.assertEquals(8, gameBoard.getState(2,3));
    }

    @Test
    public void testSetState() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setState(1,3, 1024);
        gameBoard.setState(2,0, 32);
        gameBoard.setState(3,2, 64);
        gameBoard.setState(2,2, 4);
        gameBoard.setState(3,1, 256);
        Assert.assertEquals(1024, gameBoard.getState(1,3));
        Assert.assertEquals(32, gameBoard.getState(2,0));
        Assert.assertEquals(64, gameBoard.getState(3,2));
        Assert.assertEquals(4, gameBoard.getState(2,2));
        Assert.assertEquals(256, gameBoard.getState(3,1));

    }

    @Test
    public void testGetColumn() {
        GameBoard gameBoard = new GameBoard();
        int[] column = {2, 8 , 1024, 512};
        int[] column1 = {32, 4 , 256, 128};
        int[] column2 = {4, 16 , 2, 64};
        gameBoard.board[3] = column;
        gameBoard.board[2] = column1;
        gameBoard.board[0] = column2;
        Assert.assertEquals(column, gameBoard.getColumn(3));
        Assert.assertEquals(column1, gameBoard.getColumn(2));
        Assert.assertEquals(column2, gameBoard.getColumn(0));
    }

    @Test
    public void testSetColumn() {
        GameBoard gameBoard = new GameBoard();
        int[] column = {64, 128, 4, 512};
        int[] column1 = {1024, 256, 8, 2};
        int[] column2 = {64, 32, 2, 8};
        gameBoard.setColumn(3, column);
        gameBoard.setColumn(1, column1);
        gameBoard.setColumn(2, column2);
        Assert.assertEquals(128, gameBoard.getState(3,1));
        Assert.assertEquals(1024, gameBoard.getState(1,0));
        Assert.assertEquals(8, gameBoard.getState(2,3));
    }

    @Test
    public void testGetLine() {
        GameBoard gameBoard = new GameBoard();
        int[] line = {16, 8 , 2, 32};
        int[] line1 = {128, 4 , 256, 256};
        int[] line2 = {8, 64 , 1024, 16};
        gameBoard.board[1] = line;
        gameBoard.board[0] = line1;
        gameBoard.board[3] = line2;
        Assert.assertEquals(line, gameBoard.getColumn(1));
        Assert.assertEquals(line1, gameBoard.getColumn(0));
        Assert.assertEquals(line2, gameBoard.getColumn(3));
    }

    @Test
    public void testSetLine() {
        GameBoard gameBoard = new GameBoard();
        int[] line = {2, 4, 16, 1024};
        int[] line1 = {16, 128, 8, 512};
        int[] line2 = {4, 16, 1024, 256};
        gameBoard.setLine(3, line);
        gameBoard.setLine(0, line1);
        gameBoard.setLine(1, line2);
        Assert.assertEquals(2, gameBoard.getState(0,3));
        Assert.assertEquals(512, gameBoard.getState(3,0));
        Assert.assertEquals(1024, gameBoard.getState(2,1));
    }
}

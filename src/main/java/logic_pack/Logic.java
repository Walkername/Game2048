package logic_pack;

import java.util.Random;

public class Logic {

    public static Direction direction;
    public static final int numberCellsY = 4; // column consists of 4 cells
    public static final int numberCellsX = 4; // row consists of 4 cells
    public static GameBoard gameBoard;

    public static void initialState() { // state at the beginning of the game
        direction = Direction.AWAITING;
        gameBoard = new GameBoard();
    }

    public static void waiter() {
        if(direction != Direction.AWAITING) {
            if (checkShift(direction)) choiceCell();
            direction = Direction.AWAITING;
        }
    }

    public static void choiceCell() { // random choice of cells on the game board
        int state = 2;           // the state of the emerging cell
        int randomX, randomY;

        randomX = new Random().nextInt(numberCellsX); // random coordinate X

        randomY = new Random().nextInt(numberCellsY); // random coordinate Y

        boolean busy = false;
        while(!busy){
            if(gameBoard.getState(randomX, randomY) == 0) { // if the state of the game board in these coordinates isn't busy,
                gameBoard.setState(randomX, randomY, state); // then we assign it the value "2"
                busy = true;
            }else{
                if(randomX + 1 < numberCellsX) {
                    randomX++;
                }else{
                    randomX = 0;
                    if(randomY + 1 < numberCellsY) {
                        randomY++;
                    }else{
                        randomY = 0;
                    }
                }
            }
        }
    }

    public static void createCells() {
        for (int i = 0;i < 2; i++ ) {  // to create two cells at the beginning of the game and then one cell every move
            choiceCell();
        }
    }

    public static boolean checkShift(Direction direction) {
        boolean fieldChange = false;
        switch(direction) {
            case UP:
            case DOWN:
                for(int i = 0; i < numberCellsX; i++){
                    int[] column = gameBoard.getColumn(i); // Request the next column
                    if(direction == Direction.UP){      // If direction UP then we change the order to the opposite
                        int[] revColumn = new int[column.length]; // reverse column
                        for(int j = 0; j < revColumn.length; j++){
                            revColumn[j] = column[revColumn.length - j - 1];
                        }
                        column = revColumn; // Get the column back
                    }
                    ShiftResult result = shift(column);  // Trying to shift numbers in this column
                    /* Return the line to its original order */
                    if(direction == Direction.UP){
                        int[] revColumn = new int[result.modRow.length];
                        for(int j = 0; j < revColumn.length; j++){
                            revColumn[j] = result.modRow[revColumn.length - j - 1];
                        }
                        result.modRow = revColumn;
                    }
                    /* Writing the changed column */
                    gameBoard.setColumn(i, result.modRow);
                    // If at least one line has been changed, then the whole field has been changed
                    fieldChange = fieldChange || result.theChange;
                }
                break;
            case LEFT:
            case RIGHT:
                for(int i = 0; i< numberCellsY; i++){ // Shift the numbers of all rows in the desired direction in turn
                    int[] row = gameBoard.getRow(i); // Request the next row
                    if(direction==Direction.RIGHT){ // reverse row
                        int[] revRow = new int[row.length];
                        for(int j = 0; j < revRow.length; j++){
                            revRow[j] = row[revRow.length - j - 1];
                        }
                        row = revRow;
                    }
                    ShiftResult result = shift(row);
                    if(direction==Direction.RIGHT){
                        int[] revRow = new int[result.modRow.length];
                        for(int j = 0; j < revRow.length; j++){
                            revRow[j] = result.modRow[revRow.length - j - 1];
                        }
                        result.modRow = revRow;
                    }
                    /* Writing the changed row */
                    gameBoard.setRow(i, result.modRow);
                    // If at least one line has been changed, then the whole field has been changed
                    fieldChange = fieldChange || result.theChange;
                }
                break;
        }
        return fieldChange; // Returns whether the field has changed or not
    }

    public static ShiftResult shift(int[] line) {
        ShiftResult newLine = new ShiftResult();
        int[] lineNotZeroes = new int[line.length];
        {
            int k = 0;
            for (int i = 0; i < line.length; i++) {
                if(line[i] != 0){
                    if(k != i){
                        newLine.theChange = true;
                    }
                    lineNotZeroes[k] = line[i];
                    k++;
                }
            }
            for(int i = k; i < lineNotZeroes.length; i++) {
                lineNotZeroes[i] = 0;
            }
        }
        newLine.modRow = new int[lineNotZeroes.length];
        {
            int k = 0;
            {
                int i = 0;
                while (i < lineNotZeroes.length) {
                    if((i+1 < lineNotZeroes.length) && (lineNotZeroes[i] == lineNotZeroes[i + 1])
                            && lineNotZeroes[i]!=0) {
                        newLine.theChange = true;
                        newLine.modRow[k] = lineNotZeroes[i] * 2;
                        i++;
                    } else {
                        newLine.modRow[k] = lineNotZeroes[i];
                    }
                    k++;
                    i++;
                }
            }
            for(int j = k; j < newLine.modRow.length; j++) {
                newLine.modRow[j] = 0;
            }
        }
        return newLine;
    }
}

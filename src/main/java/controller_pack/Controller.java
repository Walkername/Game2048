package controller_pack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import logic_pack.Direction;
import logic_pack.GameBoard;
import logic_pack.Logic;

public class Controller {

    boolean begin = false;

    Image imageBoard = new Image("assets/main_board.jpg");
    ImageView board = new ImageView(imageBoard);

    Image wasted = new Image("assets/wasted_game.jpg");

    @FXML
    ImageView wastedGame;

    @FXML
    AnchorPane anchorGameBoard;

    @FXML
    Button buttonStart;

    @FXML
    public void start() {
        anchorGameBoard.getChildren().clear();
        anchorGameBoard.getChildren().addAll(board, buttonStart);
        begin = true;
        Logic.initialState();   // first, method sets the initial state for the game
        Logic.createCells();    // second, create cells at the beginning of the game
        draw(Logic.gameBoard);  // thirdly, draw two cells of initial state for the game
    }

    @FXML
    public void controller(KeyEvent event) { // controller of the game
        switch (event.getCode()) {
            case R -> start();                           // r -> start and restart of the game
            case S -> Logic.direction = Direction.UP;    // S -> move cells down
            case W -> Logic.direction = Direction.DOWN;  // W -> move cells up
            case A -> Logic.direction = Direction.LEFT;  // A -> move cells left
            case D -> Logic.direction = Direction.RIGHT; // D -> move cells right
        }
        if (begin) {
            anchorGameBoard.getChildren().clear();
            anchorGameBoard.getChildren().addAll(board, buttonStart);
            Logic.logic();
            draw(Logic.gameBoard);
            begin = true; // to game won't begin without initial state in method start
        }                 // until we press play it doesn't start
    }

    public void draw(GameBoard gameBoard) {
        for (int i =0; i < 4; i++) {
            for (int j =0; j < 4; j++) {
                drawSingleCell(i * 175, j * 175, gameBoard.getState(i, j));
            }
        }
    }

    public void drawSingleCell(int x, int y, int state) { // draw cell for method "draw"
        if (state != 0) {
            String path = "assets/" + state + ".png";
            Image image = new Image(path);
            ImageView imageView = new ImageView(image);
            imageView.setX(x);
            imageView.setY(y);
            anchorGameBoard.getChildren().add(imageView);
        }
    }
}



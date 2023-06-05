import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage window) {
        // Creating the interface
        BorderPane mainLayout = new BorderPane();
        GridPane board = new GridPane();

        Button c0r0 = new Button(" ");
        Button c1r0 = new Button(" ");
        Button c2r0 = new Button(" ");
        Button c0r1 = new Button(" ");
        Button c1r1 = new Button(" ");
        Button c2r1 = new Button(" ");
        Button c0r2 = new Button(" ");
        Button c1r2 = new Button(" ");
        Button c2r2 = new Button(" ");

        Button[][] buttons = {
                {c0r0, c1r0, c2r0},
                {c0r1, c1r1, c2r1},
                {c0r2, c1r2, c2r2}
        };

        for (Button[] button : buttons) {
            for (Button value : button) {
                value.setFont(Font.font("monospaced", 40));
            }
        }

        Label turnInfo = new Label("Turn: ");
        turnInfo.setFont(Font.font(30));

        board.add(c0r0, 0, 0);
        board.add(c1r0, 1, 0);
        board.add(c2r0, 2, 0);
        board.add(c0r1, 0, 1);
        board.add(c1r1, 1, 1);
        board.add(c2r1, 2, 1);
        board.add(c0r2, 0, 2);
        board.add(c1r2, 1, 2);
        board.add(c2r2, 2, 2);

        mainLayout.setTop(turnInfo);
        mainLayout.setCenter(board);

        // Playing logic
        final String[] currentPlayer = {"X"};
        turnInfo.setText(turnInfo.getText() + currentPlayer[0]);

        for (Button[] buttonRow : buttons) {
            for (Button button : buttonRow) {
                button.setOnAction(event -> {
                    Object obj = event.getSource();
                    Button btn = (Button) obj;
                    if (btn.getText().equals(" ")) {
                        btn.setText(currentPlayer[0]);
                        currentPlayer[0] = currentPlayer[0].equals("X") ? "O" : "X";
                        turnInfo.setText("Turn: " + currentPlayer[0]);

                        if (checkWin(buttons)) {
                           turnInfo.setText("The end!");
                        }
                    }
                });
            }
        }

        // Preparing and showing the board
        Scene scene = new Scene(mainLayout);

        window.setScene(scene);
        window.setTitle("Tic-tac-toe");
        window.show();
    }

    public boolean checkWin(Button[][] buttons) {
        if (checkDiagonalMatch(buttons) || checkHorizontalMatch(buttons) || checkVerticalMatch(buttons)) {
            for (Button[] row : buttons) {
                for (Button btn : row) {
                    btn.setOnAction(null);
                }
            }
            return true;
        }
        return false;
    }

    public boolean trimAndCompare(String first, String second) {
        first = first.trim();
        second = second.trim();

        if (first.isEmpty() || second.isEmpty()) {
            return false;
        }
        return first.equals(second);
    }

    public boolean checkDiagonalMatch(Button[][] buttons) {
        boolean match = trimAndCompare(buttons[0][0].getText(), buttons[1][1].getText()) && trimAndCompare(buttons[0][0].getText(), buttons[2][2].getText());
        if (trimAndCompare(buttons[0][2].getText(), buttons[1][1].getText()) && trimAndCompare(buttons[2][0].getText(), buttons[1][1].getText())) {
            match = true;
        }
        return match;
    }

    public boolean checkHorizontalMatch(Button[][] buttons) {
        boolean match = false;

        for (Button[] button : buttons) {
            if (trimAndCompare(button[0].getText(), button[1].getText()) && trimAndCompare(button[0].getText(), button[2].getText())) {
                match = true;
            }
        }

        return match;
    }

    public boolean checkVerticalMatch(Button[][] buttons) {
        boolean match = trimAndCompare(buttons[0][0].getText(), buttons[1][0].getText()) && trimAndCompare(buttons[0][0].getText(), buttons[2][0].getText());

        if (trimAndCompare(buttons[0][1].getText(), buttons[1][1].getText()) && trimAndCompare(buttons[0][1].getText(), buttons[2][1].getText())) {
            match = true;
        }
        if (trimAndCompare(buttons[0][2].getText(), buttons[1][2].getText()) && trimAndCompare(buttons[0][2].getText(), buttons[2][2].getText())) {
            match = true;
        }

        return match;
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}
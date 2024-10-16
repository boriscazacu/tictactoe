module TicTacToe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.controller;
    exports org.example.interfaces;
    opens org.example.controller to javafx.fxml;
    exports org.example.board;
    opens org.example.board to javafx.fxml;
}
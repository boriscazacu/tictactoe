module TicTacToe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.controller;
    exports org.example.interfaces;
    opens org.example.controller to javafx.fxml;
}
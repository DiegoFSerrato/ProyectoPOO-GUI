module org.example.chessgame {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.chessgame to javafx.fxml;
    exports org.example.chessgame;
}

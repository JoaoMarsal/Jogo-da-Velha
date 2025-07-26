module com.example.velhagame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.velhagame to javafx.fxml;
    exports com.example.velhagame;
}
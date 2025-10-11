module com.example.eva01lab {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.eva01lab to javafx.fxml;
    exports com.example.eva01lab;
}
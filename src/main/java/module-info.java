module com.example.crudaluno {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.crudaluno.view to javafx.fxml;
    opens com.example.crudaluno.model to javafx.base;

    exports com.example.crudaluno;
}
